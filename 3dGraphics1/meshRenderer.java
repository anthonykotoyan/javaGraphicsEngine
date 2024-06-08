import java.awt.Color;
import java.util.ArrayList;

public class meshRenderer {

    public static Object3D sphere(int radius, int[] center, int segments, Color color, Screen screen) {
        ArrayList<int[]> vertices = new ArrayList<>();
        ArrayList<int[]> triangles = new ArrayList<>();
        
        // Generate vertices
        for (int i = 0; i <= segments; i++) {
            double lat = Math.PI / segments * i;
            for (int j = 0; j <= segments; j++) {
                double lon = 2 * Math.PI / segments * j;
                int x = (int) (center[0] + radius * Math.sin(lat) * Math.cos(lon));
                int y = (int) (center[1] + radius * Math.sin(lat) * Math.sin(lon));
                int z = (int) (center[2] + radius * Math.cos(lat));
                vertices.add(new int[] {x, y, z});
            }
        }

        // Generate triangles
        for (int i = 0; i < segments; i++) {
            for (int j = 0; j < segments; j++) {
                int first = i * (segments + 1) + j;
                int second = first + segments + 1;
                triangles.add(new int[] {first, second, first + 1});
                triangles.add(new int[] {second, second + 1, first + 1});
            }
        }

        int[][] vertexArray = vertices.toArray(new int[0][0]);
        int[][] triangleArray = triangles.toArray(new int[0][0]);
        return new Object3D(vertexArray, triangleArray, color, screen);
    }

    public static Object3D box(int[] center, int width, int height, int depth, Color color, Screen screen) {
        int[][] vertices = {
            {center[0] - width / 2, center[1] - height / 2, center[2] - depth / 2},
            {center[0] + width / 2, center[1] - height / 2, center[2] - depth / 2},
            {center[0] + width / 2, center[1] + height / 2, center[2] - depth / 2},
            {center[0] - width / 2, center[1] + height / 2, center[2] - depth / 2},
            {center[0] - width / 2, center[1] - height / 2, center[2] + depth / 2},
            {center[0] + width / 2, center[1] - height / 2, center[2] + depth / 2},
            {center[0] + width / 2, center[1] + height / 2, center[2] + depth / 2},
            {center[0] - width / 2, center[1] + height / 2, center[2] + depth / 2}
        };

        int[][] triangles = {
            // Front face
            {0, 1, 2}, {2, 3, 0},
            // Back face
            {4, 5, 6}, {6, 7, 4},
            // Left face
            {0, 4, 7}, {7, 3, 0},
            // Right face
            {1, 5, 6}, {6, 2, 1},
            // Top face
            {3, 7, 6}, {6, 2, 3},
            // Bottom face
            {0, 4, 5}, {5, 1, 0}
        };

        return new Object3D(vertices, triangles, color, screen);
    }
}
