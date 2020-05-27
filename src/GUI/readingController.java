package GUI;
import Education.ImageWithName;
import javafx.scene.image.*;


public class readingController {

    public ImageView rImage1;
    public ImageView rImage2;
    public ImageView rImage3;
    public ImageView rImage4;
    public ImageWithName ok;

    public void initialize(){
        rImage1.setImage(fakeDatabase.getRandomImageWithName().getImage());
        rImage2.setImage(fakeDatabase.getRandomImageWithName().getImage());
        rImage3.setImage(fakeDatabase.getRandomImageWithName().getImage());
        rImage4.setImage(fakeDatabase.getRandomImageWithName().getImage());
    }
}
