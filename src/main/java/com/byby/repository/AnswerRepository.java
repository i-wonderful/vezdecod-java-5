package com.byby.repository;

import com.byby.repository.entity.Answer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AnswerRepository implements PanacheRepository<Answer> {
    public List<Answer> findAnswerByQuestionId(Long questionId) {
        return find("question.id", questionId).list();
    }
}
