package com.coachingeleven.coachingsoftware.application.service;

import java.util.List;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.PlayerAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;

@Remote
public interface PlayerServiceRemote {
	
	/**
	 * Creates and persists a new player.
	 * 
	 * @param player The player to create.
	 * @throws PlayerAlreadyExistsException If a player with the same ID, email or second email already exists.
	 * */
	Player createPlayer(Player player) throws PlayerAlreadyExistsException;
	Player findPlayer(int id) throws PlayerNotFoundException;
	List<Player> findAllPlayers();
	void deletePlayer(Player player);
	Player update(Player player);
}
