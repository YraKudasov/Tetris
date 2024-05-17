import java.util.*;

public class Figure {
    protected Glass glass;
    private Cube[] cubes;

    private List<Wall> walls = new ArrayList<>();
    private Cube boundingCube;
    private int shape;

    private int turnPos;

    public Figure(Glass glass) {
        this.cubes = new Cube[4];
        this.glass = glass;
        this.turnPos = 0;
        formFigure();
    }


    private int getRandomShape() {
        int[] shapes = {1};  // Indices of shapes
        Random random = new Random();
        return shapes[random.nextInt(shapes.length)];
    }

    public void formFigure() {
        int[][][] shapes = {
                {{2, 0}, {2, 1}, {1, 0}, {2, 0}, {3, 0}},  // 'T'
                {{2, 2}, {1, 2}, {2, 0}, {2, 1}, {2, 2}}, // 'J'
                {{1, 0}, {1, 1}, {2, 1}, {1, 0}, {2, 0}}  // 'O'
        };

        int shapeIndex = getRandomShape();
        int[][] shapeCoords = shapes[shapeIndex];
        for (int i = 1; i < 5; i++) {
            int[] coords = shapeCoords[i];
            cubes[i - 1] = new Cube( coords[0], coords[1], this);
        }

        int[] boundingCoords = shapeCoords[0];
        boundingCube = new Cube(boundingCoords[0], boundingCoords[1], this);
    }


    private boolean canMove(Direction direction) {
        int countCubes = 0;
        for (int i = 0; i < cubes.length; i++) {
            Cube cube = cubes[i];
            if (cube.canMove(direction, i)) {
                countCubes++;
            }
        }
        return countCubes == cubes.length;
    }

    public void move(Direction direction) {
        if (canMove(direction)) {
            if (direction == Direction.West) {
                Arrays.sort(cubes, Comparator.comparingInt(cube -> ((Cube) cube).getCoordX()).reversed());
                for (int i = 0; i < cubes.length; i++) {
                    Cube cube = cubes[i];
                    cube.move(direction, i);
                }
                boundingCube.setCoordX(boundingCube.getCoordX() + 1);
            }
            else if (direction == Direction.East) {
                Arrays.sort(cubes, Comparator.comparingInt(cube -> ((Cube) cube).getCoordX()));
                for (int i = 0; i < cubes.length; i++) {
                    Cube cube = cubes[i];
                    cube.move(direction, i);
                }
                boundingCube.setCoordX(boundingCube.getCoordX() - 1);
            }
            else if (direction == Direction.South) {
                Arrays.sort(cubes, Comparator.comparingInt(cube -> ((Cube) cube).getCoordY()).reversed());
                for (int i = 0; i < cubes.length; i++) {
                    Cube cube = cubes[i];
                    cube.move(direction, i);
                }
                boundingCube.setCoordY(boundingCube.getCoordY() + 1);
            }
        }

        }


    private boolean canRotate(int[] waitingCubes) {
        int countCubes = 0;
        for (int i = 0; i < cubes.length; i++) {
            Cube cube = cubes[i];
            if (cube.getCoordX() != boundingCube.getCoordX() || cube.getCoordY() != boundingCube.getCoordY()) {
                if (cube.canRotate(boundingCube.getCoordX(), boundingCube.getCoordY(), i, waitingCubes)) {
                    countCubes++;
                }
            }
        }
        if (countCubes != cubes.length-1) {
            System.out.println();
            System.out.print(countCubes);
            System.out.println();
            throw new IllegalArgumentException("Figure can't rotate");
        }
        return countCubes == cubes.length-1;
    }

    public void rotate() {
        int[] waitingCubes = new int[cubes.length];
        Arrays.fill(waitingCubes, 25);
        if (canRotate(waitingCubes)) {
            for (int i = 0; i < cubes.length; i++){
                Cube cube = cubes[i];
                if ((cube.getCoordX() != boundingCube.getCoordX() || cube.getCoordY() != boundingCube.getCoordY()) && i != waitingCubes[i]){
                    cube.rotate(boundingCube.getCoordX(), boundingCube.getCoordY(), i);
                }
            }
            for (int i = 0; i < waitingCubes.length; i++){
                Cube cube = cubes[i];
                if(waitingCubes[i] != 25){
                    cube.rotate(boundingCube.getCoordX(), boundingCube.getCoordY(), i);
                }
            }
        }
    }


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

    public Cube getCube(int index) {
        // Проверяем, что индекс находится в пределах массива кубов
        if (index < 0 || index >= cubes.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        // Возвращаем куб по заданному индексу
        return cubes[index];
    }
}

