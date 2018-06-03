package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.ClubAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface TeamClubServiceRemote {
	Team createTeam(Team team) throws TeamAlreadyExistsException;

	Club createClub(Club club) throws ClubAlreadyExistsException;

	Team findTeam(String name) throws TeamNotFoundException;

	Team findTeam(int id) throws TeamNotFoundException;

	Club findClub(String name) throws ClubNotFoundException;

	Club findClub(int id) throws ClubNotFoundException;

	boolean deleteTeam(Team team);

	boolean deleteClub(Club club);

	List<Club> findAllClubs();

	List<Team> findAllTeams();

	Club updateClub(Club club);

	Team updateTeam(Team team);

	List<Team> findTeamsByClubId(int clubId);

	List<Team> findTeamsBySeasonID(int seasonID);

	Team addPlayerToTeam(int teamID, Player player);

	List<Team> getAllPreviousTeams(int teamID);

	List<Team> getAllPreviousTeamsOfSeason(int seasonID);

	List<Player> getCurrentPlayers(int teamID);

	List<Player> getHistoryPlayers(int teamID);
}
