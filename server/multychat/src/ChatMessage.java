import java.io.Serializable;

enum ChatMessageType {
    MESSAGE, LOGOUT
}
public class ChatMessage implements Serializable {


    public ChatMessageType type;
    private String message;

    ChatMessage(ChatMessageType type, String message) {
        this.type = type;
        this.message = message;
    }

    ChatMessageType getType() {
        return type;
    }

    String getMessage() {
        return message;
    }
}
