package com.coachingeleven.coachingsoftware.application.service;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.GameAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.GameNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.ChangeIn;
import com.coachingeleven.coachingsoftware.persistence.entity.ChangeOut;
import com.coachingeleven.coachingsoftware.persistence.entity.Game;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;

import java.util.List;

@Remote
public interface GameServiceRemote {
	Game createGame(Game game) throws GameAlreadyExistsException;
	Game findGame(int id) throws GameNotFoundException;
	void deleteGame(Game game);
	ChangeOut createChangeOut(ChangeOut changeOut);
	ChangeIn createChangeIn(ChangeIn changeIn);
}
