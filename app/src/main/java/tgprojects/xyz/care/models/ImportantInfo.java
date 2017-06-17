package tgprojects.xyz.care.models;

import android.graphics.Bitmap;

public class ImportantInfo {
    private String title;
    private String image;
    private Bitmap bitmap;

    public ImportantInfo(String title, String image, Bitmap bitmap) {
        this.title = title;
        this.image = image;
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
