import events.FigureActionEvent;
import events.FigureActionListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class FactoryFigures {
    private Glass glass;

    private static final Color[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    public FactoryFigures(Glass glass) {
        this.glass = glass;
    }

    public Figure createRandomFigure() {
        int[][][] shapes = {
                {{2, 0}, {2, 1}, {1, 0}, {2, 0}, {3, 0}},  // 'T'
                {{2, 2}, {1, 2}, {2, 0}, {2, 1}, {2, 2}}, // 'J'
                {{1, 0}, {1, 1}, {2, 1}, {1, 0}, {2, 0}}  // 'O'
        };

        int shapeIndex = getRandomShape();
        int[][] shapeCoords = shapes[shapeIndex];
        Cube[] cubes = new Cube[4];
        for (int i = 1; i < 5; i++) {
            int[] coords = shapeCoords[i];
            cubes[i - 1] = new Cube(coords[0], coords[1]);
        }

        int[] boundingCoords = shapeCoords[0];
        Cube boundingCube = new Cube(boundingCoords[0], boundingCoords[1]);

        Color randomColor = COLORS[new Random().nextInt(COLORS.length)]; // Choose a random color for the figure
        return new Figure(glass, cubes, boundingCube, randomColor);
    }

    private int getRandomShape() {
        int[] shapes = {0, 1, 2};
        fireFigureGenerate();
        return shapes[new Random().nextInt(shapes.length)];
    }

    ArrayList<FigureActionListener> _listeners = new ArrayList<>();

    public void addFigureGenerateListener(FigureActionListener l) {
        _listeners.add(l);
    }

    public void removeFigureGenerateListener(FigureActionListener l) {
        _listeners.remove(l);
    }

    protected void fireFigureGenerate() {
        FigureActionEvent e = new FigureActionEvent(this);
        for (FigureActionListener l : _listeners) {
            l.onFigureGenerate(e);
        }
    }
}
