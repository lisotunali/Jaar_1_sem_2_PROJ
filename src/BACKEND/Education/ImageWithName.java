package BACKEND.Education;

import javafx.scene.image.Image;

public class ImageWithName {
    private final String name;
    private final Image image;

    public ImageWithName(String name, Image image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }
}
