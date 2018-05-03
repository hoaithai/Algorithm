/*
 * Input: matrix N * N
 * Count sum each of diagonal line, row, column
 * Output: max of them
 * 
 * Template 
 * Input: first line: number of testcase
 * The next line is size of matrix
 * Then is value of matrix
 * 
 * Output: Output the answer to each test case. Each line begins with "#x" x is number of testcase
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matrix {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int T;
		int N;

		// Read data from input file
		System.setIn(new FileInputStream("res/matrix.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();// Number of testcase
		// N = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			long[][] matrix = new long[N][N];

			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					matrix[i][j] = sc.nextLong();
				}
			}

			// Count sum each of row, column and diagonal line
			long sum_row[] = new long[N];
			long sum_column[] = new long[N];
			long sum_line[] = new long[2];
			long temp_line1 = 0;
			long temp_line2 = 0;

			// Sum row
			for (int i = 0; i < N; i++) {
				long temp_row = 0;
				long temp_column = 0;
				for (int j = 0; j < N; j++) {
					//Sum row
					temp_row += matrix[i][j];
					sum_row[i] = temp_row;

					// Sum column
					temp_column += matrix[j][i];
					sum_column[i] = temp_column;

					// sum line
					if (i == j) {
						temp_line1 += matrix[i][j];
						sum_line[0] = temp_line1;
					}

					if (j == N - i - 1) {
						temp_line2 += matrix[i][N - i - 1];
						sum_line[1] = temp_line2;
					}
				}

			}

			// find max sum matrix
			long max_column;
			max_column = findMax(sum_column);
			// System.out.println("Max column: "+max_column);

			long max_row = findMax(sum_row);
			long max_line = findMax(sum_line);

			long max = max_row > max_column ? (max_row > max_line ? max_row
					: max_line) : (max_column > max_line ? max_column
					: max_line);

			System.out.println("#" + t + " " + max);
		}

		sc.close();

	}

	public static long findMax(long[] matrix) {
		long max = matrix[0];

		for (int i = 1; i < matrix.length; i++) {
			// max = matrix[i];
			if (matrix[i] >= max) {
				max = matrix[i];
			}
		}

		return max;
	}

}
