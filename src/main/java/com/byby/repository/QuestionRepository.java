package com.byby.repository;

import com.byby.repository.entity.Question;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuestionRepository implements PanacheRepository<Question> {
}
