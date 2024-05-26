import java.util.ArrayList;
import java.util.List;

public class Glass {
    private int height;
    private int width;
    private List<List<Cell>> cells;
    private Figure figure;

    private Heap heap;
    // private Stack stack;

    public Glass(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new ArrayList<>();
        figure = null;
        heap = new Heap();
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

    public Heap getHeap() {
        return heap;
    }


    public void setFigure(Figure figure) {
        this.figure = figure;
        for (int i = 0; i < 4; i++) {
            Cell cell = getCell(figure.getCube(i).getCoordX(), figure.getCube(i).getCoordY());
            // Добавляем куб в клетку
            cell.addCube(figure.getCube(i));
            System.out.print("(" + cell.getX() + ", " + cell.getY() + ") ");
        }
        System.out.println();
    }


    public void setStack(Heap heap) {
        this.heap = heap;
    }

    public Cell getCell(int x, int y) {
        // Проверяем, что координаты x и y находятся в пределах стекла
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null; // Возвращаем null, если координаты вне стекла
        }
        return cells.get(y).get(x);
    }

    public boolean isRowFull(int rowIndex) {
        for (int x = 0; x < getWidth(); x++) {
            Cell cell = getCell(x, rowIndex);
            if (cell == null || !cell.hasCube()) {
                return false; // Если хотя бы одна клетка в ряду пуста, возвращаем false
            }
        }
        return true; // Если все клетки в ряду заполнены, возвращаем true
    }

    public int[] getFilledRows() {
        List<Integer> filledRows = new ArrayList<>();
        for (int y = 0; y < getHeight(); y++) {
            if (isRowFull(y)) {
                filledRows.add(y);
            }
        }
        int[] filledRowsArray = new int[filledRows.size()];
        for (int i = 0; i < filledRows.size(); i++) {
            filledRowsArray[i] = filledRows.get(i);
        }
        return filledRowsArray;
    }

    public boolean isOverflow() {
        // Проходимся по всем клеткам второго ряда
        // Height - высота стакана
        int y = 2;
        for (int x = 0; x < getWidth(); x++) { // Width - ширина стакана
            Cell cell = getCell(x, y); // Получаем клетку по координатам x и y
            if (cell != null && cell.hasCube()) { // Проверяем, есть ли в клетке кубик
                return true; // Если есть, возвращаем true, что означает переполнение
            }

        }
        return false; // Если ни в одной клетке второго ряда кубиков нет, возвращаем false
    }

    public void deleteFigure() {
        // Выполните все необходимые операции с объектом figure

        figure = null; // Удалите ссылку на объект figure
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


}