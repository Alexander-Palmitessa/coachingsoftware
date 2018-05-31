package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.ClubAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.TeamAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.HashSet;
import java.util.List;

@Named("teamClubBean")
@RequestScoped
public class TeamClubBean {

    @EJB
    private TeamClubServiceRemote teamClubService;
    @EJB
    private CountryServiceRemote countryService;
    @EJB
    private SeasonServiceRemote seasonService;
    
    @Inject
	private NavigationBean navigationBean;

    private Team newTeam;
    private Club newClub;
    private Address newAddress;
    private Country newCountry;
    
    private List<Club> allClubs;
    private int selectedClubId;

    @PostConstruct
    public void init() {
    	newTeam = new Team();
    	newClub = new Club();
    	newAddress = new Address();
    	newCountry = new Country();
        allClubs = teamClubService.findAllClubs();
        for (Club club : allClubs) {
        	HashSet<Team> teams = new HashSet<Team>();
        	for (Team team : teamClubService.findTeamsByClubId(club.getID())) {
        		teams.add(team);
        		HashSet<Season> seasons = new HashSet<Season>();
        		for(Season season : seasonService.findSeasonsByTeam(team.getID())) {
        			seasons.add(season);
        		}
        		team.setSeasons(seasons);
			}
        	club.setTeams(teams);
		}
    }

    public String createClub() throws ClubNotFoundException, CountryAlreadyExistsException {
        try {
        	newCountry = countryService.findCountry(newCountry.getName());
    	} catch (CountryNotFounException e) {
    		newCountry = countryService.createCountry(newCountry);
    	}
        newAddress.setCountry(newCountry);
        newClub.setAddress(newAddress);
        try {
        	newClub = teamClubService.createClub(newClub);
        } catch (ClubAlreadyExistsException e) {
        	newClub = teamClubService.findClub(newClub.getName());
        }
        return navigationBean.redirectToClubForm();
    }

    public String createTeam() throws ClubNotFoundException, TeamAlreadyExistsException {
    	Club selectedClub = teamClubService.findClub(selectedClubId);
    	newTeam.setClub(selectedClub);
        teamClubService.createTeam(newTeam);
        return navigationBean.redirectToTeamForm();
    }

	public List<Club> getAllClubs() {
		return allClubs;
	}

	public void setAllClubs(List<Club> allClubs) {
		this.allClubs = allClubs;
	}

	public Team getNewTeam() {
		return newTeam;
	}

	public void setNewTeam(Team newTeam) {
		this.newTeam = newTeam;
	}

	public Club getNewClub() {
		return newClub;
	}

	public void setNewClub(Club newClub) {
		this.newClub = newClub;
	}

	public Address getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(Address newAddress) {
		this.newAddress = newAddress;
	}

	public Country getNewCountry() {
		return newCountry;
	}

	public void setNewCountry(Country newCountry) {
		this.newCountry = newCountry;
	}

	public int getSelectedClubId() {
		return selectedClubId;
	}

	public void setSelectedClubId(int selectedClubId) {
		this.selectedClubId = selectedClubId;
	}
}
