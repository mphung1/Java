/**
 * This is the frame that holds all the GUI items.  Since it is the top most
 * component it will be the connector for the other UI components.
 *
 * @summary the frame or dialog for the game
 */
package TicTacToeDemo;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.*;

/**
 * the dialog that holds the game board.
 */
public class TicTacToeFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    TicTacToePanel tPanel;
    TicTacToeScorePanel sPanel;
    TwoChatPanel tcPanel;
    public boolean HorC;

    /**
     * the frame class constructor.
     */
    public TicTacToeFrame(boolean hostOrConnect, String ip) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());
        tPanel = new TicTacToePanel(this);
        add(tPanel, BorderLayout.CENTER);
        sPanel = new TicTacToeScorePanel();
        add(sPanel, BorderLayout.NORTH);
        HorC = hostOrConnect;
        tcPanel = new TwoChatPanel(HorC, ip, tPanel);
        JScrollPane scrollPane = new JScrollPane(tcPanel.messages);
        add(scrollPane, BorderLayout.SOUTH);
        // add(tcPanel,BorderLayout.EAST);
        if (hostOrConnect)
            setTitle("Host Game");
        else
            setTitle("Client Game");
        // setVisible(true);
    }
}