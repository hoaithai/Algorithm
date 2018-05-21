import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class View {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/view.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N;
		
		for (int t = 1; t <= 10; t++) {
			N = sc.nextInt();
			
			int[] household = new int[N];
			for (int i = 0; i < N; i++) {
				household[i] = sc.nextInt();
			}
			
			int answer = 0;
			
			for (int i = 2; i < N-2; i++) {
				if (household[i] > household[i-1] & household[i] > household[i-2]
						& household[i] > household[i+1] & household[i] > household[i+2]) {
					int max = 0;
					int tmp;
					tmp = max(household[i-1], household[i-2], household[i+1]);
					
					if (tmp > household[i+2]) {
						max = tmp;
						
					} else {
						max = household[i+2];
						
					}
					//System.out.println("max: "+max);
					answer += household[i] - max;
					
				}
				
				
			}
			
			System.out.println("#"+t+" "+answer);
		}
		
		
		sc.close();
	}
	
	public static int max(int a, int b, int c) {
		int max = 0;
		
		max = a > b ? (a > c ? a :c) : (b > c ? b : c);
		
		return max;
	}

}
