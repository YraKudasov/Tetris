import java.util.*;

public class Figure implements canMoveAndRotate {
    protected Glass glass;
    private Cube[] cubes;
    private Cube boundingCube;


    public Figure(Glass glass, Cube[] cubes, Cube boundingCube) {
        this.cubes = cubes;
        this.glass = glass;
        this.boundingCube = boundingCube;
    }


    @Override
    public boolean canMove(Direction direction) {
        int countCubes = 0;
        for (int i = 0; i < cubes.length; i++) {
            Cube cube = cubes[i];
            if (cube.canMove(direction, glass)) {
                countCubes++;
            }
        }
        return countCubes == cubes.length;
    }

    @Override
    public void move(Direction direction) {
        if (canMove(direction)) {
            if (direction == Direction.West) {
                Arrays.sort(cubes, Comparator.comparingInt(cube -> ((Cube) cube).getCoordX()).reversed());
                for (int i = 0; i < cubes.length; i++) {
                    Cube cube = cubes[i];
                    cube.move(direction, i, glass);
                }
                boundingCube.setCoordX(boundingCube.getCoordX() + 1);
            }
            else if (direction == Direction.East) {
                Arrays.sort(cubes, Comparator.comparingInt(cube -> ((Cube) cube).getCoordX()));
                for (int i = 0; i < cubes.length; i++) {
                    Cube cube = cubes[i];
                    cube.move(direction, i, glass);
                }
                boundingCube.setCoordX(boundingCube.getCoordX() - 1);
            }
            else if (direction == Direction.South) {
                Arrays.sort(cubes, Comparator.comparingInt(cube -> ((Cube) cube).getCoordY()).reversed());
                for (int i = 0; i < cubes.length; i++) {
                    Cube cube = cubes[i];
                    cube.move(direction, i, glass);
                }
                boundingCube.setCoordY(boundingCube.getCoordY() + 1);
            }
        }

        }

    @Override
    public boolean canRotate(int[] waitingCubes) {
        int countCubes = 0;
        for (int i = 0; i < cubes.length; i++) {
            Cube cube = cubes[i];
            if (cube.getCoordX() != boundingCube.getCoordX() || cube.getCoordY() != boundingCube.getCoordY()) {
                if (cube.canRotate(boundingCube.getCoordX(), boundingCube.getCoordY(),  glass, waitingCubes, i)) {
                    countCubes++;
                }
            }
        }
        if (countCubes != cubes.length-1) {
            throw new IllegalArgumentException("Figure can't rotate");
        }
        return countCubes == cubes.length-1;
    }
    @Override
    public void rotate() {
        int[] waitingCubes = new int[cubes.length];
        Arrays.fill(waitingCubes, 25);
        if (canRotate(waitingCubes)) {
            for (int i = 0; i < cubes.length; i++){
                Cube cube = cubes[i];
                if ((cube.getCoordX() != boundingCube.getCoordX() || cube.getCoordY() != boundingCube.getCoordY()) && i != waitingCubes[i]){
                    cube.rotate(boundingCube.getCoordX(), boundingCube.getCoordY(), i, glass);
                }
            }
            for (int i = 0; i < waitingCubes.length; i++){
                Cube cube = cubes[i];
                if(waitingCubes[i] != 25){
                    cube.rotate(boundingCube.getCoordX(), boundingCube.getCoordY(), i, glass);
                }
            }
        }
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

