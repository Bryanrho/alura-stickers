import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        for (Map<String, String> filme : listaDeFilmes) {
            String urlImage = filme.get("image");
            String fullTitle = filme.get("fullTitle");
            String filmName = fullTitle + ".png";
            String archiveName = filmName.replace(":", "-");
            String imdbRating = filme.get("imDbRating");

            InputStream inputStream = new URL(urlImage).openStream();

            geradora.cria(inputStream, archiveName,imdbRating);

            System.out.println("\u001b[0mTítulo: \033[1;90m\u001b[47m\t" + fullTitle + "\u001b[0m");
            System.out.println("\u001b[0mPoster: \u001b[34m\u001b[4m\t" + urlImage + "\u001b[0m");
            System.out.print("\u001b[0mClassificação:  \033[1;90m" + imdbRating + "\u001b[0m ");

            double doubleRating = Double.valueOf(imdbRating);
            int intRating = (int) doubleRating;

            for (int i = 0; i < intRating; i++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }
    }
}
