import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Glass glass = new Glass(5, 5); // Создание стакана размером 5x5

        FactoryFigures factory = new FactoryFigures(glass);

        Figure figure = factory.createRandomFigure();
        glass.setFigure(figure);


        // Вывод координат фигуры
        for (Cube cube : figure.getCubes()) {
            System.out.println("Cell at (" + cube.getCoordX() + ", " + cube.getCoordY() + ")");
        }


// Вывод координат каждой клетки построчно, начиная с первой строки
        for (int y = 0; y < glass.getHeight(); y++) {
            for (int x = 0; x < glass.getWidth(); x++) {
                System.out.print("(" + glass.getCell(x,y).getX() + ", " + glass.getCell(x,y).getY() + ") ");
                if (glass.getCell(x,y).hasCube()) {
                    System.out.print(" да ");
                } else {
                    System.out.print(" нет ");
                }
            }
            System.out.println(); // Переход на новую строку после окончания вывода координат одной строки
        }

        figure.move(Direction.South);
        figure.rotate();
        System.out.println("\n");

        for (int y = 0; y < glass.getHeight(); y++) {
            for (int x = 0; x < glass.getWidth(); x++) {
                System.out.print("(" + glass.getCell(x,y).getX() + ", " + glass.getCell(x,y).getY() + ") ");
                if (glass.getCell(x,y).hasCube()) {
                    System.out.print(" да ");
                } else {
                    System.out.print(" нет ");
                }
            }
            System.out.println(); // Переход на новую строку после окончания вывода координат одной строки
        }


    }
}

