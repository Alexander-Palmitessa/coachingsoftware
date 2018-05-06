package com.coachingeleven.coachingsoftware.application.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.coachingeleven.coachingsoftware.application.exception.GameAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.GameNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Game;
import com.coachingeleven.coachingsoftware.persistence.repository.GameRepository;

@LocalBean
@TransactionAttribute(REQUIRED)
@Stateless(name = "GameService")
public class GameService implements GameServiceRemote {

	private static final Logger logger = Logger.getLogger(GameService.class.getName());
	
	@EJB
	private GameRepository gameRepository;
	
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
	public void deleteGame(Game game) {
		gameRepository.delete(Game.class, game.getID());
	}

}
