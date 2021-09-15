package com.example.classload;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Chen Xiao
 * @since 2021-08-10 22:18
 */
public class DifferentClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream stream = getClass().getResourceAsStream(fileName);
                if (stream == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[stream.available()];
                    // 将流写入字节数组b中
                    stream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };
        Object obj = classLoader.loadClass("com.example.classload.DifferentClassLoaderTest").newInstance();
//        DifferentClassLoaderTest obj1 = (DifferentClassLoaderTest) classLoader.loadClass("jvm.DifferentClassLoaderTest").newInstance();

        System.out.println(obj.getClass());
        System.out.println(obj instanceof DifferentClassLoaderTest);
        DifferentClassLoaderTest test = new DifferentClassLoaderTest();
        System.out.println(test.getClass().getClassLoader());
    }
}
