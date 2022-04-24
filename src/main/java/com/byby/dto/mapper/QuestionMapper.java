package com.byby.dto.mapper;

import com.byby.dto.model.CategoryDto;
import com.byby.dto.model.QuestionDto;
import com.byby.dto.response.CheckResponse;
import com.byby.integration.dto.CategoryExternalDto;
import com.byby.integration.dto.QuestionExternalDto;
import com.byby.repository.entity.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuestionMapper {

    public static QuestionDto toDto(Question entity) {
        if (entity == null) return null;
        QuestionDto dto = new QuestionDto();
        dto.setId(entity.getId());
        dto.setQuestion(entity.getQuestion());
        dto.setDifficulty(entity.getDifficulty());
        dto.setCategory(toDto(entity.getCategory()));
        dto.setAnswer(getRightAnswer(entity));
        return dto;
    }

    public static CategoryDto toDto(Category entity) {
        if (entity == null) return null;
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static QuestionDto toDto(QuestionExternalDto externalDto) {
        if (externalDto == null) return null;
        QuestionDto dto = new QuestionDto();
        dto.setId(externalDto.getId());
        dto.setQuestion(externalDto.getQuestion());
        dto.setDifficulty(externalDto.getValue());
        dto.setCategory(toDto(externalDto.getCategory()));
        dto.setAnswer(externalDto.getAnswer());
        return dto;
    }

    public static CheckResponse toCheckResponse(QuestionDto dto, String answer) {
        if (dto == null || answer == null) return null;
        CheckResponse response = new CheckResponse();
        response.setCorrect(Objects.equals(dto.getAnswer(), answer));
        response.setCorrectAnswer(dto.getAnswer());
        response.setQuestionId(dto.getId());
        return response;
    }

    public static CheckResponse toCheckResponse(Question question, String answer) {
        if (question == null || answer == null) return null;
        String rightAnswer = getRightAnswer(question);
        CheckResponse response = new CheckResponse();
        response.setCorrect(Objects.equals(rightAnswer, answer));
        response.setCorrectAnswer(rightAnswer);
        response.setQuestionId(question.getId());
        return response;
    }

    private static CategoryDto toDto(CategoryExternalDto externalDto) {
        if (externalDto == null) return null;
        CategoryDto dto = new CategoryDto();
        dto.setId(externalDto.getId());
        dto.setName(externalDto.getTitle());
        return dto;
    }

    public static Question toEntity(QuestionDto dto) {
        if (dto == null) return null;
        Question entity = new Question();
        entity.setQuestion(dto.getQuestion());
        entity.setDifficulty(dto.getDifficulty());
        entity.setCategory(toEntity(dto.getCategory()));
        entity.setAnswers(List.of(toEntity(dto.getAnswer(), entity)));

        return entity;
    }

    public static Category toEntity(CategoryDto dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        return entity;
    }

    public static Answer toEntity(String answerText, Question question) {
        Answer entity = new Answer();
        entity.setValue(answerText);
        entity.setCorrect(true);
        entity.setQuestion(question);
        return entity;
    }

    public static UserAnswer toUserAnswerEntity(Game game, Question question, Object answer) {
        if (game == null || question == null || answer == null) return null;

        UserAnswer entity = new UserAnswer();
        entity.setCorrect(Objects.equals(getRightAnswer(question), answer));
        entity.setQuestion(question);
        entity.setGame(game);
        return entity;
    }

    private static String getRightAnswer(Question entity) {
        return entity.getAnswers().stream()
                .filter(Answer::isCorrect)
                .map(Answer::getValue)
                .collect(Collectors.joining(", "));
    }
}
