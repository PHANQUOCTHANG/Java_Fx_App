package com.example.javafxapp.Controller.Admin;

import com.example.javafxapp.Controller.Admin.Order.OrderDetailController;
import com.example.javafxapp.Helpper.AlertInfo;
import com.example.javafxapp.Helpper.Pages;
import com.example.javafxapp.Model.Order;
import com.example.javafxapp.Service.AccountService;
import com.example.javafxapp.Service.PermissionService;
import com.example.javafxapp.Service.RolePermissionService;
import com.example.javafxapp.Service.RoleService;
import com.example.javafxapp.Utils.SaveAccountUtils;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainScreenController {

    @FXML
    private VBox permissionsSubMenu ;

    @FXML
    private JFXButton btnOverview, btnProducts, btnCategories, btnEmployees, btnOrders, btnRevenue ,
            btnAccounts, btnRole , btnPermission , btnSetting , btnLogOut;

    @FXML
    private StackPane centerPane;

    private List<JFXButton> menuButtons;

    private AccountService accountService = new AccountService() ;
    private RoleService roleService = new RoleService() ;
    private RolePermissionService rolePermissionService = new RolePermissionService() ;
    private PermissionService permissionService = new PermissionService() ;

    @FXML
    public void initialize() {
        // Danh sách các nút trong menu
        menuButtons = new ArrayList<>();
        menuButtons.add(btnOverview);
        menuButtons.add(btnProducts);
        menuButtons.add(btnCategories);
        menuButtons.add(btnOrders);
        menuButtons.add(btnRevenue) ;
        menuButtons.add(btnAccounts);
        menuButtons.add(btnRole);
        menuButtons.add(btnPermission) ;
        menuButtons.add(btnEmployees);
        menuButtons.add(btnSetting) ;
        menuButtons.add(btnLogOut) ;

        // Mặc định chọn "Tổng Quan"
        setActiveButton(btnOverview);
        handleOverview();
    }

    // css cho mục đang chọn .
    public void setActiveButton(JFXButton activeButton) {
        for (JFXButton btn : menuButtons) {
            btn.getStyleClass().remove("selected-button");
        }
        activeButton.getStyleClass().add("selected-button");
    }

    // repair
    public Object loadCenterContent(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent p;
        Object controller = null;
        try {
            p = loader.load();
            centerPane.getChildren().setAll(p);

            // dua controller qua controller con(ordercontroller) cho no biet controll bo la ai 
            // de nut addorderbtn hoat dong
            controller = loader.getController();

            // if (controller != null && controller.getClass().equals(OrderController.class)){
            //     OrderController oc = (OrderController) controller;
            //     oc.setMainScreenController(this);
            // }
            if (controller != null && controller instanceof BaseController){
                BaseController base = (BaseController) controller;
                base.setController(this);
            }

            centerPane.setMargin(p, new Insets(0));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return controller;
    }


    // overview .
    @FXML
    private void handleOverview() {
        System.out.println("Overview button clicked");
        loadCenterContent("/com/example/javafxapp/view/admin/dashboard/dashboard.fxml");
        setActiveButton(btnOverview);
    }

    // product .
    @FXML
    public void handleProducts() {
        System.out.println("Products button clicked");
        loadCenterContent("/com/example/javafxapp/view/admin/product/products.fxml");
        // Thêm logic chuyển sang trang sản phẩm
        setActiveButton(btnProducts);
    }

    // category .
    @FXML
    private void handleCategories() {
        System.out.println("Product Categories button clicked");
        loadCenterContent("/com/example/javafxapp/view/admin/category/category.fxml");
        // Thêm logic chuyển sang trang danh mục sản phẩm
        setActiveButton(btnCategories);
    }


    // employees .
    @FXML
    private void handleEmployees() {
        System.out.println("Employees button clicked");
        loadCenterContent("/com/example/javafxapp/view/admin/employee/employee.fxml");
        // Thêm logic chuyển sang trang quản lý nhân viên
        setActiveButton(btnEmployees);
    }

    // order .
    @FXML
    public void handleOrders() {
        System.out.println("Order Management button clicked");
        //        loadCenterContent("/com/example/javafxapp/view/admin/order/order.fxml");
        loadCenterContent("/com/example/javafxapp/view/orders/orders.fxml");      
        // Thêm logic chuyển sang trang quản lý đơn hàng
        setActiveButton(btnOrders);
    }

    // revenue .
    @FXML
    public void handleRevenue() {
        System.out.println("Revenue button clicked");
        loadCenterContent("/com/example/javafxapp/view/admin/revenue/revenue.fxml");
        // Thêm logic chuyển sang trang doanh thu .
        setActiveButton(btnRevenue);
    }

    // account .
    @FXML
    private void handleAccounts() {
        System.out.println("Account Management button clicked");
        loadCenterContent("/com/example/javafxapp/view/admin/account/account.fxml");
        // Thêm logic chuyển sang trang quản lý tài khoản
        setActiveButton(btnAccounts);
    }

    // role .
    @FXML
    private void handleRole() {
            System.out.println("Role button clicked");
            loadCenterContent("/com/example/javafxapp/view/admin/role/role.fxml");
            // Thêm logic chuyển sang trang vai trò .
            setActiveButton(btnRole);
        }

    // permission
    @FXML
    private void handlePermission() {
        System.out.println("Role button clicked");
        loadCenterContent("/com/example/javafxapp/view/admin/permission/permission.fxml");
        // Thêm logic chuyển sang trang phân quyền
        setActiveButton(btnPermission);
    }

    @FXML
    private void handleSetting() {
        System.out.println("Setting button clicked");
        loadCenterContent("/com/example/javafxapp/view/admin/setting/setting.fxml");
        // Thêm logic chuyển sang trang cài đặt .
        setActiveButton(btnSetting);
    }

    // log out .
    @FXML
    private void handleLogOut() {
        System.out.println("LogOut button clicked");
        // Thêm logic log out .
        if (AlertInfo.confirmAlert("Bạn có chắc muốn đăng xuất")) {
            Stage stage = (Stage) btnLogOut.getScene().getWindow() ;
            Pages.pageLogin();
            stage.close();
        }
    }


    // phân quyền
    public void setAccount(){
        List<Integer> permissions = rolePermissionService.getAllRolePermission(SaveAccountUtils.role_id) ;
        List<String> permissionNames = new ArrayList<>() ;
        for (Integer permissionId : permissions) {
            String permission_name = permissionService.findPermissionByID(permissionId).getPermission_name() ;
            permissionNames.add(permission_name) ;
        }
        setBtn(permissionNames,"Product" , btnProducts);
        setBtn(permissionNames,"Category" , btnCategories);
        setBtn(permissionNames , "Employee"  , btnEmployees);
        setBtn(permissionNames , "Order" , btnOrders) ;
        setBtn(permissionNames , "Account" , btnAccounts) ;
        setBtn(permissionNames , "Role" , btnRole);
        setBtn(permissionNames,"Permission" , btnPermission);
    }

    public void setBtn(List<String> permissions ,String permission , JFXButton button) {
        if (!permissions.contains(permission)){
            button.setVisible(false);
            button.setManaged(false);
        }
    }


    // add order page (in orderController)
    @FXML
    public void handleAddOrder(){
        loadCenterContent("/com/example/javafxapp/view/orders/orderDetail/orderDetail.fxml");
    }

    @FXML
    public void handleEditOrder(Order order){
        OrderDetailController odc = (OrderDetailController) loadCenterContent(
            "/com/example/javafxapp/view/orders/orderDetail/orderDetail.fxml");
        odc.setOrder(order);
        odc.loadData();
    }

    @FXML
    public void handleOnlineOrder(){
        loadCenterContent("/com/example/javafxapp/view/orders/orderUser/orderUser.fxml");
    }

    @FXML
    void handlePermissions() {
        // Toggle the submenu visibility
        permissionsSubMenu.setVisible(!permissionsSubMenu.isVisible());
        permissionsSubMenu.setManaged(!permissionsSubMenu.isManaged());
    }

}
