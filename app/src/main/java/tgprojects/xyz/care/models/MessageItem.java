package tgprojects.xyz.care.models;

public class MessageItem {
    private boolean mine;
    private String message;

    public MessageItem(boolean mine, String message) {
        this.mine = mine;
        this.message = message;
    }

    public boolean isMine() {
        return mine;
    }

    public String getMessage() {
        return message;
    }

}
