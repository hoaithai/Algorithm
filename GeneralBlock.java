import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlockDropping {
	public static int SIZE = 10;

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("res/blockdropping.txt"));
		Scanner sc = new Scanner(System.in);
		// T = sc.nextInt();
		final int NUM_TEST = 2;
		for (int t = 1; t <= NUM_TEST; t++) {
			int[][] input = new int[SIZE][SIZE];
			// TrinhNX : Add code
			// Step 1 : Construct board from input file
			Board board = new Board(SIZE, SIZE);
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (sc.hasNextInt()) {
						input[i][j] = sc.nextInt();
						if (input[i][j] == 0) {
							board.set(i, j, false);
						} else {
							board.set(i, j, true);
						}
					}
				}
			}
			// Step 2: Construct all the blocks
			// NOTE: Clone the board to get the adjoin block
			// TODO: Waste of memory to construct the board due to clone()
			List<Block> blocks = new ArrayList<>();
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					// We only check if the point is true value
					// And it does not belong to any block
					Point point = board.getPoint(i, j);
					if (point != null && PointBlockBoardUtil
					        .pointBelongToBlock(point, blocks) == null) {
						Board clone = board.clone();
						List<Point> points = new ArrayList<>();
						PointBlockBoardUtil.adjoinPoint(point, clone, points);
						if (!points.isEmpty()) {
							Block block = new Block();
							block.setPoints(points);
							blocks.add(block);
						}
					}
					// else => skip
				}
			}
			// Step 3: Move whole blocks 1 step down while at least one block
			// could move
			System.out.println("Before moving");
			board.toDisplay();
			final int stepDown = 1;
			Block availableBlock;
			while ((availableBlock = blocks.stream()
			        // Get block which could move
			        .filter(block -> PointBlockBoardUtil
			                .isBlockMoveDownOk(block, board, stepDown))
			        .findAny()
			        // If nothing, return null
			        .orElse(null)) != null) {
				// Perform real moving
				availableBlock.moveDown(stepDown);
				// Update the board status
				board.reset();
				PointBlockBoardUtil.updateBlockBoard(board, blocks);
			}
			// At this point, all done, print out on the board
			System.out.println("After moving");
			board.toDisplay();
			// After each step drop block
			System.out.print("#" + t + "");
			System.out.println();
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
	// It is like terris gaming
	public static final class Block {
		private List<Point> points;

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

		/**
		 * Contain point.
		 *
		 * @param point the point
		 * @return true, if contains, false otherwise
		 */
		public boolean containPoint(Point point) {
			return points.contains(point);
		}

		/**
		 * Move down.
		 *
		 * @param step the step
		 */
		public void moveDown(int step) {
			// Update all point of this block down to n steps with X
			points.forEach(point -> {
				point.setX(point.getX() + step);
			});
		}

		@Override
		public String toString() {
			return points.stream()
			        .map(point -> point.toString())
			        .collect(Collectors.joining(","));
		}
	}

	public static final class Point {
		private int x;
		private int y;

		public Point() {
			this(0, 0);
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Point) {
				Point other = (Point) obj;
				return other.getX() == this.getX()
				        && other.getY() == this.getY();
			}
			return false;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}

	public static final class Board {
		int m, n;
		boolean[][] data;

		public Board(int m, int n) {
			this.m = m;
			this.n = n;
			this.data = new boolean[m][n];
		}

		public int getM() {
			return m;
		}

		public int getN() {
			return n;
		}

		public void set(boolean[][] data) {
			this.data = data;
			this.m = data.length;
			this.n = data[0].length;
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

		@Override
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
			final String displayFormat = "%s  ";
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
	 * Well that's naming to handle all stuff like a factory instead
	 */
	public static final class PointBlockBoardUtil {

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
		public static Block pointBelongToBlock(Point point,
		        List<Block> blocks) {
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
		public static void updateBlockBoard(Board board, List<Block> blocks) {
			// TODO: Not checking the boundary
			blocks.forEach(block -> block.getPoints()
			        .forEach(point -> {
				        board.set(point.getX(), point.getY(), true);
			        }));
		}

		/**
		 * Get all adjoin point from board <br />
		 * Note: Because this will impact to board status, <br />
		 * so it should be a copy-version to avoid pollute the original one
		 *
		 * @param start
		 *
		 *        the start
		 * @param board the board
		 * @return the list, empty if nothing adjoin
		 */
		public static void adjoinPoint(Point start, Board board,
		        List<Point> points) {
			// Exit condition
			if (board.getPoint(start.getX(), start.getY()) == null) {
				return;
			}
			points.add(start);
			// mark this point false to prevent infinitive loop
			board.set(start.getX(), start.getY(), false);
			Point downSidePoint = board.getPoint(start.getX() + 1,
			        start.getY());
			if (downSidePoint != null) {
				adjoinPoint(downSidePoint, board, points);
			}

			Point upSidePoint = board.getPoint(start.getX() - 1, start.getY());
			if (upSidePoint != null) {
				adjoinPoint(upSidePoint, board, points);
			}
			Point leftSidePoint = board.getPoint(start.getX(),
			        start.getY() - 1);
			if (leftSidePoint != null) {
				adjoinPoint(leftSidePoint, board, points);
			}
			Point rightSidePoint = board.getPoint(start.getX(),
			        start.getY() + 1);
			if (rightSidePoint != null) {
				adjoinPoint(rightSidePoint, board, points);
			}
		}

		/**
		 * Checks if is block move down ok within {@code steps} down.
		 *
		 * @param block the block
		 * @param board the board
		 * @param steps the steps
		 * @return true, if is block move down ok. <br />
		 *         False if the move reach outside the bottom board or overlap
		 *         with other Block
		 */
		public static boolean isBlockMoveDownOk(Block block, Board board,
		        int steps) {
			final int boardX = board.getM();
			// If any points get bottom => false
			boolean anyBottom = block.getPoints()
			        .stream()
			        .anyMatch(point -> point.getX() == boardX - 1);
			if (anyBottom) {
				return false;
			}
			// If any collision with other block when move down => false
			boolean anyCollision = block.getPoints()
			        .stream()
			        .anyMatch(point -> {
				        int newX = point.getX() + steps;
				        int newY = point.getY();
				        // The collision if
				        // The point returned is not empty
				        // And that point does not belong to this block
				        // Otherwise, non collision
				        // NOTE: We do not count the out of border case here !!!
				        Point newPoint = board.getPoint(newX, newY);
				        return (newPoint != null
				                && !block.containPoint(newPoint));
			        });
			// If no collision => Could move
			return !anyCollision;
		}
	}
}
