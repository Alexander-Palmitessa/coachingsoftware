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
	public Team createTeam(Team team) throws TeamAlreadyExistsException;
	public Club createClub(Club club) throws ClubAlreadyExistsException;
	public Team findTeam(String name) throws TeamNotFoundException;
    public Team findTeam(int id) throws TeamNotFoundException;
    public Club findClub(String name) throws ClubNotFoundException;
    public Club findClub(int id) throws ClubNotFoundException;
    public boolean deleteTeam(Team team);
    public boolean deleteClub(Club club);
    public List<Club> findAllClubs();
    public List<Team> findAllTeams();
    public Club updateClub(Club club);
    public Team updateTeam(Team team);
    public List<Team> findTeamsByClubId(int clubId);
    public List<Team> findTeamsBySeasonID(int seasonID);
    public Team addPlayerToTeam(int teamID, Player player);
    public Team addCurrentPlayersToTeam(int oldTeamID, int newTeamID);
    public Team addHistoryPlayersToTeam(int oldTeamID, int newTeamID);
}
