package com.byby.integration.dto;

public class QuestionExternalDto {
    private Long id;
    private String question;
    private String answer;
    private Integer value;
    private CategoryExternalDto category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public CategoryExternalDto getCategory() {
        return category;
    }

    public void setCategory(CategoryExternalDto category) {
        this.category = category;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}