package com.byby.dto.response;

public class GameFinish {
    private Long questionId;
    private String question;
    private boolean isRightAnswer;

    public GameFinish(){}

    public GameFinish(Long questionId, String question, boolean isRightAnswer){
        this.questionId = questionId;
        this.question = question;
        this.isRightAnswer = isRightAnswer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isRightAnswer() {
        return isRightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        isRightAnswer = rightAnswer;
    }
}
