package com.example.demo;

import java.util.Random;

/**
 * @author Chen Xiao
 * @since 2020-03-06 16:15
 */
public class InvitationCodeUtil {

    private static final char STUFFS[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            '1', '2', '3', '4', '5', '6', '7', '8'};

    private static final int LEN = 6;

    private static char[] combination() {
        char[] chars = new char[LEN];

        for (int i = 0; i < LEN; i++) {
            chars[i] = STUFFS[new Random().nextInt(STUFFS.length)];
        }

        return chars;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            System.out.println(combination());
        }
    }
}
