package com.byby.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @SequenceGenerator(name = "questionSeq", sequenceName = "question_id_seq", allocationSize = 1, initialValue = 5)
    @GeneratedValue(generator = "questionSeq")
    private Long id;

    @Column(length = 200)
    private String question;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    @OneToMany(cascade = CascadeType.REMOVE)
    private
    List<Answer> answers;

    @Column
    private Integer difficulty;

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

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
