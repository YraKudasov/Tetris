import java.util.ArrayList;
import java.util.List;

public class Heap {
    private List<Cube> cubes;

    public Heap() {
        this.cubes = new ArrayList<>();
    }

    public void addCube(Cube cube) {
        this.cubes.add(cube);
    }

    public void burnRow(int[] filledRows) {
        for (int rowIndex : filledRows) {
            for (int i = 0; i < this.cubes.size(); i++) {
                Cube cube = this.cubes.get(i);
                if (cube.getCoordY() == rowIndex) {
                    this.cubes.remove(cube);
                    i--; // Уменьшаем счетчик, чтобы не пропустить следующий элемент после удаления
                } else if (cube.getCoordY() < rowIndex) {
                    cube.setCoordY(cube.getCoordY() + 1); // Падение рядов, находящихся выше удаленного, на одну клетку вниз
                }
            }
        }
    }
}