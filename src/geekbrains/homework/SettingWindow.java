package geekbrains.homework;

import javax.swing.*;
import java.awt.*;

public class SettingWindow extends JFrame {
    private GameWindow gameWindow;

    static final int WINDOW_X = GameWindow.WINDOW_X + 50;
    static final int WINDOW_Y = GameWindow.WINDOW_Y + 50;
    static final int WINDOW_WIDTH = 405;
    static final int WINDOW_HEIGHT = 400;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;

    private JRadioButton jrbHumVSAi;
    private ButtonGroup gameModeGroup;

    private JSlider slFieldSize;
    private JSlider slWinningLength;

    public SettingWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Setting");

        setLayout(new GridLayout(8, 1));
        add(new JLabel("Choose game mode:"));
        jrbHumVSAi = new JRadioButton("HumVSAi", true);
        gameModeGroup = new ButtonGroup();
        gameModeGroup.add(jrbHumVSAi);
        add(jrbHumVSAi);

        add(new JLabel("Choose field size:"));
        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slFieldSize.setMajorTickSpacing(1);
        slFieldSize.setPaintLabels(true);
        slFieldSize.setPaintTicks(true);
        add(slFieldSize);

        add(new JLabel("Choose wining length:"));
        slWinningLength = new JSlider(MIN_FIELD_SIZE, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        slWinningLength.setMajorTickSpacing(1);
        slWinningLength.setPaintLabels(true);
        slWinningLength.setPaintTicks(true);
        add(slWinningLength);

        slFieldSize.addChangeListener(e -> {
            slWinningLength.setMaximum(slFieldSize.getValue());
        });

        JButton buttonStartGame = new JButton("Start a game");
        add(buttonStartGame);
        buttonStartGame.addActionListener(e -> {
            int fieldSize = slFieldSize.getValue();
            int winningLength = slWinningLength.getValue();

            Logic.SIZE = fieldSize;
            Logic.DOTS_TO_WIN = winningLength;
            Logic.initMap();
            Logic.isFinishedGame = false;

            gameWindow.startNewGame(fieldSize, winningLength);

            setVisible(false);
        });

        setVisible(false);

    }
}
