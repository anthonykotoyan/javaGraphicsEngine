import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class Object3D {
    Color color;
    int[][] vertexList;
    int[][] trianglePointerList;
    int[][][] triangles;
    Screen screen;

    public Object3D(int[][] vertexList, int[][] trianglePointerList, Color color, Screen screen) {
        this.vertexList = vertexList;
        this.trianglePointerList = trianglePointerList;
        this.triangles = new int[trianglePointerList.length][3][3];
        for (int i = 0; i < trianglePointerList.length; i++) {
            for (int j = 0; j < trianglePointerList[i].length; j++) {
                triangles[i][j] = vertexList[trianglePointerList[i][j]];
            }
        }
        this.screen = screen;
        this.color = color;
    }

    public void drawObject3D(Graphics g) {
        int[][][] orderedTriangles = setDrawOrder();
        for (int[][] triangle : orderedTriangles) {
            g.setColor(color);
            Polygon3D triangle3D = new Polygon3D(triangle, color, screen);
            triangle3D.drawPolygon3D(g);
        }
    }

    public int[][][] setDrawOrder() {
        int[][] allDist = new int[triangles.length][2];
        for (int i = 0; i < triangles.length; i++) {
            int[] avgCoord = new int[3];
            for (int j = 0; j < 3; j++) {
                avgCoord[j] = (triangles[i][0][j] + triangles[i][1][j] + triangles[i][2][j]) / 3;
            }
            double dx = screen.CamPos[0] - avgCoord[0];
            double dy = screen.CamPos[1] - avgCoord[1];
            double dz = screen.CamPos[2] - avgCoord[2];
            double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
            allDist[i][0] = (int) dist;
            allDist[i][1] = i;
        }

        Arrays.sort(allDist, (a, b) -> Integer.compare(b[0], a[0]));

        int[][][] orderedTriangles = new int[triangles.length][3][3];
        for (int i = 0; i < allDist.length; i++) {
            orderedTriangles[i] = triangles[allDist[i][1]];
        }

        return orderedTriangles;
    }
}
