import java.awt.Color;

public class meshRenderer {

    public static Object3D box(int[] centerPoint, int lengthX, int lengthY, int heightZ, Color color) {

        int[][] vertices = {
                { centerPoint[0] - lengthX / 2, centerPoint[1] - lengthY / 2, centerPoint[2] - heightZ / 2 }, // 0
                { centerPoint[0] + lengthX / 2, centerPoint[1] - lengthY / 2, centerPoint[2] - heightZ / 2 }, // 1
                { centerPoint[0] + lengthX / 2, centerPoint[1] + lengthY / 2, centerPoint[2] - heightZ / 2 }, // 2
                { centerPoint[0] - lengthX / 2, centerPoint[1] + lengthY / 2, centerPoint[2] - heightZ / 2 }, // 3
                { centerPoint[0] - lengthX / 2, centerPoint[1] - lengthY / 2, centerPoint[2] + heightZ / 2 }, // 4
                { centerPoint[0] + lengthX / 2, centerPoint[1] - lengthY / 2, centerPoint[2] + heightZ / 2 }, // 5
                { centerPoint[0] + lengthX / 2, centerPoint[1] + lengthY / 2, centerPoint[2] + heightZ / 2 }, // 6
                { centerPoint[0] - lengthX / 2, centerPoint[1] + lengthY / 2, centerPoint[2] + heightZ / 2 } // 7
        };

        // Define triangles using indices of vertices
        int[][] trianglePointers = {
                { 0, 1, 2 }, { 2, 3, 0 }, // Front face
                { 1, 5, 6 }, { 6, 2, 1 }, // Right face
                { 4, 5, 1 }, { 1, 0, 4 }, // Bottom face
                { 3, 2, 6 }, { 6, 7, 3 }, // Top face
                { 4, 7, 6 }, { 6, 5, 4 }, // Back face
                { 3, 7, 4 }, { 4, 0, 3 } // Left face
        };
        return new Object3D(vertices, trianglePointers, color);
    }

    public static Object3D sphere(int radius, int[] center, int resolution, Color color) {

        int numVertices = (resolution + 1) * (resolution + 1);
        int numTriangles = resolution * resolution * 2;

        int[][] vertices = new int[numVertices][3];
        int[][] trianglePointers = new int[numTriangles][3];

        int vertexIndex = 0;
        int triangleIndex = 0;

        // Create vertices
        for (int i = 0; i <= resolution; i++) {
            double theta = Math.PI * i / resolution;
            for (int j = 0; j <= resolution; j++) {
                double phi = 2 * Math.PI * j / resolution;
                int x = (int) (center[0] + radius * Math.sin(theta) * Math.cos(phi));
                int y = (int) (center[1] + radius * Math.sin(theta) * Math.sin(phi));
                int z = (int) (center[2] + radius * Math.cos(theta));
                vertices[vertexIndex][0] = x;
                vertices[vertexIndex][1] = y;
                vertices[vertexIndex][2] = z;
                vertexIndex++;
            }
        }

        // Create triangles
        for (int i = 0; i < resolution; i++) {
            for (int j = 0; j < resolution; j++) {
                int first = i * (resolution + 1) + j;
                int second = first + resolution + 1;

                // First triangle
                trianglePointers[triangleIndex][0] = first;
                trianglePointers[triangleIndex][1] = second;
                trianglePointers[triangleIndex][2] = first + 1;
                triangleIndex++;

                // Second triangle
                trianglePointers[triangleIndex][0] = first + 1;
                trianglePointers[triangleIndex][1] = second;
                trianglePointers[triangleIndex][2] = second + 1;
                triangleIndex++;
            }
        }
        return new Object3D(vertices, trianglePointers, color);
    }

    public static Object3D custom(int[][] vertices, int[][] trianglePointers, Color color) {
        return new Object3D(vertices, trianglePointers, color);
    }

}
