package Education;

import GUI.SceneController;
import GUI.fakeDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class mathController extends GameController {

    @FXML
    private Button guessButton;

    @FXML
    private Label exerciseLabel;

    @FXML
    private TextField guessField;

    private ArrayList<Integer> numbers = new ArrayList();

    private ArrayList<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));

    private Integer correctAnswer;

    public void intialize() throws IOException, ScriptException {
        System.out.println("Initializing..");
        Game game = new Game();
        game.saveQuestionsLocally(fakeDatabase.getNumbersDatabase());
        numbers = game.getCurrentGameQuestions();
        nextQuestion();
    }


    @Override
    public void nextQuestion() throws IOException, ScriptException {
        //game.setCurrentQuestion(game.getCurrentQuestion() + 1);
        Collections.shuffle(numbers);
        Integer a = numbers.get(0);
        Integer b = numbers.get(1);
        Collections.shuffle(operators);
        String operator = operators.get(0);

        String question = a + operator + b;
        exerciseLabel.setText(question);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval(question);
        Integer res = (Integer) result;
        System.out.println(a + "   " + b + "    " +  res + "   " + result);
    }

    public void backButtonClicked() throws IOException {
        SceneController.switchTo("education");
    }
}
