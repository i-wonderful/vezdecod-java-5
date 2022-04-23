package com.byby.service.impl;

import com.byby.cache.CacheManager;
import com.byby.dto.mapper.QuestionMapper;
import com.byby.dto.model.QuestionDto;

import static com.byby.dto.mapper.QuestionMapper.*;

import com.byby.integration.JServiceRestClient;
import com.byby.repository.QuestionRepository;
import com.byby.repository.entity.Question;
import com.byby.service.QuestionService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.*;

import java.util.Random;

@ApplicationScoped
public class QuestionServiceImpl implements QuestionService {
    private static final Logger LOG = Logger.getLogger(QuestionServiceImpl.class.getName());

    @Inject
    QuestionRepository questionRepository;

    @Inject
    @RestClient
    JServiceRestClient jServiceRestClient;

    @Inject
    CacheManager cacheManager;

    Random random;

    @PostConstruct
    public void init() {
        random = new Random();
    }

    @Override
    public QuestionDto getRandom() {
        QuestionDto questionDto = random.nextBoolean() ? getLocalRandom() : getExternalRandom();
        cacheManager.put(questionDto.getId(), questionDto);
        return questionDto;
    }

    private QuestionDto getLocalRandom() {
        List<Question> questionsAll = questionRepository.listAll();
        Integer randomIndex = random.nextInt(questionsAll.size());
        Question question = questionsAll.get(randomIndex);
        return toDto(question);
    }

    private QuestionDto getExternalRandom() {
        return ofNullable(jServiceRestClient.getRandom())
                .orElse(Collections.emptyList())
                .stream()
                .findFirst()
                .map(QuestionMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Not found external random question"))
                ;
    }

}
