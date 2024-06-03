package code;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    private List<Cube> cubes;
    private Glass glass;

    public Heap(Glass glass) {
        this.glass = glass;
        this.cubes = new ArrayList<>();
    }

    public void addCube(Cube cube) {
        this.cubes.add(cube);
    }

    public int burnRow(int[] filledRows) {
        int countOfRows = filledRows.length;
        for (int rowIndex : filledRows) {
            for (int i = 0; i < this.cubes.size(); i++) {
                Cube cube = this.cubes.get(i);
                if (cube.getCoordY() == rowIndex) {
                    this.cubes.remove(cube);
                    i--; // Уменьшаем счетчик, чтобы не пропустить следующий элемент после удаления
                } else if (cube.getCoordY() < rowIndex) {
                    cube.move(Direction.South, this.glass); // Падение рядов, находящихся выше удаленного, на одну клетку вниз
                }
            }
        }
        return countOfRows;
    }

    public List<Cube> getAllCubes() {
        return new ArrayList<>(cubes);
    }

    public void clear() {
        cubes.clear();  // Очистить список кубиков
    }
}