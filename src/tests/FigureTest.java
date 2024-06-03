package tests;

import code.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FigureTest {




    private Figure figure;


    @Test
    public void testGetCubes() {
        Glass glass = new Glass(10, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        int expLengthOfCubes = 4;
        assertEquals(expLengthOfCubes, figure.getCubes().length);
    }

    @Test
    public void testCanMoveSouth() {
        Glass glass = new Glass(10, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        boolean testMovable = true;
        assertEquals(testMovable, figure.canMove(Direction.South));
    }

    @Test
    public void testCanMoveEast() {
        Glass glass = new Glass(10, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        boolean testMovable = true;
        assertEquals(testMovable, figure.canMove(Direction.East));
    }

    @Test
    public void testCanMoveWest() {
        Glass glass = new Glass(10, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        boolean testMovable = true;
        assertEquals(testMovable, figure.canMove(Direction.West));
    }

    @Test
    public void testCantMoveSouth() {
        Glass glass = new Glass(3, 7);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        boolean testMovable = false;
        assertEquals(testMovable, figure.canMove(Direction.South));
    }

    @Test
    public void testCantMoveEast() {
        Glass glass = new Glass(7, 7);
        int b = 0;
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        for (int i = 0; i < figure.getCubes().length; i++) {
            Cube cube = figure.getCubes()[i];
            if (cube.getCoordX() == 3) {
                b = 3;
                break;
            } else {
                b = 4;
            }
        }

        for (int i = 0; i < b; i++) {
            figure.move(Direction.East);
        }
        boolean testMovable = false;
        assertEquals(testMovable, figure.canMove(Direction.East));

    }

    @Test
    public void testCantMoveWest() {
        Glass glass = new Glass(7, 7);
        int b = 0;
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        for (int i = 0; i < figure.getCubes().length; i++) {
            Cube cube = figure.getCubes()[i];
            if (cube.getCoordX() == 5) {
                b = 2;
                break;
            } else {
                b = 1;
            }
        }

        for (int i = 0; i < b; i++) {
            figure.move(Direction.West);
        }
        boolean testMovable = false;
        assertEquals(testMovable, figure.canMove(Direction.West));
    }


    @Test
    public void testMoveSouth() {
        Glass glass = new Glass(7, 7);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        int expCoordX = figure.getBoundingCube().getCoordX();
        int expCoordY = figure.getBoundingCube().getCoordY() + 1;
        figure.move(Direction.South);

        assertEquals(expCoordX, figure.getBoundingCube().getCoordX());
        assertEquals(expCoordY, figure.getBoundingCube().getCoordY());
    }

    @Test
    public void testMoveEast() {
        Glass glass = new Glass(7, 7);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        int expCoordX = figure.getBoundingCube().getCoordX() - 1;
        int expCoordY = figure.getBoundingCube().getCoordY();
        figure.move(Direction.East);

        assertEquals(expCoordX, figure.getBoundingCube().getCoordX());
        assertEquals(expCoordY, figure.getBoundingCube().getCoordY());
    }


    @Test
    public void testMoveWest() {
        Glass glass = new Glass(7, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        int expCoordX = figure.getBoundingCube().getCoordX() + 1;
        int expCoordY = figure.getBoundingCube().getCoordY();
        figure.move(Direction.West);

        assertEquals(expCoordX, figure.getBoundingCube().getCoordX());
        assertEquals(expCoordY, figure.getBoundingCube().getCoordY());
    }

    @Test
    public void testCanRotate() {
        Glass glass = new Glass(7, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        int[] waitingCubes = new int[figure.getCubes().length];
        Arrays.fill(waitingCubes, 25);
        figure.move(Direction.South);
        boolean expRotate = true;
        assertEquals(expRotate, figure.canRotate(waitingCubes));
    }


    @Test
    public void testRotate() {
        Glass glass = new Glass(7, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        figure.move(Direction.South);
        int expCoordX = figure.getBoundingCube().getCoordX() - figure.getCube(1).getCoordY() + figure.getBoundingCube().getCoordY();
        int expCoordY = figure.getBoundingCube().getCoordY() + figure.getCube(1).getCoordX() - figure.getBoundingCube().getCoordX();
        figure.rotate();
        assertEquals(expCoordX, figure.getCube(1).getCoordX());
        assertEquals(expCoordY, figure.getCube(1).getCoordY());

    }


}
