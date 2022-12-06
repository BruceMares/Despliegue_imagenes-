import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
public class MiJPanel extends JPanel{
    List<Node> figurasSvg;

    public MiJPanel(List<Node> figuras, int x, int y) {
        setLayout(null);
        this.figurasSvg = figuras;
        setSize(x, y);
        setVisible(true);
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarFiguras( (Graphics2D) g );
    }

    public void dibujarFiguras(Graphics2D g) {
        for (Node figura : figurasSvg) {
            if (figura.getNodeName().trim().equalsIgnoreCase("rect")) {
                dibujarRectangulo(figura, g);
            }
            if (figura.getNodeName().trim().equalsIgnoreCase("circle")) {
                dibujarCirculo(figura, g);
            }
            if (figura.getNodeName().trim().equalsIgnoreCase("ellipse")) {
                dibujarEllipse(figura, g);
            }
            if (figura.getNodeName().trim().equalsIgnoreCase("line")) {
                dibujarLinea(figura, g);
            }
            if (figura.getNodeName().trim().equalsIgnoreCase("text")) {
                dibujarTexto(figura, g);
            }
        }
    }

    private void dibujarTexto(Node nodoDeTexto, Graphics2D g) {
        double x = 0;
        double y = 0;
        String fontFamily = "";
        double fontSize = 0;
        String fill = "";
        NamedNodeMap atributosDeTexto = nodoDeTexto.getAttributes();
        for (int i = 0; i < atributosDeTexto.getLength(); i++) {
            Node atributo = atributosDeTexto.item(i);
            if (atributo.getNodeName().trim().equalsIgnoreCase("x")) {
                x = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("y")) {
                y = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("font-family")) {
                fontFamily = atributo.getNodeValue().trim();
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("font-size")) {
                fontSize = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("fill")) {
                fill = atributo.getNodeValue().trim();
            }
        }
        String familiaDeFuentePorDefecto = "Arial";
        int tamanioPorDefecto = 12;
        if (fontSize == 0) {
            fontSize = tamanioPorDefecto;
        }
        if (fontFamily.equals("")) {
            fontFamily = familiaDeFuentePorDefecto;
        }
        Font fuente = new Font(fontFamily, Font.PLAIN, (int) fontSize);
        NodeList hijosDelNodo = nodoDeTexto.getChildNodes();
        if (hijosDelNodo.getLength() > 0) {
            String texto = hijosDelNodo.item(0).getNodeValue().trim();
            JLabel label = new JLabel(texto);
            Color colorDeRelleno = interpretarColor(fill);
            label.setForeground(colorDeRelleno);
            label.setFont(fuente);
            label.setBorder(new EmptyBorder(0,0,0,0));
            label.setBounds( (int) x, ((int) y  - (int) fontSize), getWidth(), (int) fontSize);
            add(label);
        }
    }

