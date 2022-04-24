package com.byby.service;

import com.byby.dto.response.CheckResponse;
import com.byby.mock.DtoMock;
import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class AnswerServiceTest {
    @Inject
    AnswerService answerService;

    @Test
    public void checkTest(){
        CheckResponse response = answerService.check(DtoMock.mockCheckRequest());

        assertNotNull(response);
        assertEquals(3L, response.getQuestionId());
        assertEquals("42", response.getCorrectAnswer());
        assertFalse(response.isCorrect());
    }
}
