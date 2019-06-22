import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RoomWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextArea chat;
    private JTextField input;
    private JButton send;

    public RoomWindow() {
        super("Chat Room");

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        chat = new JTextArea();
        chat.setEditable(false);
        getContentPane().add(chat);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        input = new JTextField();
        inputPanel.add(input);
        send = new JButton("Send");
        send.addActionListener((e) -> {Main.sendMessage(input.getText()); input.setText("");});
        inputPanel.add(send);
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
        getContentPane().add(inputPanel);

        setSize(800, 750);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addMessage(String text) {
        chat.setText(chat.getText() + text);
    }
}