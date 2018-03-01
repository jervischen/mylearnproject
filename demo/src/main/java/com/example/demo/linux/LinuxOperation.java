package com.example.demo.linux;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created in 2018-02-26 10:26.
 *
 * @author chenxiao
 */
public class LinuxOperation {
    private static Logger logger = LoggerFactory.getLogger(LinuxOperation.class);

    public static void test_connect_host() throws Exception {

        SimpleDateFormat date_format = new SimpleDateFormat("yyyyMMdd_HHmmss");

        String[] host_ip_list = new String[] { "192.168.2.179", "192.168.17.5", };

        String[] host_password_list = new String[] { "", "", };
        String[] identity_password_list = new String[] { "", "", };

        int[] host_port_list = new int[] { 22, 51255, };

        String[] connect_type_list = new String[] { "password", "publickey", };

        String host_user = "root";

        //
        for (int i = 0; i < host_ip_list.length; i++) {
            String host_ip = host_ip_list[i];
            int host_port = host_port_list[i];
            String host_password = host_password_list[i];
            String identity_password = identity_password_list[i];
            String connect_type = connect_type_list[i];

            JSch jsch = new JSch();
            Session session = null;

            // 连接机器ssh , 指定某种方式
            /*
                         if (connect_type.equalsIgnoreCase("password")) {
                         session = jsch.getSession(host_user, mysql_host, host_port);
                         session.setPassword(host_password);
                         } else if (connect_type.equalsIgnoreCase("publickey")) {
                         jsch.addIdentity("D:\\tmp\\admin_id_dsa_private.private", "");
                         session = jsch.getSession(host_user, mysql_host, host_port);
                         } else {
                         continue;
                         }
                         UserInfo ui = new LocalUserInfo();
                         session.setUserInfo(ui);

                         Hashtable<String, String> config = new Hashtable<String,
                         String>();
                         config.put("StrictHostKeyChecking", "no");
                         session.setConfig(config);

                         session.connect();
            */

            // 遍历所有的方式 , 成功即可

            boolean connectflag = false;
            for (int j = 0; j < connect_type_list.length; j++) {
                String v_connect_type = connect_type_list[i];
                try {
                    if (v_connect_type.equalsIgnoreCase("password")) {
                        session = jsch.getSession(host_user, host_ip, host_port);
                        session.setPassword(host_password);
                    } else if (v_connect_type.equalsIgnoreCase("publickey")) {
                        jsch.addIdentity("D:\\tmp\\admin_id_dsa_private.private", identity_password);
                        session = jsch.getSession(host_user, host_ip, host_port);
                    } else {
                        continue;
                    }
                 /*   UserInfo vui = new LocalUserInfo();
                    session.setUserInfo(vui);*/

                    Hashtable<String, String> vconfig = new Hashtable<String, String>();
                    vconfig.put("StrictHostKeyChecking", "no");
                    session.setConfig(vconfig);

                    session.connect();
                    connectflag = true;
                    break;
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            if (connectflag == false) {
                continue;
            }

            // 统一的方式

            /*            jsch.addIdentity("D:\\tmp\\admin_id_dsa_private.private");
                        session = jsch.getSession(host_user, host_ip, host_port);
                        session.setPassword(host_password);
                        Properties config = new Properties();
                        config.put("StrictHostKeyChecking", "no");
                        session.setConfig(config);
                        session.setTimeout(60);
                        session.connect();*/

            // 执行命令
            String execstr = "ls -lU --full-time --time-style='+%F_%T' ~";

            System.out.println(date_format.format(new Date()) + " \t " + execstr);

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(execstr);
            InputStream in = channel.getInputStream();
            channel.connect();
            int nextChar;
            StringBuffer sb = new StringBuffer();
            while (true) {
                while ((nextChar = in.read()) != -1) {
                    sb.append((char) nextChar);
                }
                if (channel.isClosed()) {
                    // System.out.println("exit-status: " +
                    // channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
            String ret = sb.toString();

            System.out.println(ret);
            if (channel != null) {
                channel.disconnect();
            }

            if (session != null) {
                session.disconnect();
            }
        }

        System.out.println("======================== successfully ========================");

    }
}
