package prop_contam_delay_counter;

import java.util.*;

public class counter_main {
	// ENTER DELAYS FOR GATES HERE
	static int not = 1;
	static int and = 5;
	static int nand = 12;
	static int or = 4;
	static int nor = 9;
	static int xor = 12;
	static int xnor = 3;
	static int wire = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		boolean cont = true;
		while (cont) {
			System.out.println("Please enter the circuit gates separated by commas for each\n" + 
					"layer of combinatorial circuit logic beginning with the output gate,\n" +
					"or enter x to exit:");
			System.out.println("(HINT: for multiple gate delays to be summed at once, separate the gates by spaces)");
			
			String s = scan.nextLine();
			
			if (s.equals("x")) {
				cont = false;
				continue;
			}
			
			String[] pre_layers = s.split("[+]");
			ArrayList<String[]> layers = new ArrayList<String[]>();
			ArrayList<Integer> result = new ArrayList<Integer>();
			HashMap<int[], Integer> path_delay_map = new HashMap<int[], Integer>(); // Will be used later to return longest path
			
			for (int i = 0; i < pre_layers.length; ++i)
				layers.add(pre_layers[i].split(","));
			
			int[] current_path = new int[layers.size()];
			for (int i = 0; i < layers.size(); ++i)
				current_path[i] = -1;
			
			
			// SLOWLY INCREMENT THE CURRENT_PATH TO ITERATE THROUGH ALL OF THE POSSIBLE PATHS
			for (int i = 0; i < layers.get(layers.size() - 1).length; ++i) {
				current_path[layers.size() - 1]++;
				
				// end of time = pathindex[0] = 1 (although that won't matter for right now)
				int curr_index = layers.size() - 1;
				while (current_path[curr_index] % 2 == 0 && curr_index > 0) {
					++current_path[curr_index - 1];
					
					--curr_index;
				}
				int delay = retPathDelay(current_path, layers);
				
				path_delay_map.put(current_path, delay);
				result.add(delay);
			}
			
			for (int i = 0; i < result.size(); ++i)
				System.out.print(result.get(i) + "ns, ");
			System.out.println("\n");

			result.sort(null);
			int max = result.get(result.size() - 1);
			int[] max_path = null;
			for (int[] path : path_delay_map.keySet()) {
				if (path_delay_map.get(path).equals(max)) {
					max_path = path;
				}
				//System.out.println(path_delay_map.get(path));
			}
			System.out.println("Maximum prop delay: " + max + "ns\n");
			if (max_path == null)
			/*System.out.print("Path: ");
			for (int i = 0; i < max_path.length; ++i)
				System.out.print(max_path[i] + " ");*/
			System.out.println();
		}
		
		scan.close();
	}
	
	
	

	public static int retPathDelay(int[] layers_path_index, ArrayList<String[]> layers) {
		int res = 0;
		
		//for the text-based version
		for (int i = 0; i < layers.size(); ++i) {
			int sum_value = 0;
			String[] gates = layers.get(i)[layers_path_index[i]].trim().split(" ");
			
			for (int j = 0; j < gates.length; ++j) {
				int value = 0;
				
				switch (gates[j]) {
				case "not":
					value = not;
					break;
				case "and":
					value = and;
					break;
				case "nand":
					value = nand;
					break;
				case "or":
					value = or;
					break;
				case "nor":
					value = nor;
					break;
				case "xor":
					value = xor;
					break;
				case "xnor":
					value = xnor;
					break;
				case "wire":
					value = wire;
					break;
				default:
					System.out.println("Error in input; please try again.");
					
					break;
				}
				
				sum_value += value;
			}
			
			res += sum_value;
		}
		
		return res;
	}
}
