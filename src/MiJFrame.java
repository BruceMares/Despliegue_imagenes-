import org.w3c.dom.Node;
import javax.swing.*;
import java.util.List;

public class MiJFrame extends JFrame{
    public MiJFrame(List<Node> figurasSvg, int x, int y) {
        setTitle("Figuras SVG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MiJPanel(figurasSvg, x, y));
    }

}

