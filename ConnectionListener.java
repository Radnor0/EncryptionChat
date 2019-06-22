import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener implements Runnable {

    private ServerSocket socket;

    public ConnectionListener(int port) {
        try {
            socket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = socket.accept();
                String name = new ObjectInputStream(client.getInputStream()).readObject().toString();
                Main.addConnection(new ClientHandler(name, client));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}