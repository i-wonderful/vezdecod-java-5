package com.byby.service.impl;

import com.byby.dto.mapper.GameMapper;
import com.byby.dto.model.QuestionDto;
import com.byby.dto.request.GameCheckRequest;
import com.byby.dto.request.NewGameRequest;
import com.byby.dto.response.CheckResponse;
import com.byby.dto.response.GameFinish;
import com.byby.dto.response.NewGameResponse;
import com.byby.repository.GameRepository;
import com.byby.repository.UserAnswerRepository;
import com.byby.repository.entity.Game;
import com.byby.repository.entity.Question;
import com.byby.repository.entity.UserAnswer;
import com.byby.service.GameService;
import com.byby.service.QuestionService;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.byby.dto.mapper.QuestionMapper.*;

@ApplicationScoped
public class GameServiceImpl implements GameService {
    private static final Logger LOG = Logger.getLogger(GameServiceImpl.class.getName());

    @Inject
    GameRepository gameRepository;

    @Inject
    UserAnswerRepository userAnswerRepository;

    @Inject
    QuestionService questionService;

    @Transactional
    @Override
    public NewGameResponse create(NewGameRequest newGame) {
        // todo add validation
        List<Question> questions = questionService.getQuestions(
                newGame.getMinDifficulty(),
                newGame.getMaxDifficulty(),
                newGame.getCountQuestions() -1,
                newGame.getCategories());

        Game entity = GameMapper.toEntity(newGame);
        entity.setQuestions(questions);
        gameRepository.persistAndFlush(entity);

        return new NewGameResponse(entity.getId(), entity.getQuestions().size());
    }

    // todo add cache and remote questions
    @Override
    public QuestionDto getQuestion(Long gameId, Integer questionNumber) {
        Game game = getGame(gameId);
        return toDto(getQuestionByNumber(game, questionNumber));
    }


    @Transactional
    @Override
    public CheckResponse check(Long gameId, Integer questionNumber, GameCheckRequest checkRequest) {
        Game game = getGame(gameId);
        Question question = getQuestionByNumber(game, questionNumber);

        saveUserAnswer(game, question, checkRequest.getAnswer());
        return toCheckResponse(question, checkRequest.getAnswer());
    }

    private void saveUserAnswer(Game game, Question question, String answer) {
        UserAnswer userAnswer = toUserAnswerEntity(game, question, answer);
        userAnswerRepository.persistAndFlush(userAnswer);
    }

    @Override
    public List<GameFinish> finish(Long gameId) {
        List<UserAnswer> userAnswers = userAnswerRepository.findUserAnswersGyGameId(gameId);
        LOG.info(">>> Find answers: " + userAnswers.size());
        return userAnswers.stream().map(GameMapper::toGameFinish)
                .collect(Collectors.toList());
    }


    private Game getGame(Long gameId) {
        return gameRepository.findByIdOptional(gameId)
                .orElseThrow(() -> new RuntimeException(String.format("Not found game, gameId=%d", gameId)));
    }

    private Question getQuestionByNumber(Game game, Integer questionNumber) {
        if (questionNumber > game.getQuestions().size()) {
            throw new RuntimeException("Wrong question number, it must be < " + game.getQuestions().size());
        }
        return game.getQuestions().get(questionNumber - 1);
    }

}
