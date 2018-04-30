import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Class Horse. <br />
 * The board is coordinated as (x,y) like
 *
 * <pre>
 * .-> x
 * |        |1|2|3| |
 * y        -1----
 *          -2----
 * </pre>
 */
public class Horse {

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
        int T;

        System.setIn(new FileInputStream("res/horser.txt"));
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();// Number of testcase

        for (int t = 1; t <= T; t++) {

            // Read data from input file
            int N, H;
            N = sc.nextInt();
            H = sc.nextInt();
            // TrinhNX : Update solution 1, using 2-D array
            // Plus 1 so we do not mind the 0 index of array @@
            String[][] board = new String[N + 1][N + 1];
            int[] quanco = new int[(H - 1) * 2];

            int x_Horse = sc.nextInt();
            int y_Horse = sc.nextInt();
            // For visualization, mark the Horse with *
            // We substract by 1 cause the array index starting from 0
            board[x_Horse][y_Horse] = "*";
            for (int i = 0; i < (H - 1) * 2; i++) {
                quanco[i] = sc.nextInt();
                if (i % 2 == 1) {
                    // For visualization, mark other position (non null) as x
                    board[quanco[i - 1]][quanco[i]] = "x";
                }
            }
            // Debug
            printMatrix(board);

            // boolean stop = true;
            int eat = 0;
            // TrinhNX : Idea is that
            // Check if a position near *
            // If near position have x => we do not count the 2 side xa/xb value by set those value
            // as null
            // If near position does not have x => if the xa/ xb have value, count
            // TODO: Wrap checking boundary condition but it is not tight (should check for both x
            // and y), now it is for sake of simplicity
            // Left
            if (x_Horse - 2 > 0) {
                if ("x".equals(board[x_Horse - 1][y_Horse])) {
                    // Skip this side
                } else {
                    // Else check 2 side
                    if ("x".equals(board[x_Horse - 2][y_Horse - 1])) {
                        eat++;
                    }
                    if ("x".equals(board[x_Horse - 2][y_Horse + 1])) {
                        eat++;
                    }

                }
            }
            // Top
            if (y_Horse - 2 > 0) {
                if ("x".equals(board[x_Horse][y_Horse - 1])) {
                    // Skip this side
                } else {
                    // Else check 2 side
                    if ("x".equals(board[x_Horse - 1][y_Horse - 2])) {
                        eat++;
                    }
                    if ("x".equals(board[x_Horse + 1][y_Horse - 2])) {
                        eat++;
                    }

                }
            }
            // Right
            if (x_Horse + 2 <= N) {
                if ("x".equals(board[x_Horse + 1][y_Horse])) {
                    // Skip this side
                } else {
                    // Else check 2 side
                    if ("x".equals(board[x_Horse + 2][y_Horse - 1])) {
                        eat++;
                    }
                    if ("x".equals(board[x_Horse + 2][y_Horse + 1])) {
                        eat++;
                    }

                }
            }
            // Bottom
            if (y_Horse + 2 <= N) {
                if ("x".equals(board[x_Horse][y_Horse + 1])) {
                    // Skip this side
                } else {
                    // Else check 2 side
                    if ("x".equals(board[x_Horse + 1][y_Horse + 2])) {
                        eat++;
                    }
                    if ("x".equals(board[x_Horse - 1][y_Horse + 2])) {
                        eat++;
                    }

                }
            }
            System.out.println("#" + t + " " + eat);
        }

        sc.close();
    }

    /**
     * Print the squarer matrix (size, size) from index 1
     *
     * @param size
     * @param x
     * @param y
     * @param mark
     */
    private static void printMatrix(String[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                System.out.printf("%s ", matrix[i][j] == null ? "0" : matrix[i][j]);
            }
            System.out.println();
        }

    }
}
