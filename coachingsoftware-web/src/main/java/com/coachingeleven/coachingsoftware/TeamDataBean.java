package com.coachingeleven.coachingsoftware;

import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.SeasonNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

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
	
	private Team currentTeam;
	
	private List<Season> seasons;
	private int selectedSeasonID;
	
	private List<Club> allClubs;
    private int selectedClubID;
    
    private Club selectedClub;
    
    private Country selectedCountry;
	
	@PostConstruct
    public void init() {
		currentTeam = loginBean.getLoggedInUser().getTeam();
		allClubs = teamClubService.findAllClubs();
		selectedClubID = currentTeam.getClub().getID();
		selectedClub = currentTeam.getClub();
		if(selectedClub.getAddress() == null) selectedClub.setAddress(new Address());
		seasons = seasonService.findAllSeasons();
		List<Season> teamSeasons = seasonService.findSeasonsByTeam(currentTeam.getID());
		if(teamSeasons.size() > 0) {
			selectedSeasonID = teamSeasons.get(0).getID();
		}
		selectedCountry = new Country();
		if(selectedClub.getAddress().getCountry() != null) selectedCountry.setName(selectedClub.getAddress().getCountry().getName());
    }
	
	private Team copyTeam(Team team) throws TeamAlreadyExistsException {
		Team newTeam = new Team();
		newTeam.setName(team.getName());
		newTeam.setPreviousTeam(team);
		newTeam.setTeamLogoURL(team.getTeamLogoURL());
		newTeam.setTeamPictureURL(team.getTeamPictureURL());
		
		HashSet<Player> currentPlayers = new HashSet<Player>();
		for(Player player : teamClubService.getCurrentPlayers(team.getID())) {
			Player newCurrentPlayer = new Player();
			newCurrentPlayer.setID(player.getID());
			currentPlayers.add(newCurrentPlayer);
		}
		newTeam.setCurrentPlayers(currentPlayers);
		
		newTeam = teamClubService.createTeam(newTeam);
		
		for(Player player : playerService.findHistoryPlayersByTeam(team.getID())) {
			playerService.addHistoryTeamToPlayer(player.getID(), newTeam);
		}
		
		return newTeam;
	}
	
	public String updateTeam() throws CountryAlreadyExistsException {
		try {
			selectedCountry = countryService.findCountry(selectedCountry.getName());
    	} catch (CountryNotFounException e) {
    		selectedCountry = countryService.createCountry(selectedCountry);
    	}
		
		try {
			// Assign new season to team only if the new season team is not the current team or a child of the current team
			Season newActiveSeason = seasonService.findSeason(selectedSeasonID);
			List<Team> newSeasonTeams = teamClubService.findTeamsBySeasonID(newActiveSeason.getID());
			
			// If a team is already assigned to the new season
			Team newCurrentTeam = null;
			if(newSeasonTeams != null) {
				for(Team newSeasonTeam : newSeasonTeams) {
					if(newSeasonTeam.getID() == currentTeam.getID()) {
						// Check if the team of the new season is already the current team
						newCurrentTeam = newSeasonTeam;
						break;
					} else {
						// Check if a previous teams of the new season team is equals to the current team
						List<Team> newSeasonTeamPreviousTeams = teamClubService.getAllPreviousTeams(newSeasonTeam.getID());
						for(Team newSeasonPreviousTeam : newSeasonTeamPreviousTeams) {
							if(newSeasonPreviousTeam.getID() == currentTeam.getID()) {
								newCurrentTeam = newSeasonTeam;
								break;
							}
						}
						// Check if a previous team of the current team is equals the new season team
						List<Team> currentTeamPreviousTeams = teamClubService.getAllPreviousTeams(currentTeam.getID());
						for(Team currentTeamPreviousTeam : currentTeamPreviousTeams) {
							if(currentTeamPreviousTeam.getID() == newSeasonTeam.getID()) {
								newCurrentTeam = newSeasonTeam;
								break;
							}
						}
					}
				}
			} else {
				// Add the new team to the selected season
				seasonService.addTeamToSeason(newActiveSeason.getID(), currentTeam);
			}
			
			// If the current team is not a previous team of the new season team
			if(newCurrentTeam == null) {
				// The current team is not yet assigned to the new season
				// -> Create new season with a copy of the current team
				currentTeam = copyTeam(currentTeam);
				// Add the new team to the selected season
				seasonService.addTeamToSeason(newActiveSeason.getID(), currentTeam);
			} else {
				String teamName = currentTeam.getName();
				currentTeam = newCurrentTeam;
				currentTeam.setName(teamName);
			}
			
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
			loginBean.getLoggedInUser().setTeam(currentTeam);
			userService.updateUser(loginBean.getLoggedInUser());
			
			return navigationBean.redirectToTeamDataOverview();
		} catch (SeasonNotFoundException e) {
			// TODO 
		} catch (ClubNotFoundException e) {
			// TODO 
		} catch (TeamAlreadyExistsException e) {
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

	public int getSelectedSeasonID() {
		return selectedSeasonID;
	}

	public void setSelectedSeasonID(int selectedSeasonID) {
		this.selectedSeasonID = selectedSeasonID;
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

}
