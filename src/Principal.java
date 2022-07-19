import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        // fazer conexao HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/{API-KEY}";
        String url = "https://api.mocki.io/v2/549a5d8b";
        HttpClient client = HttpClient.newHttpClient();
        URI endereco = URI.create(url); // URI é um identificador unico; url é um tipo de URI
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();

        System.out.println(body);


        // extrair só os dados que interessam (titulo, poster, rating) - ver lib jackson para parsear
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String, String> filme: listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));

            var stars = (int) Double.parseDouble(filme.get("imDbRating"));

            for(var i = 0; i<stars; i++)
                System.out.print("\u2B50");
            System.out.println();
        }

    }
}
