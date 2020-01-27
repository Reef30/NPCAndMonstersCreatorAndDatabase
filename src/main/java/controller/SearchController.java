package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import model.CharacterSignatures;
import model.ModelController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController implements Initializable,DatabaseListener {
    private ArrayList<CharacterSignatures> characterSignatures;
    private ModelController modelController;
    private MasterController masterController;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<CharacterSignatures> tab;
    @FXML
    private TableColumn<CharacterSignatures,String>race;
    @FXML
    private TableColumn<CharacterSignatures,String>name;
    @FXML
    private void handleDetailsAction() throws IOException {

        if(tab.getSelectionModel().getSelectedIndex()!=-1){
            DetailsController detailsController;
            //dopisac wczytywanie danych konkretnego obiektu po jego wyborze
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/detailsView.fxml"));
            AnchorPane pane = loader.load();
            rootPane.getChildren().setAll(pane);
            detailsController =loader.getController();
            detailsController.setMasterController(masterController);
            detailsController.setParentController(this);
            detailsController.setDetailedCharacter(tab.getSelectionModel().getSelectedItem().getCharacterId());
        }else{
            try {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/dialogView.fxml"));
                Parent root = loader.load();
                DialogController dialogController = loader.getController();
                dialogController.setText("Choose character to see details!");
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("ERROR!");
                stage.show();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }

    }
    public void setCharacterSignatures(){
        //loading on
        modelController.addListener(this);
        modelController.getCharacterSignatures();
    }
    private void setTabData(){
        tab.getItems().clear();
        race.setCellValueFactory(new PropertyValueFactory<CharacterSignatures, String>("race"));
        name.setCellValueFactory(new PropertyValueFactory<CharacterSignatures, String>("name"));
        for (int i = 0; i < characterSignatures.size(); i++) {
            tab.getItems().add(characterSignatures.get(i));
        }
    }
    public void setMasterController(MasterController masterController){
        this.masterController=masterController;
        modelController=masterController.getModelController();
        this.setCharacterSignatures();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public synchronized void dataAcquired() {
        if(masterController.getExchanger().getSignaturesUpdated()){
            characterSignatures=masterController.getExchanger().getSignatures();
            masterController.getModelController().removeListener(this);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    setTabData();
                }
            });
            //loading off
        }
    }
}
