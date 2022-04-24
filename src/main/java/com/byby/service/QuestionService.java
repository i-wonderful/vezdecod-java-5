package com.byby.service;

import com.byby.dto.model.QuestionDto;
import com.byby.repository.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsForGame(int minDifficulty, int maxDifficulty, int limit, List<Long> categories);
    QuestionDto getRandom();
}
