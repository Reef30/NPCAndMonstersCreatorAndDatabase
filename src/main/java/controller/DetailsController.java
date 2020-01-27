package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ModelController;
import model.mappings.Careers;
import model.mappings.Characters;
import model.mappings.Skills;
import model.mappings.Talents;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DetailsController implements Initializable,DatabaseListener {
    private Characters detailedCharacter;
    private SearchController parentController;
    private MasterController masterController;
    private ModelController modelController;
    private String careerLevel="";
    ArrayList<Talents>talents;
    @FXML
    private AnchorPane rootPane;

    @FXML
    Label wsLabel;
    @FXML
    Label bsLabel;
    @FXML
    Label sLabel;
    @FXML
    Label tLabel;
    @FXML
    Label iLabel;
    @FXML
    Label agiLabel;
    @FXML
    Label dexLabel;
    @FXML
    Label intLabel;
    @FXML
    Label wpLabel;
    @FXML
    Label felLabel;
    @FXML
    Label skillsListing;
    @FXML
    Label talentsListing;
    @FXML
    Label summaryLabel;
    @FXML
    ListView talentList;
    @FXML
    ListView skillList;
    @FXML
    private void deleteButtonClicked(ActionEvent event)  {
        modelController=masterController.getModelController();
        modelController.addListener(this);
        modelController.deleteCharacter(detailedCharacter);
    }
    @FXML
    private void editButtonClicked(ActionEvent event)  {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/createView.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit Character");
            CreateController createController= loader.getController();
            stage.show();
            createController.setMasterController(masterController);
            createController.transferEditingMessage(detailedCharacter);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    public void setDetailedCharacter(Integer characterId){
        modelController=masterController.getModelController();
        //loading on
        modelController.addListener(this);
        modelController.getCharacter(characterId);
        //brak loading screeena
    }
    private void setCharacterDetails(){
        Integer stat;
        stat=detailedCharacter.getStatWs();
        wsLabel.setText(stat.toString());

        stat=detailedCharacter.getStatBs();
        bsLabel.setText(stat.toString());

        stat=detailedCharacter.getStatS();
        sLabel.setText(stat.toString());

        stat=detailedCharacter.getStatT();
        tLabel.setText(stat.toString());

        stat=detailedCharacter.getStatI();
        iLabel.setText(stat.toString());

        stat=detailedCharacter.getStatAgi();
        agiLabel.setText(stat.toString());

        stat=detailedCharacter.getStatDex();
        dexLabel.setText(stat.toString());

        stat=detailedCharacter.getStatInt();
        intLabel.setText(stat.toString());

        stat=detailedCharacter.getStatWp();
        wpLabel.setText(stat.toString());

        stat=detailedCharacter.getStatFel();
        felLabel.setText(stat.toString());

        //ładowanie trzeba dodać ale później narazie to zadziała
        modelController.addListener(this);
        modelController.getCareer(detailedCharacter.getCareerId());
    }
    public void setMasterController(MasterController masterController){
        this.masterController=masterController;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void setParentController(SearchController searchController){
        this.parentController= searchController;
    }

    @Override
    public synchronized void dataAcquired() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
        if(masterController.getExchanger().getCareerUpdated()){
                    Careers career=masterController.getExchanger().getCareer();
                    if(detailedCharacter.getCareerLevel()==1){
                        careerLevel=career.getLevel1();
                    }else if(detailedCharacter.getCareerLevel()==2){
                        careerLevel=career.getLevel2();
                    }else if(detailedCharacter.getCareerLevel()==3){
                        careerLevel=career.getLevel3();
                    }else if(detailedCharacter.getCareerLevel()==4){
                        careerLevel=career.getLevel4();
                    }
                    summaryLabel.setText("name: "+detailedCharacter.getCharacterName()+
                            "\tcareer: "+career.getCareerName()+
                            "\ncareer level: "+careerLevel);
            removeThisFromListeners();
            }
        if(masterController.getExchanger().getCharacterTalentsListUpdated()){
                    talents=masterController.getExchanger().getCharacterTalentsList();
                    for(int i=0;i<talents.size();i++){
                        talentsListing.setText("Talents: ");
                         talentList.getItems().add(talents.get(i).getTalentName());
                    }
            removeThisFromListeners();
            //wylacz loading
        }
        if(masterController.getExchanger().getCharacterSkillsListUpdated()){
            ArrayList<Skills>skills=masterController.getExchanger().getCharacterSkillsList();
                    skillsListing.setText("Skills: ");
                    for(int i=0;i<skills.size();i++){
                        skillList.getItems().add(skills.get(i).getSkillName());
                    }
            modelController.getTalentsOfCharacter(detailedCharacter);
            //wylacz loading
        }
        if(masterController.getExchanger().getCharacterUpdated()){
            detailedCharacter=masterController.getExchanger().getCharacter();
            removeThisFromListeners();
            setCharacterDetails();
            addThisToListeners();
            modelController.getSkillsOfCharacter(detailedCharacter);
        }
        if(masterController.getExchanger().getDeleted()){
            parentController.setCharacterSignatures();
            rootPane.setVisible(false);
            masterController.getExchanger().setDeleted(false);
            removeThisFromListeners();
        }
            }
        });
    }
    public void addThisToListeners(){
        modelController.addListener(this);
    }
    public void removeThisFromListeners(){
        modelController.removeListener(this);
    }
}
