package Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Alien {
    private Image image;
    private ImageView imageView;
    public Alien() {
        this.image = new Image("Images/Alien.png");
        imageView = new ImageView();
        this.imageView.setImage(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setX(225);
        imageView.setY(400);
    }
}
