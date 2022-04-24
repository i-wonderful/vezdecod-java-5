package com.byby.dto.response;

public class NewGameResponse {
    private Long id;
    private Integer countQuestions;

    public NewGameResponse(Long id, Integer countQuestions){
        this.setId(id);
        this.setCountQuestions(countQuestions);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountQuestions() {
        return countQuestions;
    }

    public void setCountQuestions(Integer countQuestions) {
        this.countQuestions = countQuestions;
    }
}

