package com.example.javafxapp.Controller.Admin;

import com.example.javafxapp.Repository.RoleRepository;
import com.example.javafxapp.Model.Role;
import com.example.javafxapp.Service.RoleService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class RoleController {

    @FXML
    private TextField txtRoleId ;
    @FXML
    private TextField txtRoleName;
    @FXML
    private TextField txtDescription;
    @FXML
    private Label lblStatus;

    private final RoleService roleService = new RoleService() ;

    // add role
    @FXML
    public void addRole() {
        String roleName = txtRoleName.getText().trim();
        String description = txtDescription.getText().trim();

        if (roleName.isEmpty()) {
            lblStatus.setText("Tên quyền không được để trống!");
            return;
        }

        Role newRole = new Role(roleName, description);
        roleService.addRole(newRole);
    }

    // update role .
    @FXML
    public void updateRole () {
        String roleName = txtRoleName.getText().trim();
        String description = txtDescription.getText().trim();

        if (roleName.isEmpty()) {
            lblStatus.setText("Tên quyền không được để trống!");
            return;
        }

        Role newRole = new Role(roleName, description);
        roleService.updateRole(newRole);
    }

    // delete role .
    @FXML
    public void deleteRole() {
        int roleId = Integer.parseInt(txtRoleId.getId().trim());
        try {
            roleService.deleteRole(roleId);
            // alert
        }
        catch (Exception e) {

        }
    }

    // get all role .
    @FXML
    public List<Role> getAllRole() {
        return roleService.getAllRole() ;
    }
}

