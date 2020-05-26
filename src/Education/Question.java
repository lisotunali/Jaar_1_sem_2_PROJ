package Education;

import java.util.ArrayList;

public class Question {
    private String question;
    private Answer correctAnswer;

    public Question(String question){
        this.question = question;
    }

    public void setAnswer(Answer answer){
        this.correctAnswer = answer;
    }

    public Answer getCorrectAnswer(){
        return correctAnswer;
    }

    public String getQuestion(){
        return question;
    }


}
