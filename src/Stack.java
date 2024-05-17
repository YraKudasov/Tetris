import java.util.ArrayList;
import java.util.List;

public class Stack {
    private List<Cube> cubes;

    public Stack() {
        this.cubes = new ArrayList<>();
    }

    public void addCube(Cube cube) {
        this.cubes.add(cube);
    }

    public void burnRow() {
        // Предполагается, что кубики удаляются в порядке их добавления
        for (int i = 0; i < this.cubes.size(); i++) {
            Cube cube = this.cubes.get(i);
            // Здесь может быть логика для сжигания кубика, например, удаление из списка
            this.cubes.remove(cube);
            i--; // Уменьшаем счетчик, чтобы не пропустить следующий элемент после удаления
        }
    }
}