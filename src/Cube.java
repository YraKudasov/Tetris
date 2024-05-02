public class Cube {
    private Cell ownerCell;
    private Figure ownerFigure;
    private boolean movable;

    private Glass glass;

    public Cube(Cell ownerCell, Figure ownerFigure) {
        this.ownerCell = ownerCell;
        this.ownerFigure = ownerFigure;
        this.movable = true;
    }


    public Cell getOwnerCell() {
        return this.ownerCell;
    }

    public void setOwnerCell(Cell cell) {
        this.ownerCell = cell;
    }

    public Figure getOwnerFigure() {
        return this.ownerFigure;
    }

    public void setOwnerFigure(Figure figure) {
        this.ownerFigure = figure;
    }

    public boolean isMovable() {
        return this.movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public void move(Direction direction) {
        // Предполагая, что возможны четыре направления: вверх, вниз, влево и вправо
        switch (direction) {
            case South:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию ниже текущей
                    Cell nextCell = glass.getCell(ownerCell.getX(), ownerCell.getY()+1);
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && !nextCell.hasCube()) {
                        // Перемещаем куб в следующую ячейку
                        nextCell.addCube(this);
                        // Удаляем куб из текущей ячейки
                        ownerCell.removeCube();
                        // Обновляем ссылку на текущую ячейку
                        ownerCell = nextCell;
                    }
                }
                break;
            case East:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию ниже текущей
                    Cell nextCell = glass.getCell(ownerCell.getX()-1, ownerCell.getY());
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && !nextCell.hasCube()) {
                        // Перемещаем куб в следующую ячейку
                        nextCell.addCube(this);
                        // Удаляем куб из текущей ячейки
                        ownerCell.removeCube();
                        // Обновляем ссылку на текущую ячейку
                        ownerCell = nextCell;
                    }
                }
                break;
            case West:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию ниже текущей
                    Cell nextCell = glass.getCell(ownerCell.getX()+1, ownerCell.getY());
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && !nextCell.hasCube()) {
                        // Перемещаем куб в следующую ячейку
                        nextCell.addCube(this);
                        // Удаляем куб из текущей ячейки
                        ownerCell.removeCube();
                        // Обновляем ссылку на текущую ячейку
                        ownerCell = nextCell;
                    }
                }
                break;
            default:
                // Неизвестное направление
                break;
        }
    }

    public void rotate(Direction direction){

    }



}