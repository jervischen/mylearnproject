package com.example.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Chen Xiao
 * @since 2021-07-27 16:09
 */
public class Client {
    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 12345;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";
    public static void send(String expression){
        send(DEFAULT_SERVER_PORT,expression);
    }
    public static void send(int port,String expression){
        System.out.println("发送消息：" + expression);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            socket = new Socket(DEFAULT_SERVER_IP,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println(expression);
            System.out.println("结果为：" + in.readLine());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //一下必要的清理工作
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if(out != null){
                out.close();
                out = null;
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
