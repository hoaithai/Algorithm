package com.example.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Count {

	static int T;
	static final int max = 50;
	static String[][] data = new String[max][max];

	public static void main(String[] args) throws FileNotFoundException {
		int size;

		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			// read data from input.txt
			size = sc.nextInt();
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					data[i][j] = sc.next();
				}

			}

			// count House
			// Idea is that
			// When get a X => Ignore
			// When get a house => save it
			// When get an A => Remove all house near it by 1
			// When get a B => Remove all house near it by 2
			// When get a C => Remove all house near it by 3
			// Because it is possible that the item could be removed before
			// adding
			// ex H A H
			// x H -> This will be removed before adding because the way we loop
			// the matrix
			// In this case, to make it simple we do 2 loops
			// First loop is used to get all H
			// Second loop is used to get A/ B / C

			// The remain H is size of collection

			// About Data structure
			// It has to save the row and column
			// It has to check one item is contained so it can be removed
			// => Could create a Point(x,y) but
			// It could be use by a Map<x, y> with x is key, y is value

			// First loop to collect house
			Map<Integer, Integer> foundMap = new HashMap<>();
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					// NOTE: Require Java7 or higher
					switch (data[i][j]) {
					case "H":
						foundMap.put(i, j);
						break;
					}
				}
			}
			System.out.println("#" + t + " " + "House: " + foundMap.size());
			// Second loop to filter house
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					// NOTE: Require java8 for remove (key, value) from Map
					// TODO: Refactoring ugly pair (x,y)
					switch (data[i][j]) {
					case "A":
						// Remove 4 near item of this point
						// Ignore return data
						foundMap.remove(i - 1, j);
						foundMap.remove(i + 1, j);
						foundMap.remove(i, j + 1);
						foundMap.remove(i, j - 1);
						break;
					case "B":
						foundMap.remove(i - 2, j);
						foundMap.remove(i + 2, j);
						foundMap.remove(i - 1, j);
						foundMap.remove(i + 1, j);

						foundMap.remove(i, j + 1);
						foundMap.remove(i, j - 1);
						foundMap.remove(i, j + 2);
						foundMap.remove(i, j - 2);
						break;
					case "C":
						foundMap.remove(i - 3, j);
						foundMap.remove(i + 3, j);
						foundMap.remove(i - 2, j);
						foundMap.remove(i + 2, j);
						foundMap.remove(i - 1, j);
						foundMap.remove(i + 1, j);

						foundMap.remove(i, j + 1);
						foundMap.remove(i, j - 1);
						foundMap.remove(i, j + 2);
						foundMap.remove(i, j - 2);
						foundMap.remove(i, j + 3);
						foundMap.remove(i, j - 3);
						break;
					}
				}

				// System.out.println("TotalH: " + total_H);
				// result = total_H - near;
			}
			System.out.println("#Free: " + foundMap.size());
			System.out.println("#Detail: " +
			// Detail position
					foundMap.entrySet()
							.stream()
							.map(entry -> String.format("(%s, %s)",
									entry.getKey(), entry.getValue()))
							.collect(Collectors.joining(", ")));
		}
		sc.close();
	}
}
