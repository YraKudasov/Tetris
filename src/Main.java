import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Glass glass = new Glass(5, 5); // Создание стакана размером 5x5


        Figure figure = new Figure(glass);
        figure.formFigure();

        // Вывод координат фигуры
        for (Cube cube : figure.getCubes()) {
            Cell cell = cube.getOwnerCell();
            System.out.println("Cell at (" + cell.getX() + ", " + cell.getY() + ")");
        }

// Получение доступа к клеткам
        List<List<Cell>> cells = glass.getAccessToCells();

// Вывод координат каждой клетки построчно, начиная с первой строки
        for (int y = 0; y < glass.getHeight(); y++) {
            for (int x = 0; x < glass.getWidth(); x++) {
                Cell cell = cells.get(y).get(x);
                System.out.print("(" + cell.getX() + ", " + cell.getY() + ") ");
            }
            System.out.println(); // Переход на новую строку после окончания вывода координат одной строки
        }

        figure.move(Direction.South);

        // Вывод координат фигуры после перемещения
        for (Cube cube : figure.getCubes()) {
            Cell cell = cube.getOwnerCell();
            System.out.println("Cube at (" + cell.getX() + ", " + cell.getY() + ")");
        }

        System.out.println("\n");
        figure.rotate();
        figure.rotate();
        // Вывод координат фигуры после перемещения
        for (Cube cube : figure.getCubes()) {
            Cell cell = cube.getOwnerCell();
            System.out.println("Cell at (" + cell.getX() + ", " + cell.getY() + ")");
        }

    }
}

