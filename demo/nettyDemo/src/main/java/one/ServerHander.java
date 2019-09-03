package one;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class ServerHander implements Runnable {
    private Socket socket;

    public ServerHander(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            String result = "";
            do {

                    if ((expression = in.readLine()) == null) {
                        break;
                    }
                    log.info("服务端收到的消息：" + expression);

                    Scanner scanner = new Scanner(System.in);
                    result = Caculator.cal(expression);
                    out.print(scanner.next() + "\n");
                    out.flush();
                    //  socket.shutdownOutput();//关闭输出流

            } while (!result.equals("bye"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    in = null;
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        }
    }
}
