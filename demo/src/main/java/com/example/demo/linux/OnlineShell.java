package com.example.demo.linux;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created in 2018-02-26 10:41.
 *
 * @author chenxiao
 */
public class OnlineShell {
    private static Logger logger = LoggerFactory.getLogger(OnlineShell.class);

    private static final String USER = "chenxiao";
    private static final String PASSWORD = "RXIN7K2uWFeoCjZy";
    private static final String HOST = "210.14.152.199";
    private static final int DEFAULT_SSH_PORT = 12330;

    public static void main(String[] arg) {

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(USER, HOST, DEFAULT_SSH_PORT);
            session.setPassword(PASSWORD);

            UserInfo userInfo = new UserInfo() {
                @Override
                public String getPassphrase() {
                    System.out.println("getPassphrase");
                    return null;
                }

                @Override
                public String getPassword() {
                    System.out.println("getPassword");
                    return null;
                }

                @Override
                public boolean promptPassword(String s) {
                    System.out.println("promptPassword:" + s);
                    return false;
                }

                @Override
                public boolean promptPassphrase(String s) {
                    System.out.println("promptPassphrase:" + s);
                    return false;
                }

                @Override
                public boolean promptYesNo(String s) {
                    System.out.println("promptYesNo:" + s);
                    //notice here!
                    return true;
                }

                @Override
                public void showMessage(String s) {
                    System.out.println("showMessage:" + s);
                }
            };

            session.setUserInfo(userInfo);
            Properties config = new Properties();
            //设置 SSH 连接时不进行公钥确认
            config.put("StrictHostKeyChecking", "yes");
            session.setConfig(config);
            session.connect();

            // making a connection with timeout.
            session.connect(30000);

            Channel channel = session.openChannel("shell");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
