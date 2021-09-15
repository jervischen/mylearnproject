package com.example.alg;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chen Xiao
 * @since 2020-11-02 20:25
 */
public class ABCD {
    private static List<String> result = new ArrayList<>();
    private static List<String> result2 = new ArrayList<>();
    private static List<Integer> filter = new ArrayList<>();

    public static void main(String[] args) {
        String[] target = new String[]{"a", "b", "c", "d"};
        for (String s : target) {
            calc(0, s, target);
        }

        System.out.println(Arrays.toString(result.toArray()));

        for (String s : result) {
            calc2(0, s, result);
        }
        System.out.println(Arrays.toString(result2.toArray()));
    }

    private static void calc2(int length, String element, List<String> result) {
        for (int i = length; i < result.size(); i++) {
            String tempStr = element;
            if (!result2.contains(element + result.get(i)) && result2.size() < 3 && isOk2(element, result.get(i))) {
                result2.add(element + result.get(i));
            }
            calc2(length + 1, tempStr, result);
        }
    }

    private static boolean isOk2(String element, String val) {
        char[] chars = val.toCharArray();
        for (char aChar : chars) {
            if (element.contains(String.valueOf(aChar))) {
                return false;
            }
        }
        return true;
    }


    //递归
    public static void calc(int length, String p, String... target) {
        for (int i = length; i < target.length; i++) {
            String p1 = p;
            p = p + target[i];
            if (length == target.length) {
                endEle(p);
            }
            if (p.length() == 2) {
                if (isOK(p)) {
                    result.add(p);
                }
//                p = "";
            }
            calc(length + 1, p1, target);
        }
    }

    private static void endEle(String p) {

    }

    private static boolean isOK(String p) {
        char[] chars = p.toCharArray();
        if (chars[0] == chars[1] || result.contains(p) || result.size() == 6) {
            return false;
        }
        return true;
    }
}
