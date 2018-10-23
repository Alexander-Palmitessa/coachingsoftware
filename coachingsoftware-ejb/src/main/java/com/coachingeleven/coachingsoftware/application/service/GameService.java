package com.coachingeleven.coachingsoftware.application.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.coachingeleven.coachingsoftware.application.exception.GameAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.GameNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.LineUpPlayerAlreadyExistsException;
import com.coachingeleven.coachingsoftware.persistence.entity.*;
import com.coachingeleven.coachingsoftware.persistence.repository.*;

@LocalBean
@TransactionAttribute(REQUIRED)
@Stateless(name = "GameService")
public class GameService implements GameServiceRemote {

    private static final Logger logger = Logger.getLogger(GameService.class.getName());

    @EJB
    private GameRepository gameRepository;
    @EJB
    private ChangeInRepository changeInRepository;
    @EJB
    private ChangeOutRepository changeOutRepository;
    @EJB
    private CardRepository cardRepository;
    @EJB
    private ObjectiveRepository objectiveRepository;
    @EJB
    private GameReportRepository gameReportRepository;

    @EJB
    private LineUpPlayerRepository lineUpPlayerRepository;
    @EJB
    private PlayerGameStatsRepository playerGameStatsRepository;
    @EJB
    private PreGameReportRepository preGameReportRepository;
    @EJB
	private PostGameReportRepository postGameReportRepository;
    @EJB
    private GoalRepository goalRepository;
    @EJB
    private GameSystemRepository gameSystemRepository;

    @Override
    public Game createGame(Game game) throws GameAlreadyExistsException {
        logger.log(Level.INFO, "Creating game with id ''{0}''", game.getID());
        if (gameRepository.find(Game.class, game.getID()) != null) {
            logger.log(Level.INFO, "Game with same id already exists");
            throw new GameAlreadyExistsException();
        }
        return gameRepository.persist(game);
    }

    @Override
    public Game findGame(int id) throws GameNotFoundException {
        logger.log(Level.INFO, "Finding game with ID ''{0}''", id);
        Game game = gameRepository.find(id);
        if (game == null) {
            logger.log(Level.INFO, "Game not found");
            throw new GameNotFoundException();
        }
        return game;
    }

    @Override
    public List<Game> findAllGames() {
        return gameRepository.findAll(Game.class);
    }

    @Override
    public void deleteGame(Game game) {
        gameRepository.delete(Game.class, game.getID());
    }

    @Override
    public ChangeOut createChangeOut(ChangeOut changeOut) {
        return changeOutRepository.persist(changeOut);
    }

    @Override
    public ChangeIn createChangeIn(ChangeIn changeIn) {
        return changeInRepository.persist(changeIn);
    }

    @Override
    public Card createCard(Card card) {
        return cardRepository.persist(card);
    }

    @Override
    public Objective createObjective(Objective objective) {
        return objectiveRepository.persist(objective);
    }

    @Override
    public PostGameReport createGameReport(PostGameReport postGameReport) {
        return gameReportRepository.persist(postGameReport);
    }

    @Override
    public Game update(Game game) {
        logger.log(Level.INFO, "Updating game with id ''{0}''", game.getID());
        return gameRepository.update(game);
    }

    @Override
    public LineUpPlayer createLineUpPlayer(LineUpPlayer lineUpPlayer) throws LineUpPlayerAlreadyExistsException {
        logger.log(Level.INFO, "Creating lineupplayer with id ''{0}''", lineUpPlayer.getID());
        if (lineUpPlayerRepository.find(LineUpPlayer.class, lineUpPlayer.getID()) != null) {
            logger.log(Level.INFO, "Lineupplayer with same id already exists");
            throw new LineUpPlayerAlreadyExistsException();
        }
        return lineUpPlayerRepository.persist(lineUpPlayer);
    }

    @Override
    public void update(PlayerGameStats playerGameStats) {
        playerGameStatsRepository.update(playerGameStats);
    }

    @Override
    public void update(PreGameReport preGameReport) {
        preGameReportRepository.update(preGameReport);
    }

	@Override
	public void update(PostGameReport postGameReport) {
		postGameReportRepository.update(postGameReport);
	}

    @Override
    public void update(Goal goal) {
        goalRepository.update(goal);
    }

    @Override
    public void delete(Goal goal) {
        goalRepository.delete(Goal.class, goal.getID());
    }

    @Override
    public GameSystem createGameSystem(GameSystem gameSystem) throws GameAlreadyExistsException {
        logger.log(Level.INFO, "Creating GameSystem with id ''{0}''", gameSystem.getID());
        if (gameSystemRepository.find(GameSystem.class, gameSystem.getID()) != null) {
            logger.log(Level.INFO, "GameSystem with same id already exists");
            throw new GameAlreadyExistsException();
        }
        return gameSystemRepository.persist(gameSystem);
    }

    @Override
    public GameSystem updateGameSystem(GameSystem gameSystem) {
        return gameSystemRepository.update(gameSystem);
    }

    @Override
    public LineUpPlayer update(LineUpPlayer lineUpPlayer) {
        return lineUpPlayerRepository.update(lineUpPlayer);
    }

}
