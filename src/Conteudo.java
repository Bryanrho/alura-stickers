public class Conteudo {
    private final String title;
    private final String urlImagem;

    public Conteudo(String title, String urlImagem) {
        this.title = title;
        this.urlImagem = urlImagem;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImagem() {
        return urlImagem;
    }
}
