package com.example.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Chen Xiao
 * 两数之和
 * @since 2021-05-26 22:47
 */
public class Sum {

    /**
     * 9-1=8
     * 9-2=7
     * 9-3=6
     * 9-4=5
     * 9-5=4
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
            System.out.println(hashtable);
        }
        return new int[0];
    }

    @Test
    public void linkH(){
        LinkedHashMap<String,String> linkedMap = new LinkedHashMap();
        linkedMap.put("a","a");
        linkedMap.put("c","c");
        linkedMap.put("b","b");
        linkedMap.get("a");
        for (Object value : linkedMap.values()) {
            System.out.println(value);
        }

        HashMap<String,String> map = new HashMap();
        map.put("a","a");
        map.put("c","c");
        map.put("b","b");
        for (Object value : map.values()) {
            System.out.println(value);
        }
    }

    @Test
    public void testTwo(){
        int[] ints = twoSum(new int[]{1, 2, 3, 4, 5}, 9);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}
