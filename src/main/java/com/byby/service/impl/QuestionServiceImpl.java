package com.byby.service.impl;

import com.byby.cache.CacheManager;
import com.byby.dto.mapper.QuestionMapper;
import com.byby.dto.model.QuestionDto;

import static com.byby.dto.mapper.QuestionMapper.*;

import com.byby.integration.JServiceRestClient;
import com.byby.integration.dto.QuestionExternalDto;
import com.byby.repository.QuestionRepository;
import com.byby.repository.entity.Question;
import com.byby.service.QuestionService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.*;

import java.util.Random;
import java.util.stream.Collectors;

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

    @Inject
    RandomManager randomManager;

    @Override
    public List<Question> getQuestionsForGame(int minDifficulty, int maxDifficulty, int countQuestions, List<Long> categories) {
        List<Question> questions = getQuestionsFromDb(minDifficulty, maxDifficulty, countQuestions - 1, categories);
        if (questions.size() < countQuestions) {
            int needQuestionsCount = countQuestions - questions.size();
            LOG.info(String.format(">>> We need more questions. Found: %d, need: %d. Lets get %d external questions",
                    questions.size(), countQuestions, needQuestionsCount));

            List<QuestionDto> questionDtos = getQuestionExternal(minDifficulty, maxDifficulty, needQuestionsCount, categories);
            LOG.info(">>> Get questions from external, count: " + questionDtos.size());

            List<Question> questionFromExternal = saveQuestions(questionDtos);
            LOG.info(">>> Save questions");

            questions.addAll(questionFromExternal);
        }

        return questions;
    }

    private List<Question> getQuestionsFromDb(int minDifficulty, int maxDifficulty, int limit, List<Long> categories) {
        if (categories != null && !categories.isEmpty()) {
            return questionRepository.findQuestionsByDifficultyAndCategories(minDifficulty, maxDifficulty, categories, limit);
        } else {
            return questionRepository.findQuestionsByDifficulty(minDifficulty, maxDifficulty, limit);
        }
    }

    private List getQuestionExternal(int minDifficulty, int maxDifficulty, int count, List<Long> categories) {
        List<QuestionExternalDto> questions = jServiceRestClient.getAll();
        // todo categories filter
        return questions.stream()
                .filter(q -> q.getValue() != null)
                .filter(q -> q.getValue() < maxDifficulty)
                .filter(q -> q.getValue() > minDifficulty)
                .limit(count)
                .map(QuestionMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<Question> saveQuestions(List<QuestionDto> questionDtos) {
        List<Question> entities = questionDtos.stream()
                .map(QuestionMapper::toEntity)
                .collect(Collectors.toList());

        questionRepository.persist(entities);
        return entities;
    }

    @Override
    public QuestionDto getRandom() {
        QuestionDto questionDto = randomManager.getLocal() ? getLocalRandom() : getExternalRandom();
        cacheManager.put(questionDto.getId(), questionDto);
        return questionDto;
    }

    private QuestionDto getLocalRandom() {
        List<Question> questionsAll = questionRepository.listAll();
        int randomIndex = randomManager.nextInt(questionsAll.size());
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
