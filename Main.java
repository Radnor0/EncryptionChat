import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

    private static boolean isHost = false;
    private static String ip = "", name = "";
    private static int port;
    private static volatile ArrayList<ClientHandler> connections;
    private static RoomWindow win;

    public static void main(String[] args) {
        int hostChoice = JOptionPane.showOptionDialog(null, "Are you a host or a client?", "Encryption Chat", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Host", "Client"}, "Host");
        isHost = hostChoice == 0;

        if (!isHost)
            ip = JOptionPane.showInputDialog(null, "Enter IP to connect to");

        try {
            port = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter port number to connect to", 7777));
        } catch (NumberFormatException e) {
            port = 7777;
        }

        if (!isHost) {
            try {
                if (ip.equals(""))
                    return;
            }
            catch (NullPointerException e) {
                return;
            }
        }

        connections = new ArrayList<ClientHandler>();
        name = JOptionPane.showInputDialog(null, "Enter your name");

        if (isHost) {
            new Thread(new ConnectionListener(port)).start();
        }
        else
            connections.add(new ClientHandler(name, ip, port));

        win = new RoomWindow();

        while (true) {
            for (ClientHandler c : connections) {
                Message in = c.read();
                if (in == null)
                    continue;

                if (in.getContents() != null && in.getContents().trim() != "") 
                    win.addMessage(in.getSender() + ": " + in.getContents());   
            }
        }
    }

    public static void sendMessage(String message) {
        for (ClientHandler c : connections)
            c.sendMessage(new Message(name, message));
            win.addMessage(name + ": " + message + "\n");
    }

    public static void addConnection(ClientHandler client) {
        for (ClientHandler c : connections)
            c.sendMessage(new Message("System", client.getName() + " has connected to the server."));
        connections.add(client);
        win.addMessage("System: " + client.getName() + " has connected to the server." + "\n");
    }

    public static void removeConnection(ClientHandler client) {
        connections.remove(client);
        for (ClientHandler c : connections)
            c.sendMessage(new Message("System", client.getName() + " has disconnected from the server."));
        win.addMessage("System: " + client.getName() + " has disconnected from the server." + "\n");
    }
}