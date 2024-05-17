import java.util.ArrayList;
import java.util.List;

public class Glass {
    private int height;
    private int width;
    private List<List<Cell>> cells;
    private Figure figure;
   // private Stack stack;

    public Glass(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new ArrayList<>();
        figure = null;
    //    stack = new Stack();
        createGlass();
    }

    private void createGlass() {
        for (int y = 0; y < height; y++) {
            List<Cell> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                Cell cell = new Cell(x, y);
                System.out.print("(" + cell.getX() + ", " + cell.getY() + ") ");

                // Добавляем стену справа для каждого элемента в верхнем ряду
                if (x == 0) {
                    cell.addWall(new Wall(Direction.East), Direction.East);
                }
                // Добавляем стену снизу для каждого элемента в правом ряду
                if (x == width - 1) {
                    cell.addWall(new Wall(Direction.West), Direction.West);
                }
                // Добавляем стену справа для каждого элемента в нижнем ряду
                if (y == height - 1) {
                    cell.addWall(new Wall(Direction.South), Direction.South);
                }
                row.add(cell);
            }
            cells.add(row);
            System.out.println();
        }
    }

    public List<List<Cell>> getAccessToCells() {
        return cells;
    }

    public Figure getFigure() {
        return figure;
    }

   /* public Stack getStack() {
        return stack;
    }
*/
   public void setFigure(Figure figure) {
       this.figure = figure;
       for (int i = 0; i < 4; i++) {
           Cell cell = getCell(figure.getCube(i).getCoordX(), figure.getCube(i).getCoordY());
           // Добавляем куб в клетку
           cell.addCube(figure.getCube(i));
       }
   }

    /*
    public void setStack(Stack stack) {
        this.stack = stack;
    }
*/
    public Cell getCell(int x, int y) {
        // Проверяем, что координаты x и y находятся в пределах стекла
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null; // Возвращаем null, если координаты вне стекла
        }
        return cells.get(y).get(x);
    }
    public void getInformationAboutStateOfRows() {
        // Здесь должна быть логика для определения состояния рядов
    }

    public void checkOwnOverflow() {
        // Здесь должна быть логика для проверки переполнения стакана
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}