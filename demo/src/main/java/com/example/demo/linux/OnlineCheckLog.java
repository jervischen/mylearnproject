package com.example.demo.linux;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created in 2018-02-26 17:06.
 *
 * @author chenxiao
 */
public class OnlineCheckLog {
    private static Logger logger = LoggerFactory.getLogger(OnlineCheckLog.class);

    private static final String USER = "chenxiao";
    private static final String PASSWORD = "RXIN7K2uWFeoCjZy";
    private static final String SERVER = "210.14.152.199";
    private static final int PORT = 12330;
    private static final String keyFile = "C:\\Users\\Administrator\\Desktop\\mygit\\mylearnproject\\demo\\src\\main\\resources\\chenxiao.pem";

    public static void main(String[] args) throws Exception {
        //从配置文件中加载服务器信息
        Properties serversProp = new Properties();
        InputStream resourceAsStream = OnlineCheckLog.class.getResourceAsStream("/onlineserver.properties");
        serversProp.load(resourceAsStream);

        for (Map.Entry<Object, Object> serverProp : serversProp.entrySet()) {
            String name = (String) serverProp.getKey();
            String server = (String) serverProp.getValue();

            new OnlineCheckLog().new LinuxShell(name, server).start();
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

    class LinuxShell extends Thread {
        String name;
        String server;

        public LinuxShell(String name, String server) {
            this.name = name;
            this.server = server;
        }

        @Override
        public void run() {
            Session session = null;
            ChannelShell channel = null;
            try {
                JSch sshSingleton = new JSch();
                sshSingleton.addIdentity(keyFile);

                session = sshSingleton.getSession(USER, SERVER, PORT);
                UserInfo ui = new MyUserInfo(PASSWORD);
                session.setUserInfo(ui);
                Properties config = new Properties();
                //设置 SSH 连接时不进行公钥确认
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.connect();
                //打开命令执行管道
                channel = (ChannelShell) session.openChannel("shell");
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        channel.getInputStream()));

                PipedInputStream pipeIn = new PipedInputStream();
                PipedOutputStream pipeOut = new PipedOutputStream(pipeIn);
                channel.setInputStream(pipeIn);
                channel.connect();

                //发送linux命令
                pipeOut.write((server + "\n").getBytes());

                pipeOut.write("tail -f /data/logs/lizhi/lz_live_fanslevel_pre/lz_live_fanslevel_pre_idx0/server.log\n".getBytes());

                //读取命令输出信息
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(name + "=====>  " + msg);
                }
                in.close();
                pipeOut.close();
                pipeIn.close();
            } catch (Exception e) {
                System.out.println(name + "连接异常....");
                e.printStackTrace();
            } finally {
                if (channel != null) {
                    channel.disconnect();
                }
                if (session != null) {
                    session.disconnect();
                }
            }
        }
    }
}
