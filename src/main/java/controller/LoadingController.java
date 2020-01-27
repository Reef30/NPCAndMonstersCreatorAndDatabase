package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {

    @FXML
    ImageView imageView;
    public void startGif(){
        File loadingImage=new File("loading.gif");
        Image i = new Image(loadingImage.toURI().toString());
        imageView.setImage(i);
        imageView.setVisible(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
