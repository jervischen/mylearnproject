package com.example;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sentinellearn.A;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Chen Xiao
 * @since 2021-07-29 10:58
 */
public class Test {

    private static Cache<String, Boolean> newDeviceCache = CacheBuilder.newBuilder().maximumSize(1000).build();

    public static void main(String[] args) throws InterruptedException, IOException {
//        List<String> list = Splitter.on("\n").splitToList("1122");
//
//        System.out.println("12\\n3");
//
//        List<String> list1 = null;
//
//        System.out.println(JSONObject.toJSON(null));
//
//        synchronized (Test.class){
//            Test.class.wait();
//        }


//        List<Object> collect1 = Lists.newArrayList().stream().sorted().collect(Collectors.toList());
//
//        ArrayList<Integer> integers = Lists.newArrayList();
//        System.out.println(integers);
//        List<Integer> collect = integers.stream().map(n -> n * 2).collect(Collectors.toList());
//        System.out.println(collect.isEmpty());
//
//
//        double f = 111231.5585;
//        BigDecimal b = new BigDecimal(f);
//        int i = b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
//        System.out.println(i);
//
//
//        String originalFilename = "1111.img";
//        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
//        System.out.println(suffixName);

        A a = new A();

        while (true){
            a.a();
            LockSupport.parkNanos(1000_000 * 10000);
        }


        //
//        new CreatePerson();
//        System.in.read();
    }
}
