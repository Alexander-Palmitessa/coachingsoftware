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
import com.coachingeleven.coachingsoftware.application.exception.LineUpAlreadyExistsException;
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
    private LineUpRepository lineUpRepository;
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
    public LineUp createLineUp(LineUp lineUp) throws LineUpAlreadyExistsException {
        logger.log(Level.INFO, "Creating lineup with id ''{0}''", lineUp.getID());
        if (lineUpRepository.find(LineUp.class, lineUp.getID()) != null) {
            logger.log(Level.INFO, "Lineup with same id already exists");
            throw new LineUpAlreadyExistsException();
        }
        return lineUpRepository.persist(lineUp);
    }

    @Override
    public Game update(Game game) {
        logger.log(Level.INFO, "Updating game with id ''{0}''", game.getID());
        return gameRepository.update(game);
    }

    @Override
    public LineUp update(LineUp lineUp) {
        return lineUpRepository.update(lineUp);
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
    public LineUpPlayer update(LineUpPlayer lineUpPlayer) {
        return lineUpPlayerRepository.update(lineUpPlayer);
    }

}
