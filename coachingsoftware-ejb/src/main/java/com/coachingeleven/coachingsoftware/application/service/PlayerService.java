package com.coachingeleven.coachingsoftware.application.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.coachingeleven.coachingsoftware.application.exception.PlayerAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Contract;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Draft;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;
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
		} else if(playerRepository.find(player.getFirstEmail()) != null) {
			logger.log(Level.INFO, "Player with same email already exists");
			throw new PlayerAlreadyExistsException();
		}
		return playerRepository.persist(player);
	}
	
	// TODO: Create player method with all the parameters (firstname, lastname, email etc.) in this bean or with JSF?

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
	public List<Player> findPlayers(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findPlayers(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findPlayers(Club club) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findPlayers(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findPlayers(Draft draft) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findPlayers(Position position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findPlayers(String privateNumber, String workingNumber, String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findPlayers(Calendar birthdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findPlayers(int size, int weight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findPlayers(Contract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findPlayers(Country country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findAllPlayers() {
		return playerRepository.findAll(Player.class);
	}

}
