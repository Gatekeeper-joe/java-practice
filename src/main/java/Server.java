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
        var server = new ServerSocket(10880);
        for (;;) {
            try(Socket soc = server.accept();
                var isr = new InputStreamReader(soc.getInputStream());
                var bur = new BufferedReader(isr);
                var w = new PrintWriter(soc.getOutputStream()))
            {
                System.out.println("connected from " + soc.getInetAddress());
                bur.lines()
                        .takeWhile(line -> !line.isEmpty())
                        .forEach(System.out::println);
                w.println("""
                          HTTP/1.1 200 OK
                          Content-Type: text/html

                          <html><head><title>Hello</title></head>
                          <body><h1>Hello</h1>It works!</body></html>
                          """);
            }
        }
    }
}
