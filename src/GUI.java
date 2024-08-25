import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    private boolean playerTurn = true;

    GUI() {
        setTitle("Tic-Tac-Toe!");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(new GridLayout(3, 3));
        setResizable(false);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 25));
            add(buttons[i]);
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();
        if(Objects.equals(clickedButton.getText(), "")) {
            if(playerTurn) {
                clickedButton.setText("X");
                clickedButton.setForeground(Color.RED);
                playerTurn = false;
            } else {
                clickedButton.setText("O");
                clickedButton.setForeground(Color.MAGENTA);
                playerTurn = true;
            }
            if(checkForWinner()) {
                int o = JOptionPane.showConfirmDialog(this, "Winner! Play again?", null, JOptionPane.YES_NO_OPTION);
                if(o == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            } else if(checkForDraw()) {
                int o = JOptionPane.showConfirmDialog(this, "Draw! Play again?", null, JOptionPane.YES_NO_OPTION);
                if(o == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            }
        }
    }

    private boolean checkForWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (Objects.equals(buttons[i * 3].getText(), buttons[i * 3 + 1].getText()) &&
                Objects.equals(buttons[i * 3 + 1].getText(), buttons[i * 3 + 2].getText()) &&
                !buttons[i * 3].getText().isEmpty()) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (Objects.equals(buttons[i].getText(), buttons[i + 3].getText()) &&
                Objects.equals(buttons[i + 3].getText(), buttons[i + 6].getText()) &&
                !buttons[i].getText().isEmpty()) {
                return true;
            }
        }

        // Check diagonals
        if (Objects.equals(buttons[0].getText(), buttons[4].getText()) &&
            Objects.equals(buttons[4].getText(), buttons[8].getText()) &&
            !buttons[0].getText().isEmpty()) {
            return true;
        }
        return Objects.equals(buttons[2].getText(), buttons[4].getText()) &&
            Objects.equals(buttons[4].getText(), buttons[6].getText()) &&
            !buttons[2].getText().isEmpty();
    }

    private boolean checkForDraw() {
        for(JButton button : buttons) {
            if (button.getText().isEmpty()) {
                return false;
            }
         }

        return !checkForWinner();
    }

    private void resetGame() {
        for (JButton button : buttons) {
            playerTurn = true;
            button.setText("");
        }
    }
}
