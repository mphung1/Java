/**
 * implements the ActionListener interface.
 * This class handles the interaction with the button.
 * When the button is pushed it will call this method
 * in this class.
 *
 * @summary the button action listener class.
 */

package TicTacToeDemo;

import java.awt.event.*;

/**
 * the listener class. implements the ActionListener interface
 */
public class TicTacToeButtonListener implements ActionListener {
    private static boolean imgChoice = true;

    /**
     * this is the action handler method. It is called when the
     * button is pushed. It will change the icon and check the
     * state of the game. If the game is complete then it will
     * pop up a message dialog.
     * 
     * @param arg0 the Action Event that contains the button actions
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        TicTacToeButton tButton = (TicTacToeButton) arg0.getSource();
        tButton.getParent().getParent().tcPanel.text.setText(tButton.btnIDx + "," + tButton.btnIDy);
        tButton.getParent().getParent().tcPanel.al.actionPerformed(arg0);
        // tButton.getParent().setActionPerformed(tButton.btnIDx,tButton.btnIDy,imgChoice);
        imgChoice = !imgChoice;// flip the boolean

    }

}