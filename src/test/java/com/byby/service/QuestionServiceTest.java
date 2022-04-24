package com.byby.service;

import com.byby.dto.model.QuestionDto;
import com.byby.repository.QuestionRepository;
import com.byby.service.impl.RandomManager;
import com.byby.mock.resources.WireMockExtensions;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@QuarkusTest
@QuarkusTestResource(WireMockExtensions.class)
public class QuestionServiceTest {
    @Inject
    QuestionService questionService;

    @InjectSpy
    QuestionRepository questionRepository;

    @InjectMock
    RandomManager randomManager;

    @BeforeEach
    public void before() {
        when(randomManager.getLocal()).thenReturn(true);
        when(randomManager.nextInt(anyInt())).thenReturn(2);
    }

    @Test
    public void getRandomFromDbTest() {
        QuestionDto questionDto = questionService.getRandom();

        verify(questionRepository).listAll();

        assertNotNull(questionDto);
        assertEquals("Test Главный вопрос жизни, вселенной, и вообще", questionDto.getQuestion());
        assertEquals(3L, questionDto.getId());
        assertEquals(1L, questionDto.getCategory().getId());
        assertEquals("42", questionDto.getAnswer());
    }


    //@Test
    // todo
    public void getRandomFromExternalTest() {
        when(randomManager.getLocal()).thenReturn(false);

        QuestionDto questionDto = questionService.getRandom();
        assertNotNull(questionDto);
    }
}
