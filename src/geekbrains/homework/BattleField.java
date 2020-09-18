package geekbrains.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleField extends JPanel {
    private GameWindow gameWindow;
    private WinnerWindow winnerWindow;

    private int fieldSize;
    private int winningLength;

//    private static JLabel winnerHum;
//    private static JLabel winnerAI;
//    private JLabel winnerDraw;

    boolean isInit;

    int cellWidth;
    int cellHeight;

    public BattleField(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        winnerWindow = new WinnerWindow(this);

        setBackground(Color.black);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int cellX = e.getX() / cellWidth;
                int cellY = e.getY() / cellHeight;

                if (!Logic.isFinishedGame) {
                    Logic.humanTurn(cellX, cellY);
                }

                if (Logic.isFinishedGame) {
                    //попытка вывести победителя, но работает неправильно
//                    if (Logic.checkWinLines(Logic.DOT_X, Logic.DOTS_TO_WIN)) {
//                        winnerHum= new JLabel("YOU WIN!!!", SwingConstants.CENTER);
//                        winnerWindow.add(winnerHum);
//                    }
//                    if (Logic.checkWinLines(Logic.DOT_O, Logic.DOTS_TO_WIN)) {
//                        winnerAI = new JLabel("YOU LOSE...", SwingConstants.CENTER);
//                        winnerWindow.add(winnerAI);
//                    }
//                    if (Logic.isFull()) {
//                        winnerDraw = new JLabel("DRAW", SwingConstants.CENTER);
//                        winnerWindow.add(winnerDraw);
//                    }
                    winnerWindow.setVisible(true);
                }

                repaint();

            }
        });
    }

    public void startNewGame(int fieldSize, int winningLength) {
        this.fieldSize = fieldSize;
        this.winningLength = winningLength;

        isInit = true;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isInit) {
            return;
        }

        cellWidth = getWidth() / fieldSize;
        cellHeight = getHeight() / fieldSize;

        for (int i = 0; i < fieldSize; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, getWidth(), y);
        }

        for (int i = 0; i < fieldSize; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, getHeight());
        }

        for (int i = 0; i < Logic.SIZE; i++) {
            for (int j = 0; j < Logic.SIZE; j++) {
                if (Logic.map[i][j] == Logic.DOT_X) {
                    drawX(g, j, i);
                }
                if (Logic.map[i][j] == Logic.DOT_O) {
                    draw0(g, j, i);
                }
            }
        }
    }

    private void draw0(Graphics g, int cellX, int cellY) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.YELLOW);
        g.drawOval(cellX * cellWidth, cellY * cellHeight, cellWidth, cellHeight);
    }

    private void drawX(Graphics g, int cellX, int cellY) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.cyan);
        g.drawLine(cellX * cellWidth, cellY * cellHeight,
                (cellX + 1) * cellWidth, (cellY + 1) * cellHeight);
        g.drawLine((cellX + 1) * cellWidth, cellY * cellHeight,
                cellX * cellWidth, (cellY + 1) * cellHeight);
    }
}
