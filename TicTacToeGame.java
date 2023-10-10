import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    private int playerOneScore, playerTwoScore, totalGames;
    private JLabel playerOneScoreLabel, playerTwoScoreLabel, totalGamesLabel;

    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new GridLayout(6, 3));

        JLabel empty1 = new JLabel(" ");

        JLabel title = new JLabel("Simple TicTacToe Game");
        title.setHorizontalAlignment(JLabel.CENTER);

        JLabel empty2 = new JLabel(" ");

        add(empty1);
        add(title);
        add(empty2);

        buttons = new JButton[3][3];
        currentPlayer = 'X';
        playerOneScore = 0;
        playerTwoScore = 0;
        totalGames = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                add(buttons[row][col]);
            }
        }

        playerOneScoreLabel = new JLabel("Player 1 Score: 0");
        playerOneScoreLabel.setHorizontalAlignment(JLabel.CENTER);

        playerTwoScoreLabel = new JLabel("Player 2 Score: 0");
        playerTwoScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        
        totalGamesLabel = new JLabel("Total Games: 0");

        add(playerOneScoreLabel);
        add(playerTwoScoreLabel);
        add(totalGamesLabel);

        JButton resetButton = new JButton("Reset Game");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        JLabel note = new JLabel("Note : Player 1 = X ; Player 2 = O");
        note.setHorizontalAlignment(JLabel.CENTER);

        JButton resetScoresButton = new JButton("Reset Scores");
        resetScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetScores();
            }
        });
        
        add(resetButton);
        add(note);
        add(resetScoresButton);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                if (checkWin()) {
                    updateScore();
                    JOptionPane.showMessageDialog(TicTacToeGame.this, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(TicTacToeGame.this, "It's a draw!");
                    resetGame();
                } else {
                    switchPlayer();
                }
            }
        }
    }

    private boolean checkWin() {
        return checkRow() || checkColumn() || checkDiagonal();
    }

    private boolean checkRow() {
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(String.valueOf(currentPlayer))
                    && buttons[row][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[row][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumn() {
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(String.valueOf(currentPlayer))
                    && buttons[1][col].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][col].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal() {
        return (buttons[0][0].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][2].getText().equals(String.valueOf(currentPlayer)))
                || (buttons[0][2].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][0].getText().equals(String.valueOf(currentPlayer)));
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void updateScore() {
        if (currentPlayer == 'X') {
            playerOneScore++;
        } else {
            playerTwoScore++;
        }
        updateLabels();
    }

    private void updateLabels() {
        playerOneScoreLabel.setText("Player 1 Score: " + playerOneScore);
        playerTwoScoreLabel.setText("Player 2 Score: " + playerTwoScore);
        totalGames = playerOneScore + playerTwoScore;
        totalGamesLabel.setText("Total Games: " + totalGames);
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
    }

    private void resetScores() {
        playerOneScore = 0;
        playerTwoScore = 0;
        totalGames = 0;
        updateLabels();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGame());
    }
}
