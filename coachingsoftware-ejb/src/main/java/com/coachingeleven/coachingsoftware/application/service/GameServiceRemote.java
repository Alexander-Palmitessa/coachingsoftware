package com.coachingeleven.coachingsoftware.application.service;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.GameAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.GameNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.*;

import java.util.List;

@Remote
public interface GameServiceRemote {
	Game createGame(Game game) throws GameAlreadyExistsException;
	Game findGame(int id) throws GameNotFoundException;
	List<Game> findAllGames();
	void deleteGame(Game game);
	ChangeOut createChangeOut(ChangeOut changeOut);
	ChangeIn createChangeIn(ChangeIn changeIn);
	Card createCard(Card card);
    Objective createObjective(Objective objective);
    GameReport createGameReport(GameReport gameReport);
}
