package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class ServerGUI extends Application {

    private static Scene scene;

    // Initialization for UI
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerGUI.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    // Scene start
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("server"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

}