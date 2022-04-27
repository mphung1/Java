/**
 * The panel that holds the playing board.  It is used in 
 * the layout manager to hold the components.
 *
 * @summary the game panel for TicTacToe
 */

package TicTacToeDemo;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;

/**
 * the class that is the panel for the buttons.
 * it will hold all nine buttons.
 */
public class TicTacToePanel extends JPanel {
    private TicTacToeButton[][] m_buttons; // this is the interactive parts.
    TicTacToeFrame m_parent; // reference to the host component in case it is needed.
    // private int m_gameCount = 0;
    private int m_oWins = 0;
    private int m_xWins = 0;
    private boolean m_imageChoice;

    /**
     * the constructor for the frame. I use a reference to the parent
     * to allow the communication to work easier.
     * 
     * @param parent The JFrame that holds this panel
     */
    TicTacToePanel(TicTacToeFrame parent) {
        m_parent = parent;
        m_imageChoice = true;
        setLayout(new GridLayout(3, 3, 10, 10));
        m_buttons = new TicTacToeButton[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                m_buttons[i][j] = new TicTacToeButton(this, i, j);
                // m_buttons[i][j].addActionListener(new TicTacToeButtonListener());
                add(m_buttons[i][j]);
            }
        // m_gameCount++;
    }

    /**
     * Gets the component that holds this
     */
    public TicTacToeFrame getParent() {
        return m_parent;
    }

    /**
     * gets the collection of buttons
     * 
     * @return the button collection
     */
    TicTacToeButton[][] getButtons() {
        return m_buttons;
    }

    /**
     * resets the game board.
     */
    void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                m_buttons[i][j].reset();
        // m_gameCount++;
    }

    /**
     * updates the win status
     * 
     * @param winner 1 x won, -1 o won, 0 draw don't do anything.
     */
    public void updateWins(int winner) {
        if (winner == 0)
            return;

        if (winner == 1)
            m_xWins++;
        else
            m_oWins++;
        m_parent.sPanel.setScoreString(m_xWins, m_oWins);
    }

    /**
     * this will be called from the button action handler method.
     * it will pass in the 'index' of the pushed button and the image
     * to show on the button.
     * 
     * @param btnIDx    the row
     * @param btnIDy    the column
     * @param imgChoice the image id to show. true for x
     */
    public void setActionPerformed(int btnIDx, int btnIDy, boolean imgChoice) {
        TicTacToeButton tButton = m_buttons[btnIDx][btnIDy];
        if (tButton.getState() == true) {
            tButton.setEnabled(false);
            return;
        }
        if (imgChoice)
            tButton.setIconImage('x');
        else
            tButton.setIconImage('o');

        tButton.setState(true);
        tButton.setEnabled(false);
        // check the results for the last button that was hit (last x or o)
        int gameCondition = TicTacToeSolver.solveGame(tButton.getParent().getButtons());
        if (gameCondition == 1) {
            int valueOfWinner;

            if (imgChoice)
                valueOfWinner = 1;
            else
                valueOfWinner = -1;
            tButton.getParent().updateWins(valueOfWinner);

            int clearStatus = JOptionPane.showConfirmDialog(null, "Game has been won", "Tic Tac Toe",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (clearStatus == JOptionPane.YES_OPTION)
                tButton.getParent().resetBoard();
            else
                System.exit(0);
        } else if (gameCondition == -1) {
            int clearStatus = JOptionPane.showConfirmDialog(null, "Game is a Draw", "Tic Tac Toe",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (clearStatus == JOptionPane.YES_OPTION)
                tButton.getParent().resetBoard();
            else
                System.exit(0);
        }
        m_imageChoice = imgChoice;
    }
}