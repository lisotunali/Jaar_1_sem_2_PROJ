package Education;

public class Answer {
    private String answer;

    public Answer(String answer){
        this.answer = answer;
    }
    public Boolean checkAnswer(String givenAnswer){
        return answer.equalsIgnoreCase(givenAnswer);
    }

    public String getAnswer(){
        return answer;
    }
}
