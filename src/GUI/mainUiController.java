package GUI;

import java.io.IOException;

public class mainUiController {

    public void contactsButtonClicked() throws IOException {
        Main.switchSceneTo("contacts");
    }

    public void profileButtonClicked() throws IOException {
        Main.switchSceneTo("profile");
    }
}