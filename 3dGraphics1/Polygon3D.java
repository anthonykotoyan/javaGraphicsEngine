import java.awt.Color;
import java.awt.Graphics;
import java.util.stream.IntStream;

public class Polygon3D {
    Color c;
    int[][] coords3D;

    public Polygon3D(int[][] coords3D, Color c) {
        this.coords3D = coords3D;
        this.c = c;
    }

    public int[][] projection(int[] camPos, int fov, int width, int height) {
        int[][] coords2D = new int[coords3D.length][2];
        double distToScreen = (width / 2.0) / Math.tan(Math.toRadians(fov / 2.0));

        for (int i = 0; i < coords3D.length; i++) {
            int dx = coords3D[i][0] - camPos[0];
            int dy = coords3D[i][1] - camPos[1];
            int dz = coords3D[i][2] - camPos[2];

            if (dz != 0) {
                coords2D[i][0] = (int) (distToScreen * dx / dz + width / 2);
                coords2D[i][1] = (int) (distToScreen * dy / dz + height / 2);
            } else {
                coords2D[i][0] = Integer.MAX_VALUE;
                coords2D[i][1] = Integer.MAX_VALUE;
            }
        }

        return coords2D;
    }

    public void drawPolygon3D(Graphics g, int[] camPos, int fov, int width, int height) {
        int[][] coords2D = projection(camPos, fov, width, height);
        float avgZ = IntStream.of(coords3D[2]).sum() / coords3D[2].length;
        int maxLightDist = 500;
        float light = 1 - Math.abs(camPos[2] - avgZ) / maxLightDist;
        if (light < 0) {
            light = 0;
        }
        PolygonObject polygon = new PolygonObject(coords2D, c);
        g.setColor(c);
        polygon.drawPolygon(g, light);

    }
}
