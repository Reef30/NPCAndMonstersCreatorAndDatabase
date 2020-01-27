package controller;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ModelController;
import model.mappings.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class StatsManagingController implements Initializable,DatabaseListener {
    private MasterController masterController;
    private ModelController modelController;
    private Integer raceId;
    private Integer classId;
    private Integer careerId;
    private Integer careerLevel;
    private RaceStats raceStats;
    private ArrayList<Talents> talentsRaceList;
    private ArrayList<Talents> talentsCareerList;
    private ArrayList<Skills> skillsRaceList;
    private ArrayList<Skills> skillsCareerList;
    private ArrayList<Talents> talentsAdded= new ArrayList<>();
    private ArrayList<Skills> skillsAdded=new ArrayList<>();
    private boolean editing=false;
    private Characters character;
    private ArrayList<Talents> talentsList=new ArrayList<>();
    private  ArrayList<Skills> skillsList=new ArrayList<>();
    private Skills explainedSkill;
    private Talents explainedTalent;
    Random rand=new Random();
    @FXML
    AnchorPane rootPane;
    @FXML
    TextField wsText;
    @FXML
    TextField bsText;
    @FXML
    TextField sText;
    @FXML
    TextField tText;
    @FXML
    TextField iText;
    @FXML
    TextField agiText;
    @FXML
    TextField dexText;
    @FXML
    TextField intText;
    @FXML
    TextField wpText;
    @FXML
    TextField felText;
    @FXML
    TextField characterName;
    @FXML
    AnchorPane loading;
    @FXML
    private ListView<TalentSkillCell> talentsListView;
    @FXML
    private ListView<TalentSkillCell> skillsListView;
    private Integer characterId;

    //window manipulating methods
    private void setResizable(Boolean value){
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setResizable(value);
    }
    private void closeWindow(){
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    private void errorNameCharacterView(){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../../resources/view/dialogView.fxml"));
            Parent root = loader.load();
            DialogController dialogController = loader.getController();
            dialogController.setText("You need to name character!");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("ERROR!");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    private void createLoadingView(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/view/loadingView.fxml"));
            Parent root = loader.load();
            LoadingController loadingController = loader.getController();
            loading.getChildren().setAll(root);
            loadingController.startGif();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        loading.setVisible(false);
    }

    @FXML
    private void saveButtonClicked(){
        setTalentsAdded();
        setSkillsAdded();
        Characters character = makeCharacter();
        if(!characterName.getText().isEmpty()){
            character.setCharacterName(characterName.getText());
            if(editing){
                updateCharacter(character);
            }else{
                modelController.saveCharacter(character,skillsAdded,talentsAdded);
                rootPane.setVisible(false);
            }
        }else{
            errorNameCharacterView();
        }

    }
    private void setSkillsAdded(){
        skillsAdded.clear();
        for(TalentSkillCell skillCell:skillsListView.getItems()){
            if(skillCell.isAdded()){
                for(int i=0;i<skillsList.size();i++){
                    if(skillsList.get(i).getSkillName().equals(skillCell.getName())){
                        skillsAdded.add(skillsList.get(i));
                    }
                }
            }
        }
    }
    private void setTalentsAdded(){
        talentsAdded.clear();
        for(TalentSkillCell talentCell:talentsListView.getItems()){
            if(talentCell.isAdded()){
                for(int i=0;i<talentsList.size();i++){
                    if(talentsList.get(i).getTalentName().equals(talentCell.getName())){
                        talentsAdded.add(talentsList.get(i));
                    }
                }
            }
        }
    }
    private Characters makeCharacter(){
        character=new Characters();
        character.setRaceId(raceId);
        character.setCareerId(careerId);
        character.setCareerLevel(careerLevel);
        character.setStatWs(Integer.parseInt(wsText.getText()));
        character.setStatBs(Integer.parseInt(bsText.getText()));
        character.setStatS(Integer.parseInt(sText.getText()));
        character.setStatT(Integer.parseInt(tText.getText()));
        character.setStatI(Integer.parseInt(iText.getText()));
        character.setStatAgi(Integer.parseInt(agiText.getText()));
        character.setStatDex(Integer.parseInt(dexText.getText()));
        character.setStatInt(Integer.parseInt(intText.getText()));
        character.setStatWp(Integer.parseInt(wpText.getText()));
        character.setStatFel(Integer.parseInt(felText.getText()));
        return character;
    }
    private void updateCharacter(Characters character){
        closeWindow();
        character.setCharacterId(characterId);
        modelController.updateCharacter(character,skillsAdded,talentsAdded);
    }


    @FXML
    private void talentExplanationClicked(){
        loading.setVisible(true);
        modelController.addListener(this);
        modelController.getTalentByName(talentsListView.getSelectionModel().getSelectedItem().getName());
    }
    private void talentExplanationReady(){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../../resources/view/dialogView.fxml"));
            Parent root = loader.load();
            DialogController dialogController = loader.getController();
            String name=explainedTalent.getTalentName();
            dialogController.setText(explainedTalent.getDescription());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Description: "+name);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void skillExplanationButtonClicked(){
        loading.setVisible(true);
        modelController.addListener(this);
        modelController.getSkillByName(skillsListView.getSelectionModel().getSelectedItem().getName());
    }
    private void skillExplanationReady(){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../../resources/view/dialogView.fxml"));
            Parent root = loader.load();
            DialogController dialogController = loader.getController();
            String name=explainedSkill.getSkillName();
            dialogController.setText(explainedSkill.getDescription());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Description "+name);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void getBaseStats(){
        loading.setVisible(true);
        modelController.addListener(this);
        modelController.getRaceStats(raceId);
    }

    private void setCharacterStats(){
        Integer stat = raceStats.getStatWs()+rand.nextInt(10)+rand.nextInt(10)+2;
        wsText.setText(stat.toString());
        stat =raceStats.getStatBs()+rand.nextInt(10)+rand.nextInt(10)+2;
        bsText.setText(stat.toString());
        stat =raceStats.getStatS()+rand.nextInt(10)+rand.nextInt(10)+2;
        sText.setText(stat.toString());
        stat =raceStats.getStatT()+rand.nextInt(10)+rand.nextInt(10)+2;
        tText.setText(stat.toString());
        stat =raceStats.getStatI()+rand.nextInt(10)+rand.nextInt(10)+2;
        iText.setText(stat.toString());
        stat =raceStats.getStatAgi()+rand.nextInt(10)+rand.nextInt(10)+2;
        agiText.setText(stat.toString());
        stat =raceStats.getStatDex()+rand.nextInt(10)+rand.nextInt(10)+2;
        dexText.setText(stat.toString());
        stat =raceStats.getStatInt()+rand.nextInt(10)+rand.nextInt(10)+2;
        intText.setText(stat.toString());
        stat =raceStats.getStatWp()+rand.nextInt(10)+rand.nextInt(10)+2;
        wpText.setText(stat.toString());
        stat =raceStats.getStatFel()+rand.nextInt(10)+rand.nextInt(10)+2;
        felText.setText(stat.toString());
    }

    private void setRace(Integer raceId){
        this.raceId=raceId;
        if(!editing){
            this.getBaseStats();
        }
    }
    private void setClass(Integer classId){
            this.classId=classId;
    }
    private void setCareer(Integer careerId){
            this.careerId=careerId;
    }
    private void setCareerLevel(Integer careerLevel){
        this.careerLevel=careerLevel;
    }
    private void addTalentsAndSkills(){
                addRaceTalentsAndSkills();
    }
    private void addRaceTalentsAndSkills(){
        talentsListView.getItems().clear();
        skillsListView.getItems().clear();
        loading.setVisible(true);
        modelController.addListener(this);
        modelController.getTalentsFromRace(raceId);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       configureListsCelLFactories();
        createLoadingView();
    }
    public void configureListsCelLFactories(){
        talentsListView.setCellFactory(CheckBoxListCell.forListView(new Callback<TalentSkillCell, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TalentSkillCell cell) {
                return cell.addedProperty();
            }
        }));
        skillsListView.setCellFactory(CheckBoxListCell.forListView(new Callback<TalentSkillCell, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TalentSkillCell cell) {
                return cell.addedProperty();
            }
        }));
    }
    public void setMasterController(MasterController masterController){
        this.masterController=masterController;
    }
    public void editingMessage(Characters character){
        this.character=character;
        this.characterId=character.getCharacterId();
        this.modelController=masterController.getModelController();
        editing=true;
        setResizable(false);
        characterName.setText(character.getCharacterName());
        setEditedCharacterStats();
        setRaceCareerProperties(character.getRaceId(),null,character.getCareerId(),character.getCareerLevel());
        modelController.addListener(this);
        modelController.getTalentsFromRace(character.getRaceId());
    }
    public void transferChoices(Integer raceId, Integer classId, Integer careerId,Integer careerLevel){
        modelController = masterController.getModelController();
        setRaceCareerProperties(raceId,classId,careerId,careerLevel);
    }
    private void setRaceCareerProperties(Integer raceId, Integer classId, Integer careerId, Integer careerLevel){
        this.setRace(raceId);
        this.setClass(classId);
        this.setCareer(careerId);
        this.setCareerLevel(careerLevel);
    }
    private void setEditedCharacterStats(){
        Integer stat;
    stat=character.getStatWs();
    wsText.setText(stat.toString());

    stat=character.getStatBs();
    bsText.setText(stat.toString());

    stat=character.getStatS();
    sText.setText(stat.toString());

    stat=character.getStatT();
    tText.setText(stat.toString());

    stat=character.getStatI();
    iText.setText(stat.toString());

    stat=character.getStatAgi();
    agiText.setText(stat.toString());

    stat=character.getStatDex();
    dexText.setText(stat.toString());

    stat=character.getStatInt();
    intText.setText(stat.toString());

    stat=character.getStatWp();
    wpText.setText(stat.toString());

    stat=character.getStatFel();
    felText.setText(stat.toString());
}
    @Override
    public synchronized void dataAcquired() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (masterController.getExchanger().getRaceTalentsListUpdated()) {
                    readRaceTalentsList();
                } else if (masterController.getExchanger().getCareerTalentsListUpdated()) {
                    readCareerTalentsList();
                } else if (masterController.getExchanger().getRaceSkillsListUpdated()) {
                    readRaceSkillsList();
                } else if (masterController.getExchanger().getCareerSkillsListUpdated()) {
                    readCareerSkillsList();
                } else if (masterController.getExchanger().getRaceStatsUpdated()) {
                    readRaceStats();
                } else if (masterController.getExchanger().getCharacterTalentsListUpdated()) {
                    readCharacterTalents();
                } else if (masterController.getExchanger().getCharacterSkillsListUpdated()) {
                    readCharacterSkills();
                } else if (masterController.getExchanger().getSkillUpdated()) {
                    readSkill();
                } else if (masterController.getExchanger().getTalentUpdated()) {
                    readTalent();
                }
                loading.setVisible(false);
            }
        });
    }
    private synchronized void readRaceTalentsList(){
                talentsRaceList = masterController.getExchanger().getRaceTalentsList();
                for(int i=0;i<talentsRaceList.size();i++) {
                    for(int j=0;j<talentsList.size();j++){
                        if(talentsList.get(j).getTalentName().equals(talentsRaceList.get(i).getTalentName())){
                            talentsList.remove(j);
                        }
                    }
                    talentsList.add(talentsRaceList.get(i));
                }
                modelController.getTalentsFromCareer(careerId,careerLevel);
    }
    private synchronized void readCareerTalentsList(){
                talentsCareerList = masterController.getExchanger().getCareerTalentsList();
                for(int i=0;i<talentsCareerList.size();i++) {
                    for(int j=0;j<talentsList.size();j++){
                        if(talentsList.get(j).getTalentName().equals(talentsCareerList.get(i).getTalentName())){
                            talentsList.remove(j);
                        }
                    }
                    talentsList.add(talentsCareerList.get(i));
                }
                talentsListView.getItems().clear();
                for(int i=0;i<talentsList.size();i++) {
                    TalentSkillCell talentCell = new TalentSkillCell(talentsList.get(i).getTalentName(), false);
                    talentsListView.getItems().add(talentCell);
                }
                if(talentsListView.getItems().size()>0&&!editing) {
                    talentsListView.getItems().get(rand.nextInt(talentsListView.getItems().size())).setAdded(true);
                    talentsListView.getItems().get(rand.nextInt(talentsListView.getItems().size())).setAdded(true);
                    talentsListView.getItems().get(rand.nextInt(talentsListView.getItems().size())).setAdded(true);
                }
        modelController.getSkillsFromRace(raceId);
    }
    private synchronized void readRaceSkillsList(){
                skillsRaceList = masterController.getExchanger().getRaceSkillsList();
                for(int i=0;i<skillsRaceList.size();i++) {
                    for(int j=0;j<skillsList.size();j++){
                        if(skillsList.get(j).getSkillName().equals(skillsRaceList.get(i).getSkillName())){
                            skillsList.remove(j);
                        }
                    }
                    skillsList.add(skillsRaceList.get(i));
                }
                /*skillsListView.getItems().clear();
                for(int i=0;i<skillsList.size();i++) {
                    TalentSkillCell skillCell = new TalentSkillCell(skillsList.get(i).getSkillName(), false);
                    skillsListView.getItems().add(skillCell);
                }

                 */
                modelController.getSkillsFromCareer(careerId,careerLevel);
    }
    private synchronized void readCareerSkillsList (){
                skillsCareerList = masterController.getExchanger().getCareerSkillsList();
                for(int i=0;i<skillsCareerList.size();i++) {
                    for(int j=0;j<skillsList.size();j++){
                        if(skillsList.get(j).getSkillName().equals(skillsCareerList.get(i).getSkillName())){
                            skillsList.remove(j);
                        }
                    }
                    skillsList.add(skillsCareerList.get(i));
                }
                skillsListView.getItems().clear();
                for(int i=0;i<skillsList.size();i++){
                    TalentSkillCell skillCell=new TalentSkillCell(skillsList.get(i).getSkillName(),false);
                    skillsListView.getItems().add(skillCell);
                }
                if(skillsListView.getItems().size()>0&&!editing) {
                    skillsListView.getItems().get(rand.nextInt(skillsListView.getItems().size())).setAdded(true);
                    skillsListView.getItems().get(rand.nextInt(skillsListView.getItems().size())).setAdded(true);
                    skillsListView.getItems().get(rand.nextInt(skillsListView.getItems().size())).setAdded(true);
                }
        modelController.removeListener(this);
        loading.setVisible(false);
        if(editing){
            loading.setVisible(true);
            modelController.addListener(this);
            modelController.getSkillsOfCharacter(character);
        }
    }
    private synchronized void readRaceStats(){
        this.raceStats=masterController.getExchanger().getRaceStats();
                setCharacterStats();
        modelController.removeListener(this);
        addTalentsAndSkills();
    }
    private synchronized void readCharacterTalents(){
                talentsAdded.addAll(masterController.getExchanger().getCharacterTalentsList());
                for(Talents talent:talentsAdded){
                    for(int i =0;i<talentsListView.getItems().size();i++){
                        if(talentsListView.getItems().get(i).getName().equals(talent.getTalentName())){
                            talentsListView.getItems().get(i).setAdded(true);
                        }
                    }
                }
                loading.setVisible(false);
        modelController.removeListener(this);
    }
    private synchronized void readCharacterSkills(){
                skillsAdded.addAll(masterController.getExchanger().getCharacterSkillsList());
                for(Skills skill:skillsAdded){
                    for(int i =0;i<skillsListView.getItems().size();i++){
                        if(skillsListView.getItems().get(i).getName().equals(skill.getSkillName())){
                            skillsListView.getItems().get(i).setAdded(true);
                        }
                    }
                }
            modelController.getTalentsOfCharacter(character);
                loading.setVisible(false);
    }
    private synchronized void readSkill(){
        explainedSkill=masterController.getExchanger().getSkill();
        modelController.removeListener(this);
                skillExplanationReady();
                loading.setVisible(false);
    }
    private synchronized void readTalent(){
        explainedTalent=masterController.getExchanger().getTalent();
        modelController.removeListener(this);
                talentExplanationReady();
                loading.setVisible(false);
    }
}