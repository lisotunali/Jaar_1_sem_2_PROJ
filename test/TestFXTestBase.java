import GUI.Main;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import java.util.concurrent.TimeoutException;





public class TestFXTestBase extends ApplicationTest {

    @BeforeEach
    public void setUp() throws Exception {
        ApplicationTest.launch(Main.class);
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


    @Override
    public void start(Stage stage) {
        stage.show();
    }

}
