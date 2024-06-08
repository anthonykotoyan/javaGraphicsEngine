import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;
public class Screen extends JPanel implements KeyListener, Runnable {

    private static final int TARGET_FPS = 60;
    private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
    private boolean running = true;
    private boolean[] keysPressed = new boolean[256];

    double SleepTime = 1000 / 60.0;
    long lastRefresh = System.nanoTime();
    public int width = 1200;
    public int height = 700;
    public int[] CamPos = new int[] { width / 2, height / 2 -300, -300 };
    public float[] CamRot = new float[] { 0, 0 };
    int[] circleCenter = new int[] { width / 2, height / 2 - 500, 1000 };
    int circleRadius = 50;
    float sensitivity = 0.05f;
    int fov = 90;
    int speed = 10;
    ArrayList<Object3D> objs = new ArrayList<>();

    // ball bouncing shit
    float gravity = .5f;
    float[] ballVelocity = new float[] {0,0};

    public Screen() {
        addKeyListener(this);
        setFocusable(true);
        
        Thread t = new Thread(this);
        t.start();
    }

    public void paintComponent(Graphics g) {
        renderObj();
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);
        drawObj(objs, g);
    }

    void SleepAndRefresh() {
        long now = System.nanoTime();
        long updateLength = now - lastRefresh;
        lastRefresh = now;
        double delta = updateLength / ((double) OPTIMAL_TIME);

        while (delta < 1) {
            Thread.yield();
            now = System.nanoTime();
            updateLength = now - lastRefresh;
            delta = updateLength / ((double) OPTIMAL_TIME);
        }

        lastRefresh = System.nanoTime();

        repaint();
    }

    public void keyPressed(KeyEvent e) {
        keysPressed[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keysPressed[e.getKeyCode()] = false;
    }

    public void run() {
        while (running) {
            handleInput();
            SleepAndRefresh();
        }
    }

    public void handleInput() {
        if (keysPressed[KeyEvent.VK_A])
            CamPos[0] -= speed;
        if (keysPressed[KeyEvent.VK_D])
            CamPos[0] += speed;
        if (keysPressed[KeyEvent.VK_SPACE])
            CamPos[1] -= speed;
        if (keysPressed[KeyEvent.VK_SHIFT])
            CamPos[1] += speed;
        if (keysPressed[KeyEvent.VK_W])
            CamPos[2] += speed;
        if (keysPressed[KeyEvent.VK_S])
            CamPos[2] -= speed;
        
        // Adjust CamRot for arrow keys
        if (keysPressed[KeyEvent.VK_LEFT])
            CamRot[0] += sensitivity; // Adjust rotation left
        if (keysPressed[KeyEvent.VK_RIGHT])
            CamRot[0] -= sensitivity; // Adjust rotation right
        if (keysPressed[KeyEvent.VK_UP])
            CamRot[1] -= sensitivity; // Adjust rotation up
        if (keysPressed[KeyEvent.VK_DOWN])
            CamRot[1] += sensitivity; // Adjust rotation down
    }

    public void renderObj() {
        objs.clear();
        int[] floorCenter = new int[] { width / 2, height / 2, 1000 };
        int[] floorDimensions = new int[] { 1000, 10, 1000 };

        ballVelocity[1] += gravity;
        if (circleCenter[1] + circleRadius > floorCenter[1] + floorDimensions[1]) {
            circleCenter[1] = floorCenter[1] + floorDimensions[1] - circleRadius - 10;
            ballVelocity[1] *= -.92;
        }
        circleCenter[1] += ballVelocity[1];

        // Create walls for the room
        
        
        // Create green circle
        



        Object3D floor = meshRenderer.box(floorCenter, floorDimensions[0], floorDimensions[1], floorDimensions[2], Color.BLUE, this);
        objs.add(floor);
        Object3D circle = meshRenderer.sphere(circleRadius, circleCenter, 20, Color.GREEN, this);
        objs.add(circle);
        
    }

    public void drawObj(ArrayList<Object3D> objs, Graphics g) {
        for (Object3D obj : objs) {
            obj.drawObject3D(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is required for KeyListener, but not used in this example.
    }
}
