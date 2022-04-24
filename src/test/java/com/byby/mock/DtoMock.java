package com.byby.mock;

import com.byby.dto.request.CheckRequest;
import com.byby.dto.request.NewGameRequest;
import com.byby.dto.response.CheckResponse;

public class DtoMock {

    public static NewGameRequest mockNewGameRequest() {
        NewGameRequest mock = new NewGameRequest();
        mock.setCountQuestions(2);
        mock.setMaxDifficulty(0);
        mock.setMaxDifficulty(5);
        return mock;
    }

    public static CheckRequest mockCheckRequest() {
        CheckRequest mock = new CheckRequest();
        mock.setQuestionId(3L);
        mock.setAnswer("Answer1");
        return mock;
    }

    public static CheckResponse mockCheckResponse() {
        CheckResponse mock = new CheckResponse();
        mock.setQuestionId(12L);
        mock.setCorrect(true);
        mock.setCorrectAnswer("42");
        return mock;
    }
}
