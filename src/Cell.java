import java.util.ArrayList;
import java.util.List;

public class Cell {

    private int x;
    private int y;
    private List<Cell> neighborCells;
    private Glass ownerTetris;
    private List<Wall> walls;
    private Cube cube;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.neighborCells = new ArrayList<>();
        this.ownerTetris = null;
        this.walls = new ArrayList<>();
        this.cube = null;
    }

    public void setNeighbor(Cell cell) {
        this.neighborCells.add(cell);
        cell.neighborCells.add(this);
    }

    public List<Wall> getWalls() {
        return this.walls;
    }

    public void addWall(Wall wall) {
        this.walls.add(wall);
    }

    public void removeWall(Wall wall) {
        this.walls.remove(wall);
    }

    public Cube getCube() {
        return this.cube;
    }

    public void addCube(Cube cube) {
        this.cube = cube;
    }

    public void removeCube() {
        this.cube = null;
    }

    // Дружественный метод для получения координаты X
    public int getX() {
        return x;
    }

    // Дружественный метод для получения координаты Y
    public int getY() {
        return y;
    }

    public boolean hasCube() {
        return this.cube != null;
    }
}
