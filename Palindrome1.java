import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Palindrome1 {
	public static int NUM_CHAR = 8;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/palindrome1.txt"));
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int count = 0;
			int len;
			len = sc.nextInt();
			// read input data
			char[][] input = new char[NUM_CHAR][NUM_CHAR];

			for (int i = 0; i < NUM_CHAR; i++) {
				String s = sc.next();
				// System.out.println(s);
				input[i] = s.toCharArray();
			}

			// Solve from here
			/*
			 * String t1 = "CBBC"; char[] s1 = t1.toCharArray();
			 * System.out.println(isPalindrome(t1));
			 */
			for (int row = 0; row < NUM_CHAR; row++) {
				String r = "";
				String cl = "";
				for (int col = 0; col < NUM_CHAR; col++) {
					// System.out.println(input[row][col]);
					r += input[row][col];
			
					cl += input[col][row];
				}

				// System.out.print("row:"+r);
				//System.out.println("cl: "+cl);
				for (int l = 0; l < NUM_CHAR; l++) {
					if (l + len - 1 < NUM_CHAR) {
						String sub = r.substring(l, l + len);

						if (isPalindrome(sub)) {
							count++;
							//System.out.println(sub);
						}
						
						String s_cl = cl.substring(l, l+len);
						
						if (isPalindrome(s_cl)) {
							count++;
							//System.out.println(s_cl);
						}
					}

				}
			}

			 System.out.println("#"+t+" "+count);
		}

		sc.close();

	}

	public static boolean isPalindrome(String s) {
		int start = 0;
		int stop = s.length() - 1;

		while (stop > start) {
			if (s.charAt(start) != s.charAt(stop)) {
				return false;
			}
			++start;
			--stop;
		}
		return true;
	}

}
