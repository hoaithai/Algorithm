import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class intima {

	public int docao(char[] array) {
		int k = 0;
		int len = array.length;
		int c_mo = 0, c_dong = 0;

		for (int i = 2; i < len; i++) {
			if (array[i] == '(') {
				c_mo++;
			}

			if (array[i] == ')') {
				c_dong--;
			}

			if (c_mo == c_dong) {

			}
		}

		return k;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int T;
		System.setIn(new FileInputStream("res/intima.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();// Number of testcase

		for (int t = 1; t <= 1; t++) {
			int total = 0;
			int k, h = 0;
			k = sc.nextInt();

			String input = "";
			input = sc.next();

			char[] cut = input.toCharArray();
			int c_mo = 0, c_dong = 0;

			int size = cut.length / 2;
			int[][] cvr = new int[size][size];
			int row = 0, column = 0;
			
			// int[][] cvr = new int[][];
			System.out.println(cut.length);
			for (int i = 0; i < cut.length; i++) {
				int[] r = new int[cut.length];
			
				if (cut[i] == '(') {
					c_mo++;
					
					if (cut[i + 1] == '0') {
						cvr[0][0] = 0;
						row++;
					}

					if (cut[i + 1] != '0' && cut[i+1] != ')') {
						System.out.println(cut[i+1]);
						cvr[row][column] = Character
								.getNumericValue(cut[i + 1]);
						//column++;
						row++;

					}

				} else if (cut[i] == ')') {
					c_dong++;

				}

			}
			System.out.println("================");
			for (int j = 0; j < size; j++) {
				for (int s = 0; s < size; s++) {
					System.out.print(cvr[j][s]);
				}
				System.out.println();
			}

			System.out
					.println("#" + t + "c_mo: " + c_mo + " c_dong: " + c_dong);
		}

	}

}
