package com.byby.service;

import com.byby.dto.model.QuestionDto;
import com.byby.dto.request.GameCheckRequest;
import com.byby.dto.request.NewGameRequest;
import com.byby.dto.response.CheckResponse;
import com.byby.dto.response.GameFinish;
import com.byby.dto.response.NewGameResponse;

import java.util.List;

public interface GameService {
    NewGameResponse create(NewGameRequest newGame);
    QuestionDto getQuestion(Long gameId, Integer questionNumber);
    CheckResponse check(Long gameId, Integer questionNumber, GameCheckRequest checkRequest);
    List<GameFinish> finish(Long gameId);
}
