import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel {

    public int width = 1200;
    public int height = 700;
    int[] CameraPos = new int[] { (int)width/2, (int)height/2, 0 };
    int fov = 90;
    
    Object3D cube;

    public Screen() {
        int[] centerPoint = {(int)width / 2, (int)height / 2, 1000};
        int lengthX = 100; 
        int lengthY = 100; 
        int heightZ = 200; 

        
        int[][] vertices = {
            {centerPoint[0] - lengthX / 2, centerPoint[1] - lengthY / 2, centerPoint[2]}, // Vertex 0
            {centerPoint[0] + lengthX / 2, centerPoint[1] - lengthY / 2, centerPoint[2]}, // Vertex 1
            {centerPoint[0] + lengthX / 2, centerPoint[1] + lengthY / 2, centerPoint[2]}, // Vertex 2
            {centerPoint[0] - lengthX / 2, centerPoint[1] + lengthY / 2, centerPoint[2]}, // Vertex 3
            {centerPoint[0], centerPoint[1], centerPoint[2] + heightZ}                    // Vertex 4 (apex of a pyramid)
        };

        
        int[][] trianglePointers = {
            {0, 1, 4}, 
            {1, 2, 4}, 
            {2, 3, 4}, 
            {3, 0, 4}, 
            {0, 1, 2}, 
            {2, 3, 0}  
        };
        cube = new Object3D(vertices, trianglePointers, Color.RED);

    }

    public void paintComponent(Graphics g) {
        cube.drawObject3D(g, CameraPos, fov, width, height);
    }

}
