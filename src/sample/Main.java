package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage stage = new Stage();
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage.setTitle("Calculator");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
