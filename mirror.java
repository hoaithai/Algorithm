import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mirror {

    static int       T;
    static final int max = 100;

    public static void main(String[] args) throws FileNotFoundException {
        int size;

        // TODO Auto-generated method stub
        System.setIn(new FileInputStream("res/mirror.txt"));
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();//Number of testcase

        for ( int t =1; t<= T; t++) {
            //int times = 0;
            //read data from input.txt
            size = sc.nextInt();
            int[][] data = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j<size; j++) {
                    data[i][j]= sc.nextInt();
                }

            }
            int hit = 0; // Store the number of hit
            // Store the direction of move
            // If direction = 1 => move to right
            // If direction = 2 => move to left
            // If direction = 3 => move to up
            // If direction = 4 => move to down
            // Other => ignore
            int direction = 1; 
            boolean isContinue = true;
		// The row / column is modified based on the movement
            for (int row = 0; row < size && isContinue;) {
                for (int column = 0; column < size && isContinue; ) {
			// If this reaches borders by column/row => Stop movement
			// NOTE: This assumes the start position always is 0
                    // If this is a 0 => keep moving with direction
               
                    // If this is 1 =>
                        // ok then increase hit by 1
                        // Move to left by 1
                    // If this is 2 =>
                        // ok then increase hit by 1
                        // Move to right by 1
                    // Stop condition
                    if(column <0 || column >= size || row < 0 || row >= size) {
                        isContinue = false;
                    } else {
                        switch(data[row][column]) {
                            case 0:
                                switch(direction){
                                    case 1:
                                        column++;
                                        break;
                                    case 2:
                                        column--;
                                        break;
                                    case 3:
                                        row--;
                                        break;
                                    case 4:
                                        row++;
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Invalid direction " + direction);
                                }
                                break;
                            case 1:
                                direction = 2;
                                column--;
                                hit++;
                                break;
                            case 2:
                                direction = 4;
                                row++;
                                hit++;
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid value " + data[row][column]);
                        }
                    }
                    /**
                    while (true) {
                        switch (huong) {
                            case 1:
                                if (data[row][column] == 2) {
                                    row++;
                                    times++;

                                   // break;
                                } else {
                                    huong = 1;
                                }

                                if (data[row][column] == 1) {
                                    column++;
                                    times++;
                                  //  break;
                                } else {
                                    huong = 1;
                                }

                            case 2:
                                if (data[row][column] == 2) {
                                    column++;
                                    times++;
                                   // break;
                                } else {
                                    huong = 2;
                                }

                                if (data[row][column] == 1) {
                                    column--;
                                    times++;
                                   // break;
                                } else {
                                    huong = 2;
                                }


                            case 3:
                                if (data[row][column] == 2) {
                                    row--;
                                    times++;
                                   // break;
                                } else {
                                    huong = 3;
                                }

                                if (data[row][column] == 1) {
                                    row++;
                                    times++;
                                   // break;
                                } else {
                                    huong = 3;
                                }

                            case 4:
                                if (data[row][column] == 2) {
                                    column++;
                                   // break;
                                } else {
                                    huong = 4;
                                }

                                if (data[row][column] == 1) {
                                    column--;
                                    times++;
                                   // break;
                                } else {
                                    huong = 4;
                                }

                        }

                    }
                    */
                }

            }
            System.out.println("#" + t + " " + hit);
        }

    }
}
