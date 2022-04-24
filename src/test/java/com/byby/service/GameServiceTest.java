package com.byby.service;

import com.byby.dto.response.GameFinish;
import com.byby.dto.response.NewGameResponse;
import com.byby.mock.DtoMock;
import com.byby.repository.GameRepository;
import com.byby.repository.UserAnswerRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@QuarkusTest
public class GameServiceTest {

    @Inject
    GameService gameService;

    @InjectMock
    QuestionService questionService;

    @InjectSpy
    GameRepository gameRepository;

    @InjectSpy
    UserAnswerRepository userAnswerRepository;

    @Test
    public void createGameTest() {
        NewGameResponse response = gameService.create(DtoMock.mockNewGameRequest());
        assertNotNull(response);
        assertNotNull(response.getId());

        verify(questionService).getQuestionsForGame(eq(0), eq(5), eq(2), isNull());
        verify(gameRepository).persistAndFlush(any());
    }

    @Test
    public void finishGameTest() {
        List<GameFinish> finishes = gameService.finish(1L);

        verify(userAnswerRepository).findUserAnswersGyGameId(eq(1L));

        assertNotNull(finishes);
        assertEquals(3, finishes.size());
        assertEquals(finishes.get(0).getQuestion(), "Test Третья планета от Солнца");
        assertEquals(finishes.get(0).isRightAnswer(), false);
        assertEquals(finishes.get(1).getQuestion(), "Test Столица Мадагаскара");
        assertEquals(finishes.get(1).isRightAnswer(), true);
    }

    // todo

}
