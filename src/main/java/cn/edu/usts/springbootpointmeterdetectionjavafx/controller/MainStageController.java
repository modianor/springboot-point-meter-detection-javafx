package cn.edu.usts.springbootpointmeterdetectionjavafx.controller;

import cn.edu.usts.springbootpointmeterdetectionjavafx.MainController;
import cn.edu.usts.springbootpointmeterdetectionjavafx.pojo.Item;
import cn.edu.usts.springbootpointmeterdetectionjavafx.service.ItemService;
import cn.edu.usts.springbootpointmeterdetectionjavafx.tools.ImageConvert;
import cn.edu.usts.springbootpointmeterdetectionjavafx.tools.Point_Meter_Detect;
import cn.edu.usts.springbootpointmeterdetectionjavafx.view.HistoryStageView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @description
 * @create 2017-05-20 下午1:55
 * @email 1138716463@qq.com
 */
@FXMLController
public class MainStageController implements Initializable {


    FileChooser chooser = new FileChooser();

    @FXML
    private Button btn_choose;
    @FXML
    private Button btn_detect;
    @FXML
    private Button search_history;
    @FXML
    private ImageView qr_image_view;
    @FXML
    private TextArea qr_content_text_area;

    private static String image_path;

    Stage stage;

    @Autowired
    ItemService itemService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage = (Stage) btn_detect.getScene().getWindow();
            }
        });
    }

    public void choose_file(ActionEvent actionEvent) {
        chooser.setTitle("Select File !!");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = chooser.showOpenDialog(this.stage);
        if (file != null) {
            image_path = file.getAbsolutePath();
            Image image = new Image("file:" + file.getAbsolutePath());
            qr_image_view.setImage(image);
        }
    }

    public void detect(ActionEvent actionEvent) {
        try {
            ImageIO.read(new File(image_path));
        } catch (IOException e) {
            qr_content_text_area.clear();
            qr_content_text_area.setText("无法打开！");
        }
        Item result = null;

        result = Point_Meter_Detect.do_detect(image_path);
        String s = "抱歉！无法识别";
        if (result != null) {
            Image image = ImageConvert.Mat2Image(result.src);
            qr_image_view.setImage(image);
            result.src.release();
            s = result.getAngle() + "°" + "  " + result.getValue() + "℃";
            itemService.save(result);
        }


        qr_content_text_area.clear();
        qr_content_text_area.setText(s);
    }

    public void handleButtonClicks(ActionEvent mouseEvent) {
        MainController.showView(HistoryStageView.class, Modality.WINDOW_MODAL);
    }


}