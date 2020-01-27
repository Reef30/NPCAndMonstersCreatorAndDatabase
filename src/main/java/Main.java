import controller.MasterController;
import controller.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MasterController masterController=new MasterController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/startView.fxml"));
        Parent root = loader.load();
        StartController startController =loader.getController();
        startController.setMasterController(masterController);
        Stage mainStage=primaryStage;
        mainStage.setResizable(false);
        mainStage.setTitle("Character base and creator for WFRP 4th edition");
        mainStage.setScene(new Scene(root, 300, 600));
        mainStage.centerOnScreen();
        mainStage.show();
        masterController.addMainStage(mainStage);
        masterController.addRoot(root);
    }
    public static void main(String[] args) {
        launch(args);
    }

}
