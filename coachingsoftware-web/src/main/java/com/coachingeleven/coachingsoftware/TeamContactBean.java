package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamContactServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("teamContactBean")
@RequestScoped
public class TeamContactBean {

	@Inject
	private LoginBean loginBean;

	@Inject
	private NavigationBean navigationBean;

	@EJB
	private TeamContactServiceRemote teamContactService;
	@EJB
	private TeamClubServiceRemote teamClubService;
	@EJB
	private CountryServiceRemote countryService;

	private List<TeamContact> teamContacts;

	private TeamContact newTeamContact;
	private Address newTeamContactAddress;
	private Country newTeamContactCountry;

	private Team currentTeam;

	private Role[] roles;

	@PostConstruct
	public void init() {
		newTeamContact = new TeamContact();
		newTeamContactAddress = new Address();
		newTeamContactCountry = new Country();
		roles = Role.values();
		try {
			currentTeam = teamClubService.findTeam(loginBean.getUserTeamID());
			if (currentTeam.getTeamContacts() != null || !currentTeam.getTeamContacts().isEmpty()) {
				teamContacts = new ArrayList<>();
				teamContacts.addAll(currentTeam.getTeamContacts());
			}
		} catch (TeamNotFoundException e) {
			e.printStackTrace();
			//TODO: LOGGER
		}
	}

	public String createTeamContact() throws CountryAlreadyExistsException {
		try {
			newTeamContactCountry = countryService.findCountry(newTeamContactCountry.getName());
		} catch (CountryNotFounException e) {
			newTeamContactCountry = countryService.createCountry(newTeamContactCountry);
		}
		newTeamContactAddress.setCountry(newTeamContactCountry);
		newTeamContact.setAddress(newTeamContactAddress);
		newTeamContact.setTeam(currentTeam);
		try {
			newTeamContact = teamContactService.createTeamContact(newTeamContact);
			currentTeam.addTeamContact(newTeamContact);
			teamClubService.updateTeam(currentTeam);

		} catch (TeamContactAlreadyExistsException e) {
			e.printStackTrace();
			//TODO: LOGGER
		}
		return navigationBean.redirectToAddressOverview();
	}

	public List<TeamContact> getTeamContacts() {
		return teamContacts;
	}

	public void setTeamContacts(List<TeamContact> teamContacts) {
		this.teamContacts = teamContacts;
	}

	public TeamContact getNewTeamContact() {
		return newTeamContact;
	}

	public void setNewTeamContact(TeamContact newTeamContact) {
		this.newTeamContact = newTeamContact;
	}

	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public Address getNewTeamContactAddress() {
		return newTeamContactAddress;
	}

	public void setNewTeamContactAddress(Address newTeamContactAddress) {
		this.newTeamContactAddress = newTeamContactAddress;
	}

	public Country getNewTeamContactCountry() {
		return newTeamContactCountry;
	}

	public void setNewTeamContactCountry(Country newTeamContactCountry) {
		this.newTeamContactCountry = newTeamContactCountry;
	}

	public Role[] getRoles() {
		return roles;
	}
}
