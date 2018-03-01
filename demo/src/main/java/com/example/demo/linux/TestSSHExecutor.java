package com.example.demo.linux;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2018-02-27 19:14.
 *
 * @author chenxiao
 */
public class TestSSHExecutor {
    private static Logger logger = LoggerFactory.getLogger(TestSSHExecutor.class);

    @Test
    public void testSSH() throws Exception{
        SSHExecutor ssh =  SSHExecutor.newInstance();
        System.out.println("================");
        long shell1 = ssh.shell("ls\n","D:\\lzgit\\demo\\src\\main\\resources\\shell1.txt");
        long shell2 = ssh.shell("pwd\n","D:\\lzgit\\demo\\src\\main\\resources\\shell2.txt");
        System.out.println("shell 1 执行了"+shell1+"ms");
        System.out.println("shell 2 执行了"+shell2+"ms");
        System.out.println("================");
        int cmd1 = ssh.exec("ls\n");
        ssh.close();
    }

    @Test
    public void testSSHKey()throws Exception{
        SSHExecutor ssh =  SSHExecutor.newInstanceKey();
        System.out.println("================");
        long shell2 = ssh.shell("1\n","D:\\lzgit\\demo\\src\\main\\resources\\shell2.txt");
        System.out.println("shell 2 执行了"+shell2+"ms");
        System.out.println("================");
        int cmd1 = ssh.exec("ls\n");
        ssh.close();
    }
}
