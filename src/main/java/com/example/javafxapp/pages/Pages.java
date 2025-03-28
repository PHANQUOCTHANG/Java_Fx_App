package com.example.javafxapp.pages;

import com.example.javafxapp.alert.AlertInfo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Pages {

    public Pages() {
    }

    // Chuyển qua page dashboard .
    public static void pageDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(Pages.class.getResource("/com/example/javafxapp/view/dashboard/dashboard.fxml"));
            Pane root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            AlertInfo.showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở trang Dashboard.");
        }
    }

    // chuyển qua trang login .
    public static void pageLogin(){
        try {
            FXMLLoader loader = new FXMLLoader(Pages.class.getResource("/com/example/javafxapp/view/login_signup/signin.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage() ;
            stage.getIcons().add(new Image(Pages.class.getResourceAsStream("/com/example/javafxapp/view/images/icons.jpg")));
            stage.setScene(scene);
            stage.setTitle("Coffee Shop Management");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            AlertInfo.showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở trang Login.");
        }
    }

    // chuyển qua trang sign-up .
    public static void pageSignUp(){
        try {
            FXMLLoader loader = new FXMLLoader(Pages.class.getResource("/com/example/javafxapp/view/login_signup/signup.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage() ;
            stage.getIcons().add(new Image(Pages.class.getResourceAsStream("/com/example/javafxapp/view/images/icons.jpg")));
            stage.setScene(scene);
            stage.setTitle("Coffee Shop Management");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            AlertInfo.showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể mở trang Sign up.");
        }
    }


}
