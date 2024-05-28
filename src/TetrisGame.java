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

        JButton restartButton = new JButton("Restart");

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                _model.restart();
                _gamePanel.repaint(); // Перерисовка игрового поля
            }
        });

        restartButton.setFocusable(false); // Убираем фокус с кнопки рестарта

        controlPanel.add(restartButton);

        _gamePanel.setFocusable(true);
        _gamePanel.requestFocusInWindow(); // Добавляем фокус на GameFieldPanel после добавления кнопки


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


}