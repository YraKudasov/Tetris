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

    private boolean isWall(Direction direction) {
        // Проверяем, есть ли уже стена в указанном направлении
        for (Wall existingWall : walls) {
            if (existingWall.getDirection().equals(direction)) {
                return true;
            }
        }
        return false;
    }
    public List<Wall> getWalls() {
        return this.walls;
    }

    public boolean addWall(Wall wall, Direction direction) {
        // Проверяем, нет ли уже стены в указанном направлении
        if (!isWall(direction)) {
            this.walls.add(wall);
            return true;
        }
        return false;
    }

    public boolean removeWall(Wall wall, Direction direction) {
        // Перебираем все стены в массиве walls
        for (Wall existingWall : walls) {
            // Проверяем, совпадает ли направление стены с заданным направлением
            if (existingWall.getDirection().equals(direction)) {
                // Удаляем стену из массива walls
                walls.remove(existingWall);
                // Возвращаем, так как стена была найдена и удалена
                return true;
            }
        }
      return false;  // Если стена в заданном направлении не найдена, ничего не делаем
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
