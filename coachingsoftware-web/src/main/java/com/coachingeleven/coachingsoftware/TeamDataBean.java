package com.coachingeleven.coachingsoftware;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.StatisticsServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.util.BlockCount;
import com.coachingeleven.coachingsoftware.util.DateFormatterBean;
import com.coachingeleven.coachingsoftware.util.SystemCount;
import com.coachingeleven.coachingsoftware.util.ZoneCountTeam;

@Named("teamDataBean")
@RequestScoped
public class TeamDataBean {

	@EJB
	private TeamClubServiceRemote teamClubService;
	@EJB
	private SeasonServiceRemote seasonService;
	@EJB
	private CountryServiceRemote countryService;
	@EJB
	private UserServiceRemote userService;
	@EJB
	private PlayerServiceRemote playerService;

	@Inject
	private LoginBean loginBean;
	@Inject
	private NavigationBean navigationBean;

	@Inject
	private DateFormatterBean dateFormatterBean; //TODO: Remove when current season is selected below
	@EJB
	private StatisticsServiceRemote statisticsService;

	private Team currentTeam;

	private List<Season> seasons;

	private List<Club> allClubs;
	private int selectedClubID;

	private Club selectedClub;

	private Country selectedCountry;

	private ZoneCountTeam zoneStats;

	private SystemCount systemCount;

	private BlockCount blockCount;

	@PostConstruct
	public void init() {
		currentTeam = loginBean.getLoggedInUserTeam();
		allClubs = teamClubService.findAllClubs();
		selectedClubID = currentTeam.getClub().getID();
		selectedClub = currentTeam.getClub();
		if (selectedClub.getAddress() == null) selectedClub.setAddress(new Address());
		seasons = seasonService.findAllSeasons();
		selectedCountry = new Country();
		if (selectedClub.getAddress().getCountry() != null)
			selectedCountry.setName(selectedClub.getAddress().getCountry().getName());


		Season mockupSeason = new Season(); //TODO: Remove when currentSeason is selectable
		mockupSeason.setStartDate(dateFormatterBean.getCalendar("01.01.1980")); //TODO: Remove when currentSeason is selectable
		mockupSeason.setEndDate(dateFormatterBean.getCalendar("01.01.2020")); //TODO: Remove when currentSeason is selectable
		zoneStats = new ZoneCountTeam(statisticsService, mockupSeason, currentTeam); //TODO: currentSeason
		systemCount = new SystemCount(statisticsService, mockupSeason, currentTeam); //TODO: currentSeason
		blockCount = new BlockCount(statisticsService, mockupSeason, currentTeam); //TODO: currentSeason
	}

	public String updateTeam() throws CountryAlreadyExistsException {
		try {
			selectedCountry = countryService.findCountry(selectedCountry.getName());
		} catch (CountryNotFounException e) {
			selectedCountry = countryService.createCountry(selectedCountry);
		}

		try {
			// Update address of the club of the team
			Address newAddress = selectedClub.getAddress();
			selectedClub = teamClubService.findClub(selectedClubID);
			selectedClub.setAddress(newAddress);
			selectedClub.getAddress().setCountry(selectedCountry);
			teamClubService.updateClub(selectedClub);

			// Set the (new) club
			currentTeam.setClub(selectedClub);
			teamClubService.updateTeam(currentTeam);

			// Assign team to user
//			loginBean.getLoggedInUser().setTeam(currentTeam);
			userService.updateUser(loginBean.getLoggedInUser());

			return navigationBean.redirectToTeamDataOverview();
		} catch (ClubNotFoundException e) {
			// TODO 
		}
		return navigationBean.toTeamDataOverview();
	}

	public void updateCurrentClub() {
		try {
			selectedClub = teamClubService.findClub(selectedClubID);
			selectedCountry = new Country();
			selectedCountry.setName(selectedClub.getAddress().getCountry().getName());
		} catch (ClubNotFoundException e) {
			// TODO 
		}
	}

	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
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

	public Country getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(Country selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public Club getSelectedClub() {
		return selectedClub;
	}

	public void setSelectedClub(Club selectedClub) {
		this.selectedClub = selectedClub;
	}

	public ZoneCountTeam getZoneStats() {
		return zoneStats;
	}

	public void setZoneStats(ZoneCountTeam zoneStats) {
		this.zoneStats = zoneStats;
	}

	public SystemCount getSystemCount() {
		return systemCount;
	}

	public void setSystemCount(SystemCount systemCount) {
		this.systemCount = systemCount;
	}

	public BlockCount getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(BlockCount blockCount) {
		this.blockCount = blockCount;
	}
}
