package com.byby.controller;

import com.byby.dto.request.CheckRequest;
import com.byby.dto.response.ResponseDto;
import com.byby.service.AnswerService;
import com.byby.service.QuestionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import static com.byby.dto.response.ResponseDto.ResponseCode.*;

@Path("/question")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionController {

    @Inject
    QuestionService questionService;

    @Inject
    AnswerService answerService;

    @GET
    @Path("/random")
    public ResponseDto getRandom() {
        return new ResponseDto(OK, questionService.getRandom());
    }

    @POST
    @Path("/check")
    public ResponseDto check(CheckRequest checkRequest) {
        return new ResponseDto(OK, answerService.check(checkRequest));
    }
}
