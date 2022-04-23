package com.byby.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @SequenceGenerator(name = "categorySeq", sequenceName = "category_id_seq", allocationSize = 1, initialValue = 5)
    @GeneratedValue(generator = "categorySeq")
    private Long id;

    @Column(length = 50)
    private  String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "category")
    private List<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
