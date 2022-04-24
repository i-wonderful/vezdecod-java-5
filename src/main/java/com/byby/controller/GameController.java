package com.byby.controller;

import com.byby.dto.request.GameCheckRequest;
import com.byby.dto.request.NewGameRequest;
import com.byby.dto.response.*;
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
    public ResponseDto newGame(NewGameRequest newGameRequest) {
        return new ResponseDto(gameService.create(newGameRequest));
    }

    @GET
    @Path("/{game_id}/{question_number}")
    public ResponseDto getQuestion(@PathParam("game_id") Long gameId, @PathParam("question_number") Integer questionNumber) {
        return new ResponseDto(gameService.getQuestion(gameId, questionNumber));
    }

    @POST
    @Path("/{game_id}/{question_number}/check")
    public ResponseDto check(@PathParam("game_id") Long gameId,
                               @PathParam("question_number") Integer questionNumber,
                               GameCheckRequest checkRequest) {
        // тут номер вопроса, а не айди, поэтому не может воспользоваться ранее написанным методом в AnswerService
        return new ResponseDto(gameService.check(gameId, questionNumber, checkRequest));
    }

    @POST
    @Path("/{game_id}/finish")
    public ResponseDto finish(@PathParam("game_id") Long gameId){
       List<GameFinish> finishes = gameService.finish(gameId);
       return new ResponseDto(new ResponseDto.ListResponseWrapper(finishes));
    }
}
