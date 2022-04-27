/**
 * A sub class of the JPanel that will hold
 * a text field to present the score of the 
 * tictactoe game.
 * 
 * @summary tictactoe score panel
 */

package TicTacToeDemo;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * the class implemented to display the score
 */
public class TicTacToeScorePanel extends JPanel {
    private JTextField m_scoreString;

    /**
     * the constructor for the score panel
     */
    public TicTacToeScorePanel() {
        m_scoreString = new JTextField();
        this.add(m_scoreString);
    }

    /**
     * the method to call with the two scores to display
     * 
     * @param xScore - the x player score
     * @param oScore - the o player score
     */
    public void setScoreString(int xScore, int oScore) {
        StringBuilder sb = new StringBuilder();
        sb.append("Player X has " + xScore + " wins ");
        sb.append("Player O has " + oScore + " wins");
        m_scoreString.setText(sb.toString());
    }

    /**
     * the method to call to clear the score panel.
     */
    public void clearScore() {
        m_scoreString.setText("");
    }
}
