import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler {
    String name;
    private ObjectOutputStream write;
    private Socket socket;
    private ObjectInputStream read;

    public ClientHandler(String name, String ip, int port) {
        this.name = name;

        try {
            socket = new Socket(ip, port);
            write = new ObjectOutputStream(socket.getOutputStream());
            write.writeObject(name);  
            read = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClientHandler(String name, Socket socket) {
        try {
            this.socket = socket;
            write = new ObjectOutputStream(socket.getOutputStream());  
            read = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeClient() {
        try {
            write.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        try {
            write.writeObject(message);
            write.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message read() {
        try {
            return (Message) read.readObject();
        } catch (NullPointerException e) {
            return null;
        } catch (SocketException e) {
            Main.removeConnection(this);
            closeClient();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getName() {
        return name;
    }
}