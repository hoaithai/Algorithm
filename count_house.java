import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class count_house {
	static int T;
	static final int max = 50;
	static String[][] data = new String[max][max];
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int result = 0;
		int size;
		
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for ( int t =1; t<= 1; t++) {
			int near = 0;
			int total_H = 0;
			//read data from input.txt
			size = sc.nextInt();
			for (int i = 0; i < size; i++) {
				for (int j = 0; j<size; j++) {
					data[i][j]= sc.next();
				}
				
			}
			
			//count House
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					
					//if (data[i][j] == "X") continue;
					
					if (data[i][j] == "H"){
						total_H ++;
						System.out.println(total_H);
					}
					//System.out.println(data[i][j]);
					
					//cell A
					if (data[i][j] == "A") {
						if ( (i-1) >= 0 && data[i-1][j] == "H") {
							near += 1;
						}
						if ( (i+1) <=size & data[i+1][j] == "H"){
							near +=1;
						}
						if ( (j-1) >= 0 && data[i][j-1] == "H") {
							near += 1;
						}
						if ( (j+1) <=size & data[i][j+1] == "H"){
							
							near +=1;
						}
					}
					//cell B
					
					if (data[i][j] == "B") {
						if ( (i-1) >= 0 && data[i-1][j] == "H") {
							near += 1;
						}
						if ( (i+1) <=size & data[i+1][j] == "H"){
							near +=1;
						}
						if ( (j-1) >= 0 && data[i][j-1] == "H") {
							near += 1;
						}
						if ( (j+1) <=size & data[i][j+1] == "H"){
							
							near +=1;
						}
						
						if ( (i-2) >= 0 && data[i-2][j] == "H") {
							near += 1;
						}
						if ( (i+2) <=size & data[i+2][j] == "H"){
							near +=1;
						}
						if ( (j-2) >= 0 && data[i][j-2] == "H") {
							near += 1;
						}
						if ( (j+2) <=size & data[i][j+2] == "H"){
							
							near +=1;
						}
					}
					
					//cell C
					if (data[i][j] == "C") {
						if ( (i-1) >= 0 && data[i-1][j] == "H") {
							near += 1;
						}
						if ( (i+1) <=size & data[i+1][j] == "H"){
							near +=1;
						}
						if ( (j-1) >= 0 && data[i][j-1] == "H") {
							near += 1;
						}
						if ( (j+1) <=size & data[i][j+1] == "H"){
							
							near +=1;
						}
						
						if ( (i-2) >= 0 && data[i-2][j] == "H") {
							near += 1;
						}
						if ( (i+2) <=size & data[i+2][j] == "H"){
							near +=1;
						}
						if ( (j-2) >= 0 && data[i][j-2] == "H") {
							near += 1;
						}
						if ( (j+2) <=size & data[i][j+2] == "H"){
							
							near +=1;
						}
						
						if ( (i-3) >= 0 && data[i-3][j] == "H") {
							near += 1;
						}
						if ( (i+3) <=size & data[i+3][j] == "H"){
							near +=1;
						}
						if ( (j-3) >= 0 && data[i][j-3] == "H") {
							near += 1;
						}
						if ( (j+3) <=size & data[i][j+3] == "H"){
							
							near +=1;
						}
					}
				}	
			}
			
			System.out.println("TotalH: " +total_H);
			result = total_H - near;
			System.out.println("#" + T + " " + result);
		} 
	}

}
