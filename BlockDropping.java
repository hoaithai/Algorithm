import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BlockDropping {
	public static int SIZE = 10;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		// int T;

		System.setIn(new FileInputStream("res/blockdropping.txt"));
		Scanner sc = new Scanner(System.in);

		// T = sc.nextInt();

		for (int t = 1; t <= 2; t++) {
			int[][] input = new int[SIZE][SIZE];

			// Read data from input file
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					input[i][j] = sc.nextInt();
					// System.out.print(" " + input[i][j]);
				}
				// System.out.println();
			}
			// System.out.println("=============");

			boolean check = true;

			// Solved from here
			while (check) {
				for (int i = SIZE - 2; i >= 0; i--) {
					for (int j = 0; j < SIZE; j++) {
						// In case the block is horizon
						if (i - 1 >= 0) {
							if (input[i][j] == 1 & input[i - 1][j] == 1) {
								if (j + 1 < SIZE) {
									//Check element beside to sure this block is independent
									if (input[i][j + 1] == 0) {
										if (input[SIZE - 1][j] == 0) {
											input[i][j] = 0;
											input[i - 1][j] = 0;

											input[SIZE - 1][j] = 1;
											input[SIZE - 2][j] = 1;

										} else {
											if (i + 1 < SIZE) {
												for (int c = i + 1; c < SIZE; c++) {
													if (input[c][j] == 1) {
														input[i][j] = 0;
														input[i - 1][j] = 0;

														input[c - 1][j] = 1;
														input[c - 2][j] = 1;
													}
												}
											}

										}
									}
								} else if (j + 1 == SIZE) {
									if (input[SIZE - 1][j] == 0) {
										input[i][j] = 0;
										input[i - 1][j] = 0;

										input[SIZE - 1][j] = 1;
										input[SIZE - 2][j] = 1;

									} else {
										if (i + 1 < SIZE) {
											for (int c = i + 1; c < SIZE; c++) {
												if (input[c][j] == 1) {
													input[i][j] = 0;
													input[i - 1][j] = 0;

													input[c - 1][j] = 1;
													input[c - 2][j] = 1;
												}
											}
										}

									}

								} else {
									check = false;
								}

							}
						} else {
							check = false;
						}

						// In case block is vertical
						if (j + 1 < SIZE) {
							if (input[i][j] == 1 & input[i][j + 1] == 1) {
								if (i + 1 < SIZE) {
									if (input[SIZE - 1][j] == 0
											& input[SIZE - 1][j + 1] == 0) {
										input[i][j] = 0;
										input[i][j + 1] = 0;

										input[SIZE - 1][j] = 1;
										input[SIZE - 1][j + 1] = 1;
									} else if (input[SIZE - 1][j] != 0
											|| input[SIZE - 1][j + 1] != 0) {
										for (int r = i + 1; r < SIZE; r++) {
											if (input[r][j] == 1
													|| input[r][j + 1] == 1) {
												if (r - 1 >= 0) {
													input[r - 1][j] = 1;
													input[r - 1][j + 1] = 1;

													input[i][j] = 0;
													input[i][j + 1] = 0;

													break;
												}
											}
										}

									}
								} else {
									check = false;
								}

							}
						} else {
							check = false;
						}

					}
				}
			}
			// After each step drop block
			// printMatrix(input);
			// System.out.println("======================");
			System.out.print("#" + t + "");
			countHigh(input);
			System.out.println();

			// System.out.println(sumClumn(10, input));

		}

		sc.close();
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(" " + matrix[i][j]);
			}
			System.out.println();
		}
	}

	public static void countHigh(int[][] matrix) {
		int high[] = new int[SIZE];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (matrix[j][i] == 1) {
					high[i] = SIZE - j;
					break;
				}

			}
			System.out.print(" " + high[i]);
		}
	}
}
