package com.coachingeleven.coachingsoftware.application.service;

import java.util.List;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.PlayerAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

@Remote
public interface PlayerServiceRemote {
	
	/**
	 * Creates and persists a new player.
	 * 
	 * @param player The player to create.
	 * @throws PlayerAlreadyExistsException If a player with the same ID, email or second email already exists.
	 * */
	public Player createPlayer(Player player) throws PlayerAlreadyExistsException;
	
	public Player findPlayer(int id) throws PlayerNotFoundException;
	public Player findPlayer(String email) throws PlayerNotFoundException;
	public List<Player> findAllPlayers();
	public void deletePlayer(Player player);
    public Player update(Player player);
    public List<Player> findCurrentPlayersByTeam(int teamId);
    public List<Player> findHistoryPlayersByTeam(int teamId);
    public Player addHistoryTeamToPlayer(int playerID, Team team);
}
