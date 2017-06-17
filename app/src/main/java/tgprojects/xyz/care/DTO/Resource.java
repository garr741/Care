package tgprojects.xyz.care.DTO;

import java.net.URL;

/**
 * Created by mvieck on 6/17/17.
 */

public class Resource {
    private String url;
    private String subject;
    private String title;
    private String description;

    public Resource() {
    }

    public Resource(String url, String subject, String title, String description) {
        this.url = url;
        this.subject = subject;
        this.title = title;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
