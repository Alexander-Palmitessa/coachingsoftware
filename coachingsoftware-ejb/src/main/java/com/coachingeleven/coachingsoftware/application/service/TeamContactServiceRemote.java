package com.coachingeleven.coachingsoftware.application.service;

import java.util.List;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;

@Remote
public interface TeamContactServiceRemote {
	
	TeamContact createTeamContact(TeamContact teamContact) throws TeamContactAlreadyExistsException;
	List<Team> findTeamsByContact(Contact contact);
	List<Team> findUnassingnedTeamsByContact(Contact contact);
	List<Contact> findContactsByTeam(Team team);
	List<Player> findPlayersByTeam(Team team);
	
}
