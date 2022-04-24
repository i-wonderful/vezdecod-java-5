package com.byby.repository;

import com.byby.repository.entity.Question;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class QuestionRepository implements PanacheRepository<Question> {

    public List<Question> findQuestionsByDifficulty(Integer minDifficulty, Integer maxDifficulty, int limit) {
        return find("difficulty > ?1 and difficulty < ?2", minDifficulty, maxDifficulty)
                .range(0, limit)
                .list();
    }

    public List<Question> findQuestionsByDifficultyAndCategories(Integer minDifficulty, Integer maxDifficulty, List<Long> categories, int limit) {
        return find("difficulty > ?1 and difficulty < ?2 and category.id in ?3", minDifficulty, maxDifficulty, categories)
                .range(0, limit)
                .list();
    }
}
