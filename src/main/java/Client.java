import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class Client {

    /**
     * 指定したURLにアクセスした際に取得されるHTMLファイルをlimit()で指定した行数分読み込み、コンソールに出力
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("https://example.com/");
        HttpRequest req = HttpRequest.newBuilder(uri).build();
        HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        body.lines()
                .limit(5)
                .forEach(System.out::println);
    }
}
