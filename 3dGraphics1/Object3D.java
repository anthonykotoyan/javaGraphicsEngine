import java.awt.Color;
import java.awt.Graphics;

public class Object3D {
    Color c;
    int[][] VertexList;
    int[][] trianglePointerList;
    int[][][] triangles;
    public Object3D(int[][] VertexList,int[][] trianglePointerList, Color c){
        this.VertexList = VertexList;
        this.trianglePointerList = trianglePointerList;
        this.triangles = new int[trianglePointerList.length][3][3];
        for (int i = 0; i < trianglePointerList.length; i++) {
            for (int j = 0; j < trianglePointerList[i].length; j++) { 
                triangles[i][j] = VertexList[trianglePointerList[i][j]];
            }
        }
        this.c = c;
    }
    public void drawObject3D(Graphics g, int[] camPos, int fov, int width, int height) {
        for (int i = 0; i < triangles.length; i++) {
            g.setColor(c);
            Polygon3D triangle3D = new Polygon3D(triangles[i], c);
            triangle3D.drawPolygon3D(g, camPos, fov, width, height);
        }

    }

    
}
