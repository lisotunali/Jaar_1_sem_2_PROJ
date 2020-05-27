package Education;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ImageWithName{
    private String name;
    private Image image;
    private ArrayList<ImageWithName> imagesDatabase = new ArrayList<>();

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

    public ImageWithName getRandomImageWithName(){

        return imagesDatabase.get(1);
    }
}
