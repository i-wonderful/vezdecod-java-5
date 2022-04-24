package com.byby.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @SequenceGenerator(name = "gameSeq", sequenceName = "game_id_seq", allocationSize = 1, initialValue = 5)
    @GeneratedValue(generator = "gameSeq")
    private Long id;

    private Integer minDifficulty;

    private Integer maxDifficulty;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    private List<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinDifficulty() {
        return minDifficulty;
    }

    public void setMinDifficulty(Integer minDifficulty) {
        this.minDifficulty = minDifficulty;
    }

    public Integer getMaxDifficulty() {
        return maxDifficulty;
    }

    public void setMaxDifficulty(Integer maxDifficulty) {
        this.maxDifficulty = maxDifficulty;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
