/*
 * List natural numbers from 1 in binary numbers, then it will go as follows:  
                1, 10, 11, 100, 101, … 

	Create a string after deleting commas and spaces in-between and it will go “11011100101…”. 

Write a program that prints the corresponding number in decimal numbers when five numbers are read continuously 
from the n-th character in the string above.
  
Input: 
Throughout 10 lines, each test case is given in one line. Each test case consists of one positive integer, n. (1 ≤ n ≤ 1000)
  
Output:
Print answers to 10 test cases in 10 lines. Each line starts with "#x", leaves one space and writes the answer. (x: index of case)
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BinaryString {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream ("res/binary.txt"));
		Scanner sc = new Scanner(System.in);
		
		String input = generateString(1000);
		
		for (int t = 1; t <= 10; t++) {
			//i number=0;
			int start = sc.nextInt();
			// Review 4: No need to init value here but using directly
			// String child = input.subString()
			String child = "";
			child += input.substring(start-1, start+6);
			
			int number = convertBinaryToInt(child);
			System.out.println("#"+t+" "+number);
		}
		sc.close();
	}
	
	public static String generateString(int N) {
		// Review 1: Concat string using StringBuilder, not += operator
		String input = "";
		for (int i = 1; i <= N; i++) {
			input += Integer.toBinaryString(i);
		}
		
		return input;
		
	}
	
	//String s has length is 5
	// Review 2: In this case, 5 should be put into parameter lik
	// convertFromBinaryStringToInt(String s, int range)
	public static int convertBinaryToInt(String s) {
		// Review 3: Integer.valueOf has a method to support binary converter
		int result = 0;
		String [] cut = s.split("");
		
		int a = Integer.parseInt(cut[0]);
		int b = Integer.parseInt(cut[1]);
		int c = Integer.parseInt(cut[2]);
		int d = Integer.parseInt(cut[3]);
		int e = Integer.parseInt(cut[4]);
		
		result += a * Math.pow(2, 4) + b * Math.pow(2,3) +  c * Math.pow(2,2)
				+ d * Math.pow(2,1)+ e * Math.pow(2,0);
		
		
		return result;
	}
}
