package com.example.alg;

/**
 * @author Chen Xiao
 * @since 2021-02-25 09:56
 */
public class Eight {

    private static int total = 0;
    private static int n = 8;

    private static int c[] = new int[n];

    public static void main(String[] args) {
        queen(0);
        System.out.println(total);
    }

    static void queen(int row) {
        if (row == n) {
            total++;
        } else {
            for (int col = 0; col != n; col++) {
                c[row] = col;
                if (isOk(row)) {
                    queen(row + 1);
                }
            }
        }
    }

    static boolean isOk(int row) {
        for (int j = 0; j != row; j++) {
            if (c[row] == c[j] || row - c[row] == j - c[j] || row + c[row] == j + c[j]) {
                return false;
            }
        }
        return true;
    }
}
