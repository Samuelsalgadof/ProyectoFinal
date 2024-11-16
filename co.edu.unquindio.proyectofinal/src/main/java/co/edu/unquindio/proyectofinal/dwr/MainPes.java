package co.edu.unquindio.proyectofinal.dwr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPes extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainPes.class.getResource("usuario.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("samuel mlp!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
//Muñoz perrita tonta
