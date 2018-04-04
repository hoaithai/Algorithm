import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class count_house_ver2 {

	static int T;
	static final int max = 50;
	//static String[][] data = new String[max][max];
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int size;
		
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();//Number of testcase
		
		for ( int t =1; t<= 1; t++) {
			int total_H = 0;
			//read data from input.txt
			size = sc.nextInt();
			String[][] data = new String[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j<size; j++) {
					data[i][j]= sc.next();
				}
				
			}
			
			//count House
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (data[i][j].equals("H")) total_H++;
				
					if ((data[i][j] != null) & (data[i][j].equals("H"))){
						//System.out.println(total_H);
						
						//Cell A
						if ( (i-1) >= 0) {
							if ( data[i-1][j].equals("A")){
							total_H --;}
						}
						if ( (i+1) < size ){
							if ( data[i+1][j].equals("A")) {
							total_H --;}
						}
						if ( (j-1) >= 0 ) {
							if ( data[i][j-1].equals("A")){
							total_H --;}
						}
						if ( (j+1) <size){ 
								if (data[i][j+1].equals("A")){
							
									total_H --;}
						}
						
						
						//cell B
						if ( (i-2) >= 0) {
							if (data[i-2][j].equals("B")) {
							total_H --;}
						}
						if ( (i+2) <(size -2)){
							if ( data[i+2][j].equals("B")){
							total_H --;}
						}
						if ( (j-2) >= 0) {
							if ( data[i][j-2].equals("B")) {
								total_H --;
							}
						}
						if ( (j+2) <= (size -2)){
							if ( data[i][j+2].equals("B")) {
								total_H --;
							}
						}
						
						
						//cell C
						if ( (i-3) >= 0 ) {
							if ( data[i-3][j].equals("C")) {
								total_H --;
							}
						}
						if ( (i+3) <size-3){
							if ( data[i+3][j].equals("C")) {
								total_H --;
							}
						}
						if ( (j-3) >= 0) {
							if ( data[i][j-3].equals("C")) {
								total_H --;
							}
						}
						if ( (j+3) <size-3){
							if ( data[i][j+3].equals("C")) {
								total_H --;
							}
							
						}
					}
				}
			}
		
			System.out.println("TotalH: " +total_H);
			System.out.println("#" + t + " " + total_H);
		}

	}
}
