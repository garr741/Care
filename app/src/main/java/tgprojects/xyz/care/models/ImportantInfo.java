package tgprojects.xyz.care.models;

public class ImportantInfo {
    private String title;
    private String image;

    public ImportantInfo(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
