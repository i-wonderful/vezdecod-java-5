package com.byby.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_answer")
public class UserAnswer {
    @Id
    @SequenceGenerator(name = "useranswerSeq", sequenceName = "useranswer_id_seq", allocationSize = 1, initialValue = 5)
    @GeneratedValue(generator = "useranswerSeq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(name = "ua_question_id_fk"))
    private Question question;

    @ManyToOne
    @JoinColumn(name = "game_id", foreignKey = @ForeignKey(name = "ua_game_id_fk"))
    private Game game;

    private boolean isCorrect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
