package com.coachingeleven.coachingsoftware.application.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

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

@Remote
public interface PlayerServiceRemote {
	
	/**
	 * Creates and persists a new player.
	 * 
	 * @param player The player to create.
	 * @throws PlayerAlreadyExistsException If a player with the same ID, email or second email already exists.
	 * */
	Player createPlayer(Player player) throws PlayerAlreadyExistsException;
	
	// TODO: 'finding player' is in use case xy
	Player findPlayer(int id) throws PlayerNotFoundException;
	Player findPlayer(String email) throws PlayerNotFoundException;
	List<Player> findPlayers(String firstName, String lastName);
	List<Player> findPlayers(Address address);
	List<Player> findPlayers(Club club);
	List<Player> findPlayers(Role role);
	List<Player> findPlayers(Draft draft);
	List<Player> findPlayers(Position position);
	List<Player> findPlayers(String privateNumber, String workingNumber, String mobileNumber);
	List<Player> findPlayers(Calendar birthdate);
	List<Player> findPlayers(int size, int weight);
	List<Player> findPlayers(Contract contract);
	List<Player> findPlayers(Country country);
	List<Player> findAllPlayers();

	void deletePlayer(Player player);
}
