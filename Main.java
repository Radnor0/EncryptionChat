import javax.swing.JOptionPane;

public class Main {

    private static boolean isHost = false;
    private static String ip = "";

    public static void main(String[] args) {
        int hostChoice = JOptionPane.showOptionDialog(null, "Are you a host or a client?", "Encryption Chat", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Host", "Client"}, "Host");
        isHost = hostChoice == 0;
        System.out.println(isHost);

        if (!isHost)
            ip = JOptionPane.showInputDialog(null, "Enter IP to connect to");

        if (!isHost) {
            try {
                if (ip.equals(""))
                    return;
            }
            catch (NullPointerException e) {
                return;
            }
        }

        new RoomWindow();
    }

    public static void sendMessage(String message) {
    }
}