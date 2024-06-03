package tests;


import code.Cube;
import code.Direction;
import code.Glass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CubeTest {

    private Cube cube;
    private Glass glass;

    @Test
    public void testSetMovable() {
        cube = new Cube(3,3);
        boolean testMovable = false;
        cube.setMovable(false);
        assertEquals(testMovable, cube.isMovable());

    }

    @Test
    public void testCanMoveSouth() {
        cube = new Cube(3,3);
        glass = new Glass(10, 10);
        boolean testCanMove = true;
        boolean canMove = cube.canMove(Direction.South, glass);
        assertEquals(testCanMove, canMove);
    }

    @Test
    public void testCanMoveWest() {
        cube = new Cube(3,3);
        glass = new Glass(10, 10);
        boolean testCanMove = true;
        boolean canMove = cube.canMove(Direction.West, glass);
        assertEquals(testCanMove, canMove);
    }

    @Test
    public void testCanMoveEast() {
        cube = new Cube(3,3);
        glass = new Glass(10, 10);
        boolean testCanMove = true;
        boolean canMove = cube.canMove(Direction.East, glass);
        assertEquals(testCanMove, canMove);
    }

    @Test
    public void testCantMoveSouthBorderGlass() {
        cube = new Cube(3,9);
        glass = new Glass(10, 10);
        boolean testCanMove = false;
        boolean canMove = cube.canMove(Direction.South, glass);
        assertEquals(testCanMove, canMove);
    }

    @Test
    public void testCantoveWestBorderGlass() {
        cube = new Cube(9,3);
        glass = new Glass(10, 10);
        boolean testCanMove = false;
        boolean canMove = cube.canMove(Direction.West, glass);
        assertEquals(testCanMove, canMove);
    }

    @Test
    public void testCantMoveEastBorderGlass() {
        cube = new Cube(0,3);
        glass = new Glass(10, 10);
        boolean testCanMove = false;
        boolean canMove = cube.canMove(Direction.East, glass);
        assertEquals(testCanMove, canMove);
    }

    @Test
    public void testCantMoveSouthHeap() {
        cube = new Cube(5,3);
        Cube cube_new = new Cube(5,4);
        cube_new.setMovable(false);
        glass = new Glass(10, 10);
        glass.getCell(5,4).addCube(cube_new);
        boolean testCanMove = false;
        boolean canMove = cube.canMove(Direction.South, glass);
        assertEquals(testCanMove, canMove);
    }
    @Test
    public void testCantMoveEastHeap() {
        cube = new Cube(5,3);
        Cube cube_new = new Cube(4,3);
        cube_new.setMovable(false);
        glass = new Glass(10, 10);
        glass.getCell(4,3).addCube(cube_new);
        boolean testCanMove = false;
        boolean canMove = cube.canMove(Direction.East, glass);
        assertEquals(testCanMove, canMove);
    }

    @Test
    public void testCantMoveWestHeap() {
        cube = new Cube(5,3);
        Cube cube_new = new Cube(6,3);
        cube_new.setMovable(false);
        glass = new Glass(10, 10);
        glass.getCell(6,3).addCube(cube_new);
        boolean testCanMove = false;
        boolean canMove = cube.canMove(Direction.West, glass);
        assertEquals(testCanMove, canMove);
    }


    @Test
    public void testMoveSouth() {
        cube = new Cube(3,3);
        glass = new Glass(10, 10);
        Cube expCube = new Cube(3, 4);
        cube.move(Direction.South, glass);
        assertEquals(expCube.getCoordX(), cube.getCoordX());
        assertEquals(expCube.getCoordY(), cube.getCoordY());
    }

    @Test
    public void testMoveWest() {
        cube = new Cube(3,3);
        glass = new Glass(10, 10);
        Cube expCube = new Cube(4, 3);
        cube.move(Direction.West, glass);
        assertEquals(expCube.getCoordX(), cube.getCoordX());
        assertEquals(expCube.getCoordY(), cube.getCoordY());
    }

    @Test
    public void testMoveEast() {
        cube = new Cube(3,3);
        glass = new Glass(10, 10);
        Cube expCube = new Cube(2, 3);
        cube.move(Direction.East, glass);
        assertEquals(expCube.getCoordX(), cube.getCoordX());
        assertEquals(expCube.getCoordY(), cube.getCoordY());
    }
}
