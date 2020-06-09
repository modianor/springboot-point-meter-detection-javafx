package cn.edu.usts.springbootpointmeterdetectionjavafx;

import cn.edu.usts.springbootpointmeterdetectionjavafx.view.MainStageView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainController extends AbstractJavaFxApplicationSupport {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(MainController.class, MainStageView.class, args);
    }

    /**
     * Start.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }
}
