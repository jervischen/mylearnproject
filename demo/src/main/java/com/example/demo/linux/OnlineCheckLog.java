package com.example.demo.linux;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created in 2018-02-26 17:06.
 * 查询线上日志
 *
 * @author chenxiao
 */
public class OnlineCheckLog {
    private static Logger logger = LoggerFactory.getLogger(OnlineCheckLog.class);

    /**
     * 用户名
     */
    private static final String USER = "chenxiao";
    /**
     * 密码
     */
    private static final String PASSWORD = "RXIN7K2uWFeoCjZy";
    /**
     * 跳板机地址
     */
    private static final String SERVER = "210.14.152.199";
    private static final int PORT = 12330;
    /**
     * 秘钥
     */
    private static final String keyFile = "C:\\Users\\Administrator\\Desktop\\mygit\\mylearnproject\\demo\\src\\main\\resources\\chenxiao.pem";


    public static void main(String[] args) throws Exception {
        //从配置文件中加载服务器信息
        Properties serversProp = new Properties();
        InputStream resourceAsStream = OnlineCheckLog.class.getResourceAsStream("/onlineserver.properties");
        serversProp.load(resourceAsStream);

        for (Map.Entry<Object, Object> serverProp : serversProp.entrySet()) {
            String name = (String) serverProp.getKey();
            String server = (String) serverProp.getValue();


            if (name.split("&&").length < 2) {
                System.err.println("请将项目名称和机器名用&&隔开");
                break;
            }

            //执行的命令
            String dir = name.split("&&")[0];


            String command = "tail -f";
            List<String> commandList = new ArrayList<>();
            //进入项目的日志文件目录
            commandList.add(String.format("cd /data/logs/lizhi/%s/%s_idx0/",dir, dir));
            //执行查看日志命令
            commandList.add("tail -f server.log |grep 'received' ");


            new OnlineCheckLog().new LinuxShell(name, server, commandList).start();
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
        List<String> commandList;

        public LinuxShell(String name, String server, List<String> commandList) {
            this.name = name;
            this.server = server;
            this.commandList = commandList;
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
                for (String command : commandList) {
                    pipeOut.write((command + "\n").getBytes());
                }

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
