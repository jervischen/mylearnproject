package com.example.alg;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Xiao
 * @since 2020-11-02 20:01
 */
public class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || k > n) {
            return res;
        }

        List<Integer> c = new ArrayList<>();
        generateCombinations(n, k, 1, c);
        return res;

    }

    /**
     * 回溯求所有组合结果
     * @param n
     * @param k
     * @param start 开始搜索新元素的位置
     * @param c 当前已经找到的组合
     */
    private void generateCombinations(int n,int k,int start,List<Integer> c) {
        if (c.size() == k) {
            //这里需要注意java的值传递
            //此处必须使用重新创建对象的形式，否则 res 列表中存放的都是同一个引用
            res.add(new ArrayList<>(c));
            return;
        }

        //通过终止条件，进行剪枝优化，避免无效的递归
        //c中还剩 k - c.size()个空位，所以[ i ... n]中至少要有k-c.size()个元素
        //所以i最多为 n - (k - c.size()) + 1
        for(int i = start;i <= n - (k - c.size()) + 1; i++) {
            c.add(i);
            generateCombinations(n, k, i + 1, c);
            //记得回溯状态啊
            c.remove(c.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combine(4,4));

        Object[] obs = new Object[]{"A",2};
        System.out.println(Joiner.on(":").join(obs));
        join("C","D",obs);
        join("C","D", Lists.newArrayList(1,2));


        System.out.println(new Solution().getClass().getSimpleName());

        String join = Joiner.on("_").skipNulls().join(1, 2, 3);
        System.out.println(join);
        System.out.println("{\"level3\":{\"coins\":160,\"award\":\"【心动大作战】\n恭喜#user1与#user2 心动值达1600点，获得蜜恋爱人称号与心动宝箱直购权1次！ 心动宝箱最高可开出33440金币甜蜜时刻情人节限定新礼物哦！心动宝箱直购链接↓↓↓\"}}");

    }

    private static void join(Object... obs){
        System.out.println(Joiner.on(":").join(obs));
    }
}
