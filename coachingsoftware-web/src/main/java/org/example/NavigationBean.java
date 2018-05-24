package org.example;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

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
	
	public String toUserSettings() {
		return "/secured/pages/userSettings.xhtml";
	}
	
	public String redirectToUserSettings() {
		return "/secured/pages/userSettings.xhtml?faces-redirect=true";
	}
	
	public String toClubForm() {
		return "/secured/forms/clubForm.xhtml";
	}
	
	public String redirectToClubForm() {
		return "/secured/forms/clubForm.xhtml?faces-redirect=true";
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
	
}
