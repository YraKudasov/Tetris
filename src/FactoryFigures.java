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
                {{5, 1}, {5, 1}, {4, 1}, {6, 1}, {5, 2}},  // 'T'
                {{6, 2}, {6, 2}, {5, 2}, {4, 2}, {6, 1}}, // 'J'
                {{4, 2}, {4, 2}, {5, 2}, {6, 2}, {4, 1}}, // 'L'
                {{5, 2}, {5, 2}, {4, 2}, {4, 1}, {5, 1}}, // 'O'
                {{4, 2}, {4, 2}, {3, 2}, {5, 2}, {6, 2}},  // 'I'
                {{4, 2}, {4, 2}, {3, 2}, {4, 1}, {5, 1}},  // 'S'
                {{5, 2}, {5, 2}, {6, 2}, {5, 1}, {4, 1}}  // 'Z'
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
        int[] shapes = {0, 1, 2, 3, 4, 5, 6};
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
