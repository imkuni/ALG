import java.util.ArrayList;
import java.util.Scanner;

//Ibrahim Mert Küni
//16253801
//14/05/2019
public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter number of rows in A: ");
        int rowsInA = s.nextInt();
        System.out.print("Enter number of columns in A / rows in B: ");
        int columnsInA = s.nextInt();
        System.out.print("Enter number of columns in B: ");
        int columnsInB = s.nextInt();

        int[][] first = new int[rowsInA][columnsInA];
        int[][] second = new int[columnsInA][columnsInB];
        //Matrix A
        System.out.println("Enter matrix A");
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[0].length; j++) {
                first[i][j] = s.nextInt();
            }
        }
        //Matrix B
        System.out.println("Enter matrix B");
        for (int i = 0; i < second.length; i++) {
            for (int j = 0; j < second[0].length; j++) {
                second[i][j] = s.nextInt();
            }
        }
        //2x2 - 2x2 - 2x2 - 2x2 - 2x2 - 2x2 - 2x2 - 2x2 - 2x2 - 2x2 - 2x2 - 2x2 - 2x2 - 2x2 - 2x2
        if(rowsInA==2 && columnsInA==2 && columnsInB==2){
            int[][] c = classic(first, second);
            print(c);

        }
        //else 3x3 4x4 vs vs vs -----------------------------------------------------------------
        else{
            System.out.println("daa");
            ArrayList<ArrayList<Integer>> twoDimArrayListFirst = arrayToArraylist(first);
            ArrayList<ArrayList<Integer>> twoDimArrayListSecond = arrayToArraylist(second);

            int[][] c = strassen(twoDimArrayListFirst, twoDimArrayListSecond);
            print(c);
        }
    }

    private static  ArrayList<ArrayList<Integer>> arrayToArraylist(int[][] array){
        ArrayList<ArrayList<Integer>> twoDimArrayList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < array[0].length; i++) {
            ArrayList<Integer> intArrayList = new ArrayList<Integer>();
            for (int var : array[i]) {
                intArrayList.add(var);
            }
            twoDimArrayList.add(intArrayList);
        }
        return twoDimArrayList;
    }
    private static void print(int[][] c){
        //print
        System.out.println("Product of A and B is");
        for (int[] aC : c) {
            for (int j = 0; j < c[0].length; j++) {
                System.out.print(aC[j] + " ");
            }
            System.out.println();
        }
    }
    //classic//classic//classic//classic//classic//classic//classic//classic//classic//classic//classic//classic
    private static int[][] classic(int[][] first, int[][] second) {
        int rowsInA = first.length;
        int columnsInA = first[0].length; // same as rows in B
        int columnsInB = second[0].length;
        int[][] result = new int[rowsInA][columnsInB];
        for (int i = 0; i < rowsInA; i++) {
            for (int j = 0; j < columnsInB; j++) {
                for (int k = 0; k < columnsInA; k++) {
                    result[i][j] = result[i][j] + first[i][k] * second[k][j];
                }
            }
        }
        return result;
    }
    //strassen//strassen//strassen//strassen//strassen//strassen//strassen//strassen//strassen//strassen//strassen
    static int LEAF_SIZE = 1;
    private static int[][] ikjAlgorithm(int[][] A, int[][] B) {
        int n = A.length;

        // initialise C
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    private static int nextPowerOfTwo(int n) {
        int log2 = (int) Math.ceil(Math.log(n) / Math.log(2));
        return (int) Math.pow(2, log2);
    }

    public static int[][] strassen(ArrayList<ArrayList<Integer>> A,
                                   ArrayList<ArrayList<Integer>> B) {
        // Make the matrices bigger so that you can apply the strassen
        // algorithm recursively without having to deal with odd
        // matrix sizes
        int n = A.size();
        int m = nextPowerOfTwo(n);
        int[][] APrep = new int[m][m];
        int[][] BPrep = new int[m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                APrep[i][j] = A.get(i).get(j);
                BPrep[i][j] = B.get(i).get(j);
            }
        }

        int[][] CPrep = strassenR(APrep, BPrep);
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = CPrep[i][j];
            }
        }
        return C;
    }

    private static int[][] strassenR(int[][] A, int[][] B) {
        int n = A.length;

        if (n <= LEAF_SIZE) {
            return ikjAlgorithm(A, B);
        } else {
            // initializing the new sub-matrices
            int newSize = n / 2;
            int[][] a11 = new int[newSize][newSize];
            int[][] a12 = new int[newSize][newSize];
            int[][] a21 = new int[newSize][newSize];
            int[][] a22 = new int[newSize][newSize];

            int[][] b11 = new int[newSize][newSize];
            int[][] b12 = new int[newSize][newSize];
            int[][] b21 = new int[newSize][newSize];
            int[][] b22 = new int[newSize][newSize];

            int[][] aResult = new int[newSize][newSize];
            int[][] bResult = new int[newSize][newSize];

            // dividing the matrices in 4 sub-matrices:
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    a11[i][j] = A[i][j]; // top left
                    a12[i][j] = A[i][j + newSize]; // top right
                    a21[i][j] = A[i + newSize][j]; // bottom left
                    a22[i][j] = A[i + newSize][j + newSize]; // bottom right

                    b11[i][j] = B[i][j]; // top left
                    b12[i][j] = B[i][j + newSize]; // top right
                    b21[i][j] = B[i + newSize][j]; // bottom left
                    b22[i][j] = B[i + newSize][j + newSize]; // bottom right
                }
            }

            // Calculating p1 to p7:
            aResult = add(a11, a22);
            bResult = add(b11, b22);
            int[][] p1 = strassenR(aResult, bResult);
            // p1 = (a11+a22) * (b11+b22)

            aResult = add(a21, a22); // a21 + a22
            int[][] p2 = strassenR(aResult, b11); // p2 = (a21+a22) * (b11)

            bResult = subtract(b12, b22); // b12 - b22
            int[][] p3 = strassenR(a11, bResult);
            // p3 = (a11) * (b12 - b22)

            bResult = subtract(b21, b11); // b21 - b11
            int[][] p4 = strassenR(a22, bResult);
            // p4 = (a22) * (b21 - b11)

            aResult = add(a11, a12); // a11 + a12
            int[][] p5 = strassenR(aResult, b22);
            // p5 = (a11+a12) * (b22)

            aResult = subtract(a21, a11); // a21 - a11
            bResult = add(b11, b12); // b11 + b12
            int[][] p6 = strassenR(aResult, bResult);
            // p6 = (a21-a11) * (b11+b12)

            aResult = subtract(a12, a22); // a12 - a22
            bResult = add(b21, b22); // b21 + b22
            int[][] p7 = strassenR(aResult, bResult);
            // p7 = (a12-a22) * (b21+b22)

            // calculating c21, c21, c11 e c22:
            int[][] c12 = add(p3, p5); // c12 = p3 + p5
            int[][] c21 = add(p2, p4); // c21 = p2 + p4

            aResult = add(p1, p4); // p1 + p4
            bResult = add(aResult, p7); // p1 + p4 + p7
            int[][] c11 = subtract(bResult, p5);
            // c11 = p1 + p4 - p5 + p7

            aResult = add(p1, p3); // p1 + p3
            bResult = add(aResult, p6); // p1 + p3 + p6
            int[][] c22 = subtract(bResult, p2);
            // c22 = p1 + p3 - p2 + p6

            // Grouping the results obtained in a single matrix:
            int[][] C = new int[n][n];
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    C[i][j] = c11[i][j];
                    C[i][j + newSize] = c12[i][j];
                    C[i + newSize][j] = c21[i][j];
                    C[i + newSize][j + newSize] = c22[i][j];
                }
            }
            return C;
        }
    }









}


