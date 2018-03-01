package com.example.demo.linux;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

/**
 * Created in 2018-02-26 17:06.
 *
 * @author chenxiao
 */
public class DockerMultiExec {
    private static Logger logger = LoggerFactory.getLogger(DockerMultiExec.class);

    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final int DEFAULT_SSH_PORT = 22;

    public static void main(String[] args) throws Exception {
        JSch sshSingleton = new JSch();
        //从配置文件中加载服务器信息
        Properties serversProp = new Properties();
        InputStream resourceAsStream = DockerMultiExec.class.getResourceAsStream("/dockerserver.properties");
        serversProp.load(resourceAsStream);

        for (Map.Entry<Object, Object> serverProp : serversProp.entrySet()) {
            String name = (String) serverProp.getKey();
            String server = (String) serverProp.getValue();
            Session session = sshSingleton.getSession(USER, server,DEFAULT_SSH_PORT);
            session.setPassword(PASSWORD);
            Properties config = new Properties();
            //设置 SSH 连接时不进行公钥确认
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            //打开命令执行管道
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    channel.getInputStream()));
            //发送linux命令
            channel.setCommand("netstat -tunpl|grep 'java'|wc -l");
            channel.connect();
            //读取命令输出信息
            String msg;
            while ((msg = in.readLine()) != null) {
                //不存在java端口则表示进程关闭
               if (Integer.valueOf(msg) < 1){
                   System.out.println(name + "进程已关闭，请查看原因");
               }
            }

            channel.disconnect();
            session.disconnect();
        }
    }

}
