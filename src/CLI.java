import java.util.HashSet;
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
	
	public static void main(String[] args) {
		
		// TODO: parse args 
		
		int[][] m = null;
		int j = 0;
		
		Scanner scin = new Scanner(System.in);
		
		while (scin.hasNextLine()) {
			String line = scin.nextLine();
			String[] split = line.split(" ");
			if (m == null) m = new int[split.length][split.length];
			for (int i = 0; i < split.length; i++) {
				m[i][j] = Integer.parseInt(split[i]);
			}
			j++;
		}
		
		disp(m);
	}	
}
