package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.ClubAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.TeamAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
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
    
    @Inject
	private NavigationBean navigationBean;

    private Team team;
    private Club club;
    private Address clubAddress;
    private Country clubCountry;
    
    private List<Club> allClubs;
    private int selectedClubID;

    @PostConstruct
    public void init() {
    	team = new Team();
        club = new Club();
        clubAddress = new Address();
        clubCountry = new Country();
        allClubs = teamClubService.findAllClubs();
        for (Club club : allClubs) {
        	HashSet<Team> teams = new HashSet<Team>();
        	for (Team team : teamClubService.findTeamsByClubId(club.getID())) {
        		teams.add(team);
			}
        	club.setTeams(teams);
		}
    }

    public String createClub() throws ClubNotFoundException, CountryAlreadyExistsException {
        try {
        	clubCountry = countryService.findCountry(clubCountry.getName());
    	} catch (CountryNotFounException e) {
    		clubCountry = countryService.createCountry(clubCountry);
    	}
        clubAddress.setCountry(clubCountry);
        club.setAddress(clubAddress);
        try {
            club = teamClubService.createClub(club);
        } catch (ClubAlreadyExistsException e) {
            club = teamClubService.findClub(club.getName());
        }
        return navigationBean.toClubForm();
    }

    public String createTeam() throws ClubNotFoundException, TeamAlreadyExistsException {
    	club = teamClubService.findClub(selectedClubID);
        team.setClub(club);
        teamClubService.createTeam(team);
        return navigationBean.toTeamForm();
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

	public List<Club> getAllClubs() {
		return allClubs;
	}

	public void setAllClubs(List<Club> allClubs) {
		this.allClubs = allClubs;
	}

	public int getSelectedClubID() {
		return selectedClubID;
	}

	public void setSelectedClubID(int selectedClubID) {
		this.selectedClubID = selectedClubID;
	}

	public Address getClubAddress() {
		return clubAddress;
	}

	public void setClubAddress(Address clubAddress) {
		this.clubAddress = clubAddress;
	}

	public Country getClubCountry() {
		return clubCountry;
	}

	public void setClubCountry(Country clubCountry) {
		this.clubCountry = clubCountry;
	}
}
