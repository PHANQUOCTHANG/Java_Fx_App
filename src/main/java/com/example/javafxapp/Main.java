package com.example.javafxapp;
import com.example.javafxapp.Model.Account;
import com.example.javafxapp.Repository.AccountRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;


import org.controlsfx.control.PopOver;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
//        // quan li san pham 2
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javafxapp/view/login_signup/auth.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/javafxapp/view/images/icons.jpg")));
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Coffee Shop Management");
//        primaryStage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javafxapp/view/mainScreen/mainScreen.fxml"));
       Scene scene = new Scene(fxmlLoader.load());
       primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/javafxapp/view/images/icons.jpg")));
       primaryStage.setScene(scene);
       primaryStage.setTitle("Coffee Shop Management");
       primaryStage.show();
    }


    public static void main(String[] args) {
//        AccountRepository accountRepository = new AccountRepository() ;
//        accountRepository.add(new Account("Admin1234" , "123456" , 1));
        launch();
    }
}