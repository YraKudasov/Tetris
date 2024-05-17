public class Cube {

    private Figure ownerFigure;
    private boolean movable;

    private int cubeX;
    private int cubeY;

    public Cube(int coordX, int coordY, Figure ownerFigure) {
        this.cubeX = coordX;
        this.cubeY = coordY;
        this.ownerFigure = ownerFigure;
        this.movable = true;
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

    public boolean canMove(Direction direction, int index) {
        switch (direction) {
            case South:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию ниже текущей
                    Cell nextCell = ownerFigure.glass.getCell(ownerFigure.getCube(index).cubeX, ownerFigure.getCube(index).cubeY + 1);
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && (!nextCell.hasCube() || nextCell.getCube().getOwnerFigure() == this.getOwnerFigure())) {
                        return true;
                    }
                }
                return false;
            case East:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию левее текущей
                    Cell nextCell = ownerFigure.glass.getCell(ownerFigure.getCube(index).cubeX - 1, ownerFigure.getCube(index).cubeY);
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && (!nextCell.hasCube() || nextCell.getCube().getOwnerFigure() == this.getOwnerFigure())) {
                        return true;
                    }
                }
                return false;
            case West:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию правее текущей
                    Cell nextCell = ownerFigure.glass.getCell(ownerFigure.getCube(index).cubeX + 1, ownerFigure.getCube(index).cubeY);
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && (!nextCell.hasCube() || nextCell.getCube().getOwnerFigure() == this.getOwnerFigure())) {
                        return true;
                    }
                }
                return false;
        }
        return false;
    }


    public void move(Direction direction, int index) {
        // Предполагая, что возможны четыре направления: вверх, вниз, влево и вправо
        switch (direction) {
            case South:
                // Получаем ячейку, которая находится на одну позицию ниже текущей
                Cell nextCellSouth = ownerFigure.glass.getCell(ownerFigure.getCube(index).cubeX, ownerFigure.getCube(index).cubeY+1);
                // Перемещаем куб в следующую ячейку
                nextCellSouth.addCube(this);
                // Удаляем куб из текущей ячейки
                ownerFigure.glass.getCell(ownerFigure.getCube(index).cubeX, ownerFigure.getCube(index).cubeY).removeCube();
                ownerFigure.getCube(index).cubeY ++;
                break;
            case East:
                // Получаем ячейку, которая находится на одну позицию левее текущей
                Cell nextCellEast = ownerFigure.glass.getCell(ownerFigure.getCube(index).cubeX - 1, ownerFigure.getCube(index).cubeY);
                // Перемещаем куб в следующую ячейку
                nextCellEast.addCube(this);
                // Удаляем куб из текущей ячейки
                ownerFigure.glass.getCell(ownerFigure.getCube(index).cubeX, ownerFigure.getCube(index).cubeY).removeCube();
                ownerFigure.getCube(index).cubeX --;
                break;
            case West:
                // Получаем ячейку, которая находится на одну позицию правее текущей
                Cell nextCellWest = ownerFigure.glass.getCell(ownerFigure.getCube(index).cubeX + 1, ownerFigure.getCube(index).cubeY);
                // Перемещаем куб в следующую ячейку
                nextCellWest.addCube(this);
                // Удаляем куб из текущей ячейки
                ownerFigure.glass.getCell(ownerFigure.getCube(index).cubeX, ownerFigure.getCube(index).cubeY).removeCube();
                ownerFigure.getCube(index).cubeX ++;
                break;
            default:
                // Неизвестное направление
                break;
        }
    }

    public boolean canRotate(int boundingX, int boundingY, int index, int[] waitingCubes){
        if (isMovable()) {
            // Получаем ячейку, которая находится на одну позицию ниже текущей
            Cell nextCell = ownerFigure.glass.getCell(boundingX - ownerFigure.getCube(index).cubeY + boundingY, boundingY + ownerFigure.getCube(index).cubeX - boundingX);
            // Проверяем, что следующая ячейка пуста
            if (nextCell != null && (!nextCell.hasCube() || nextCell.getCube().getOwnerFigure() == this.getOwnerFigure())) {
                if (nextCell.hasCube() && nextCell.getCube().getOwnerFigure() == this.getOwnerFigure()) {
                    waitingCubes[index] = index;
                }
                return true;
            }
        }
        return false;
    }

    public void rotate(int boundingX, int boundingY, int index) {
        // Получаем ячейку, которая находится на одну позицию ниже текущей
        Cell nextCell = ownerFigure.glass.getCell(boundingX - ownerFigure.getCube(index).cubeY + boundingY, boundingY + ownerFigure.getCube(index).cubeX - boundingX);
        // Перемещаем куб в следующую ячейку
        nextCell.addCube(this);
        // Удаляем куб из текущей ячейки
        ownerFigure.glass.getCell(ownerFigure.getCube(index).cubeX, ownerFigure.getCube(index).cubeY).removeCube();

        int oldX = ownerFigure.getCube(index).cubeX;
        ownerFigure.getCube(index).cubeX = boundingX - ownerFigure.getCube(index).cubeY + boundingY;
        ownerFigure.getCube(index).cubeY = boundingY + oldX - boundingX;
    }

    public void setCoordX(int newX) {
        cubeX = newX;
    }
    public int getCoordX() { return cubeX; }

    public void setCoordY(int newY) {
        cubeY = newY;
    }
    public int getCoordY() {
        return cubeY;
    }

}