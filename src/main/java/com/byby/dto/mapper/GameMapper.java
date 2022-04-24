package com.byby.dto.mapper;

import com.byby.dto.request.NewGameRequest;
import com.byby.dto.response.GameFinish;
import com.byby.repository.entity.Game;
import com.byby.repository.entity.UserAnswer;

public class GameMapper {

    public static Game toEntity(NewGameRequest dto) {
        if (dto == null) return null;

        Game game = new Game();

        game.setMinDifficulty(dto.getMinDifficulty());
        game.setMaxDifficulty(dto.getMaxDifficulty());

        return game;
    }

    public static GameFinish toGameFinish(UserAnswer entity) {
        if (entity == null) return null;

        GameFinish gameFinish = new GameFinish();
        gameFinish.setRightAnswer(entity.isCorrect());
        gameFinish.setQuestion(entity.getQuestion().getQuestion());
        gameFinish.setQuestionId(entity.getQuestion().getId());

        return gameFinish;

    }
}
