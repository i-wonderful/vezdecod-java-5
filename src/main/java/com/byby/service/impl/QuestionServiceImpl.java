package com.byby.service.impl;

import com.byby.dto.model.QuestionDto;

import static com.byby.dto.mapper.QuestionMapper.*;

import com.byby.repository.QuestionRepository;
import com.byby.repository.entity.Question;
import com.byby.service.QuestionService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class QuestionServiceImpl implements QuestionService {
    private static final Logger logger = Logger.getLogger(QuestionServiceImpl.class.getName());

    @Inject
    QuestionRepository questionRepository;


    @Override
    public QuestionDto getRandom() {
        List<Question> questionsAll = questionRepository.listAll();
        Integer randomIndex = new Random().nextInt(questionsAll.size());
        logger.info(">>> RandomIndex = " + randomIndex);
        Question question = questionsAll.get(randomIndex);
        return toDto(question);
    }


}
