package com.byby.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckResponse implements ResponseResultDto {
    private Long questionId;
    private boolean isCorrect;
    private String correctAnswer;

    @JsonProperty("question_id")
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @JsonProperty("is_correct")
    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @JsonProperty("correct_answer")
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
