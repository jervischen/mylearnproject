package com.example.leetcode;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

/**
 * @author Chen Xiao
 * 最长回文子字符串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/bao-li-zhong-xin-kuo-san-dong-tai-gui-hu-qdvv/
 * @since 2021-05-29 10:49
 */
public class LongestPalindrome {


    public static void main(String[] args) {
//        System.out.println(zxks("abba"));
        System.out.println(zxks("aba"));
    }
    /**
     * 中心扩散法
     * @param s
     * @return
     */
    static String zxks(String s) {
        //边界条件判断
        if (s.length() < 2)
            return s;
        //start表示最长回文串开始的位置，
        //maxLen表示最长回文串的长度
        int start = 0, maxLen = 0;
        int length = s.length();
        for (int i = 0; i < length; ) {
            //如果剩余子串长度小于目前查找到的最长回文子串的长度，直接终止循环
            // （因为即使他是回文子串，也不是最长的，所以直接终止循环，不再判断）
            if (length - i <= maxLen / 2)
                break;
            int left = i, right = i;
            while (right < length - 1 && s.charAt(right + 1) == s.charAt(right))
                ++right; //过滤掉重复的
            //下次在判断的时候从重复的下一个字符开始判断
            i = right + 1;
            //然后往两边判断，找出回文子串的长度
            while (right < length - 1 && left > 0 && s.charAt(right + 1) == s.charAt(left - 1)) {
                ++right;
                --left;
            }
            //保留最长的
            if (right - left + 1 > maxLen) {
                start = left;
                maxLen = right - left + 1;
            }
        }
        //截取回文子串
        return s.substring(start, start + maxLen);
    }


    /**
     * 暴利破解
     * @param s
     * @return
     */
    public String blpj(String s) {
        if (s.length() < 2)
            return s;
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i; j < s.length(); j++) {
                //截取所有子串，然后在逐个判断是否是回文的
                if (isPalindrome(s, i, j)) {
                    if (maxLen < j - i + 1) {
                        start = i;
                        maxLen = j - i + 1;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    //判断是否是回文串
    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }

}
