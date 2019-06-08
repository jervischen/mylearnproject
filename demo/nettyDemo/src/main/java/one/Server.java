package one;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO阻塞
 * 线程多，好资源
 */
public class Server {
    //端口号
    private static int DEFAULT_PORT = 7777;
    private static ServerSocket serverSocket;

    private static void start() throws IOException{
        start(DEFAULT_PORT);
    }

    private static void start(int prot) throws IOException{
        if (serverSocket != null){return ;}
        try {
            serverSocket =  new ServerSocket(prot);
            System.out.println("启动成功。。。");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerHander(socket)).start();
            }

        }finally {

        }
    }

    public static void main(String[] args) throws IOException {
        Server.start();
    }
}
