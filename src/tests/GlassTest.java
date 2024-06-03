package tests;

import code.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GlassTest {
    private Glass glass;

    @Test
    public void testGetCell() {
        glass = new Glass(10, 10);
        int expCellX = 4;
        int expCellY = 5;

        assertEquals(expCellX, glass.getCell(4,5).getX());
        assertEquals(expCellY, glass.getCell(4,5).getY());
    }
    @Test
    public void testGetFigure() {
        glass = new Glass(10, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        Figure figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        assertEquals(figure, glass.getFigure());
    }

    @Test
    public void testGetHeap() {
        glass = new Glass(10, 10);
        Heap heap = new Heap(glass);
        glass.setHeap(heap);
        assertEquals(heap, glass.getHeap());
    }

    @Test
    public void testIsRowNotFull() {
        glass = new Glass(10, 10);
        for (int i = 0; i < glass.getWidth()-1; i++){
            Cube cube = new Cube(i, 7);
            glass.getCell(i, 7).addCube(cube);
        }
        boolean testRowFull = false;
        assertEquals(testRowFull, glass.isRowFull(7));
    }


    @Test
    public void testIsRowFull() {
        glass = new Glass(10, 10);
        for (int i = 0; i < glass.getWidth(); i++){
            Cube cube = new Cube(i, 7);
            glass.getCell(i, 7).addCube(cube);
        }
        boolean testRowFull = true;
        assertEquals(testRowFull, glass.isRowFull(7));
    }

    @Test
    public void testOneFilledRow() {
        glass = new Glass(10, 10);
        for (int i = 0; i < glass.getWidth(); i++){
            Cube cube = new Cube(i, 7);
            glass.getCell(i, 7).addCube(cube);
        }
        int[] filledRows = glass.getFilledRows();
        int expLength = 1;
        assertEquals(expLength, filledRows.length);
    }

    @Test
    public void testTwoFilledRows() {
        glass = new Glass(10, 10);
        for (int i = 0; i < glass.getWidth(); i++){
            Cube cube = new Cube(i, 7);
            Cube cube1 = new Cube(i, 6);
            glass.getCell(i, 7).addCube(cube);
            glass.getCell(i, 6).addCube(cube1);
        }
        int[] filledRows = glass.getFilledRows();
        int expLength = 2;
        assertEquals(expLength, filledRows.length);
    }


    @Test
    public void testIsNotOverflow() {
        glass = new Glass(10, 10);
        for (int i = 0; i < glass.getWidth(); i++){
            Cube cube = new Cube(i, 7);
            Cube cube1 = new Cube(i, 6);
            glass.getCell(i, 7).addCube(cube);
            glass.getCell(i, 6).addCube(cube1);
        }
        boolean expIsOverflow = false;
        assertEquals(expIsOverflow, glass.isOverflow());
    }

    @Test
    public void testIsOverflow() {
        glass = new Glass(10, 10);
        Cube cube = new Cube(5, 3);
        glass.getCell(5,3).addCube(cube);

        boolean expIsOverflow = true;
        assertEquals(expIsOverflow, glass.isOverflow());
    }

    @Test
    public void testDeleteFigure() {
        glass = new Glass(10, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        Figure figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);

        assertEquals(figure, glass.getFigure());

        glass.deleteFigure();

        assertEquals(null, glass.getFigure());
    }

    @Test
    public void testGetHeight() {
        glass = new Glass(10, 10);

        int expHeight = 10;
        assertEquals(expHeight, glass.getHeight());
    }

    @Test
    public void testGetWidth() {
        glass = new Glass(10, 10);

        int expWidth = 10;
        assertEquals(expWidth, glass.getHeight());
    }

    @Test
    public void testClear() {
        glass = new Glass(10, 10);
        FactoryFigures factoryFigures = new FactoryFigures(glass);
        Figure figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
        Heap heap = new Heap(glass);
        glass.setHeap(heap);

        glass.clearGlass();

        assertEquals(null, glass.getFigure());
        assertEquals(0, glass.getHeap().getAllCubes().size());
    }

}
