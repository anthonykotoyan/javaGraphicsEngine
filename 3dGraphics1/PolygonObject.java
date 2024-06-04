import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class PolygonObject {
    Polygon P;
    Color c;

    public PolygonObject(int[][] coords2D, Color c) {
        P = new Polygon();
        int[][] extractedCoords = extractXYArray(coords2D);
        for (int i = 0; i < extractedCoords[0].length; i++) {
            P.addPoint(extractedCoords[0][i], extractedCoords[1][i]);
        }
        this.c = c;
    }

    void drawPolygon(Graphics g, float light) {
        float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);

        hsb[2] *= light; // Multiply the brightness value
        System.out.println(light);
        // Convert back to RGB
        int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        Color c_light = new Color(rgb);

        g.setColor(Color.black);
        g.drawPolygon(P);
        fillPolygon(g, c_light);

    }

    void fillPolygon(Graphics g, Color c) {
        g.setColor(c);
        g.fillPolygon(P);
    }

    public int[][] extractXYArray(int[][] coords) {
        int[] xValues = new int[coords.length];
        int[] yValues = new int[coords.length];

        for (int i = 0; i < coords.length; i++) {
            xValues[i] = coords[i][0];
            yValues[i] = coords[i][1];
        }

        return new int[][] { xValues, yValues };
    }
}
