package controller;

import javafx.scene.Parent;
import javafx.stage.Stage;
import model.ModelController;
import model.SynchronizedDataExchanger;

public class MasterController {
    private SynchronizedDataExchanger exchanger;
    private Stage mainStage;
    private Parent root;
    private ModelController modelController;

    public MasterController(){
        this.modelController=new ModelController(this);
        this.exchanger=new SynchronizedDataExchanger();
    }
    public void addMainStage(Stage stage){
        this.mainStage=stage;
    }
    public void addRoot(Parent root){
        this.root=root;
    }
    public ModelController getModelController(){
        return modelController;
    }
    public SynchronizedDataExchanger getExchanger(){return exchanger;}
}
