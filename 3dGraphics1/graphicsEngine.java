import javax.swing.JFrame;
import java.awt.Toolkit;

public class graphicsEngine extends JFrame {

    static JFrame F = new graphicsEngine();
    Screen ScreenObject = new Screen();

    public graphicsEngine() {
        add(ScreenObject);
        setUndecorated(false);
        setSize(ScreenObject.width, ScreenObject.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {

    }
}