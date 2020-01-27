package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    MasterController masterController;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private void searchButtonClicked(ActionEvent event) throws IOException {
        masterController.getModelController().removeAllListeners();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../../resources/view/searchView.fxml"));
        AnchorPane pane = loader.load();
        SearchController searchController =loader.getController();
        searchController.setMasterController(masterController);
        rootPane.getChildren().setAll(pane);
    }
    @FXML
    private void createButtonClicked(ActionEvent event) throws IOException {
        masterController.getModelController().removeAllListeners();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../../resources/view/createView.fxml"));
        AnchorPane pane = loader.load();
        CreateController createController =loader.getController();
        createController.setMasterController(masterController);
        rootPane.getChildren().setAll(pane);
    }
    public void setMasterController(MasterController masterController){
        this.masterController=masterController;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }
}