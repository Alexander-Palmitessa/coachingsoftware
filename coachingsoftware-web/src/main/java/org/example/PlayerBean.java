package org.example;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.persistence.entity.Player;

@Named(value = "playerBean")
@RequestScoped
public class PlayerBean {
	
	@Inject
	private LoginBean loginBean;
	
	private List<Player> userTeamPlayers;
	
	@PostConstruct
    public void init() {
		userTeamPlayers = new ArrayList<Player>();
		if(loginBean.getCurrentUser() != null) {
			userTeamPlayers.addAll(loginBean.getCurrentUser().getTeam().getPlayers());
		}
    }

	public List<Player> getUserTeamPlayers() {
		return userTeamPlayers;
	}
}
