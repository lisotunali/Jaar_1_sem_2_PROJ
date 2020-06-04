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
    private Label exerciseLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private TextField guessField;

    private ArrayList<Integer> numbers = new ArrayList();

    private ArrayList<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));

    private String correctAnswerString;

    public void initialize() throws IOException, ScriptException {
        System.out.println("Initializing..");
        game = new Game();
        game.setCurrentscore(0);
        game.saveQuestionsLocally(fakeDatabase.getNumbersDatabase());
        numbers = game.getCurrentGameQuestions();
        startTimer(timerLabel);
        nextQuestion();
    }


    @Override
    public void nextQuestion() throws IOException, ScriptException {
        //game.setCurrentQuestion(game.getCurrentQuestion() + 1);
        Collections.shuffle(numbers);
        Integer a = numbers.get(0);
        Integer b = numbers.get(0);
        Collections.shuffle(operators);
        String operator = operators.get(0);
        for(Integer number : numbers){
            if(checkEasySum(a, number, operator)){
                b = number;
            }
        }

        String question = a + operator + b;
        exerciseLabel.setText(question);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval(question);
        Integer res = (Integer) result;
        correctAnswerString = res.toString();
    }

    public Boolean checkEasySum (Integer leftNumber, Integer rightNumber, String operator) {
        if("*".equals(operator)){
            return leftNumber * rightNumber <= 40;
        }
        if("/".equals(operator)){
            return leftNumber % rightNumber == 0;
        }
        if("-".equals(operator)){
            return leftNumber - rightNumber >= 0;
        }
        return true;
    }

    public void doneClicked() throws IOException, ScriptException {
        checkAnswer(guessField.getText(), correctAnswerString);
        guessField.clear();
        nextQuestion();
    }

    public void backButtonClicked() throws IOException {
        SceneController.switchTo("education");
    }
}
