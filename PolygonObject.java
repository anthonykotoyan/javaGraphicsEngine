import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class PolygonObject {
    Polygon P;
    Color c;
    public PolygonObject(int[][] coords2D, Color c){
        P = new Polygon();
        int[][] extractedCoords = extractXYArray(coords2D);
        P.xpoints = extractedCoords[0];
        P.ypoints = extractedCoords[1];
        P.npoints = extractedCoords[0].length;
        this.c = c;
    }

    void drawPolygon(Graphics g){
        g.setColor(c);
        g.drawPolygon(P);
    }

    public int[][] extractXYArray(int[][] coords){
        int[] xValues = new int[coords.length];
        int[] yValues = new int[coords.length];

        for (int i = 0; i < coords.length; i++) {
            xValues[i] = coords[i][0];
            yValues[i] = coords[i][1];
        }

        return new int[][] {xValues, yValues};
        
    }
}
