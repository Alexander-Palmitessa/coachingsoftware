package com.coachingeleven.coachingsoftware.application.service;

import java.util.List;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamContactNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContactId;

@Remote
public interface TeamContactServiceRemote {
	
	TeamContact find(TeamContactId id) throws TeamContactNotFoundException;
	TeamContact createTeamContact(TeamContact teamContact) throws TeamContactAlreadyExistsException;
	List<Team> findTeamsByContact(Contact contact);
	List<Team> findUntrainedTeams();
	List<Contact> findContactsByTeam(Team team);
	List<Player> findPlayersByTeam(Team team);
	TeamContact update(TeamContact teamContact);
	List<Team> findAssignedTeams(int contactID);
	List<TeamContact> findAssignedTeamContacts(int teamID, int contactID);
	List<Player> findUnassingnedPlayers();
	List<Player> findPlayersByTeamAndSeason(int teamID, Season season);
	
}
