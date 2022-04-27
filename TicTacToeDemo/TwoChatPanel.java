package TicTacToeDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 * This adds a panel to show the conversation
 * between the client and server.
 */
public class TwoChatPanel extends JPanel {
    JTextField text = new JTextField();
    JButton send = new JButton();
    JTextArea messages = new JTextArea(5, 50);
    PrintWriter pout;
    BufferedReader br;
    ActionListener al;
    String ipstring;
    boolean ready2send = false;
    TwoChatPanel pt;
    boolean HorC;
    String cliOrServ;
    ServerSocket ss;
    Socket s;
    TicTacToePanel m_game;
    TicTacToeButton m_tcbutton;

    /**
     * This constructor builds the UI components that are
     * used to message between the client and server.
     * 
     * @param hostOrConnect is this the host side of the client server
     * @param ip            the ip to connect to if this is a client.
     * @param game          the game panel that is being connected.
     */
    TwoChatPanel(boolean hostOrConnect, String ip, TicTacToePanel game) {
        m_game = game;
        setLayout(new GridLayout(3, 1));
        ipstring = ip;
        text.setSize(160, 30);
        text.setText("-------------------");
        add(text);
        send.setText("Send");
        send.setSize(60, 30);
        add(send);
        messages.setEditable(false);
        messages.setBorder(new EtchedBorder());
        messages.setLocation(5, text.getHeight() + text.getY() + 5);
        messages.setSize(getWidth() - 30, getHeight() - text.getY() - text.getHeight() - 50);

        add(messages);
        al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ready2send = true;
            }
        };
        // this adds the action listener to the send button.
        send.addActionListener(al);
        pt = this;
        HorC = hostOrConnect;
        if (HorC)
            cliOrServ = "\nServer: ";
        else
            cliOrServ = "\nClient: ";
        Messenger.start();
    }
//this is the conversation part of the application.
    Thread Messenger = new Thread() {
        public void run() {
            try {
                if (HorC) {
                    System.out.println("Waiting for an incoming connection.\nEnter my ip at client side.\nMy ip: "
                            + InetAddress.getLocalHost().getHostAddress());
                    messages.setText("Waiting for an incoming connection.\nEnter my ip at client side.\nMy ip: "
                            + InetAddress.getLocalHost().getHostAddress());
                    ss = new ServerSocket(9999);
                    s = ss.accept();
                    s.setKeepAlive(true);
                } else {
                    messages.setText("Connecting to:" + ipstring + ":9999");
                    s = new Socket(InetAddress.getByName(ipstring), 9999);
                }
                text.setEnabled(true);
                pout = new PrintWriter(s.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                messages.setText(messages.getText() + "\nConnected to:" + s.getInetAddress().getHostAddress() + ":"
                        + s.getPort());
                while (true) {
                    if (ready2send == true) {
                        pout.println(text.getText());
                        messages.setText(messages.getText() + "\nMe: " + text.getText());
                        boolean xoro = HorC; // cliOrServ.equalsIgnoreCase("client");
                        String[] xy = text.getText().split(",");
                        int btnIDx = Integer.parseInt(xy[0]);
                        int btnIDy = Integer.parseInt(xy[1]);
                        m_game.setActionPerformed(btnIDx, btnIDy, !xoro);
                        ready2send = false;
                    }
                    if (br.ready()) {
                        String move = br.readLine();
                        messages.setText(messages.getText() + cliOrServ + move);
                        boolean xoro = HorC; // cliOrServ.equalsIgnoreCase("client");
                        String[] xy = move.split(",");
                        int btnIDx = Integer.parseInt(xy[0]);
                        int btnIDy = Integer.parseInt(xy[1]);
                        m_game.setActionPerformed(btnIDx, btnIDy, xoro);
                    }
                    Thread.sleep(80);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(pt, ex.getMessage());
                messages.setText("Cannot connect!");
                try {
                    wait(3000);
                } catch (InterruptedException ex1) {
                }
                // System.exit(0);
            }
        }
    };
}
