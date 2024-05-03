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
                row.add(new Cell(x, y));
            }
            cells.add(row);
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
        return new Cell(x, y); // Возвращаем ячейку с заданными координатами
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