package com.byby.repository;

import com.byby.repository.entity.UserAnswer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserAnswerRepository implements PanacheRepository<UserAnswer> {

    public List<UserAnswer> findUserAnswersGyGameId(Long gameId){
        return find("game.id", gameId).list();
    }
}
