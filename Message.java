import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private String contents, sender;

    public Message(String sender, String contents) {
        this.sender = sender;
        this.contents = contents + "\n";
    }

    public String getSender() {
        return sender;
    }

    public String getContents() {
        return contents;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}