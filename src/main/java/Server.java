import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * 実行するとport10880上で待受け、アクセスしてきたクライアントの情報をコンソールに出力
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10880);
        for (;;) {
            try(Socket soc = server.accept();
                InputStreamReader isr = new InputStreamReader(soc.getInputStream());
                BufferedReader bur = new BufferedReader(isr);
                PrintWriter w = new PrintWriter(soc.getOutputStream()))
            {
                System.out.println("connected from " + soc.getInetAddress());
                bur.lines()
                        .takeWhile(line -> !line.isEmpty())
                        .forEach(System.out::println);
                w.println("""
                            HTTP/1.1 200 OK
                            ontent-Type: text/html

                            <html><head><title>Hello</title></head>
                            <body><h1>Hello</h1>It works!</body></html>
                            """);
            }
        }
    }
}
