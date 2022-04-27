/**
 * A button to hold the user interaction parts 
 * for the TicTacToe board.
 * 
 */

package TicTacToeDemo;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * the button that the user will interact with.
 * it will have an x or o when the button is pressed.
 */
public class TicTacToeButton extends JButton {
    private ImageIcon m_image;
    private TicTacToePanel m_panel;
    private boolean m_state = false; // this tells if we are set or not?
    private int m_xoValue = 0; // -1 is a O 1 is X 0 is not set.
    public int btnIDx;
    public int btnIDy;
    private File fx = new File("/home/manderso88/CSC/CSC2014/CSC2014/TicTacToeDemo/X.png");
    private File fo = new File("/home/manderso88/CSC/CSC2014/CSC2014/TicTacToeDemo/O.png");

    TicTacToeButton(TicTacToePanel parent, int x, int y) {
        m_panel = parent;
        btnIDx = x;
        btnIDy = y;
        this.addActionListener(new TicTacToeButtonListener());
    }

    /**
     * set the icon on the button.
     * 
     * @param icn x to put an x icon on the button o to put an o icon on.
     */
    void setIconImage(char icn) {
        Image ii;
        try {

            if (icn == 'x') {
                ii = ImageIO.read(fx);
                m_xoValue = 1;
            } else {
                ii = ImageIO.read(fo);
                m_xoValue = -1;
            }

            m_image = new ImageIcon(ii);

            setIcon(m_image);

        } catch (IOException ioe) {
            System.out.println("unable to read icon image");
        }
    }

    /**
     * get the panel that holds the button
     */
    public TicTacToePanel getParent() {
        return m_panel;
    }

    /**
     * get the state (has this button been pressed.)
     * 
     * @return true if the button has been pressed.
     */
    boolean getState() {
        return m_state;
    }

    /**
     * set the state of the button
     * 
     * @param true to set that the button has been pressed.
     */
    void setState(boolean state) {
        m_state = state;
    }

    /**
     * set the x or o value -1 or 1
     * 
     * @param x
     */
    void setXOValue(int x) {
        m_xoValue = x;
    }

    /**
     * gets the x or o value -1 or 1
     * 
     * @return 1 for x -1 for o
     */
    int getXOValue() {
        return m_xoValue;
    }

    /**
     * check if the value of this button is the same as another button.
     */
    boolean equals(TicTacToeButton tb) {
        return m_xoValue == tb.getXOValue();
    }

    /**
     * clear the button.
     */
    void reset() {
        m_state = false;
        m_xoValue = 0;
        setIcon(null);
        setEnabled(true);
    }
}