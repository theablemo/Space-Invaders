package Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpaceShip {
    private Image image;
    private ImageView imageView;
    public SpaceShip() {
        this.image = new Image("Images/spaceship.png");
        imageView = new ImageView();
        this.imageView.setImage(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        imageView.setX(360);
        imageView.setY(550);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
