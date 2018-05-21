package org.example;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = -2289682738617915324L;

	public String toLogin() {
		return "/pages/userLogin.xhtml";
	}
	
	public String redirectToLogin() {
		return "/pages/userLogin.xhtml?faces-redirect=true";
	}
	
	public String toPlayerOverview() {
		return "/secured/playerOverview.xhtml";
	}
	
	public String redirectToPlayerOverview() {
		return "/secured/playerOverview.xhtml?faces-redirect=true";
	}
	
	public String toUserSettings() {
		return "/secured/userSettings.xhtml";
	}
	
	public String redirectToUserSettings() {
		return "/secured/userSettings.xhtml?faces-redirect=true";
	}
	
}
