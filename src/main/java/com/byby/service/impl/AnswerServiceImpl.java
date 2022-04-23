package com.byby.service.impl;

import com.byby.cache.CacheManager;
import com.byby.dto.model.QuestionDto;
import com.byby.dto.request.CheckRequest;
import com.byby.dto.response.CheckResponse;
import com.byby.service.AnswerService;
import org.jboss.logmanager.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static com.byby.dto.mapper.QuestionMapper.toCheckResponse;

@ApplicationScoped
public class AnswerServiceImpl implements AnswerService {
    private static Logger LOG = Logger.getLogger(AnswerServiceImpl.class.getName());

    @Inject
    CacheManager cacheManager;

    @Override
    public CheckResponse check(CheckRequest checkRequest) {
        Long questionId = checkRequest.getQuestionId();
        String answer = checkRequest.getAnswer();

        QuestionDto questionCache = cacheManager.getFromCacheOrDb(questionId);

        return toCheckResponse(questionCache, answer);
    }

}
