import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import sun.net.www.content.audio.wav;

public class Flatten {
	public static int SIZE = 100;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/flatten.txt"));
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 1; t++) {
			int dump;
			int[] wall = new int[SIZE];
			int answer = 0;
			
			// Read data from input file
			dump = sc.nextInt();

			for (int i = 0; i < SIZE; i++) {
				wall[i] = sc.nextInt();
				System.out.print(wall[i]+"  ");
			}
			
			System.out.println("=============");
		
			int max, min;
			int imax, imin; 
			
			max = maxMatrix(wall)[0];
			imax = maxMatrix(wall)[1];
			
			min = minMatrix(wall)[0];
			imin = minMatrix(wall)[1];
			
			for (int change = dump; change >= 0; change--) {
				wall[imax] = max - 1;
				wall[imin] = min +1;
				
				System.out.println("Max: "+max);
				System.out.println("Min: "+min);
				
				printMatrix(wall);
				System.out.println("=============");
			}
			
			//int tallest = maxMatrix(wall)[0];
			//int shortest = minMatrix(wall)[0];
			answer = max - min;
			
			System.out.println("#"+t+" "+answer);
			
		}

		sc.close();
	}

	public static int[] maxMatrix(int[] matrix) {
		int max = matrix[0];
		int index = 0;
		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i] >= max) {
				max = matrix[i];
				index = i;
			}
		}

		return new int[]{max, index};

	}

	public static int[] minMatrix(int[] matrix) {
		int min = matrix[0];
		int index = 0;
		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i] <= min) {
				min = matrix[i];
				index = i;
			}
		}

		return new int[] {min, index};

	}
	
	public static void printMatrix(int[] ma) {
		for (int i = 0; i < ma.length; i++) {
			System.out.print(ma[i]+"  ");
		}
	}
}
