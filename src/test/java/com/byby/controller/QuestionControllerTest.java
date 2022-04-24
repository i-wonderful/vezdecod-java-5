package com.byby.controller;

import com.byby.dto.model.QuestionDto;
import static com.byby.mock.DtoMock.*;
import com.byby.service.AnswerService;
import com.byby.service.QuestionService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.*;

@QuarkusTest
public class QuestionControllerTest {

    @InjectMock
    QuestionService questionService;

    @InjectMock
    AnswerService answerService;

    @BeforeEach
    public void before() {
        when(questionService.getRandom()).thenReturn(mockQuestion());
        when(answerService.check(any())).thenReturn(mockCheckResponse());
    }

    @Test
    public void getRandomTest() {
        given()
                .when().get("/question/random")
                .then()
                .statusCode(200)
                .body(containsString("result"))
                .body(not(containsString("Test answer")))
                .body(containsString("Test question"));
        verify(questionService).getRandom();
    }

    @Test
    public void getCheck() {
        given()
                .contentType("application/json")
                .body(mockCheckRequest())
                .when().post("/question/check")
                .then()
                .statusCode(200)
                .body(containsString("42"));

        verify(answerService).check(any());
    }

    private QuestionDto mockQuestion() {
        QuestionDto mock = new QuestionDto();
        mock.setAnswer("Test answer");
        mock.setQuestion("Test question");
        return mock;
    }
}
