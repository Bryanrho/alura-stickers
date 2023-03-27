import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexao HTTP e buscar os top 250 filmes imdb

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados que interessam: titulo, poster e classificacao
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("\u001b[0mTítulo: \033[1;90m\u001b[47m\t" + filme.get("title") + "\u001b[0m");
            System.out.println("\u001b[0mPoster: \u001b[34m\u001b[4m\t" + filme.get("image") + "\u001b[0m");
            System.out.print("\u001b[0mClassificação:  \033[1;90m" + filme.get("imDbRating") + "\u001b[0m ");

            double doubleRating = Double.valueOf(filme.get("imDbRating"));
            int intRating = (int) doubleRating;

            for (int i = 0; i < intRating; i++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }
    }
}
