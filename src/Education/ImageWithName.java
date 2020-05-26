package Education;

import javafx.scene.image.*;

public class ImageWithName {
    private String name;
    private Image image;

    public ImageWithName(String name, Image image){
        this.name = name;
        this.image = image;
    }
    public String getName(){
        return name;
    }
    public Image getImage(){
        return image;
    }
}
