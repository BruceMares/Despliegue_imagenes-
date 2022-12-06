import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        if (args.length != 1) {
            System.out.println("Error. Se requiere una URL como unico argumento para este programa. Programa terminado.");
            return;
        }

        URL url = new URL(args[0]);
        URLConnection conexion = url.openConnection();
        String texto = "";
        try (InputStream entrada = conexion.getInputStream();
             BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                texto += linea;
                texto += "\n";
            }
        }
        MiParserSVG miParserSVG = new MiParserSVG();
        List<Node> figurasParseadas = miParserSVG.leerFigurasDeSVG(texto);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MiJFrame miJframe = new MiJFrame(figurasParseadas, 900, 600);
                miJframe.setSize(900, 600);
                miJframe.setResizable(false);
                miJframe.setVisible(true);
            }
        });
    }
}
