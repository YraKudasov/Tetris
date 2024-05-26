import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFieldPanel extends JPanel implements KeyListener {

    private GameModel _model;

    private static final int CELL_SIZE = 30;
    private static final int GAP = 2;
    private static final int FONT_HEIGHT = 15;

    // ------------------------- �������� ���������� ---------------------------

    private static final Color BACKGROUND_COLOR = new Color(175, 255, 175);
    private static final Color GRID_COLOR = Color.GREEN;


    public GameFieldPanel(GameModel model) {
        _model = model;

        // ������������� �������
        int width = 2 * GAP + CELL_SIZE * _model.getGlass().getWidth();
        int height = 2 * GAP + CELL_SIZE * _model.getGlass().getHeight();
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.RED);


        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {

        // ��������� ����
        int width = getWidth();
        int height = getHeight();
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);   // ���������������� ���� ����

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            _model.getGlass().getFigure().move(Direction.East); // Движение фигуры влево
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            _model.getGlass().getFigure().move(Direction.West); // Движение фигуры вправо
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            _model.getGlass().getFigure().move(Direction.South); // Ускорение падения фигуры
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
