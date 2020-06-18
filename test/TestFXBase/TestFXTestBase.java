package TestFXBase;

import GUI.Main;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
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
    public void start(Stage stage) throws IOException {
        stage.show();
    }

}
