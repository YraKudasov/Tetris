import java.util.Random;

public class Figure {
    private Glass glass;
    private Cube[] cubes;
    private Cube boundingCube;
    private char shape;

    private int turnPos;

    public Figure(Glass glass) {
        this.glass = glass;
        this.cubes = new Cube[4];
        this.shape = getRandomShape();
        this.turnPos = 0;
        formFigure();
    }



    private char getRandomShape() {
        char[] shapes = {'I', 'J', 'L', 'O', 'S', 'T', 'Z'};
        Random random = new Random();
        return shapes[random.nextInt(shapes.length)];
    }

    private void formFigure() {
        int[][][] shapes = {
                {{0, 0}, {-1, 0}, {1, 0}, {0, -1}},  // 'I'
                {{0, 0}, {-1, 0}, {0, -1}, {1, -1}}, // 'J'
                {{0, 0}, {-1, 0}, {0, -1}, {-1, -1}}, // 'L'
                {{0, 0}, {-1, 0}, {1, 0}, {1, -1}, {-1, -1}}, // 'O'
                {{0, 0}, {-1, 0}, {0, -1}, {1, -1}, {0, -2}}, // 'S'
                {{0, 0}, {-1, 0}, {0, -1}, {1, -1}, {0, 1}}, // 'T'
                {{0, 0}, {-1, 0}, {0, -1}, {1, -1}, {1, 0}} // 'Z'
        };
        int[][] shapeCoords = shapes[shape - 'A'];  // Assuming 'A' corresponds to 'I', 'B' to 'J', and so on
        for (int i = 0; i < 4; i++) {
            int[] coords = shapeCoords[i];
            Cell cell = glass.getCell(coords[0], coords[1]);
            cubes[i] = new Cube(cell, this);
        }
        boundingCube = cubes[0];
    }

    public void fall() {
        for (Cube cube : cubes) {
            cube.move(Direction.South);
        }
    }

    public void move(Direction direction) {
        for (Cube cube : cubes) {
            cube.move(direction);
        }
    }

    public void rotate() {
        boundingCube.rotate(direction);
        for (Cube cube : cubes) {
            if (cube != boundingCube) {
                cube.copyPosition(boundingCube);
            }
        }
    }

    public void pushDown() {
        for (Cube cube : cubes) {
            cube.pushDown();
        }
    }
}