    private void dibujarLinea(Node figura, Graphics2D g) {
        double x1 = 0;
        double y1 = 0;
        double x2 = 0;
        double y2 = 0;
        String stroke = "";
        double strokeWidth = 0;
        NamedNodeMap atributosDeLinea = figura.getAttributes();
        for (int i = 0; i < atributosDeLinea.getLength(); i++) {
            Node atributo = atributosDeLinea.item(i);
            if (atributo.getNodeName().trim().equalsIgnoreCase("x1")) {
                x1 = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("x2")) {
                x2 = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("y1")) {
                y1 = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("y2")) {
                y2 = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("stroke")) {
                stroke = atributo.getNodeValue().trim();
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("stroke-width")) {
                strokeWidth = Double.parseDouble(atributo.getNodeValue().trim());
            }
        }
        Color color = interpretarColor(stroke);
        g.setColor(color);
        g.setStroke(new BasicStroke( (int) strokeWidth) );
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    private void dibujarEllipse(Node figura, Graphics2D g) {
        double cx = 0;
        double cy = 0;
        double rx = 0;
        double ry = 0;
        String fill = "";
        String stroke = "";
        double strokeWidth = 0;
        NamedNodeMap atributosDeEllipse = figura.getAttributes();
        for (int i = 0; i < atributosDeEllipse.getLength(); i++) {
            Node atributo = atributosDeEllipse.item(i);
            if (atributo.getNodeName().trim().equalsIgnoreCase("cx")) {
                cx = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("cy")) {
                cy = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("rx")) {
                rx = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("ry")) {
                ry = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("fill")) {
                fill = atributo.getNodeValue().trim();
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("stroke")) {
                stroke = atributo.getNodeValue().trim();
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("stroke-width")) {
                strokeWidth = Double.parseDouble(atributo.getNodeValue().trim());
            }
        }
        int ancho = (int) (rx * 2);
        int alto = (int) (ry * 2);
        int esquinaDelBibujoX = (int) (cx - (ancho / 2));
        int esquinaDelDibujoY = (int) (cy - (alto / 2));
        Color colorDelRelleno = interpretarColor(fill);
        g.setColor(colorDelRelleno);
        g.fillOval(esquinaDelBibujoX, esquinaDelDibujoY, ancho, alto);
        Color colorDelContorno = interpretarColor(stroke);
        g.setStroke(new BasicStroke((int) strokeWidth));
        g.setColor(colorDelContorno);
        Shape ellipse = new Ellipse2D.Double(esquinaDelBibujoX,  esquinaDelDibujoY, ancho, alto);
        g.draw(ellipse);
    }

    private void dibujarCirculo(Node figura, Graphics2D g) {
        double cx = 0;
        double cy = 0;
        double r = 0;
        String fill = "";
        String stroke = "";
        double strokeWidth = 0;
        NamedNodeMap atributosDelCirculo = figura.getAttributes();
        for (int i = 0; i < atributosDelCirculo.getLength(); i++) {
            Node atributo = atributosDelCirculo.item(i);
            if (atributo.getNodeName().trim().equalsIgnoreCase("cx")) {
                cx = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("cy")) {
                cy = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("r")) {
                r = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("fill")) {
                fill = atributo.getNodeValue().trim();
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("stroke")) {
                stroke = atributo.getNodeValue().trim();
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("stroke-width")) {
                strokeWidth = Double.parseDouble(atributo.getNodeValue().trim());
            }
        }
        int diametro = (int) (r * 2);
        int esquinaDelBibujoX = (int) (cx - (diametro / 2));
        int esquinaDelDibujoY = (int) (cy - (diametro / 2));
        Color colorDelRelleno = interpretarColor(fill);
        g.setColor(colorDelRelleno);
        g.fillOval(esquinaDelBibujoX, esquinaDelDibujoY, diametro, diametro);
        Color colorDelContorno = interpretarColor(stroke);
        g.setColor(colorDelContorno);
        g.setStroke(new BasicStroke((int) strokeWidth));
        Shape contornoDelCirculo = new Ellipse2D.Double(esquinaDelBibujoX, esquinaDelDibujoY, diametro, diametro);
        g.draw(contornoDelCirculo);
    }

    private void dibujarRectangulo(Node figura, Graphics2D g) {
        double width = 0;
        double height = 0;
        double x = 0;
        double y = 0;
        String fill = "";
        String stroke = "";
        double strokeWidth = 0;
        NamedNodeMap atributosDelRectangulo = figura.getAttributes();
        for (int i = 0; i < atributosDelRectangulo.getLength(); i++) {
            Node atributo = atributosDelRectangulo.item(i);
            if (atributo.getNodeName().trim().equalsIgnoreCase("width")) {
                width = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("height")) {
                height = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("x")) {
                x = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("y")) {
                y = Double.parseDouble(atributo.getNodeValue().trim());
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("fill")) {
                fill = atributo.getNodeValue().trim();
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("stroke")) {
                stroke = atributo.getNodeValue().trim();
            }
            if (atributo.getNodeName().trim().equalsIgnoreCase("stroke-width")) {
                strokeWidth = Double.parseDouble(atributo.getNodeValue().trim());
            }
        }
        Color colorDelRelleno = interpretarColor(fill);
        g.setColor(colorDelRelleno);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
        Color colorDelContorno = interpretarColor(stroke);
        g.setStroke(new BasicStroke((int) strokeWidth));
        g.setColor(colorDelContorno);
        g.drawRect((int) x, (int) y, (int) width, (int) height);
    }

    private Color interpretarColor(String fill) {
        if (fill.trim().equalsIgnoreCase("none") || fill.trim().equalsIgnoreCase("transparent")) {
            return new Color(0, 0, 0, 0);
        }
        String colorSinEspacios = fill.trim();
        String parentesis = "(";
        String parentesisDos = ")";
        if (colorSinEspacios.contains(parentesis) && colorSinEspacios.contains(parentesisDos)) {
            String contenidoEntreParentesis = colorSinEspacios.substring(colorSinEspacios.indexOf('(') + 1, colorSinEspacios.indexOf(')'));
            String[] partesDeNumeroRGB = contenidoEntreParentesis.split(",");
            if (partesDeNumeroRGB.length == 3 || partesDeNumeroRGB.length == 4) {
                int primerNumero = Integer.parseInt(partesDeNumeroRGB[0].trim());
                int segundoNumero = Integer.parseInt(partesDeNumeroRGB[1].trim());
                int tercerNumero = Integer.parseInt(partesDeNumeroRGB[2].trim());
                if (partesDeNumeroRGB.length == 3) {
                    return new Color(primerNumero, segundoNumero, tercerNumero);
                }
                else if (partesDeNumeroRGB.length == 4) {
                    double alfa = Double.parseDouble(partesDeNumeroRGB[3].trim());
                    if (alfa == 0) {
                        return new Color(primerNumero, segundoNumero, tercerNumero, (int) alfa);
                    }
                    else {
                        return new Color(primerNumero, segundoNumero, tercerNumero);
                    }
                }
            }
        }
        else if (colorSinEspacios.contains("#") && colorSinEspacios.trim().length() == 7) {
            return Color.decode(colorSinEspacios.trim());
        }
        else if (colorSinEspacios != null && colorSinEspacios != "") {
            if (colorSinEspacios.equalsIgnoreCase("red")) {
                return Color.RED;
            }
            if (colorSinEspacios.equalsIgnoreCase("cyan")) {
                return Color.CYAN;
            }
            if (colorSinEspacios.equalsIgnoreCase("darkgray")) {
                return Color.DARK_GRAY;
            }
            if (colorSinEspacios.equalsIgnoreCase("white")) {
                return Color.WHITE;
            }
            if (colorSinEspacios.equalsIgnoreCase("pink")) {
                return Color.PINK;
            }
            if (colorSinEspacios.equalsIgnoreCase("orange")) {
                return Color.ORANGE;
            }
            if (colorSinEspacios.equalsIgnoreCase("magenta")) {
                return Color.MAGENTA;
            }
            if (colorSinEspacios.equalsIgnoreCase("lightgray")) {
                return Color.LIGHT_GRAY;
            }
            if (colorSinEspacios.equalsIgnoreCase("green")) {
                return Color.GREEN;
            }
            if (colorSinEspacios.equalsIgnoreCase("gray")) {
                return Color.GRAY;
            }
            if (colorSinEspacios.equalsIgnoreCase("blue")) {
                return Color.BLUE;
            }
            if (colorSinEspacios.equalsIgnoreCase("black")) {
                return Color.BLACK;
            }
            if (colorSinEspacios.equalsIgnoreCase("yellow")) {
                return Color.YELLOW;
            }
        }
        return Color.BLACK;
    }

}

