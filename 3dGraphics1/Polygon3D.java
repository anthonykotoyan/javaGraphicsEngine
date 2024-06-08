import java.awt.Color;
import java.awt.Graphics;

public class Polygon3D {
    Color c;
    int[][] coords3D;
    Screen screen;

    public Polygon3D(int[][] coords3D, Color c, Screen screen) {
        this.coords3D = coords3D;
        this.c = c;
        this.screen = screen;
    }

    public int[][] projection() {
        int[][] coords2D = new int[coords3D.length][2];
        double distToScreen = (screen.width / 2.0) / Math.tan(Math.toRadians(screen.fov / 2.0));
    
        // Get combined rotation matrix
        double[][] rotationMatrix = getRotationMatrix(screen.CamRot[0], screen.CamRot[1]);
    
        for (int i = 0; i < coords3D.length; i++) {
            int dx = coords3D[i][0] - screen.CamPos[0];
            int dy = coords3D[i][1] - screen.CamPos[1];
            int dz = coords3D[i][2] - screen.CamPos[2];
    
            // Apply rotation matrix
            double[] rotatedCoords = new double[3];
            rotatedCoords[0] = rotationMatrix[0][0] * dx + rotationMatrix[0][1] * dy + rotationMatrix[0][2] * dz;
            rotatedCoords[1] = rotationMatrix[1][0] * dx + rotationMatrix[1][1] * dy + rotationMatrix[1][2] * dz;
            rotatedCoords[2] = rotationMatrix[2][0] * dx + rotationMatrix[2][1] * dy + rotationMatrix[2][2] * dz;
            
            if (rotatedCoords[2] != 0) {
                coords2D[i][0] = (int) (distToScreen * rotatedCoords[0] / rotatedCoords[2] + screen.width / 2);
                coords2D[i][1] = (int) (distToScreen * rotatedCoords[1] / rotatedCoords[2] + screen.height / 2);
            } else {
                coords2D[i][0] = Integer.MAX_VALUE;
                coords2D[i][1] = Integer.MAX_VALUE;
            }
        }
    
        return coords2D;
    }
    
    public void drawPolygon3D(Graphics g) {
        int[][] coords2D = projection();
        int[] avgCoord = new int[3];
        for (int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < coords3D.length; j++) {
                sum += coords3D[j][i];
            }
            avgCoord[i] = sum / coords3D.length;
        }
        double dx = screen.CamPos[0] - avgCoord[0];
        double dy = screen.CamPos[1] - avgCoord[1];
        double dz = screen.CamPos[2] - avgCoord[2];
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
        int maxLightDist = 2000;
        float light = (float) (1 - (dist / maxLightDist));
        if (light < 0) {
            light = 0;
        }
        PolygonObject polygon = new PolygonObject(coords2D, c);
        g.setColor(c);
        polygon.drawPolygon(g, light);
    }

    public double[][] getRotationMatrix(double yaw, double pitch) {
        // Calculate sin and cos values for yaw and pitch
        double cosPitch = Math.cos(pitch);
        double sinPitch = Math.sin(pitch);
        double cosYaw = Math.cos(yaw);
        double sinYaw = Math.sin(yaw);
    
        // Rotation matrix for pitch
        double[][] rotationX = {
            {1, 0, 0},
            {0, cosPitch, -sinPitch},
            {0, sinPitch, cosPitch}
        };
    
        // Rotation matrix for yaw
        double[][] rotationY = {
            {cosYaw, 0, sinYaw},
            {0, 1, 0},
            {-sinYaw, 0, cosYaw}
        };
    
        // Multiply rotation matrices: rotationY * rotationX
        return multiplyMatrices(rotationY, rotationX);
    }
    
    
    public double[][] multiplyMatrices(double[][] a, double[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;
        double[][] result = new double[rowsA][colsB];
    
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }
}
