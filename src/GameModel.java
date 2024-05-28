import events.FigureActionEvent;
import events.FigureActionListener;

import javax.swing.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameModel {

    private Timer timer;
    private Glass glass;

    private FactoryFigures factoryFigures;
    private int score;

    public GameModel() {
        this.glass = new Glass(20, 10);
        this.factoryFigures = new FactoryFigures(glass);
        this.score = 0;
    }


    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new FallingFigureTask(), 0, 1000);
    }

    public void restart(){
        glass.clearGlass();
        score = 0;

        // Останавливаем текущий таймер
        if (timer != null) {
            timer.cancel();
            timer.purge(); // Очищаем очередь таймера
        }
        
        start();
    }

    public void initiateFigureGeneration() {
        // Инициируем генерацию фигур фабрикой фигур
        Figure figure = factoryFigures.createRandomFigure();
        glass.setFigure(figure);
    }

    public void addFigureToHeap() {
        Figure figure = glass.getFigure();
        Heap heap = glass.getHeap();

        // Добавляем кубики из фигуры в кучу
        Cube[] cubes = figure.getCubes();
        for (Cube cube : cubes) {
            cube.setMovable(false);
            heap.addCube(cube);
        }

        // Удаляем действующую фигуру из стакана
        glass.deleteFigure();

    }

    public void updateScore(int points) {
        // Обновляем счет
        score += points;
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


    // Класс, который будет вызываться каждую секунду
    private class FallingFigureTask extends TimerTask {
        @Override
        public void run() {
            Figure figure = glass.getFigure();
            if (figure != null) {
                figure.move(Direction.South);
            } else {
                // Фигура упала или отсутствует, останавливаем таймер и генерируем новую фигуру
                timer.cancel(); // Останавливаем таймер
                if (isGameOver()) {
                    // Если стакан переполнен, завершаем игру
                    System.out.println("Игра окончена, стакан переполнен!");
                    // Здесь можно добавить код для завершения игры
                } else {
                    initiateFigureGeneration(); // Генерируем новую фигуру
                    glass.getFigure().addFigureActionListener(new FigureObserver());
                    timer = new Timer(); // Создаем новый таймер
                    timer.scheduleAtFixedRate(new FallingFigureTask(), 0, 1000); // Запускаем таймер снова
                }
            }


        }

    }

    private class FigureObserver implements FigureActionListener {

        @Override
        public void onFigureFell(FigureActionEvent e) {
            addFigureToHeap();
            if (!isGameOver()) {
                int ratio = glass.getHeap().burnRow(glass.getFilledRows());
                updateScore( ratio * 100);
                System.out.print("Фигура упала \n");
            } else {
                System.out.print("Игра окончена\n Счет:" + getScore()+"\n");
            }
        }



        @Override
        public void onFigureMoveDown(FigureActionEvent e) {

        }

        @Override
        public void onFigureGenerate(FigureActionEvent e) {

        }

    }


}

