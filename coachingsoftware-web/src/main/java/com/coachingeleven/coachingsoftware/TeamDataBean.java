package com.coachingeleven.coachingsoftware;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

@Named("teamDataBean")
@RequestScoped
public class TeamDataBean {
	
	@EJB
    private TeamClubServiceRemote teamClubService;
	@EJB
    private SeasonServiceRemote seasonService;
	
	@Inject
	private LoginBean loginBean;
	
	private Team currentTeam;
	
	private List<Season> seasons;
	private int selectedSeasonID;
	
	@PostConstruct
    public void init() {
		currentTeam = loginBean.getLoggedInUser().getTeam();
		seasons = seasonService.findAllSeasons();
		selectedSeasonID = currentTeam.getActiveSeason().getID();
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

}
