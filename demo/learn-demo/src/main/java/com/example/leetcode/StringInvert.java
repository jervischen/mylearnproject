package com.example.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Chen Xiao
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * <p>
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * <p>
 * 注意，您的结果中 不应 包含任何括号。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2021-05-26 15:54
 */
public class StringInvert {

    public static void main(String[] args) {
//        LinkedList<String> stack = new LinkedList<String>();
//        stack.push("a");
//        stack.push("b");
//        stack.push("c");
//        System.out.println(stack.getFirst());
//        System.out.println(stack.getLast());
//
//        System.out.println(stack.size());
//
//        System.out.println(stack.poll());//队列方法，队首
//        System.out.println(stack.pop());//栈方法 ，栈顶
//        System.out.println(stack.size());

//        System.out.println(stack("(1(23)4)"));


//        StringBuffer sb = new StringBuffer();
//        sb.append("ab");
////        sb.append("c");
////        sb.insert(1,"123");
//        System.out.println(sb.toString());

//        stack.add("1");
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());

//        HashSet<Character> a = new HashSet<>();
//        a.add('a');
//
//        "abc".charAt(1);
//        String bb = new String();
//
//
//        for (int i=0;i< 5;i++){
//            System.out.print(i);
//            System.out.print(" ");
//        }
//
//        System.out.println(" ");
//        for (int i=0;i< 5;++i){
//            System.out.print(i);
//            System.out.print(" ");
//        }


        System.out.println("01234".substring(0, 1));
    }


    @Test
    public void testQueue() {
        System.out.println("字符串反转：" + queue("(123)"));

        //先进先出
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");

        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue);
    }

    /**
     * 队列 先进先出 FIFO
     *
     * @param s
     * @return
     */
    public static String queue(String s) {
        LinkedBlockingQueue<String> stack = new LinkedBlockingQueue<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.offer(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.peek());
                sb.insert(0, stack.poll());
                System.out.println(sb);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * 栈 先进后出  FILO
     *
     * @param s
     * @return
     */
    public static String stack(String s) {
        Deque<String> stack = new LinkedList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
                System.out.println(sb);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * 双端队列
     *
     * @param s
     * @return
     */
    public static String deque(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        Deque<Character> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == ')') {
                StringBuilder path = new StringBuilder();
                while (!d.isEmpty()) {
                    if (d.peekLast() != '(') {
                        path.append(d.pollLast());
                    } else {
                        d.pollLast();
                        for (int j = 0; j < path.length(); j++) {
                            d.addLast(path.charAt(j));
                        }
                        break;
                    }
                }
            } else {
                d.addLast(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!d.isEmpty()) sb.append(d.pollFirst());
        return sb.toString();
    }

    @Test
    public void testdeque() {
        System.out.println(deque("(abc)"));
        Deque<Character> d = new ArrayDeque<>();
        d.addLast('a');
        d.addLast('b');
        d.addLast('c');
        System.out.println(d);
        System.out.println("最后一个元素：" + d.pollLast());
        System.out.println("最后一个元素：" + d.pollFirst());


    }


    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; i++) {
                if (nums[i] + nums[j] == target) {
                    System.out.println(String.format("[%d,%d]", i, j));
                    return new int[]{i, j};
                }
            }
        }

        return new int[0];
    }


    /**
     * 链表求和
     * https://leetcode-cn.com/problems/add-two-numbers/submissions/
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
//        if (carry > 0) {
//            tail.next = new ListNode(carry);
//        }
        return head;
    }

    @Test
    public void testAddTwoNumbers() {

        ListNode l1 = new ListNode(6);
        ListNode a = new ListNode(5);
        ListNode b = new ListNode(5);
        l1.next = a;
        a.next = b;
        ListNode l2 = new ListNode(6);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(2);
        l2.next = c;
        c.next = d;
        ListNode listNode = addTwoNumbers(l1, l2);

        while (l1 != null) {
            System.out.print(l1.val);
            System.out.print(",");
            l1 = l1.next;
        }
        System.out.println();
        while (l2 != null) {
            System.out.print(l2.val);
            System.out.print(",");
            l2 = l2.next;
        }
        System.out.println();
        while (listNode != null) {
            System.out.print(listNode.val);
            System.out.print(",");
            listNode = listNode.next;
        }
        System.out.println();
    }

    /**
     * 无重复字符的最长子串
     *
     * @param s pwwkew abcabcbb
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    @Test
    public void testA() {
        System.out.println(longestPalindrome("babadcabbac"));
//        System.out.println(longestPalindrome("babad"));
    }

    public static String longestPalindrome(String s) {
        HashMap<Integer, int[]> reMap = new HashMap<>();
        String str = "";
        int[] res = new int[2];
        int anser = 0;
        int len = s.length();
        for (int start = 0; start < len; start++) {
            for (int end = start + 1; end < len; end++) {
                if (s.charAt(start) == s.charAt(end)) {
                    if (isEques(s.substring(start, end + 1))) {
                        if (s.substring(start, end + 1).length() > 1) {
                            System.out.println(s.substring(start, end + 1));
                        }
                    }
                }
            }
        }

        return "";
    }

    private static boolean isEques(String str) {
        char[] chars = str.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }

            i = i + 1;
            j = j - 1;
        }

        return true;
    }

}
