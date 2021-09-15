package com.example.alg;

import com.example.demo.bean.CategoryVo;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chen Xiao
 * @since 2021-02-25 16:34
 */
public class ABCD2 {
    private static List<String> result = new ArrayList<>();
    private static List<String> filter = new ArrayList<>();


    public static void main(String[] args) {
        String[] target = new String[]{"11", "22", "33", "44"};

        calc(0, target[0], target);
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println(filter);
    }

    //递归
    public static void calc(int length, String itemStr, String... target) {
        for (int i = length; i < target.length; i++) {
            String tempStr = itemStr;

            if (tempStr.split(",").length == target.length) {
                return;
            }
            // debugger
            String str = tempStr + "," + target[i];
            if (result.indexOf(str) == -1 && str.split(",").length == target.length && tempStr.indexOf(target[i]) == -1) {
               /* if (!filter.contains(tempStr.split(",")[0]  +","+tempStr.split(",")[1])){
                    filter.add(tempStr.split(",")[0]  +","+tempStr.split(",")[1]);
                }*/
                result.add(str);
            }
            // debugger
            if (tempStr.indexOf(target[i]) == -1) {
                calc(length + 1, str, target);
            }
        }
    }

    @Test
    public  void c(){

        a();
    }

    public static void a(Object ...a){
        System.out.println(a.length);
//        ArrayList<Object> list = Lists.newArrayList("123",a);
////        list.add(Lists.newArrayList(a));
//        for (Object serializable : list) {
//            System.out.println(serializable);
//
//        }
//        b(list);
    }

    public static void b(Object ... a){

        ArrayList<Object> objects = Lists.newArrayList(a);
        for (Object object : objects) {
            System.out.println(object);
        }
    }

    @Test
    public void testStream(){
        List<CategoryVo> strings = Lists.newArrayList();
        strings.add(new CategoryVo().setNum(6));
        strings.add(new CategoryVo().setNum(4));
        strings.add(new CategoryVo().setNum(2));
        strings.add(new CategoryVo().setNum(3));

        List<CategoryVo> collect = strings.stream().sorted(Comparator.comparing(CategoryVo::getNum).reversed()).collect(Collectors.toList());
        for (CategoryVo categoryVo : collect) {
            System.out.println(categoryVo);
        }
    }

}
