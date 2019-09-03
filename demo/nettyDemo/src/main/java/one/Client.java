package one;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class Client {

    private static int PROT = 7777;
    private static String HOST = "localhost";


    public static void send(String expression) throws IOException {
        Socket socket = new Socket(HOST, PROT);
        OutputStream outputStream = socket.getOutputStream();
        //输出到服务端
        PrintWriter printWriter = new PrintWriter(outputStream);
        InputStream inputStream = socket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String result;
        do {
            Scanner scanner = new Scanner(System.in);
            printWriter.write(scanner.next() + "\n");
            printWriter.flush();
            //关闭输出流
            //  socket.shutdownOutput();

            //获取输入流，并读取服务器端的响应信息


            if ((result = in.readLine()) != null) {
                log.info("服务端传来的消息：" + result);
            }

        } while (!result.equals("bye"));
        outputStream.close();
        printWriter.close();
        inputStream.close();
        in.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Client.send("pppp");
    }
}
