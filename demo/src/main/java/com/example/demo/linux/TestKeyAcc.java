package com.example.demo.linux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Properties;

/**
 * Created in 2018-02-27 17:01.
 *
 * @author chenxiao
 */
public class TestKeyAcc {
    private static Logger logger = LoggerFactory.getLogger(TestKeyAcc.class);

    public static void main(String[] arg) {
        String keyFile = "C:\\Users\\Administrator\\Desktop\\mygit\\mylearnproject\\demo\\src\\main\\resources\\chenxiao.pem";
        String user = "chenxiao";
        String host = "210.14.152.199";
        int port = 12330;
        String passphrase = "RXIN7K2uWFeoCjZy";

        Session session = null;
        Channel channel = null;
        try {
            JSch jsch = new JSch();
            jsch.addIdentity(keyFile);
            session = jsch.getSession(user, host, port);
            // username and passphrase will be given via UserInfo interface.
            UserInfo ui = new MyUserInfo(passphrase);
            session.setUserInfo(ui);
            Properties config = new Properties();
            //设置 SSH 连接时不进行公钥确认
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            channel = session.openChannel("shell");
            PipedInputStream pipeIn = new PipedInputStream();
            PipedOutputStream pipeOut = new PipedOutputStream(pipeIn);
            // FileOutputStream fileOut = new FileOutputStream(outputFileName, true);
            channel.setInputStream(pipeIn);
            channel.setOutputStream(System.out);
            channel.connect();

            Thread.sleep(2000);
            pipeOut.write("192.168.80.203\n".getBytes());
            Thread.sleep(2000);
            pipeOut.write("cd //data/logs//lizhi//lz_live_fanslevel_pre//lz_live_fanslevel_pre_idx0\n".getBytes());
            Thread.sleep(2000);
            pipeOut.write("tail -n 50 server.log\n".getBytes());
            Thread.sleep(2000);
            pipeOut.close();
            pipeIn.close();
            System.out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }finally {
            if (channel !=null){
                channel.disconnect();
            }
            if (session !=null){
                session.disconnect();
            }
        }
    }

    public static class MyUserInfo implements UserInfo {
        private String passphrase = null;

        public MyUserInfo(String passphrase) {
            this.passphrase = passphrase;
        }

        @Override
        public String getPassphrase() {
            return passphrase;
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public boolean promptPassphrase(String s) {
            return true;
        }

        @Override
        public boolean promptPassword(String s) {
            return true;
        }

        @Override
        public boolean promptYesNo(String s) {
            return true;
        }

        @Override
        public void showMessage(String s) {
            System.out.println(s);
        }
    }
}
