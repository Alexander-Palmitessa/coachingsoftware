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
import com.coachingeleven.coachingsoftware.application.exception.SeasonNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
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
		seasons = seasonService.findAllSeasons();
		List<Season> teamSeasons = seasonService.findSeasonsByTeam(currentTeam.getID());
		if(teamSeasons.size() > 0) {
			selectedSeasonID = teamSeasons.get(0).getID();
		}
		selectedCountry = new Country();
		selectedCountry.setName(selectedClub.getAddress().getCountry().getName());
    }
	
	public String updateTeam() throws CountryAlreadyExistsException {
		try {
			selectedCountry = countryService.findCountry(selectedCountry.getName());
    	} catch (CountryNotFounException e) {
    		selectedCountry = countryService.createCountry(selectedCountry);
    	}
		
		try {
			// Assign new season to team only if the new season doesn't have the team (current teamID and previous team IDs).
			// Otherwise search for previous team which references the current team for the new season.
			// If there are no previous assigned teams for the new season and the new season doesn't have the current team
			// -> Create new team which references the current team
			Season newActiveSeason = seasonService.findSeason(selectedSeasonID);
			boolean isCurrentTeamInNewSeason = false;
			List<Team> teamsOfNewSeason = teamClubService.findTeamsBySeasonID(newActiveSeason.getID());
			for(Team teamOfSeason : teamsOfNewSeason) {
				if(teamOfSeason.getID() == currentTeam.getID()) {
					isCurrentTeamInNewSeason = true;
					break;
				}
			}
			
			if(!isCurrentTeamInNewSeason) {
				// Check if a previous team of season teams is already assigned to the current team
				boolean isPrevTeamAssigned = false;
				for(Team seasonTeam : teamsOfNewSeason) {
					// Check if the current team has previous teams
					Team prevTeam = currentTeam.getPreviousTeam() != null ? teamClubService.findTeam(currentTeam.getPreviousTeam().getID()) : null;
					while(prevTeam != null) {
						if(prevTeam.getID() == seasonTeam.getID()) {
							// If a previous team was already assigned to the new season
							// -> the previous team is the current team
							isPrevTeamAssigned = true;
							currentTeam = prevTeam;
							break;
						}
						if(isPrevTeamAssigned) break;
						try {
							prevTeam = prevTeam.getPreviousTeam() != null ? teamClubService.findTeam(prevTeam.getPreviousTeam().getID()) : null;
						} catch (TeamNotFoundException e) {
							prevTeam = null;
						}
					}
					// Check if the season team has previous teams who references the current team
					Team prevSeasonTeam = seasonTeam.getPreviousTeam() != null ? teamClubService.findTeam(seasonTeam.getPreviousTeam().getID()) : null;
					while(prevSeasonTeam != null) {
						if(prevSeasonTeam.getID() == currentTeam.getID()) {
							// If a previous team was already assigned to the new season
							// -> the previous team is the current team
							isPrevTeamAssigned = true;
							currentTeam = prevSeasonTeam;
							break;
						}
						if(isPrevTeamAssigned) break;
						try {
							prevSeasonTeam = prevSeasonTeam.getPreviousTeam() != null ? teamClubService.findTeam(prevSeasonTeam.getPreviousTeam().getID()) : null;
						} catch (TeamNotFoundException e) {
							prevSeasonTeam = null;
						}
					}
				}
				
				if(!isPrevTeamAssigned) {
					// Team is not yet assigned to the new season and there is also no previous assigned team for the new season
					// -> Create new team(same attributes, but without games and seasons) which references the old team
					Team newTeam = new Team();
					newTeam.setName(currentTeam.getName());
					newTeam.setPreviousTeam(currentTeam);
					newTeam.setTeamLogoURL(currentTeam.getTeamLogoURL());
					newTeam.setTeamPictureURL(currentTeam.getTeamPictureURL());
					
					int oldTeamID = currentTeam.getID();
					currentTeam = teamClubService.createTeam(newTeam);
					seasonService.addTeamToSeason(newActiveSeason.getID(), currentTeam);
					teamClubService.addCurrentPlayersToTeam(oldTeamID, currentTeam.getID());
					teamClubService.addCurrentPlayersToTeam(oldTeamID, currentTeam.getID());
				}
			}
			
			Address newAddress = selectedClub.getAddress();
			selectedClub = teamClubService.findClub(selectedClubID);
			selectedClub.setAddress(newAddress);
			selectedClub.getAddress().setCountry(selectedCountry);
			teamClubService.updateClub(selectedClub);
			currentTeam.setClub(selectedClub);
			teamClubService.updateTeam(currentTeam);
			
			// Assign new team to user
			loginBean.getLoggedInUser().setTeam(currentTeam);
			userService.updateUser(loginBean.getLoggedInUser());
			
			return navigationBean.redirectToTeamDataOverview();
		} catch (SeasonNotFoundException e) {
			// TODO 
		} catch (ClubNotFoundException e) {
			// TODO 
		} catch (TeamNotFoundException e1) {
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
