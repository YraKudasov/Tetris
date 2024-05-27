public class Cube {


    private boolean movable;

    private int cubeX;
    private int cubeY;

    public Cube(int coordX, int coordY) {
        this.cubeX = coordX;
        this.cubeY = coordY;
        this.movable = true;
    }



    public boolean isMovable() {
        return this.movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public boolean canMove(Direction direction, Glass glass) {
        switch (direction) {
            case South:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию ниже текущей
                    Cell nextCell = glass.getCell(this.cubeX, this.cubeY + 1);
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && (!nextCell.hasCube() || nextCell.hasCube() && nextCell.getCube().movable)) {
                        return true;
                    }
                }
                return false;
            case East:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию левее текущей
                    Cell nextCell = glass.getCell(this.cubeX - 1, this.cubeY);
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && (!nextCell.hasCube() || nextCell.hasCube() && nextCell.getCube().movable)) {
                        return true;
                    }
                }
                return false;
            case West:
                if (isMovable()) {
                    // Получаем ячейку, которая находится на одну позицию правее текущей
                    Cell nextCell = glass.getCell(this.cubeX + 1, this.cubeY);
                    // Проверяем, что следующая ячейка пуста
                    if (nextCell != null && (!nextCell.hasCube() || nextCell.hasCube() && nextCell.getCube().movable)) {
                        return true;
                    }
                }
                return false;
        }
        return false;
    }


    public void move(Direction direction,  Glass glass) {
        // Предполагая, что возможны четыре направления: вверх, вниз, влево и вправо
        switch (direction) {
            case South:
                // Получаем ячейку, которая находится на одну позицию ниже текущей
                Cell nextCellSouth = glass.getCell(this.cubeX, this.cubeY+1);
                // Перемещаем куб в следующую ячейку
                nextCellSouth.addCube(this);
                // Удаляем куб из текущей ячейки
                glass.getCell(this.cubeX, this.cubeY).removeCube();
                this.cubeY ++;
                break;
          case East:
                // Получаем ячейку, которая находится на одну позицию левее текущей
                Cell nextCellEast = glass.getCell(this.cubeX - 1, this.cubeY);
                // Перемещаем куб в следующую ячейку
                nextCellEast.addCube(this);
                // Удаляем куб из текущей ячейки
                glass.getCell(this.cubeX, this.cubeY).removeCube();
                this.cubeX --;
                break;
            case West:
                // Получаем ячейку, которая находится на одну позицию правее текущей
                Cell nextCellWest = glass.getCell(this.cubeX + 1, this.cubeY);
                // Перемещаем куб в следующую ячейку
                nextCellWest.addCube(this);
                // Удаляем куб из текущей ячейки
                glass.getCell(this.cubeX, this.cubeY).removeCube();
                this.cubeX ++;
                break;
            default:
                // Неизвестное направление
                break;
       }
    }

    public boolean canRotate(int boundingX, int boundingY, Glass glass, int[] waitingCubes, int index){
        if (isMovable()) {
            // Получаем ячейку, которая находится на одну позицию ниже текущей
            Cell nextCell = glass.getCell(boundingX - this.cubeY + boundingY, boundingY + this.cubeX - boundingX);
            // Проверяем, что следующая ячейка пуста
            if (nextCell != null && (!nextCell.hasCube() || nextCell.hasCube() && nextCell.getCube().movable)) {
                if (nextCell.hasCube() && nextCell.getCube().movable) {
                    waitingCubes[index] = index;
                }
                return true;
            }
        }
        return false;
    }

    public void rotate(int boundingX, int boundingY, int index, Glass glass) {
        // Получаем ячейку, которая находится на одну позицию ниже текущей
        Cell nextCell = glass.getCell(boundingX - this.cubeY + boundingY, boundingY + this.cubeX - boundingX);
        // Перемещаем куб в следующую ячейку
        nextCell.addCube(this);
        // Удаляем куб из текущей ячейки
        glass.getCell(this.cubeX, this.cubeY).removeCube();

        int oldX = this.cubeX;
        this.cubeX = boundingX - this.cubeY + boundingY;
        this.cubeY = boundingY + oldX - boundingX;
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