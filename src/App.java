import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexao HTTP e buscar os top 250 filmes imdb

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ExtratorDeConteudos extrator = new ExtratorDeConteudoDoIMDB();

        // String url =
        // "https://api.nasa.gov/planetary/apod?api_key=eWg4JHWqe2Ioe8Iu9M2ueuQGtjdptP5OExNlF8N9&start_date=2022-06-12&end_date=2022-06-14";
        // ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        for (Conteudo conteudo : conteudos) {

            String nomeArquivo = conteudo.getTitle() + ".png";
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[0mTítulo: \033[1;90m\u001b[47m\t" + conteudo.getTitle() + "\u001b[0m");
            System.out.println("\u001b[0mPoster: \u001b[34m\u001b[4m\t" + conteudo.getUrlImagem() + "\u001b[0m");
            System.out.println();

        }
    }
}
