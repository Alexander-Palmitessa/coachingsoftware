package com.coachingeleven.coachingsoftware.application.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.coachingeleven.coachingsoftware.application.exception.PlayerAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.repository.PlayerRepository;

@LocalBean
@TransactionAttribute(REQUIRED)
@Stateless(name = "PlayerService")
public class PlayerService implements PlayerServiceRemote {

	private static final Logger logger = Logger.getLogger(PlayerService.class.getName());
	
	@EJB
	private PlayerRepository playerRepository;
	
	@Override
	public Player createPlayer(Player player) throws PlayerAlreadyExistsException {
		logger.log(Level.INFO, "Creating player with id ''{0}''", player.getID());
		if (playerRepository.find(Player.class, player.getID()) != null) {
			logger.log(Level.INFO, "Player with same id already exists");
			throw new PlayerAlreadyExistsException();
		} else if(playerRepository.find(player.getEmail()) != null) {
			logger.log(Level.INFO, "Player with same email already exists");
			throw new PlayerAlreadyExistsException();
		}
		return playerRepository.persist(player);
	}

	@Override
	public Player findPlayer(int id) throws PlayerNotFoundException {
		logger.log(Level.INFO, "Finding player with ID ''{0}''", id);
		Player player = playerRepository.find(id);
		if (player == null) {
			logger.log(Level.INFO, "Player not found");
			throw new PlayerNotFoundException();
		}
		return player;
	}

	@Override
	public Player findPlayer(String email) throws PlayerNotFoundException {
		logger.log(Level.INFO, "Finding player with email ''{0}''", email);
		Player player = playerRepository.find(email);
		if (player == null) {
			logger.log(Level.INFO, "Player not found");
			throw new PlayerNotFoundException();
		}
		return player;
	}
	
	@Override
	public void deletePlayer(Player player) {
		playerRepository.delete(Player.class, player.getID());
	}

	@Override
	public Player update(Player player) {
		return playerRepository.update(player);
	}

	@Override
	public List<Player> findAllPlayers() {
		return playerRepository.findAll(Player.class);
	}

	@Override
	public List<Player> findCurrentPlayersByTeam(int teamId) {
		return playerRepository.findCurrentPlayersByTeam(teamId);
	}

	@Override
	public List<Player> findHistoryPlayersByTeam(int teamId) {
		return playerRepository.findHistoryPlayersByTeam(teamId);
	}

	@Override
	public Player addHistoryTeamToPlayer(int playerID, Team team) {
		return playerRepository.addHistoryTeamToPlayer(playerID, team);
	}

}
