package com.byby.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @SequenceGenerator(name = "answerSeq", sequenceName = "answer_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "answerSeq")
    private Long id;

    @Column(length = 100)
    private String value;

    @Column
    private
    boolean isCorrect;

    @ManyToOne
    private
    Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
