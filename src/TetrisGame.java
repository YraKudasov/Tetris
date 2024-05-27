import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TetrisGame extends JFrame {

    private GameModel _model;

    private GameFieldPanel _gamePanel;

    //===================================================================== main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TetrisGame();
            }
        });

    }

    public TetrisGame() {
        this.setTitle("Tetris");
        _model = new GameModel();
        _model.start();
        _gamePanel = new GameFieldPanel(_model);


        JPanel controlPanel = new JPanel(new FlowLayout());
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(controlPanel, BorderLayout.NORTH);
        content.add(_gamePanel, BorderLayout.CENTER);

        setContentPane(content);
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        _gamePanel.setFocusable(true);
        _gamePanel.setVisible(true);


    }
    class ActionNewGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            _model.start();
        }


    }

}