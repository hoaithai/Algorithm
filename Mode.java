/*In the following number distribution,
10, 8, 7, 2, 2, 4, 8, 8, 8, 9, 5, 5, 3
8 is the mode.
Create a program outputting a mode. If there are more than one mode, output the largest score.
Constraints:
There are 1,000 students.
The score ranges 0 ~ 100.

Input:
The first line in the input file is the number of the test case. The scores are given in next lines.
 

Output:
Output the answer to each test case. Each line begins with ‘#x’, and the answer is recorded after a line feed.
Here, x is the number of the test case.
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mode {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/mode.txt"));
		Scanner sc = new Scanner(System.in);

		int T;
		int priority;

		T = sc.nextInt();

		// Idea: Create a array mode: save the time appear of number in score.
		// the index of array is score
		// Then print the largest of this array => this is mode
		System.out.println(System.currentTimeMillis());
		for (int t = 1; t <= T; t++) {
			int[] score = new int[1000];
			priority = sc.nextInt();

			for (int i = 0; i < score.length; i++) {
				score[i] = sc.nextInt();
			}

			int largest = 0;

			int[] mode = new int[101];
			for (int i = 0; i < mode.length; i++) {
				mode[i] = 0;
				// System.out.println(mode[i]);
			}

			for (int i = 0; i < score.length; i++) {
				int count = countNumber(score, score[i]);

				mode[score[i]] = count;
			}

			for (int i = 0; i < mode.length; i++) {
				// mode[i] = 0;
				// System.out.println(""+i+" "+mode[i]);

				int max = findMax(mode);

				if (mode[i] == max) {
					largest = i;
					// System.out.println("Mode is: "+i);
				}
			}

			System.out.println("#" + t + " " + largest);

		}
		System.out.println(System.currentTimeMillis());
		sc.close();
	}

	
	public static int countNumber(int[] matrix, int x) {
		int count = 0;

		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] == x) {
				count++;
			}

		}

		return count;
	}

	public static int findMax(int[] matrix) {
		int max = matrix[0];

		for (int i = 1; i < matrix.length - 1; i++) {
			if (matrix[i] > max) {
				max = matrix[i];
			}
		}

		return max;

	}
}
