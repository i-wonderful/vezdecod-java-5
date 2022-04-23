package com.byby.service.impl;

import com.byby.dto.request.CheckRequest;
import com.byby.dto.response.CheckResponse;
import com.byby.repository.AnswerRepository;
import com.byby.repository.entity.Answer;
import com.byby.service.AnswerService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class AnswerServiceImpl implements AnswerService {
    @Inject
    AnswerRepository answerRepository;

    @Override
    public CheckResponse check(CheckRequest checkRequest) {
        Long questionId = checkRequest.getQuestionId();
        List<Answer> answers = answerRepository.findAnswerByQuestionId(questionId);

        CheckResponse response = new CheckResponse();
        response.setCorrect(isCorrect(checkRequest.getAnswer(), answers));
        response.setCorrectAnswer(getCorrectAnswer(answers));
        response.setQuestionId(questionId);
        return response;
    }

    private boolean isCorrect(String answerValueCheck, List<Answer> answers) {
        return answers.stream()
                .filter(a -> Objects.equals(answerValueCheck, a.getValue()))
                .map(Answer::isCorrect)
                .findFirst().orElse(false);
    }

    private String getCorrectAnswer(List<Answer> answers){
        return answers.stream()
                .filter(Answer::isCorrect)
                .map(Answer::getValue)
                .findFirst().orElseThrow(() -> new RuntimeException("Not found right answer"));
    }
}
