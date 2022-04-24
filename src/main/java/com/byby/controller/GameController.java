package com.byby.controller;

import com.byby.dto.model.QuestionDto;
import com.byby.dto.request.GameCheckRequest;
import com.byby.dto.request.NewGameRequest;
import com.byby.dto.response.CheckResponse;
import com.byby.dto.response.GameFinish;
import com.byby.dto.response.NewGameResponse;
import com.byby.service.GameService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameController {

    @Inject
    GameService gameService;

    @POST
    public NewGameResponse newGame(NewGameRequest newGameRequest) {
        return gameService.create(newGameRequest);
    }

    @GET
    @Path("/{game_id}/{question_number}")
    public QuestionDto getQuestion(@PathParam("game_id") Long gameId, @PathParam("question_number") Integer questionNumber) {
        return gameService.getQuestion(gameId, questionNumber);
    }

    @POST
    @Path("/{game_id}/{question_number}/check")
    public CheckResponse check(@PathParam("game_id") Long gameId,
                               @PathParam("question_number") Integer questionNumber,
                               GameCheckRequest checkRequest) {
        return gameService.check(gameId, questionNumber, checkRequest);
    }

    @POST
    @Path("/{game_id}/finish")
    public List<GameFinish> finish(@PathParam("game_id") Long gameId){
       return gameService.finish(gameId);
    }
}
