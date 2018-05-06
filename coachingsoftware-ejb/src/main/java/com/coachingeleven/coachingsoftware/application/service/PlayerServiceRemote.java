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
	public Player createPlayer(Player player) throws PlayerAlreadyExistsException;
	
	// TODO: 'finding player' is in use case xy
	public Player findPlayer(int id) throws PlayerNotFoundException;
	public Player findPlayer(String email) throws PlayerNotFoundException;
	public List<Player> findPlayers(String firstName, String lastName);
	public List<Player> findPlayers(Address address);
	public List<Player> findPlayers(Club club);
	public List<Player> findPlayers(Role role);
	public List<Player> findPlayers(Draft draft);
	public List<Player> findPlayers(Position position);
	public List<Player> findPlayers(String privateNumber, String workingNumber, String mobileNumber);
	public List<Player> findPlayers(Calendar birthdate);
	public List<Player> findPlayers(int size, int weight);
	public List<Player> findPlayers(Contract contract);
	public List<Player> findPlayers(Country country);

	public void deletePlayer(Player player);
}
