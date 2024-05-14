public class Cube {
    private Cell ownerCell;
    private Figure ownerFigure;
    private boolean movable;

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

    public boolean canMove(Direction direction) {
        switch (direction) {
            case South:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию ниже текущей
                    Cell nextCell = ownerFigure.glass.getCell(ownerCell.getX(), ownerCell.getY() + 1);
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && (!nextCell.hasCube() || nextCell.getCube().getOwnerFigure() == this.getOwnerFigure())) {
                        return true;
                    }
                }
                return false;
            case East:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию ниже текущей
                    Cell nextCell = ownerFigure.glass.getCell(ownerCell.getX() - 1, ownerCell.getY());
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && (!nextCell.hasCube() || nextCell.getCube().getOwnerFigure() == this.getOwnerFigure())) {
                        return true;
                    }
                }
                return false;
            case West:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию ниже текущей
                    Cell nextCell = ownerFigure.glass.getCell(ownerCell.getX() + 1, ownerCell.getY());
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && (!nextCell.hasCube() || nextCell.getCube().getOwnerFigure() == this.getOwnerFigure())) {
                        return true;
                    }
                }
                return false;
        }
        return false;
    }


    public void move(Direction direction) {
        // Предполагая, что возможны четыре направления: вверх, вниз, влево и вправо
        switch (direction) {
            case South:
                // Получаем ячейку, которая находится на одну позицию ниже текущей
                Cell nextCellSouth = ownerFigure.glass.getCell(ownerCell.getX(), ownerCell.getY() + 1);
                // Перемещаем куб в следующую ячейку
                nextCellSouth.addCube(this);
                // Удаляем куб из текущей ячейки
                ownerCell.removeCube();
                // Обновляем ссылку на текущую ячейку
                ownerCell = nextCellSouth;
                break;
            case East:
                // Получаем ячейку, которая находится на одну позицию ниже текущей
                Cell nextCellEast = ownerFigure.glass.getCell(ownerCell.getX() - 1, ownerCell.getY());
                // Перемещаем куб в следующую ячейку
                nextCellEast.addCube(this);
                // Удаляем куб из текущей ячейки
                ownerCell.removeCube();
                // Обновляем ссылку на текущую ячейку
                ownerCell = nextCellEast;
                break;
            case West:
                Cell nextCellWest = ownerFigure.glass.getCell(ownerCell.getX() + 1, ownerCell.getY());
                // Перемещаем куб в следующую ячейку
                nextCellWest.addCube(this);
                // Удаляем куб из текущей ячейки
                ownerCell.removeCube();
                // Обновляем ссылку на текущую ячейку
                ownerCell = nextCellWest;
                break;
            default:
                // Неизвестное направление
                break;
        }
    }

    public boolean canRotate(int boundingX, int boundingY){
        if (isMovable()) {
            // Получаем ячейку, которая находится на одну позицию ниже текущей
            Cell nextCell = ownerFigure.glass.getCell(boundingX - ownerCell.getY() + boundingY, boundingY + ownerCell.getX() - boundingX);
            // Проверяем, что следующая ячейка пуста
            if (nextCell != null && (!nextCell.hasCube() || nextCell.getCube().getOwnerFigure() == this.getOwnerFigure())) {
                return true;
            }
        }
        return false;
    }

    public void rotate(int boundingX, int boundingY) {
        // Получаем ячейку, которая находится на одну позицию ниже текущей
        Cell nextCell = ownerFigure.glass.getCell(boundingX - ownerCell.getY() + boundingY, boundingY + ownerCell.getX() - boundingX);
        // Перемещаем куб в следующую ячейку
        nextCell.addCube(this);
        // Удаляем куб из текущей ячейки
        ownerCell.removeCube();
        // Обновляем ссылку на текущую ячейку
        ownerCell = nextCell;

    }


}