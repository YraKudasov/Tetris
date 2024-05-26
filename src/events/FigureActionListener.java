package events;

import java.util.EventListener;

public interface FigureActionListener extends EventListener {
    void onFigureFell(FigureActionEvent e);

}
