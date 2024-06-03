package code.events;

import java.util.EventListener;

public interface FigureActionListener extends EventListener {
    void onFigureFell(FigureActionEvent e);

    void onFigureMoveDown(FigureActionEvent e);

    void onFigureGenerate(FigureActionEvent e);
}
