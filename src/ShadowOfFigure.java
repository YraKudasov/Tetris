import java.awt.*;
import java.util.Arrays;

public class ShadowOfFigure {

    private Cube[] cubesOfShadow;
    private Color color;

    private Glass glass;
    private Figure figure;

    public ShadowOfFigure(Color color, Figure figure, Glass glass) {
        this.color = color;
        this.figure = figure;
        this.glass = glass;
    }

    public void updateShadow(Cube[] cubes) {
        // Обновляем позиции кубиков тени
        int minY = glass.getHeight();

        cubesOfShadow = new Cube[cubes.length];
        for (int i = 0; i < cubes.length; i++) {
            cubesOfShadow[i] = new Cube(cubes[i].getCoordX(), cubes[i].getCoordY());
        }


        for (Cube cube : cubesOfShadow) {
            int currentMinY = 0;
            for (int i = cube.getCoordY()+1; i < glass.getHeight(); i++) {
                Cell nextCell = glass.getCell(cube.getCoordX(), i);
                if (nextCell != null && (!nextCell.hasCube() || nextCell.hasCube() && nextCell.getCube().isMovable())) {
                    currentMinY++;
                } else {
                    break;
                }

            }
            if (minY > currentMinY){
                minY = currentMinY;
            }
        }
        for (Cube cube : cubesOfShadow){
            cube.setCoordY(cube.getCoordY() + minY);
        }
    }


    public Color getColor() {
        return color;
    }

    public Cube[] getCubesOfShadow() {
        return cubesOfShadow;
    }

}
