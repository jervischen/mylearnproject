package javaagent;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * @author Chen Xiao
 * @since 2021-09-13 16:15
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        // get pid
        String pid = name.split("@")[0];
        System.out.println("Pid:" + pid);


        a();
    }

    private static void a() throws InterruptedException {
        while (true) {
            Thread.sleep(2000);
            System.out.println("Hello World2223");
        }
    }
}
