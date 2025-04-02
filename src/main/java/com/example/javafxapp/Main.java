package com.example.javafxapp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // quan li san pham 2
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javafxapp/view/login_signup/auth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/javafxapp/view/images/icons.jpg")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Coffee Shop Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}