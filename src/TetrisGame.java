import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisGame extends JFrame {

    private GameModel _model;
    private GameFieldPanel _gamePanel;
    private boolean gameStarted = false; // Flag to track if the game has started

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
        _gamePanel = new GameFieldPanel(_model);

        JPanel controlPanel = new JPanel(new FlowLayout());

        JButton restartButton = new JButton("Начать заново");

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                _model.restart();
                _gamePanel.repaint(); // Repaint the game field
            }
        });

        restartButton.setFocusable(false); // Remove focus from the restart button

        controlPanel.add(restartButton);

        _gamePanel.setFocusable(true);
        _gamePanel.requestFocusInWindow(); // Set focus on the game field panel

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(controlPanel, BorderLayout.NORTH);
        content.add(_gamePanel, BorderLayout.CENTER);

        setContentPane(content);

        // Prompt dialog to start a new game
        int input = JOptionPane.showOptionDialog(null, "Добро пожаловать в Тетрис. Нажми Yes для начала новой игры! \n" +
                        "Управление: \n" +
                        "⬅ переместить фигуру влево\n" +
                        "➡ переместить фигуру вправо\n" +
                        "⬇ ускорить падение фигуры\n" +
                        "⬆ поворот фигуры",
                "Tetris", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if (input == JOptionPane.YES_OPTION) {
            gameStarted = true; // Set the gameStarted flag to true
            startGame(); // Start the game
        } else {
            // User clicked "No" or closed the dialog, exit the application
            System.exit(0);
        }
    }

    private void startGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        _gamePanel.setFocusable(true);
        _gamePanel.setVisible(gameStarted); // Set the visibility of the game field panel based on the gameStarted flag
        _model.start(); // Start the game model
    }
}