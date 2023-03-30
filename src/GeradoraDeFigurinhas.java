import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String archiveName) throws Exception {

        // leitura da imagem
        // InputStream inputStream = new FileInputStream(new
        // File("entrada/image1.jpg"));
        // InputStream inputStream = new
        // URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();

        BufferedImage imageOriginal = ImageIO.read(inputStream);

        // criar nova imagem em memoria com transparencia e tamanho novo
        int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();

        int newHeight = height + 400;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem em memória
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imageOriginal, 0, 0, null);

        // configurar a fonte
        Font font = new Font("Comic Sans MS", Font.BOLD, 120);
        graphics.setColor(Color.RED);
        graphics.setFont(font);

        // escrever uma frase na nova imagem
        FontMetrics fontMetrics = graphics.getFontMetrics();
        String text = "Topzera";
        Rectangle2D rectangle = fontMetrics.getStringBounds(text, graphics);
        int textWidth = (int) rectangle.getWidth();
        int positionX = (width - textWidth) / 2;
        int positionY = newHeight - 200;
        graphics.drawString(text, positionX, positionY);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, font, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();

        transform.translate(positionX, positionY);
        graphics.setTransform(transform);

        BasicStroke stroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(stroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        // escrever a imagem em um arquivo
        ImageIO.write(newImage, "png", new File("saida/" + archiveName));
    }
}
