package com.byby.controller.validator;

import com.byby.controller.exception.ValidationException;
import com.byby.dto.request.NewGameRequest;

public class GameValidator {
    public static boolean validateNewGame(NewGameRequest request) {

        boolean isValid = request.getMaxDifficulty() >= 0 &&
                request.getMaxDifficulty() >= 0 &&
                request.getMinDifficulty() <= request.getMaxDifficulty() &&
                request.getCountQuestions() > 0;
        if (!isValid) {
            throw new ValidationException("New game not valid");
        }
        return isValid;
    }
}
