package com.byby.dto.mapper;

import com.byby.dto.model.CategoryDto;
import com.byby.dto.model.QuestionDto;
import com.byby.integration.dto.CategoryExternalDto;
import com.byby.integration.dto.QuestionExternalDto;
import com.byby.repository.entity.Category;
import com.byby.repository.entity.Question;

public class QuestionMapper {

    public static QuestionDto toDto(Question entity){
        if (entity == null) return null;
        QuestionDto dto = new QuestionDto();
        dto.setId(entity.getId());
        dto.setQuestion(entity.getQuestion());
        dto.setDifficulty(entity.getDifficulty());
        dto.setCategory(toDto(entity.getCategory()));
        return dto;
    }

    public static CategoryDto toDto(Category entity){
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
        return dto;
    }

    private static CategoryDto toDto(CategoryExternalDto externalDto) {
        if (externalDto == null) return null;
        CategoryDto dto = new CategoryDto();
        dto.setId(externalDto.getId());
        dto.setName(externalDto.getTitle());
        return dto;
    }
}
