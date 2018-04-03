package com.example.demo.linux;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

/**
 * Created in 2018-03-02 10:40.
 * 查询docker环境是否正常
 * @author chenxiao
 */
public class DockerCheckAlive {
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final int DEFAULT_SSH_PORT = 22;

    public static void main(String[] args) throws Exception {
        //从配置文件中加载服务器信息
        Properties serversProp = new Properties();
        InputStream resourceAsStream = DockerCheckAlive.class.getResourceAsStream("/dockerserver.properties");
        serversProp.load(resourceAsStream);

        for (Map.Entry<Object, Object> serverProp : serversProp.entrySet()) {
            String name = (String) serverProp.getKey();
            String server = (String) serverProp.getValue();

            if(name.startsWith("docker1")){
                continue;
            }
            new DockerCheckAlive().new LinuxExec(name, server).start();
        }
    }

    class LinuxExec extends Thread {
        String name;
        String server;

        public LinuxExec(String name, String server) {
            this.name = name;
            this.server = server;
        }

        @Override
        public void run() {
            JSch sshSingleton = new JSch();
            Session session = null;
            ChannelExec channel = null;
            try {
                session = sshSingleton.getSession(USER, server, DEFAULT_SSH_PORT);
                session.setPassword(PASSWORD);
                Properties config = new Properties();
                //设置 SSH 连接时不进行公钥确认
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.connect();
                //打开命令执行管道
                channel = (ChannelExec) session.openChannel("exec");
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        channel.getInputStream()));
                //发送linux命令
                channel.setCommand("netstat -tunpl|grep 'java'|wc -l");
                channel.connect();

                //读取命令输出信息
                String msg;
                while ((msg = in.readLine()) != null) {
                    //不存在java端口则表示进程关闭
                    if (Integer.valueOf(msg) < 1) {
                        System.err.println(name + "进程已关闭，请查看原因并重启");
                    }else{
                        System.out.println(name + "正常");
                    }
                }
                in.close();
            } catch (Exception e) {
                System.out.println(name + "连接异常，请重启....");
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
