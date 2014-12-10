import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CLI {
	
	public static void disp(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void disp(Opinion[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.println("Opinion[" + i + "][" + j + "] = " + matrix[i][j]);
			}
		}
	}
	
	public static void authenticityWalk(int[][] m, int root, Opinion[] ka, Opinion[] rt) {
		Set<Integer> visited = new HashSet<Integer>();
		List<Integer> queue = new ArrayList<Integer>();
		
		// KA and RT maps
		Opinion[][] KA = new Opinion[m.length][m.length];
		Opinion[][] RT = new Opinion[m.length][m.length];
		
		// Root KA and RT are \omega = {1.0, 0.0, 0.0} 
		KA[root][root] = new Opinion(1.0, 0.0, 0.0);
		RT[root][root] = new Opinion(1.0, 0.0, 0.0);
		
		// First-hand KA and RT evidence...
		int k = 0;
		int i = root;
		for (int j = 0; j < m.length; j++) {
			if (m[i][j] == 1) {
				KA[i][j] = ka[k];
				RT[i][j] = rt[k];
				k++; // advance to next child
			}
		}
		
//		System.out.println("KA evidence");
//		disp(KA);
//		System.out.println("RT evidence");
//		disp(RT);
		
		// BFS to compute key authenticity
		queue.add(root);
		while (queue.size() > 0) {
			int parent = queue.remove(0); // pop
			if (!visited.contains(parent)) {
				visited.add(parent);
				
				for (int child = 0; child < m.length; child++) {
					if (m[parent][child] == 1) {
						// TODO: compute authenticity
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		// TODO: parse args
		
		Scanner scin = new Scanner(System.in);
		
		int[][] m = null;
		int j = 0;
		int root = 0;
		Opinion[] ka = null;
		Opinion[] rt = null;
		
		while (scin.hasNextLine()) {
			String line = scin.nextLine();
			if (line.length() > 0) {
				if (line.startsWith("root:")) {
					String[] split = line.split(":");
					root = Integer.parseInt(split[1].trim());
				} else if (line.toLowerCase().startsWith("ka:")) {
					String[] split = line.split(":")[1].split(" "); // space separated...
					ka = new Opinion[(split.length - 1) / 3]; // tuple of 3... 
					for (int e = 1, k = 0; e < split.length; e += 3, k++) {
						ka[k] = new Opinion(Double.parseDouble(split[e]), 
								Double.parseDouble(split[e+1]), 
								Double.parseDouble(split[e+2]));
					}
				} else if (line.toLowerCase().startsWith("rt:")) {
					String[] split = line.split(":")[1].split(" "); // space separated...
					rt = new Opinion[(split.length - 1) / 3]; // tuple of 3... 
					for (int e = 1, k = 0; e < split.length; e += 3, k++) {
						rt[k] = new Opinion(Double.parseDouble(split[e]), 
								Double.parseDouble(split[e+1]), 
								Double.parseDouble(split[e+2]));
					}
				} else {
					String[] split = line.split(" ");
					if (m == null) m = new int[split.length][split.length];
					for (int i = 0; i < split.length; i++) {
						m[i][j] = Integer.parseInt(split[i]);
					}
					j++;
				}
			}
		}
		
		// Sanity check
		if (ka == null || rt == null) {
			System.err.println("Invalid configuration: try again...");
			System.exit(-1);
		}
		
		// Generate the KA and RT values...
		disp(m);
		authenticityWalk(m, root, ka, rt);
	}	
}
