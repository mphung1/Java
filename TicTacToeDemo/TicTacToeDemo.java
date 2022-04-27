/**
 * First pass at the TicTacToe game UI
 * note: set the DISPLAY=:0.0 to run on my configuration.                        
 * @summary short description for the file
 */
package TicTacToeDemo;

import java.net.InetAddress;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This is the entry point class for the project.
 * 
 */
public class TicTacToeDemo {
    static TicTacToeFrame game = null;// = new TicTacToeFrame();

    private static void CreateFrameAndRun(boolean isServer, String ipString) {
        game = new TicTacToeFrame(isServer, ipString);
        game.setVisible(true);
    }

    public static void createAndShowGUI(boolean isServer, String ipString) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CreateFrameAndRun(isServer, ipString);
            }
        });
    }

    /**
     * the main entry method as required by the JVM to run
     * 
     * @param args an array of string objects that can be passed into the method not
     *             used.
     */
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        int inp = JOptionPane.showConfirmDialog(null,
                "Do you want to host the chat?\nYes - Act as server\nNo - Act as client", "Want to host a chat?",
                JOptionPane.YES_NO_OPTION);
        if (inp == 0) {
            createAndShowGUI(true, null);
            // game = new TicTacToeFrame(true, null);// .setVisible(true);
        } else {
            String ipstring = JOptionPane.showInputDialog("Please enter the ip address");
            try {
                InetAddress.getByName(ipstring);
                createAndShowGUI(false, ipstring);
                // game = new TicTacToeFrame(false, ipstring);// .setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid or Unreachable IP");
            }
        }
    }
}