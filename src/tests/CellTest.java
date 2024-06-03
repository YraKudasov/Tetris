package tests;

import code.Cell;
import code.Cube;
import code.Direction;
import code.Wall;
import org.junit.Assert;
import org.junit.Test;

public class CellTest {
    private Cell cell;
    private Cube cube;
    private Wall wall;
    @Test
    public void testAddCube() {
        cube = new Cube(3,4);
        cell = new Cell(3,4);
        cell.addCube(cube);
        boolean expHasCube = true;
        Assert.assertEquals(expHasCube, cell.hasCube());

    }

    @Test
    public void testRemoveCube() {
        cube = new Cube(3,4);
        cell = new Cell(3,4);
        cell.addCube(cube);
        cell.removeCube();
        boolean expHasCube = false;
        Assert.assertEquals(expHasCube, cell.hasCube());

    }

    @Test
    public void testAddWallSouth() {
        cell = new Cell(3,4);
        wall = new Wall(Direction.South);

        cell.addWall(wall, Direction.South);

        boolean expWall = true;
        Assert.assertEquals(expWall, cell.isWall(Direction.South));
    }

    @Test
    public void testAddWallNorth() {
        cell = new Cell(3,4);
        wall = new Wall(Direction.North);

        cell.addWall(wall, Direction.North);

        boolean expWall = true;
        Assert.assertEquals(expWall, cell.isWall(Direction.North));
    }

    @Test
    public void testAddWallEast() {
        cell = new Cell(3,4);
        wall = new Wall(Direction.East);

        cell.addWall(wall, Direction.East);

        boolean expWall = true;
        Assert.assertEquals(expWall, cell.isWall(Direction.East));
    }

    @Test
    public void testAddWallWest() {
        cell = new Cell(3,4);
        wall = new Wall(Direction.West);

        cell.addWall(wall, Direction.West);

        boolean expWall = true;
        Assert.assertEquals(expWall, cell.isWall(Direction.West));
    }
}
