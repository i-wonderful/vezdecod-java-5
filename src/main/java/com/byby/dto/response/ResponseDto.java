package com.byby.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseDto {
    private ResponseCode code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    private ResponseResultDto result;

    public ResponseDto(ResponseCode code, String description, ResponseResultDto result) {
        this.code = code;
        this.description = description;
        this.result = result;
    }

    public ResponseDto(ResponseCode code, ResponseResultDto result) {
        this.code = code;
        this.result = result;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResponseResultDto getResult() {
        return result;
    }

    public void setResult(ResponseResultDto result) {
        this.result = result;
    }

    public static enum ResponseCode {
        OK,
        VALIDATION_ERROR,
        INTERNAL_ERROR
    }
}
