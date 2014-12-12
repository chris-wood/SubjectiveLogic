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
	
	public static void authenticityWalk(int[][] m, int root, NodeOpinion[] ka, NodeOpinion[] rt) {
		Set<Integer> visited = new HashSet<Integer>();
		List<Integer> queue = new ArrayList<Integer>();
		
		// KA and RT maps
		Opinion[][] KA = new Opinion[m.length][m.length];
		Opinion[][] RT = new Opinion[m.length][m.length];
		
		// Root KA and RT are \omega = {1.0, 0.0, 0.0} 
		KA[root][root] = new Opinion(1.0, 0.0, 0.0);
		RT[root][root] = new Opinion(1.0, 0.0, 0.0);
		
		// First-hand KA and RT evidence injections
		for (int k = 0; k < ka.length; k++) {
			NodeOpinion op = ka[k];
			KA[op.u][op.v] = op.opinion;
		}
		for (int k = 0; k < rt.length; k++) {
			NodeOpinion op = ka[k];
			RT[op.u][op.v] = op.opinion;
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
		
		// TODO: parse args, if necessary
		
		Scanner scin = new Scanner(System.in);
		
		int[][] m = null;
		int j = 0;
		int root = 0;
		List<NodeOpinion> kaList = new ArrayList<NodeOpinion>();
		List<NodeOpinion> rtList = new ArrayList<NodeOpinion>();
		
		while (scin.hasNextLine()) {
			String line = scin.nextLine();
			if (line.length() > 0) {
				if (line.startsWith("root")) {
					String[] split = line.split(" ");
					root = Integer.parseInt(split[1].trim());
				} else if (line.toLowerCase().startsWith("ka")) { // ka v1 v2 d b u
					String[] split = line.split(" ");
					Opinion opinion = new Opinion(split[3], split[4], split[5]);
					kaList.add(new NodeOpinion(Integer.parseInt(split[1]), Integer.parseInt(split[2]), opinion));
				} else if (line.toLowerCase().startsWith("rt")) {
					String[] split = line.split(" ");
					Opinion opinion = new Opinion(split[3], split[4], split[5]);
					rtList.add(new NodeOpinion(Integer.parseInt(split[1]), Integer.parseInt(split[2]), opinion));
				} else { // row of the adjacency matrix
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
		if (kaList.isEmpty() || rtList.isEmpty()) {
			System.err.println("Invalid configuration: try again...");
			System.exit(-1);
		}
		
		// Generate the KA and RT values...
		disp(m);
		authenticityWalk(m, root, (NodeOpinion[]) kaList.toArray(), (NodeOpinion[]) rtList.toArray());
	}	
}
