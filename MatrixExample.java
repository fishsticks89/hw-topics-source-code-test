import java.util.Random;
import java.util.Scanner;

public class MatrixExample {
    static void assertEQ(int[][] recieved, int[][] actual) {
        for (int i = 0; i < recieved.length; i++) {
            for (int j = 0; j < recieved[i].length; j++) {
                if (recieved[i][j] != actual[i][j]) {
                    printMatrix(recieved);
                    throw new Error("Test failed " + recieved[i][j] + " != " + actual[i][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        { // provided test
            int[][] matrix = {
                    { 1, 2, 3, 4, 5, 6 },
                    { 4, 5, 6, 3, 7, 2 },
                    { 27, 8, 9, 5, 3, 21 },
                    { 73, 2, 19, 5, 1, 8 },
                    { 47, 9, 9, 5, 0, 22 },
                    { 78, 86, 1, 4, 1, 21 },
                    { 73, 18, 2, 2, 5, 11 }
            };

            int numRows = 6;
            int numCols = 7;

            int[][] matrix2 = generateRandomMatrix(numRows, numCols);
            int[][] result = multiplyMatrices(matrix, matrix2);

            System.out.println("result length: " + result.length + " x " +
                    result[0].length);
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        }
        { // mult test 1
            int[][] mult_matrix_0 = {
                    { 1, 2, 3 },
                    { 4, 5, 6 },
                    { 7, 8, 9 }
            };
            int[][] mult_matrix_1 = {
                    { 9, 8, 7 },
                    { 6, 5, 4 },
                    { 3, 2, 1 }
            };
            int[][] expected_result_matrix = {
                    { 30, 24, 18 },
                    { 84, 69, 54 },
                    { 138, 114, 90 }
            };
            int[][] result_matrix = multiplyMatrices(mult_matrix_0, mult_matrix_1);
            assertEQ(result_matrix, expected_result_matrix);
        }
        { // mult test 2
            int[][] mult_matrix_0 = {
                    { 2, 4, 6 },
                    { 8, 10, 12 },
                    { 14, 16, 18 }
            };
            int[][] mult_matrix_1 = {
                    { 1, 3, 5 },
                    { 7, 9, 11 },
                    { 13, 15, 17 }
            };
            int[][] expected_result_matrix = {
                    { 108, 132, 156 },
                    { 234, 294, 354 },
                    { 360, 456, 552 }
            };
            int[][] result_matrix = multiplyMatrices(mult_matrix_0, mult_matrix_1);
            assertEQ(result_matrix, expected_result_matrix);
        }
        { // mult test 3
            int[][] mult_matrix_0 = {
                    { 3, 6, 9 },
                    { 12, 15, 18 },
                    { 21, 24, 27 }
            };
            int[][] mult_matrix_1 = {
                    { 2, 4, 6 },
                    { 8, 10, 12 },
                    { 14, 16, 18 }
            };
            int[][] expected_result_matrix = {
                    { 180, 216, 252 },
                    { 396, 486, 576 },
                    { 612, 756, 900 }
            };
            int[][] result_matrix = multiplyMatrices(mult_matrix_0, mult_matrix_1);
            assertEQ(result_matrix, expected_result_matrix);
        }
        { // rand tests
            var matrix_0 = generateRandomMatrix(3, 3);
            var matrix_1 = generateRandomMatrix(8, 8);
            var matrix_2 = generateRandomMatrix(5, 3);

            System.out.println("Matrix 0 (3x3):");
            printMatrix(matrix_0);
            System.out.println("Matrix 1 (8x8):");
            printMatrix(matrix_1);
            System.out.println("Matrix 2 (5x3):");
            printMatrix(matrix_2);

            System.out.println();
            // ask for y/n
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Does this look random (y/n)?");
                String response = scanner.nextLine();
                if (response.equals("n")) {
                    throw new Error("Test failed");
                } else if (response.equals("y")) {
                    break;
                }
            }
            scanner.close();
        }
        System.out.println("All tests passed!");
        System.out.println("yippee");
    }

    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        if (cols1 != rows2) {
            throw new IllegalArgumentException(
                    "Number of columns in the first matrix must be equal to the number of rows in the second matrix.");
        }

        // Some more issues here too
        int[][] result = new int[rows1][cols2];

        // Lots of issues with this code, it used to be working perfectly though
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    public static int[][] generateRandomMatrix(int numRows, int numCols) {
        int[][] matrix = new int[numRows][numCols];
        Random random = new Random();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = random.nextInt(100); // Generates random values between 0 and 99
            }
        }

        return matrix;
    }

}
