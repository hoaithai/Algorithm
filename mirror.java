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
			
			
			int row =0, column= 0;
			boolean cond = true;
			int huong = 4;
			while (cond) {
				if (row < 0 || row >=size || column >= size || column < 0 ) {
					//if (data[row][column] == 0) {
					cond = false;
				} else {
				switch (huong) {
					case 1:
						
							if (data[row][column] == 2) {
								
								System.out.println("Case1-2 "+"huong: "+huong);
								column++;
								times++;
									
								huong = 4;
								System.out.println("Case1-2 huong: "+huong);
								break;
							} else if (data[row][column] == 1) {
										
								System.out.println("Case1-1 huong: "+huong);
								column--;
								times++;
										
								huong = 2;
								System.out.println("Case1-1 huong: "+huong);
								break;
							} else {
								huong = 1;
								column++;
								break;
							}
						
											
					case 2:
						
							if (data[row][column] == 2) {
								System.out.println("Case2-2 huong: "+huong);
								row--;
								times++;
									
								huong = 3;
								System.out.println("Case2-2 huong: "+huong);
								break;
							} else if (data[row][column] == 1) {
								System.out.println("Case2-1 huong: "+huong);
								row++;
								times++;
										
								huong = 1;
								System.out.println("Case2-1 huong: "+huong);
								break;
							} else {
								huong = 2;
								column--;
								break;
							} 
						
								
					case 3:
						
							if (data[row][column] == 2) {
								System.out.println("Case3-2 huong: "+huong);
								column--;
								times++;
									
								huong = 2;
								System.out.println("Case3-2 huong: "+huong);
								break;
							} else if (data[row][column] == 1) {
								System.out.println("Case3-1 huong: "+huong);
								column++;
								times++;
									
								huong = 4;
								System.out.println("Case3-1 huong: "+huong);
								break;
							} else {
								huong = 3;
								row--;
								break;
							}
						
						
					case 4:
						
							if (data[row][column] == 2) {
								System.out.println("Case4-2 huong: "+huong);
								row++;
								times++;
									
								huong = 1;
								System.out.println("Case4-2 huong: "+huong);
								break;
							} else if (data[row][column] == 1) {
								System.out.println("Case4-1 huong: "+huong);
								row--;
								times++;
									
								huong = 3;
								System.out.println("Case4-1 huong: "+huong);
								break;
							} else {
								huong = 4;
								column++;
								break;
							}
							
						
						
				}		
							//}
				}
			}
			
			System.out.println("#" + t + " " + times);
		}

	}
}
