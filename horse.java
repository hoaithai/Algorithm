import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class horse {
	public static int max = 100;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int T;

		// int[]banco = new int[max * max];

		System.setIn(new FileInputStream("res/horser.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();// Number of testcase

		for (int t = 1; t <= 1; t++) {
			int eat = 0;
			int N, H;
			String input = "";
			N = sc.nextInt();
			H = sc.nextInt();
			//input = sc.next();
			int[] banco = new int[N * N];
			//System.out.println(input);
			
			for (int i = 0; i < H*H; i++) {
				banco[i] = sc.nextInt();
				//System.out.println(" "+ banco[i]);
			}

			

			// System.out.println("#" + t + " " + eat);
		}

		sc.close();
	}

}
