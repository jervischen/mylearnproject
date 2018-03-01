package com.example.demo.linux;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created in 2018-02-27 17:01.
 *
 * @author chenxiao
 */
public class TestKeyAcc2 {
    private static Logger logger = LoggerFactory.getLogger(TestKeyAcc2.class);

    public static void main(String[] arg) {
        String keyFile = "D:\\lzgit\\demo\\src\\main\\resources\\chenxiao.pem";
        String user = "chenxiao";
        String host = "210.14.152.199";
        int port = 12330;
        String passphrase = "RXIN7K2uWFeoCjZy";
        try {
            JSch jsch = new JSch();
            jsch.addIdentity(keyFile);
            Session session = jsch.getSession(user, host, port);
            // username and passphrase will be given via UserInfo interface.
            UserInfo ui = new MyUserInfo(passphrase);
            session.setUserInfo(ui);
            session.connect();

            Channel channel = session.openChannel("shell");


            channel.connect();


/*
            Thread.sleep(2000);
            out.write("192.168.80.203\n");
            Thread.sleep(2000);
            out.write("cd //data/logs//lizhi//lz_live_fanslevel_pre//lz_live_fanslevel_pre_idx0\n");

            Thread.sleep(2000);
            out.write("tail -n 50 server.log\n");
            Thread.sleep(2000);


            in.close();
            out.close();
            channel.disconnect();
            session.disconnect();*/
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static class MyUserInfo implements UserInfo {
        private String passphrase = null;

        public MyUserInfo(String passphrase) {
            this.passphrase = passphrase;
        }

        public String getPassphrase() {
            return passphrase;
        }

        public String getPassword() {
            return null;
        }

        public boolean promptPassphrase(String s) {
            return true;
        }

        public boolean promptPassword(String s) {
            return true;
        }

        public boolean promptYesNo(String s) {
            return true;
        }

        public void showMessage(String s) {
            System.out.println(s);
        }
    }
}
