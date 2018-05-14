import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BlockDropping {
	public static int SIZE = 10;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//int T;

		System.setIn(new FileInputStream("res/blockdropping.txt"));
		Scanner sc = new Scanner(System.in);

		// T = sc.nextInt();

		for (int t = 0; t < 1; t++) {
			int[][] input = new int[SIZE][SIZE];

			// Read data from input file
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					input[i][j] = sc.nextInt();
					System.out.print(" " + input[i][j]);
				}
				System.out.println();
			}
			System.out.println("=============");

			boolean check = true;
	//		int row = 9, column = 0;

			// Solved from here
			while (check) {
				for (int i = SIZE - 1; i >= 0; i--) {
					for (int j = 0; j < SIZE; j++) {
						/*if (i-1 < 0 || j+1 > SIZE || i+1 > SIZE) {
							check = false;
						}*/
						
						// In case block is horizon
						if (i - 1 >= 0) {
							if (input[i][j] == 1 & input[i - 1][j] == 1) {
								if (i + 1 < SIZE) {
									if (input[i + 1][j] == 0) {
										input[i - 1][j] = 0;

										input[i + 1][j] = 1;
									} 
									else {
										check = false;
									}

								}

							}

						}

						// In case block is vertical 1 1
						if (j + 1 < SIZE) {
							if (input[i][j] == 1 & input[i][j + 1] == 1) {
								if (i + 1 < SIZE) {
									if (input[i + 1][j] == 0
											& input[i + 1][j + 1] == 0) {
										// drop this block(1 1)
										input[i + 1][j] = 1;
										input[i + 1][j + 1] = 1;

										input[i][j] = 0;
										input[i][j + 1] = 0;
										// continue;
									} 
									else {
										check = false;
									}
								}
							}

						}

					}
				}
				
				// After each step drop block
				for (int i = 0; i < SIZE; i++) {
					for (int j = 0; j < SIZE; j++) {
						System.out.print(" " + input[i][j]);
					}
					System.out.println();
				}
				System.out.println("================");
			}

//			// After each step drop block
//			for (int i = 0; i < SIZE; i++) {
//				for (int j = 0; j < SIZE; j++) {
//					System.out.print(" " + input[i][j]);
//				}
//				System.out.println();
//			}

			
			countHigh(10, input);

			// System.out.println(sumClumn(10, input));

		}

		sc.close();
	}

	public static void countHigh(int n, int[][] matrix) {
		int high[] = new int[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			count = 0;
			for (int j = 0; j < n; j++) {
				if (matrix[j][i] == 1) {
					count++;
					high[i] = count;
				}

			}
			System.out.print(" " + high[i]);
		}
	}

}
