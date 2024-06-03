package tests;

import code.Cube;
import code.Glass;
import code.Heap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeapTest {

    private Heap heap;
    @Test
    public void testGetCubes() {
        heap = new Heap(new Glass(10,10));
        for (int i = 0; i < 7; i++) {
            Cube cube = new Cube(i,2);
            heap.addCube(cube);
        }
        List<Cube> cubes = heap.getAllCubes();
        assertEquals(7, cubes.size());
    }

    @Test
    public void testClear() {
        heap = new Heap(new Glass(10,10));
        for (int i = 0; i < 9; i++) {
            Cube cube = new Cube(i,2);
            heap.addCube(cube);
        }
        heap.clear();
        List<Cube> cubes = heap.getAllCubes();
        assertEquals(0, cubes.size());
    }
    @Test
    public void testBurnRow() {
        heap = new Heap(new Glass(10,10));
        for (int i = 0; i < 9; i++) {
            Cube cube = new Cube(i,2);
            heap.addCube(cube);
        }
        List<Integer> filledRows = new ArrayList<>();
        filledRows.add(2);
        int[] filledRowsArray = new int[filledRows.size()];
        for (int i = 0; i < filledRows.size(); i++) {
            filledRowsArray[i] = filledRows.get(i);
        }

        heap.burnRow(filledRowsArray);
        List<Cube> cubes = heap.getAllCubes();
        assertEquals(0, cubes.size());
    }

    @Test
    public void testBurnRows() {
        heap = new Heap(new Glass(10,10));
        for (int i = 0; i < 9; i++) {
            Cube cube = new Cube(i,2);
            heap.addCube(cube);
            Cube cube1 = new Cube(i, 3);
            heap.addCube(cube1);
        }
        List<Integer> filledRows = new ArrayList<>();
        filledRows.add(2);
        filledRows.add(3);
        int[] filledRowsArray = new int[filledRows.size()];
        for (int i = 0; i < filledRows.size(); i++) {
            filledRowsArray[i] = filledRows.get(i);
        }

        heap.burnRow(filledRowsArray);
        List<Cube> cubes = heap.getAllCubes();
        assertEquals(0, cubes.size());
    }
}
