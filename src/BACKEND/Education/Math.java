package BACKEND.Education;

import BACKEND.fakeDatabase;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Math extends BaseGame {
    private ArrayList<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
    private String question = "";

    public Math() {
        setCurrentGameType("math");
        saveQuestionsLocally(fakeDatabase.getNumbersDatabase());
    }

    @Override
    public void nextQuestion() {
        generateNewQuestion();
        setAnswer();
    }

    private void generateNewQuestion() {
        ArrayList<Integer> numbers = getCurrentGameQuestions();

        Collections.shuffle(numbers);
        Integer a = numbers.get(0);
        Integer b = numbers.get(0);

        Collections.shuffle(operators);
        String operator = operators.get(0);

        for (Integer number : numbers) {
            if (checkEasySum(a, number, operator)) {
                b = number;
            }
        }

        question = a + operator + b;
    }

    private Boolean checkEasySum(Integer leftNumber, Integer rightNumber, String operator) {
        switch (operator) {
            case "*":
                return leftNumber * rightNumber <= 40;
            case "/":
                return leftNumber % rightNumber == 0;
            case "-":
                return leftNumber - rightNumber >= 0;
            default:
                return true;
        }
    }

    private void setAnswer() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        Object result = null;

        try {
            result = engine.eval(question);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        Integer res = (Integer) result;
        setCorrectAnswer(res != null ? res.toString() : null);
    }

    public String getQuestion() {
        return question;
    }
}
