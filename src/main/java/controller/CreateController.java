package controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.ModelController;
import model.mappings.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CreateController implements Initializable,DatabaseListener{
    private Random rand;
    private volatile Boolean editing=false;
    private ModelController modelController;
    private MasterController masterController;
    private volatile   StatsManagingController statsManagingController;
    private volatile Integer careerId=-1;
    private volatile Integer raceId=-1;
    private volatile Integer classId=-1;
    private volatile Boolean creating=false;
    private volatile Integer careerSelected=-1;
    private volatile String selectedCareerName="";
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ChoiceBox racesChoice;
    @FXML
    private ChoiceBox classesChoice;
    @FXML
    private ChoiceBox careersChoice;
    @FXML
    private Label careerLevelLabel;
    @FXML
    private Label classes;
    @FXML
    private Label careers;
    @FXML
    private ChoiceBox careerLevels;
    @FXML
    private AnchorPane loading;
    private Characters editedCharacter;

    /*Platform.runLater(new Runnable() {
            @Override
            public void run() {
                racesChoice.getSelectionModel().select(raceId);
                classesChoice.getSelectionModel().select(classId);
                careersChoice.getSelectionModel().select(careerIdTemp);
            }
        });

     */
    @FXML
    private void createButtonClicked(ActionEvent event) throws IOException {
        if(classesChoice.getItems().size()==0) {
            creating=true;
        }

        loading.setVisible(true);
        if (racesChoice.getSelectionModel().getSelectedIndex() == -1) {
            racesChoice.getSelectionModel().select(rand.nextInt(racesChoice.getItems().size()));
        }
        if(classesChoice.getSelectionModel().getSelectedIndex()==-1){
            classesChoice.getSelectionModel().select(rand.nextInt(classesChoice.getItems().size()));
        }
        modelController.addListener(this);
        modelController.getRaceId(racesChoice.getSelectionModel().getSelectedItem().toString());
    }
    private void createCompleted(){
        setStatsManagingView();
        statsManagingController.setMasterController(masterController);
        this.transferChoices(statsManagingController);
        classes.setVisible(true);
        careers.setVisible(true);
        careerLevels.setVisible(true);
        rootPane.setVisible(true);
        creating=false;
        loading.setVisible(false);
    }
    private void careerUpdate(){
            if(careersChoice.getSelectionModel().getSelectedIndex()==-1){
                careersChoice.getSelectionModel().select(rand.nextInt(careersChoice.getItems().size()));
            }
        modelController.addListener(this);
        modelController.getCareerId(careersChoice.getSelectionModel().getSelectedItem().toString());
    }
    public void initializeRacesChoice(){
        modelController.addListener(this);
        racesChoice.getItems().clear();
        classesChoice.getItems().clear();
        modelController.getRaces();
        loading.setVisible(true);
    }
    @FXML
    private void racesSelected(ActionEvent event){
    }
    @FXML
    private void classesSelected(ActionEvent event)  {
        careerRead();
    }
    private void careerRead(){
        loading.setVisible(true);
        modelController.addListener(this);
        careersChoice.getItems().clear();
        modelController.getCareersFromClass(classesChoice.getSelectionModel().getSelectedIndex()+1);
    }
    @FXML
    private void careersSelected(ActionEvent event)  {
        careerLevelLabel.setVisible(true);
        careerLevels.setVisible(true);
        if(careersChoice.getSelectionModel().getSelectedItem().toString()!=null)
            selectedCareerName=careersChoice.getSelectionModel().getSelectedItem().toString();
        careerSelected=careersChoice.getSelectionModel().getSelectedIndex();

    }
    public void setMasterController(MasterController masterController){
        this.masterController=masterController;
        this.modelController=masterController.getModelController();
            initializeRacesChoice();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rand=new Random();
        careerLevels.getItems().add(1);
        careerLevels.getItems().add(2);
        careerLevels.getItems().add(3);
        careerLevels.getItems().add(4);
        careerLevels.setValue(careerLevels.getItems().get(0));
        classesChoice.setVisible(false);
        classes.setVisible(false);
        careersChoice.setVisible(false);
        careers.setVisible(false);
        careerLevels.setVisible(false);
        careerLevelLabel.setVisible(false);
        setLoadingGif();
    }
    public void setLoadingGif(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/loadingView.fxml"));
            Parent root = loader.load();
            LoadingController loadingController = loader.getController();
            loading.getChildren().setAll(root);
            loadingController.startGif();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        loading.setDisable(true);
    }
    public void setStatsManagingView(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/statsManagingView.fxml"));
            AnchorPane pane = loader.load();
            statsManagingController = loader.getController();
            rootPane.getChildren().setAll(pane);
            if(editing){
                careerLevels.getSelectionModel().select(editedCharacter.getCareerLevel()-1);
                StatsManagingController statsManagingController= loader.getController();
                statsManagingController.setMasterController(masterController);
                statsManagingController.editingMessage(editedCharacter);
            }
        }catch (IOException e){}
        rootPane.setVisible(false);
        if(editing)
            rootPane.setVisible(true);
    }
    public void transferEditingMessage(Characters character) throws IOException {
        this.editedCharacter=character;
        editing=true;
        loading.setVisible(true);
        //wywołanie inicjalizatora wypełniającego wczytanymi danymi edytor

    }
    public void settingEditedDataCompleted(){
        careersChoice.setVisible(true);
        classesChoice.setVisible(true);
        careerLevels.setVisible(true);
        careerLevelLabel.setVisible(true);
        setStatsManagingView();
        loading.setVisible(false);
    }
    private void transferChoices(StatsManagingController controller){

        classesChoice.setVisible(true);
        careersChoice.setVisible(true);
        careers.setVisible(true);
        controller.setMasterController(masterController);
        controller.transferChoices(raceId,classId,careerId,
                careerLevels.getSelectionModel().getSelectedIndex()+1);
    }
    private void selectRaceAndClass(){
            racesChoice.getSelectionModel().select(editedCharacter.getRaceId()-1);
        modelController.addListener(this);
       modelController.getClassOfCareer(editedCharacter.getCareerId());
    }
    @Override
    public synchronized void dataAcquired() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(masterController.getExchanger().getRacesListUpdated()){
                    ArrayList<Races>racesList=masterController.getExchanger().getRacesList();
                            for(int i=0;i<racesList.size();i++) {
                                racesChoice.getItems().add(racesList.get(i).getRaceName());
                            }
                    modelController.getClasses();
                }
                if(masterController.getExchanger().getClassesListUpdated()){
                    ArrayList<Classes> classList = masterController.getExchanger().getClassesList();
                            for(int i=0;i<classList.size();i++) {
                                classesChoice.getItems().add(classList.get(i).getClassName());
                            }
                    removeThisFromListeners();
                    classesChoice.setVisible(true);
                    classes.setVisible(true);
                    loading.setVisible(false);
                    if(editing){
                        selectRaceAndClass();
                    }

                }
                if(masterController.getExchanger().getCareersListUpdated()){
                    ArrayList<Careers> careerList =masterController.getExchanger().getCareersList();
                    for(int i=0;i<careerList.size();i++) {
                        careersChoice.getItems().add(careerList.get(i).getCareerName());
                    }
                    careers.setVisible(true);
                    careersChoice.setVisible(true);
                    removeThisFromListeners();
                    loading.setVisible(false);
                            careerUpdate();
                    if(editing){
                        addThisToListeners();
                        modelController.getRace(editedCharacter.getRaceId());
                    }
                }
                if(masterController.getExchanger().getRaceIdUpdated()){
                    raceId=masterController.getExchanger().getRaceId();
                    modelController.getClassId(classesChoice.getSelectionModel().getSelectedItem().toString());
                }
                if(masterController.getExchanger().getClassIdUpdated()){
                    classId=masterController.getExchanger().getClassId();
                    modelController.getCareerId(selectedCareerName);
                }
                if(masterController.getExchanger().getCareerIdUpdated()){
                    careerId=masterController.getExchanger().getCareerId();
                    removeThisFromListeners();
                    if(raceId!=-1&&classId!=-1&&careerId!=-1){
                        loading.setVisible(false);
                        createCompleted();
                    }
                }
                if(masterController.getExchanger().getRaceUpdated()){
                            racesChoice.getSelectionModel().select(masterController.getExchanger().getRace().getRaceName());
                    modelController.getClassOfCareer(editedCharacter.getCareerId());

                }
                if(masterController.getExchanger().getClassOfCareerUpdated()){
                    classesChoice.getSelectionModel().select(masterController.getExchanger().getClassOfCareer().getClassName());
                    modelController.getCareer(editedCharacter.getCareerId());
                    removeThisFromListeners();
                    settingEditedDataCompleted();

                }
                if(masterController.getExchanger().getCareerUpdated()){
                    careersChoice.getSelectionModel().select(masterController.getExchanger().getCareer().getCareerName());
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
