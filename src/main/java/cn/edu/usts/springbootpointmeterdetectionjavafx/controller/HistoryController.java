package cn.edu.usts.springbootpointmeterdetectionjavafx.controller;

import cn.edu.usts.springbootpointmeterdetectionjavafx.pojo.Item;
import cn.edu.usts.springbootpointmeterdetectionjavafx.service.ItemService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class HistoryController implements Initializable {
    @FXML
    private TableView<Item> tbData;

    @FXML
    public TableColumn img_id;

    @FXML
    public TableColumn angle;

    @FXML
    public TableColumn value;

    @FXML
    public TableColumn date;

    @FXML
    private Button home;
    @FXML
    private Button end;
    @FXML
    private Button up;
    @FXML
    private Button down;

    @Autowired
    ItemService itemService;

    Pageable pageRequest = PageRequest.of(0, 13);
    Page<Item> page = null;

    public HistoryController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        img_id.setCellValueFactory(new PropertyValueFactory<>("imgId"));
        angle.setCellValueFactory(new PropertyValueFactory<>("angle"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        page = itemService.findAll(pageRequest);
        do_page(page);
    }


    public void do_page(Page<Item> page) {
        ObservableList<Item> itemsModels = FXCollections.observableList(page.getContent());
        tbData.setItems(itemsModels);
    }


    public void go_home(ActionEvent actionEvent) {
        System.out.println("返回首页");
        pageRequest = pageRequest.first();

        page = itemService.findAll(pageRequest);
        do_page(page);

    }

    public void go_up(ActionEvent actionEvent) {
        if (page.hasPrevious()) {
            pageRequest = pageRequest.previousOrFirst();

            page = itemService.findAll(pageRequest);
            do_page(page);
            System.out.println("上一页");
        }
    }

    public void go_end(ActionEvent actionEvent) {
        pageRequest = PageRequest.of(page.getTotalPages() - 1, 13);
        page = itemService.findAll(pageRequest);
        do_page(page);
        System.out.println("返回尾页");

    }

    public void go_down(ActionEvent actionEvent) {

        if (page.hasNext()) {
            pageRequest = pageRequest.next();

            page = itemService.findAll(pageRequest);
            do_page(page);
            System.out.println("下一页");
        }
    }
}
