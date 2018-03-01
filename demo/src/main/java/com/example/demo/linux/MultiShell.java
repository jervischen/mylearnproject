package com.example.demo.linux;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created in 2018-02-26 10:41.
 *
 * @author chenxiao
 */
public class MultiShell {
    private static Logger logger = LoggerFactory.getLogger(MultiShell.class);

    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final String HOST = "172.17.10.58";
    private static final int DEFAULT_SSH_PORT = 22;

    public static void main(String[] arg) {

        try {
            JSch jsch = new JSch();

            //从配置文件中加载服务器信息
            Properties serversProp = new Properties();
            InputStream resourceAsStream = OnlineMultiExec.class.getResourceAsStream("/server.properties");
            serversProp.load(resourceAsStream);

            for (Map.Entry<Object, Object> serverProp : serversProp.entrySet()) {

                String name = (String) serverProp.getKey();
                String server = (String) serverProp.getValue();

                System.out.println("Start working on: " + name);
                Session session = jsch.getSession(USER, server, DEFAULT_SSH_PORT);
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

                session.connect();


                Channel channel = session.openChannel("shell");
                System.out.println(channel);

                channel.setInputStream(System.in);

                channel.setOutputStream(System.out);

                channel.connect();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
