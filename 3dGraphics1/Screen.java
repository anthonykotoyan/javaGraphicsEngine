import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Screen extends JPanel implements KeyListener {

    double SleepTime = 1000 / 60, lastRefresh = 0;
    public int width = 1200;
    public int height = 700;
    int[] CameraPos = new int[] { width / 2, height / 2, 0 };
    int fov = 90;
    int speed = 10;
    ArrayList<Object3D> objs = new ArrayList<>();

    public Screen() {
        addKeyListener(this);
        setFocusable(true);
        renderObj();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);
        drawObj(objs, g);
        SleepAndRefresh();
    }

    void SleepAndRefresh() {
        while (true) {
            if (System.currentTimeMillis() - lastRefresh > SleepTime) {
                lastRefresh = System.currentTimeMillis();
                repaint();
                break;
            } else {
                try {
                    Thread.sleep((long) (System.currentTimeMillis() - lastRefresh));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A)
            CameraPos[0] -= speed;
        if (e.getKeyCode() == KeyEvent.VK_D)
            CameraPos[0] += speed;
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            CameraPos[1] -= speed;
        if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            CameraPos[1] += speed;
        if (e.getKeyCode() == KeyEvent.VK_W)
            CameraPos[2] += speed;
        if (e.getKeyCode() == KeyEvent.VK_S)
            CameraPos[2] -= speed;
    }

    public void renderObj() {
        int[][] sphereCenterPoint = new int[][] { { width / 2 - 150, height / 2, 1000 },
                { width / 2 + 150, height / 2, 1000 } };
        int sphereRadius = 50;
        Object3D sphere1 = meshRenderer.sphere(sphereRadius, sphereCenterPoint[0], 7, Color.RED);
        objs.add(sphere1);
        Object3D sphere2 = meshRenderer.sphere(sphereRadius, sphereCenterPoint[1], 7, Color.RED);
        objs.add(sphere2);
        int[] boxCenter = new int[] { width / 2, height / 2 + 200, 1000 };
        int[] boxDimensions = new int[] { 400, 100, 100 };
        Object3D box1 = meshRenderer.box(boxCenter, boxDimensions[0], boxDimensions[1], boxDimensions[2], Color.blue);
        objs.add(box1);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is required for KeyListener, but not used in this example.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // This method is required for KeyListener, but not used in this example.
    }

    public void drawObj(ArrayList<Object3D> objs, Graphics g) {
        for (Object3D obj : objs) {
            obj.drawObject3D(g, CameraPos, fov, width, height);
        }
    }
}
