import java.util.Random;

public class FactoryFigures {
    private Glass glass;

    public FactoryFigures(Glass glass) {
        this.glass = glass;
    }

    public Figure createRandomFigure() {
        int[][][] shapes = {
                {{2, 0}, {2, 1}, {1, 0}, {2, 0}, {3, 0}},  // 'T'
                {{2, 2}, {1, 2}, {2, 0}, {2, 1}, {2, 2}}, // 'J'
                {{1, 0}, {1, 1}, {2, 1}, {1, 0}, {2, 0}}  // 'O'
        };

        int shapeIndex = getRandomShape();
        int[][] shapeCoords = shapes[shapeIndex];
        Cube[] cubes = new Cube[4];
        for (int i = 1; i < 5; i++) {
            int[] coords = shapeCoords[i];
            cubes[i - 1] = new Cube(coords[0], coords[1]);
        }

        int[] boundingCoords = shapeCoords[0];
        Cube boundingCube = new Cube(boundingCoords[0], boundingCoords[1]);

        return new Figure(glass, cubes, boundingCube);
    }

    private int getRandomShape() {
        int[] shapes = {0, 1, 2};
        Random random = new Random();
        return shapes[random.nextInt(shapes.length)];
    }
}