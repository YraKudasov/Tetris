import events.FigureActionEvent;
import events.FigureActionListener;

import java.util.List;
import java.util.Timer;

public class GameModel {

    private Timer timer;
    private Glass glass;


    private FactoryFigures factoryFigures;
    private int score;

    public GameModel() {
        this.glass = new Glass(5, 5);
        this.factoryFigures = new FactoryFigures(glass);
        this.score = 0;
    }


    public void start(){
        while (!isGameOver()) {
            initiateFigureGeneration();
            glass.getFigure().addFigureActionListener(new FigureObserver());
        }
    }


    public void initiateFigureGeneration() {
        // Инициируем генерацию фигур фабрикой фигур
        Figure figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
    }

    public void addFigureToHeap(){
       Figure figure = glass.getFigure();
       Heap heap = glass.getHeap();

        // Добавляем кубики из фигуры в кучу
        Cube[] cubes = figure.getCubes();
        for (Cube cube : cubes) {
            heap.addCube(cube);
        }

        // Удаляем действующую фигуру из стакана
        glass.deleteFigure();

    }

    public void updateScore(int points) {
        // Обновляем счет
        score += points;
    }

    public void figureFell(Figure figure, Heap heap) {
        Cube[] fellCubes = figure.getCubes();
        for (Cube cube : fellCubes) {
            cube.setMovable(false); // Set the movable property to false
            heap.addCube(cube);// Add the cube to the Stack object
        }
        glass.deleteFigure();
    }


        public boolean isGameOver() {
            // Определяем окончание игры
            return glass.isOverflow();
        }


    // Геттеры и сеттеры для доступа к свойствам
    public Glass getGlass() {
        return glass;
    }

    public FactoryFigures getFactoryFigures() {
        return factoryFigures;
    }

    public int getScore() {
        return score;
    }





    private class FigureObserver implements FigureActionListener {


        @Override
        public void onFigureFell(FigureActionEvent e) {
            addFigureToHeap();
            if(!isGameOver()){
                updateScore(200);
                glass.getHeap().burnRow(glass.getFilledRows());
            }
            else {
                System.out.print("Игра окончена \n Счёт:" + getScore());
            }
        }

    }

}

