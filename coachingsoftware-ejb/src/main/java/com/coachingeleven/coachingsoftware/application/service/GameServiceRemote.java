package com.coachingeleven.coachingsoftware.application.service;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.GameAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.GameNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Game;

@Remote
public interface GameServiceRemote {
	public Game createGame(Game game) throws GameAlreadyExistsException;
	public Game findGame(int id) throws GameNotFoundException;
	public void deleteGame(Game game);
}
