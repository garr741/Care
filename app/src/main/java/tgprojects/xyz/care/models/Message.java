package tgprojects.xyz.care.models;

/**
 * Created by tylorgarrett on 6/17/17.
 */

public class Message {
    private int id;
    private String content;
    private String name;

    public Message(int id, String content, String name) {
        this.id = id;
        this.content = content;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
