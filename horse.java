import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Class Horse. <br />
 * The board is coordinated as (x,y) like
 * 
 * <pre>
 * .-> x 
 * |		|1|2|3| |
 * y		-1----
 * 			-2----	
 * </pre>
 */
public class Horse {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int T;

		System.setIn(new FileInputStream("res/horser.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();// Number of testcase

		for (int t = 1; t <= 1; t++) {

			// Read data from input file
			int N, H;
			N = sc.nextInt();
			H = sc.nextInt();
			int[] quanco = new int[(H - 1) * 2];

			int x_Horse = sc.nextInt();
			int y_Horse = sc.nextInt();

			for (int i = 0; i < (H - 1) * 2; i++) {
				quanco[i] = sc.nextInt();
			}

			boolean stop = true;

			int eat = 0;
			// Algorithm here
			/*
			 * while (stop) { if () { stop = false; } else {
			 */
			// TrinhNX: 
			// 1. Update comparison (all index from 0 => Change > to >=)
			// 2. Update break condition => Remove break because it breaks the loop (for)
			
			for (int i1 = 0; i1 < quanco.length; i1++) {

				if (i1 % 2 == 0) {

					// Xet huong di len
					// if (x_Horse - 1 > 0) {
					if (x_Horse - 1 >= 0) {
						if (x_Horse - 1 == quanco[i1]
						        && y_Horse == quanco[i1 + 1]) {
							// break;
						} else {
							if (x_Horse - 2 >= 0) {
								if ((quanco[i1] == x_Horse - 2)) {
									if (y_Horse - 1 >= 0
									        && (quanco[i1 + 1] == y_Horse
									                - 1)) {
										System.out.println("an dc 1a");
										eat++;
									}

									if (y_Horse + 1 < N
									        && quanco[i1 + 1] == y_Horse + 1) {
										System.out.println("an dc 1b");
										eat++;
									}
								}
							}
						}
					}

					// Xet huong di xuong
					if (x_Horse + 1 < N) {
						if (x_Horse + 1 == quanco[i1]
						        && y_Horse == quanco[i1 + 1]) {
							//break;
						} else {
							if (x_Horse + 2 < N) {
								if ((quanco[i1] == x_Horse + 2)) {
									if (y_Horse - 1 >= 0
									        && (quanco[i1 + 1] == y_Horse
									                - 1)) {
										eat++;
										System.out.println("an dc 2a");
									}

									if (y_Horse + 1 < N
									        && quanco[i1 + 1] == y_Horse + 1) {
										System.out.println("an dc 2b");
										eat++;
									}
								}
							}

						}
					}

					// Xet huong sang Phai

					if (y_Horse + 1 < N) {
						if (y_Horse + 1 == quanco[i1 + 1]
						        && x_Horse == quanco[i1]) {
							//break;
						} else {
							if (y_Horse + 2 < N) {
								if (quanco[i1 + 1] == y_Horse + 2) {
									if (x_Horse - 1 >= 0
									        && quanco[i1] == x_Horse - 1) {
										eat++;
										System.out.println("an dc 3a");
									}

									if (x_Horse + 1 < N
									        && quanco[i1] == x_Horse + 1) {
										System.out.println("an dc 3b");
										eat++;
									}
								}
							}
						}

					}

					// Xet huong sang Trai
					if (y_Horse - 1 >= 0) {
						if (x_Horse == quanco[i1]
						        && y_Horse - 1 == quanco[i1 + 1]) {
							//break;
						} else {
							// System.out.println();
							if (y_Horse - 2 >= 0) {

								if (quanco[i1 + 1] == y_Horse - 2) {
									if (x_Horse - 1 >= 0
									        && quanco[i1] == x_Horse - 1) {
										System.out.println("an dc 4a");
										eat++;
									}
									if (x_Horse + 1 < N
									        && quanco[i1] == x_Horse + 1) {
										System.out.println("an dc 4b");
										eat++;
									}
								}
							}
						}
					}
				}
				// }
			}
			System.out.println("#" + t + " " + eat);
		}

		sc.close();
	}

}
