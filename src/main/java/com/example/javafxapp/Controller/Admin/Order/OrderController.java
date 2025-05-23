package com.example.javafxapp.Controller.Admin.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.javafxapp.Controller.Admin.BaseController;
import com.example.javafxapp.Controller.Admin.MainScreenController;
import com.example.javafxapp.Helpper.TextNormalizer;
import com.example.javafxapp.Model.Order;
import com.example.javafxapp.Service.OrderService;
import com.jfoenix.controls.JFXButton;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class OrderController extends BaseController {
    @FXML
    private ComboBox statusComboBox;

    @FXML
    private Label firstPage;

    @FXML
    private Label lastPage;

    @FXML
    private JFXButton nextBtn;

    @FXML
    private JFXButton prevBtn;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField searchField;


    private OrderService orderService = new OrderService();

    private List<Order> orders = new ArrayList<>();

    private int currentPage = 0;
    private final int ordersPerPage = 10;

    // map lưu trữ key value là id -> orderDetailController 
    // dùng để xoá mục orderDetail tương ứng
    Map<Integer, OrderItemController> mp = new HashMap<>();

    @FXML
    void goToOnline(){
        msc.handleOnlineOrder();
    }

    public MainScreenController getMSC(){
        return msc;
    }


    public void loadData(){
        orders = orderService.getAllOrder();
        loadPage(currentPage);

        List<String> statusList = new ArrayList<>();
        statusList.add("Đang chờ xử lí");
        statusList.add("Đang xử lí");
        statusList.add("Đã xử lí");
        statusList.add("Đã huỷ");
        statusList.add("Tất cả");

        statusComboBox.getItems().addAll(statusList);
        statusComboBox.setValue("Tất cả");
    }

    private void loadPage(int pageIdx){
        int row = 0;
        grid.getChildren().clear();
        int start = pageIdx * ordersPerPage;
        firstPage.setText(String.valueOf(currentPage + 1));
        for (int i = start; i < Math.min(start + ordersPerPage, orders.size()); i++){
            Order order = orders.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/com/example/javafxapp/View/Orders/orderItem.fxml"));
                HBox hbox = loader.load();

                OrderItemController oic = loader.getController();
                oic.setOrder(order);
                oic.setOrderController(this);
                mp.put(order.getId(), oic);

                grid.add(hbox, 0, row++);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        prevBtn.setDisable(currentPage == 0);
        nextBtn.setDisable((currentPage + 1) * ordersPerPage >= orders.size());
    }
    @FXML
    public void initialize(){
        PauseTransition pause = new PauseTransition(Duration.seconds(0.75));

        // ham search
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            pause.stop(); // Dừng transition nếu đang chạy
            pause.setOnFinished(event -> {
                if (newValue != null) {
                    String cleaned = newValue.trim().replaceAll("\\s+", " ");
                    if (cleaned.isEmpty()) {
                        updateOrderDisplay(orders);
                        return;
                    }

                    String keyword = TextNormalizer.normalize(cleaned);

                    List<Order> filtered = new ArrayList<>();
                    for (Order order : orders) {
                        if (TextNormalizer.normalize(order.getStaffName()).contains(keyword)) {
                            filtered.add(order);
                        }
                    }

                    updateOrderDisplay(filtered);
                }
            });
            pause.playFromStart(); // Bắt đầu đếm lại 1s sau mỗi lần nhập

            
        });

        loadData();
        lastPage.setText(String.valueOf(orders.size() / ordersPerPage + 
                            (orders.size() % ordersPerPage != 0 ? 1 : 0)));
    }

    private void updateOrderDisplay(List<Order> l){
        List<Order> o = orders;
        orders = l;
        loadPage(currentPage);
        orders = o;
    }

    @FXML
    void nextPage(ActionEvent event) {
        if ((currentPage + 1) * ordersPerPage < orders.size())
            loadPage(++currentPage);
    }

    @FXML
    void prevPage(ActionEvent event) {
        if (currentPage > 0)
            loadPage(--currentPage);
    }

    @FXML
    void addOrder(ActionEvent event) {
        System.out.println("Add order button clicked!");
        msc.handleAddOrder();
    }

    @FXML
    void filterAction(ActionEvent event) {
        String selectedStatus = (String) statusComboBox.getValue();
        if (selectedStatus.equals("Tất cả"))
            orders = orderService.getAllOrder();
        else {
            String s = "";
            if (selectedStatus.equals("Đang chờ xử lí"))
                s = "Pending";
            else if (selectedStatus.equals("Đang xử lí"))
                s = "Processing";
            else if (selectedStatus.equals("Đã xử lí"))
                s = "Completed";
            else s = "Cancelled";
            orders = orderService.getOrderByStatus(s);
        }
        if (orders.isEmpty() || orders == null){
            System.out.println("Khong lay du lieu duoc!");
        }
        currentPage = 0;
        loadPage(currentPage);
    }

    @FXML
    void searchOrder(ActionEvent event) {
        
    }

    public void deleteOrder(int id){
        orders.removeIf(od -> od.getId() == id);
        loadPage(0);
    }


}
