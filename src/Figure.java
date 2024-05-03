import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Figure {
    protected Glass glass;
    private Cube[] cubes;

    private List<Wall> walls = new ArrayList<>();
    private Cube boundingCube;
    private int shape;

    private int turnPos;

    public Figure(Glass glass) {
        this.glass = glass;
        this.cubes = new Cube[4];
        this.turnPos = 0;
        formFigure();
    }



    private int getRandomShape() {
        int[] shapes = {0, 1};  // Indices of shapes
        Random random = new Random();
        return shapes[random.nextInt(shapes.length)];
    }

    public void formFigure() {
        int[][][] shapes = {
                {{2, 0}, {2, 0}, {1, 0}, {3, 0}, {2, 1}},  // 'T'
                {{2, 2}, {2, 2}, {2, 0}, {2, 1}, {1, 2}}  // 'J'
        };

        int shapeIndex = getRandomShape();
        int[][] shapeCoords = shapes[shapeIndex];
        for (int i = 1; i < 5; i++) {
            int[] coords = shapeCoords[i];
            Cell cell = glass.getCell(coords[0], coords[1]);

            cubes[i-1] = new Cube(cell, this);
        }

        boundingCube = cubes[0];
    }


    private boolean canMove(Direction direction){
        int countCubes = 0;
        for (Cube cube : cubes) {
            if(cube.canMove(direction)){
                countCubes++;
            }
        }
        return countCubes == cubes.length;
    }

    public void move(Direction direction) {
        if(canMove(direction)){
            for (Cube cube : cubes) {
                cube.move(direction);
            }
        }
    }

  /*  public void rotate() {
        boundingCube.rotate(direction);
        for (Cube cube : cubes) {
            if (cube != boundingCube) {
                cube.copyPosition(boundingCube);
            }
        }
    }
*/

    // Метод для добавления стены к клетке
    public void addWall(Wall wall) {
        walls.add(wall);
    }

    // Метод для удаления стены из клетки
    public void removeWall(Wall wall) {
        walls.remove(wall);
    }

    // Метод для получения списка всех стен клетки
    public List<Wall> getWalls() {
        return walls;
    }

    public Cube[] getCubes() {
        return cubes;
    }
}

