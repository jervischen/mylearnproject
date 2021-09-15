package com.example.demo.dynamicprogram;

/**
 * @author Chen Xiao
 * @since 2021-05-13 16:08
 */
public class Tone {

    static int count = 0;

    static int maxRow = 50;

    static int maxCol = 50;

    static int[][] rs = new int[maxRow][maxCol];


    private static int getRoald(int row, int col) {

        if (row == maxRow - 1 && col == maxCol - 1) {
            return 1;
        }


        if (row > maxRow - 1 || col > maxCol - 1) {
            return 0;
        }
        if (rs[row][col] != 0) {
            return rs[row][col];
        }


        return rs[row][col] = getRoald(row + 1, col) + getRoald(row, col + 1);
    }

    public static void main(String[] args) {
        int roald = getRoald(0, 0);
        System.out.println(roald);
        System.out.println(count);
    }

}
