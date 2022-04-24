package com.byby.controller;

import com.byby.dto.request.NewGameRequest;
import com.byby.dto.response.GameFinish;
import com.byby.dto.response.NewGameResponse;
import static com.byby.mock.DtoMock.*;
import com.byby.service.GameService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

@QuarkusTest
public class GameControllerTest {
    @InjectMock
    GameService gameService;

    @BeforeEach
    public void before() {
        when(gameService.create(any())).thenReturn(mockNewGameResponse());
        when(gameService.finish(any())).thenReturn(mockGameFinises());
    }

    @Test
    public void createTest() {
        given()
                .contentType("application/json")
                .body(mockNewGameRequest())
                .when().post("/game")
                .then()
                .statusCode(200)
                .body(containsString("55"));
        verify(gameService).create(any());
    }

    @Test
    public void finishTest(){
        given()
                .contentType("application/json")
                .when().post("/game/1/finish")
                .then()
                .statusCode(200)

                .body("result.size()", is(3),
                        "result[0].questionId", is(1),
                        "result[0].question", is("Test q1"),
                        "result[1].questionId", is(2),
                        "result[1].question", is("Test q2"),
                        "result[2].questionId", is(3),
                        "result[2].question", is("Test q3")
                );
        verify(gameService).finish(eq(1L));
    }



    private NewGameResponse mockNewGameResponse() {
        return new NewGameResponse(55L, 1);
    }

    private List<GameFinish> mockGameFinises(){
        return List.of(new GameFinish(1L, "Test q1",  true),
                new GameFinish(2L, "Test q2",  false),
                new GameFinish(3L, "Test q3",  true));
    }
}
