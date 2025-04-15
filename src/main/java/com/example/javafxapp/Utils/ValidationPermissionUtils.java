package com.example.javafxapp.Utils;

import com.example.javafxapp.Helpper.AlertInfo;
import javafx.scene.control.Alert;

public class ValidationPermissionUtils {

    public static boolean validationPermissionName(String permissionName) {
        if (permissionName.isEmpty()) {
            AlertInfo.showAlert(Alert.AlertType.ERROR , "Lỗi" , "Tên không rỗng");
            return false ;
        }
        if (permissionName.length() < 4) {
            AlertInfo.showAlert(Alert.AlertType.ERROR , "Lỗi" , "Tên ít nhất 4 kí tự");
            return false ;
        }
        return true ;
    }


}
