import events.FigureActionEvent;
import events.FigureActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameFieldPanel extends JPanel implements KeyListener {

    private GameModel _model;

    private static final int CELL_SIZE = 25;



    // ----------------------------------------------------

    private static final Color BACKGROUND_COLOR = new Color(108, 88, 76);
    private static final Color GRID_COLOR = Color.WHITE;


    public GameFieldPanel(GameModel model) {
        _model = model;

        int width = CELL_SIZE * _model.getGlass().getWidth();
        int height =  CELL_SIZE * _model.getGlass().getHeight();
        setPreferredSize(new Dimension(width, height));

        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        FigureObserver observer = new FigureObserver();
        _model.getGlass().getFigure().addFigureActionListener(observer);
        FigureObserver observer2 = new FigureObserver();
        _model.getFactoryFigures().addFigureGenerateListener(observer2);
        int width = getWidth();
        int height = getHeight();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 4*CELL_SIZE, width, height);
        drawGrid(g);
        drawFigure(g);
        drawShadowOfFigure(g);
        drawHeap(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + _model.getScore(), 10, 30);
    }

    private void drawGrid(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(GRID_COLOR);

        for (int i = 0; i <= _model.getGlass().getWidth(); i++) {
            int x =  CELL_SIZE * (i);
            g.drawLine(x, 4*CELL_SIZE, x, height);
        }

        for (int i = 4; i <= _model.getGlass().getHeight(); i++) {
            int y =  CELL_SIZE * (i);
            g.drawLine(0, y, width, y);
        }

    }


    private void drawFigure(Graphics g) {

        // Устанавливаем цвет для рисования фигуры
        g.setColor(_model.getGlass().getFigure().getColor());
        for (Cube cube : _model.getGlass().getFigure().getCubes()) {
            int x = cube.getCoordX();
            int y = cube.getCoordY();
            if(y>=4) {
                drawCube(g, x, y);
            }
        }
    }

    private void drawShadowOfFigure(Graphics g) {

        // Устанавливаем цвет для рисования фигуры
        g.setColor(_model.getGlass().getFigure().getShadow().getColor());
        for (Cube cube : _model.getGlass().getFigure().getShadow().getCubesOfShadow()) {
            int x = cube.getCoordX();
            int y = cube.getCoordY();
            if(y>=4) {
                drawShadowCube(g, x, y);
            }
        }
    }

    private void drawShadowCube(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3.0f));
        g.drawLine(CELL_SIZE * x, CELL_SIZE * y, CELL_SIZE * (x+1), CELL_SIZE * y);
        g.drawLine(CELL_SIZE * x, CELL_SIZE * y, CELL_SIZE * x, CELL_SIZE * (y+1));
        g.drawLine(CELL_SIZE * x, CELL_SIZE * (y+1), CELL_SIZE * (x+1), CELL_SIZE * (y+1));
        g.drawLine(CELL_SIZE * (x+1), CELL_SIZE * y, CELL_SIZE * (x+1), CELL_SIZE * (y+1));
    }

    private void drawCube(Graphics g, int x, int y) {
        g.fillRect(CELL_SIZE * x,  CELL_SIZE * y, (CELL_SIZE-1), (CELL_SIZE-1));
    }

    private void drawHeap(Graphics g) {
        g.setColor(Color.GRAY); // Set color for drawing the heap
        for (Cube cube : _model.getGlass().getHeap().getAllCubes()) {
            int x = cube.getCoordX();
            int y = cube.getCoordY();
            drawCube(g, x, y);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Figure figure = _model.getGlass().getFigure();
            if (figure == null) return;
            _model.getGlass().getFigure().move(Direction.East); // Движение фигуры влево
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Figure figure = _model.getGlass().getFigure();
            if (figure == null) return;
            _model.getGlass().getFigure().move(Direction.West); // Движение фигуры вправо
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            Figure figure = _model.getGlass().getFigure();
            if (figure == null) return;
            _model.getGlass().getFigure().move(Direction.South); // Ускорение падения фигуры
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            Figure figure = _model.getGlass().getFigure();
            if (figure == null) return;
            _model.getGlass().getFigure().rotate(); // Ускорение падения фигуры
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class FigureObserver implements FigureActionListener {

        @Override
        public void onFigureFell(FigureActionEvent e) {

        }

        @Override
        public void onFigureMoveDown(FigureActionEvent e) {
            repaint();
        }

        @Override
        public void onFigureGenerate(FigureActionEvent e) {
            repaint();
        }

    }
}
