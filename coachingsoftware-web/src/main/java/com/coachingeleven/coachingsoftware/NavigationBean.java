package com.coachingeleven.coachingsoftware;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

	// TODO: Update links
	
	private static final long serialVersionUID = -2289682738617915324L;

	public String toLogin() {
		return "/public/pages/userLogin.xhtml";
	}

	public String redirectToLogin() {
		return "/public/pages/userLogin.xhtml?faces-redirect=true";
	}

	public String toPlayerOverview() {
		return "/secured/pages/playerOverview.xhtml";
	}

	public String redirectToPlayerOverview() {
		return "/secured/pages/playerOverview.xhtml?faces-redirect=true";
	}

	public String redirectToCurrentPlayersOverview() {
		return "/secured/pages/currentPlayersOverview.xhtml?faces-redirect=true";
	}

	public String toUserSettings() {
		return "/secured/pages/update/updateUserContact.xhtml";
	}

	public String redirectToUserSettings() {
		return "/secured/pages/update/updateUserContact.xhtml?faces-redirect=true";
	}

	public String toCreateClubPage() {
		return "/secured/pages/createClub.xhtml";
	}

	public String redirectToCreateClubPage() {
		return "/secured/pages/createClub.xhtml?faces-redirect=true";
	}

	public String toTeamForm() {
		return "/secured/forms/teamForm.xhtml";
	}

	public String redirectToTeamForm() {
		return "/secured/forms/teamForm.xhtml?faces-redirect=true";
	}

	public String toSeasonForm() {
		return "/secured/forms/seasonForm.xhtml";
	}

	public String redirectToSeasonForm() {
		return "/secured/forms/seasonForm.xhtml?faces-redirect=true";
	}

	public String toHome() {
		return "/public/pages/home.xhtml";
	}

	public String redirectToHome() {
		return "/public/pages/home.xhtml?faces-redirect=true";
	}

	public String toPlayer() {
		return "/secured/pages/playerView.xhtml";
	}

	public String redirectToPlayer() {
		return "/secured/pages/playerView.xhtml?faces-redirect=true";
	}
	
	public String toPlayerForm() {
		return "/secured/forms/createPlayer.xhtml";
	}
	
	public String redirectToPlayerForm() {
		return "/secured/forms/createPlayer.xhtml?faces-redirect=true";
	}
	
	public String to404() {
		return "/public/pages/404.xhtml";
	}
	
	public String redirectTo404() {
		return "/public/pages/404.xhtml?faces-redirect=true";
	}
	
	public String toTeamDataOverview() {
		return "/secured/pages/teamOverviewData.xhtml";
	}
	
	public String redirectToTeamDataOverview() {
		return "/secured/pages/teamOverviewData.xhtml?faces-redirect=true";
	}
  
	public String redirectToGameOverview() {
		return "/secured/pages/gameList.xhtml?faces-redirect=true";
	}

	public String toUpdateGameForm(int id) {
		return "/secured/forms/updateGameForm.xhtml?faces-redirect=true&id="+ id;
	}

	public String toNewGameForm() {
		return "/coachingsoftware/secured/forms/newGameForm.xhtml";
	}

	public String toLineUpForm() {
		return "/secured/forms/lineUpForm.xhtml";
	}

	public String redirectToEvaluationTalkForm() {
		return "/secured/pages/playerTalk.xhtml?faces-redirect=true";
	}

	public String toEvaluationTalkForm() {
		return "/secured/pages/playerTalk.xhtml";
	}

	public String redirectToPerformanceDiagnosticsForm() {
		return "/secured/pages/performanceDiagnostics.xhtml?faces-redirect=true";
	}

	public String toPerformanceDiagnosticsForm() {
		return "/secured/pages/performanceDiagnostics.xhtml";
	}

	public String redirectToAddressOverview() {
		return "/secured/pages/addressOverview.xhtml?faces-redirect=true";
	}

	public String toAddressOverview() {
		return "/secured/pages/addressOverview.xhtml";
	}
	
	public String redirectToAssignCurrentTeam() {
		return "/secured/pages/assignCurrentTeam.xhtml?faces-redirect=true";
	}

	public String toAssignCurrentTeam() {
		return "/secured/pages/assignCurrentTeam.xhtml";
	}
}
