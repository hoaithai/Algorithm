import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class mirror {

	static int T;
	static final int max = 100;
	
	public static void main(String[] args) throws FileNotFoundException {
		int size;
	
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/mirror.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();//Number of testcase
		
		for ( int t =1; t<= 1; t++) {
			int times = 0;
			//read data from input.txt
			size = sc.nextInt();
			int[][] data = new int[size][size];
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j<size; j++) {
					data[i][j]= sc.nextInt();
				}
				
			}
			
			int huong = 1;
			
			for (int row = 0; row < size; row++) {
				for (int column = 0; column < size; column++) {
					while (true) {
						switch (huong) {
							case 1:
								if (data[row][column] == 2) {
									row++;
									times++;
									
									break;
								} else {
									huong = 1;
								}
								
								if (data[row][column] == 1) {
									column++;
									times++;
									break;
								} else {
									huong = 1;
								}
									
							case 2:
								if (data[row][column] == 2) {
									column++;
									times++;
									break;
								} else {
									huong = 2;
								}
								
								if (data[row][column] == 1) {
									column--;
									times++;
									break;
								} else {
									huong = 2;
								}
								
							
							case 3:
								if (data[row][column] == 2) {
									row--;
									times++;
									break;
								} else {
									huong = 3;
								}
								
								if (data[row][column] == 1) {
									row++;
									times++;
									break;
								} else {
									huong = 3;
								}
								
							case 4:
								if (data[row][column] == 2) {
									column++;
									break;
								} else {
									huong = 4;
								}
								
								if (data[row][column] == 1) {
									column--;
									times++;
									break;
								} else {
									huong = 4;
								}
							
						}	
					
					

					}
				}
			}
			System.out.println("#" + t + " " + times);
		}

	}
}
