# Java Graphics Engine

A simple 3D graphics engine implemented in Java using Swing. This project demonstrates the basic principles of 3D rendering including projection, object modeling, and drawing.

## Features

- **3D Object Rendering**: Render basic 3D objects, including a cube and a pyramid.
- **Projection**: Convert 3D coordinates to 2D screen coordinates.
- **Customizable Camera**: Adjust camera position and field of view (FOV).
- **Coloring**: Objects can be rendered with specified colors.

## Files

- `graphicsEngine.java`: The main class that initializes and displays the JFrame containing the 3D scene.
- `Screen.java`: The JPanel where the 3D objects are rendered.
- `Object3D.java`: Represents a 3D object and handles the rendering logic.
- `Polygon3D.java`: Handles the projection of 3D coordinates to 2D coordinates and draws the polygons.
- `PolygonObject.java`: Represents a 2D polygon and handles its drawing on the screen.

## How to Run

1. **Clone the repository**:
    ```sh
    git clone https://github.com/your-username/java-graphics-engine.git
    cd java-graphics-engine
    ```

2. **Compile the Java files**:
    ```sh
    javac *.java
    ```

3. **Run the application**:
    ```sh
    java graphicsEngine
    ```

## Project Status

This project is currently unfinished. Contributions and improvements are welcome.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

