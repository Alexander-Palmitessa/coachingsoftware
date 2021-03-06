package com.coachingeleven.coachingsoftware.application.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamContactNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContactId;
import com.coachingeleven.coachingsoftware.persistence.repository.PlayerRepository;
import com.coachingeleven.coachingsoftware.persistence.repository.TeamContactRepository;

@LocalBean
@Stateless(name = "TeamContactService")
@TransactionAttribute(REQUIRED)
public class TeamContactService implements TeamContactServiceRemote {

	private static final Logger logger = Logger.getLogger(TeamContactService.class.getName());
	
	@EJB
    private TeamContactRepository teamContactRepository;
	@EJB
	private PlayerRepository playerRepository;
	
	@Override
	public TeamContact createTeamContact(TeamContact teamContact) throws TeamContactAlreadyExistsException {
		logger.log(Level.INFO, "Creating teamContact with teamID " + teamContact.getTeam().getID() + " and contactID " + teamContact.getContact().getID());
		if(teamContact != null && teamContact.getTeam() != null && teamContact.getContact() != null) {
			TeamContactId teamContactId = new TeamContactId(teamContact.getTeam().getID(), teamContact.getContact().getID(), teamContact.getJoinDate());
			if (teamContactRepository.find(TeamContact.class, teamContactId) != null) {
	            logger.log(Level.INFO, "TeamContact with teamID " + teamContact.getTeam().getID() + " and contactID " + teamContact.getContact().getID() + " already exists");
	            throw new TeamContactAlreadyExistsException();
	        }
		}
        return teamContactRepository.persist(teamContact);
	}

	@Override
	public List<Team> findTeamsByContact(Contact contact) {
		return teamContactRepository.findTeamsByContact(contact.getID());
	}

	@Override
	public List<Team> findUntrainedTeams() {
		return teamContactRepository.findUntrainedTeams();
	}

	@Override
	public List<Contact> findContactsByTeam(Team team) {
		return teamContactRepository.findContactsByTeam(team.getID());
	}

	@Override
	public List<Player> findPlayersByTeam(Team team) {
		return teamContactRepository.findPlayersByTeam(team.getID());
	}

	@Override
	public TeamContact update(TeamContact teamContact) {
		return teamContactRepository.update(teamContact);
	}

	@Override
	public TeamContact find(TeamContactId id) throws TeamContactNotFoundException {
		logger.log(Level.INFO, "Finding TeamContact with id " + id);
		TeamContact teamContact = teamContactRepository.find(TeamContact.class, id);
        if (teamContact == null) {
            logger.log(Level.INFO, "TeamContact not found");
            throw new TeamContactNotFoundException();
        }
        return teamContact;
	}

	@Override
	public List<Team> findAssignedTeams(int contactID) {
		return teamContactRepository.findAssignedTeams(contactID);
	}

	@Override
	public List<TeamContact> findAssignedTeamContacts(int teamID, int contactID) {
		return teamContactRepository.findAssignedTeamContacts(teamID,contactID);
	}

	@Override
	public List<Player> findUnassingnedPlayers() {
		return teamContactRepository.findUnassingnedPlayers();
	}

	@Override
	public List<Player> findPlayersByTeamAndSeason(int teamID, Season season) {
		return teamContactRepository.findPlayersByTeamAndSeason(teamID, season);
	}

}
