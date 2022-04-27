/**
 * logic to determine if the game has reached an end state.
 *
 * @summary determines if there is a winner
 */

package TicTacToeDemo;

/**
 * a class to solve if the game is complete
 */
public class TicTacToeSolver {
    /**
     * a method to compute if the game is done.
     * 
     * @param buttonGroup the collection of buttons
     * @return true if the game is won.
     */
    public static int solveGame(TicTacToeButton[][] buttonGroup) {
        boolean bFoundWin = false;

        // the horizontals
        if ((buttonGroup[0][0].getState()) && (buttonGroup[0][1].getState()) && (buttonGroup[0][2].getState()))
            bFoundWin = buttonGroup[0][0].equals(buttonGroup[0][1]) && buttonGroup[0][1].equals(buttonGroup[0][2]);

        if (!bFoundWin)
            if ((buttonGroup[1][0].getState()) && (buttonGroup[1][1].getState()) && (buttonGroup[1][2].getState()))
                bFoundWin = (buttonGroup[1][0].equals(buttonGroup[1][1])
                        && (buttonGroup[1][1].equals(buttonGroup[1][2])));

        if (!bFoundWin)
            if ((buttonGroup[2][0].getState()) && (buttonGroup[2][1].getState()) && (buttonGroup[2][2].getState()))
                bFoundWin = (buttonGroup[2][0].equals(buttonGroup[2][1])
                        && (buttonGroup[2][1].equals(buttonGroup[2][2])));
        // the verticals
        if (!bFoundWin)
            if ((buttonGroup[0][0].getState()) && (buttonGroup[1][0].getState()) && (buttonGroup[2][0].getState()))
                bFoundWin = (buttonGroup[0][0].equals(buttonGroup[1][0])
                        && (buttonGroup[1][0].equals(buttonGroup[2][0])));

        if (!bFoundWin)
            if ((buttonGroup[0][1].getState()) && (buttonGroup[1][1].getState()) && (buttonGroup[2][1].getState()))
                bFoundWin = (buttonGroup[0][1].equals(buttonGroup[1][1])
                        && (buttonGroup[1][1].equals(buttonGroup[2][1])));

        if (!bFoundWin)
            if ((buttonGroup[0][2].getState()) && (buttonGroup[1][2].getState()) && (buttonGroup[2][2].getState()))
                bFoundWin = (buttonGroup[0][2].equals(buttonGroup[1][2])
                        && (buttonGroup[1][2].equals(buttonGroup[2][2])));

        // now the diagonals
        if (!bFoundWin)
            if ((buttonGroup[0][0].getState()) && (buttonGroup[1][1].getState()) && (buttonGroup[2][2].getState()))
                bFoundWin = (buttonGroup[0][0].equals(buttonGroup[1][1])
                        && (buttonGroup[1][1].equals(buttonGroup[2][2])));

        if (!bFoundWin)
            if ((buttonGroup[0][2].getState()) && (buttonGroup[1][1].getState()) && (buttonGroup[2][0].getState()))
                bFoundWin = (buttonGroup[0][2].equals(buttonGroup[1][1])
                        && (buttonGroup[1][1].equals(buttonGroup[2][0])));
        // if we find one that has not been interacted set flag true and return 0 ?
        boolean moreButtons = false;
        if (!bFoundWin) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (buttonGroup[i][j].getState() == false)
                        moreButtons = true;
            if (moreButtons)
                return 0;
            else
                return -1;
        } else
            return 1;
    }
}
