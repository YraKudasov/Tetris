import java.awt.*;
import java.util.*;

import events.FigureActionEvent;
import events.FigureActionListener;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Figure implements canMoveAndRotate {
    protected Glass glass;
    private Cube[] cubes;
    private Cube boundingCube;

    private ShadowOfFigure shadow;

    private Color color;


    public Figure(Glass glass, Cube[] cubes, Cube boundingCube, Color color) {
        this.cubes = cubes;
        this.glass = glass;
        this.boundingCube = boundingCube;
        this.color = color;
        this.shadow = new ShadowOfFigure(color, this);
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
        if (direction == Direction.South) {
            if (!canMove(direction)) {
                fireFigureFell();
            } else {
                Arrays.sort(cubes, Comparator.comparingInt(cube -> ((Cube) cube).getCoordY()).reversed());
                for (int i = 0; i < cubes.length; i++) {
                    Cube cube = cubes[i];
                    cube.move(direction, glass);
                }
                boundingCube.setCoordY(boundingCube.getCoordY() + 1);
                System.out.print("вниз ");
                shadow.updateShadow(getCubes());
                fireFigureMoveDown();
            }
        } else {
            if (canMove(direction)) {
                if (direction == Direction.West) {
                    Arrays.sort(cubes, Comparator.comparingInt(cube -> ((Cube) cube).getCoordX()).reversed());
                    for (int i = 0; i < cubes.length; i++) {
                        Cube cube = cubes[i];
                        cube.move(direction, glass);
                    }
                    boundingCube.setCoordX(boundingCube.getCoordX() + 1);
                    System.out.print("вправо ");
                    shadow.updateShadow(getCubes());
                } else if (direction == Direction.East) {
                    Arrays.sort(cubes, Comparator.comparingInt(cube -> ((Cube) cube).getCoordX()));
                    for (int i = 0; i < cubes.length; i++) {
                        Cube cube = cubes[i];
                        cube.move(direction, glass);
                    }
                    boundingCube.setCoordX(boundingCube.getCoordX() - 1);
                    System.out.print("влево ");
                    shadow.updateShadow(getCubes());
                }
            }
        }
    }


    @Override
    public boolean canRotate(int[] waitingCubes) {
        int countCubes = 0;
        for (int i = 0; i < cubes.length; i++) {
            Cube cube = cubes[i];
            if (cube.getCoordX() != boundingCube.getCoordX() || cube.getCoordY() != boundingCube.getCoordY()) {
                if (cube.canRotate(boundingCube.getCoordX(), boundingCube.getCoordY(), glass, waitingCubes, i)) {
                    countCubes++;
                }
            }
        }
        if (countCubes != cubes.length - 1) {
            throw new IllegalArgumentException("Figure can't rotate");
        }
        return countCubes == cubes.length - 1;
    }

    @Override
    public void rotate() {
        int[] waitingCubes = new int[cubes.length];
        Arrays.fill(waitingCubes, 25);
        if (canRotate(waitingCubes)) {
            for (int i = 0; i < cubes.length; i++) {
                Cube cube = cubes[i];
                if ((cube.getCoordX() != boundingCube.getCoordX() || cube.getCoordY() != boundingCube.getCoordY()) && i != waitingCubes[i]) {
                    cube.rotate(boundingCube.getCoordX(), boundingCube.getCoordY(), i, glass);
                }
            }
            for (int i = 0; i < waitingCubes.length; i++) {
                Cube cube = cubes[i];
                if (waitingCubes[i] != 25) {
                    cube.rotate(boundingCube.getCoordX(), boundingCube.getCoordY(), i, glass);
                }
            }
        }
        shadow.updateShadow(getCubes());
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Cube[] getCubes() {
        return cubes;
    }

    public ShadowOfFigure getShadow() {
        return shadow;
    }

    public Glass getGlass() {return glass;}

    public Cube getCube(int index) {
        // Проверяем, что индекс находится в пределах массива кубов
        if (index < 0 || index >= cubes.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        // Возвращаем куб по заданному индексу
        return cubes[index];
    }

    //-----------------------------------------------Event----------------------------
    ArrayList<FigureActionListener> _listeners = new ArrayList<>();


    public void addFigureActionListener(FigureActionListener l) {
        _listeners.add(l);
    }


    public void removeFigureActionListener(FigureActionListener l) {
        _listeners.remove(l);
    }


    protected void fireFigureFell() {
        FigureActionEvent e = new FigureActionEvent(this);
        for (FigureActionListener l : _listeners) {
            l.onFigureFell(e);
        }
    }

    protected void fireFigureMoveDown() {
        FigureActionEvent e = new FigureActionEvent(this);
        for (FigureActionListener l : _listeners) {
            l.onFigureMoveDown(e);
        }
    }
}

