import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MiParserSVG {
    String rectangulo = "rect";
    String circulo = "circle";
    String elipse = "ellipse";
    String linea = "line";
    String texto = "text";

    public List<Node> leerFigurasDeSVG(String textoSVG) throws ParserConfigurationException, IOException, SAXException {

        List<String> nombresDeFigurasBuscadas = new ArrayList<>();
        nombresDeFigurasBuscadas.add(rectangulo);
        nombresDeFigurasBuscadas.add(circulo);
        nombresDeFigurasBuscadas.add(elipse);
        nombresDeFigurasBuscadas.add(linea);
        nombresDeFigurasBuscadas.add(texto);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new InputSource(new StringReader(textoSVG)));

        doc.getDocumentElement().normalize();

        Element raiz = doc.getDocumentElement();

        List<Node> todasLasFigurasEncontradas = new ArrayList<>();

        for (String nombreDeFigura : nombresDeFigurasBuscadas) {
            NodeList figurasConElMismoNombre = raiz.getElementsByTagName(nombreDeFigura);
            for (int i = 0; i < figurasConElMismoNombre.getLength(); i++) {
                Node nuevaFiguraEncontrada = figurasConElMismoNombre.item(i);
                todasLasFigurasEncontradas.add(nuevaFiguraEncontrada);
            }
        }
        return todasLasFigurasEncontradas;
    }

}

