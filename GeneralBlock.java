package com.na.guru;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneralBlock {
	public static int SIZE = 10;

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("res/blockdropping.txt"));
		Scanner sc = new Scanner(System.in);

		// T = sc.nextInt();

		for (int t = 1; t <= 2; t++) {
			int[][] input = new int[SIZE][SIZE];
			// TrinhNX : Add code
			// Construct board
			Board board = new Board(SIZE, SIZE);
			// Read data from input file
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					input[i][j] = sc.nextInt();
					// System.out.print(" " + input[i][j]);
					if (input[i][j] == 0) {
						board.set(i, j, false);
					} else {
						board.set(i, j, true);
					}
				}
				// System.out.println();
			}
			// System.out.println("=============");
			board.toDisplay();
			Board clone = board.clone();
			Point start = new Point(0, 2);
			List<Point> blocks = new ArrayList<>();
			PointBlockBoardUtil.adjoinPoint(start, clone, blocks);
			System.out.println(blocks.size());
			// After each step drop block
			// printMatrix(input);
			// System.out.println("======================");
			System.out.print("#" + t + "");
			countHigh(input);
			System.out.println();

			// System.out.println(sumClumn(10, input));

		}

		sc.close();
	}

	// This problem is exciting.
	// For convenience, use the Point class from Java

	// We will define a block which include points
	// Each block can join with another one to make new
	// Each block can move, for sake of simple, just move down
	// If moving is invalid (to bottom, collide with other one) => Revert

	// we also define a board with size (MxN)
	// We define a graph to draw a block on the board
	// It is like teris gaming
	private static final class Block {
		private List<Point> points;
		private boolean isMoveDownValid = false;

		public Block() {
			points = new ArrayList<>();
		}

		public void setPoints(List<Point> points) {
			this.points = points;
		}

		public List<Point> getPoints() {
			return points;
		}

		/**
		 * Adds the point to block.
		 *
		 * @param point the point
		 */
		public void addPoint(Point point) {
			if (point == null) {
				// kindly ignore
				System.err.println("Null point");
			}
			if (points.contains(point)) {
				throw new IllegalArgumentException("Point had existed" + point);
			}
			points.add(point);
		}
	}

	private static final class Point {
		private int x;
		private int y;

		public Point() {
			this(0, 0);
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

	}

	private static final class Board {
		int m, n;
		boolean[][] data;

		public Board(int m, int n) {
			this.m = m;
			this.n = n;
			this.data = new boolean[m][n];
		}

		public void set(int x, int y, boolean data) {
			this.data[x][y] = data;
		}

		/**
		 * Gets the value at position {@code x, y}, return false if out of
		 * border
		 *
		 * @param x the x
		 * @param y the y
		 * @return true, if successful
		 */
		public boolean get(int x, int y) {
			try {
				return this.data[x][y];
			} catch (IndexOutOfBoundsException ex) {
				// Lazy handling out of border
				return false;
			}
		}

		/**
		 * Gets the point if found
		 *
		 * @param x the x
		 * @param y the y
		 * @return the point, null if not match (out of border, false value)
		 */
		public Point getPoint(int x, int y) {
			if (get(x, y)) {
				return new Point(x, y);
			}
			return null;
		}

		public void reset() {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					this.data[i][j] = false;
				}
			}
		}

		public Board clone() {
			Board clone = new Board(m, n);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					clone.set(i, j, this.data[i][j]);
				}
			}
			return clone;
		}

		/**
		 * To display the board status.
		 */
		public void toDisplay() {
			final String emptyChar = "0";
			final String busyChar = "1";
			final String displayFormat = "%s\t";
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (data[i][j]) {
						System.out.printf(displayFormat, busyChar);
					} else {
						System.out.printf(displayFormat, emptyChar);
					}
				}
				System.out.println();
			}
		}
	}

	/**
	 * The Class PointBlockBoardUtil <br />
	 * Well that's naming
	 */
	private static final class PointBlockBoardUtil {

		/**
		 * Merge 2 block into new one <br />
		 * (same meaning with construct a new block from 2 items)
		 *
		 * @param b1 the b 1
		 * @param b2 the b 2
		 * @return the block
		 */
		public static Block merge(Block b1, Block b2) {
			Block block = new Block();
			block.setPoints(Stream.concat(b1.getPoints()
			        .stream(),
			        b2.getPoints()
			                .stream())
			        .distinct()
			        .collect(Collectors.toList()));
			return block;
		}

		/**
		 * Check point belong to at least one block <br />
		 * 
		 * @param point the point
		 * @param blocks the blocks
		 * @return the block contains or null if not found
		 */
		public static Block pointBelongTo(Point point, List<Block> blocks) {
			return blocks.stream()
			        .filter(block -> block.getPoints()
			                .contains(point))
			        .findAny()
			        .orElse(null);
		}

		/**
		 * Prints the block on board <br />
		 * if cell is busy then mark is 1, otherwise 0.
		 *
		 * @param board the board
		 * @param block the block
		 */
		public static void displayBlockOnBoard(Board board, Block block) {
			// TODO: Not checking the boundary
			block.getPoints()
			        .forEach(point -> {
				        board.set(point.getX(), point.getY(), true);
			        });
			board.toDisplay();
		}

		/**
		 * Get all adjoin point from board
		 *
		 * @param start the start
		 * @param board the board
		 * @return the list, never empty
		 */
		public static void adjoinPoint(Point start, Board board,
		        List<Point> points) {
			// Exit condition
			if (board.getPoint(start.getX(), start.getY()) == null) {
				return;
			}
			points.add(start);
			// mark this point false
			board.set(start.getX(), start.getY(), false);
			System.out.println("---");
			board.toDisplay();
			Point downSidePoint = board.getPoint(start.getX() + 1,
			        start.getY());
			if (downSidePoint != null) {
				// board.set(downSidePoint.getX(), downSidePoint.getY(), false);
				adjoinPoint(downSidePoint, board, points);
			}

			Point upSidePoint = board.getPoint(start.getX() - 1, start.getY());
			if (upSidePoint != null) {
				// board.set(upSidePoint.getX(), upSidePoint.getY(), false);
				adjoinPoint(upSidePoint, board, points);
			}
			Point leftSidePoint = board.getPoint(start.getX(),
			        start.getY() - 1);
			if (leftSidePoint != null) {
				// board.set(leftSidePoint.getX(), leftSidePoint.getY(), false);
				adjoinPoint(leftSidePoint, board, points);
			}
			Point rightSidePoint = board.getPoint(start.getX(),
			        start.getY() + 1);
			if (rightSidePoint != null) {
				// board.set(rightSidePoint.getX(), rightSidePoint.getY(),
				// false);
				adjoinPoint(rightSidePoint, board, points);
			}
		}
	}
}
