package com.coachingeleven.coachingsoftware;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.PlayerNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;

@Named(value = "currentPlayerBean")
@SessionScoped
public class CurrentPlayerBean implements Serializable {
	
	private static final long serialVersionUID = 6131952009836384318L;

	@Inject
	private NavigationBean navigationBean;
	
	@EJB
	private PlayerServiceRemote playerService;
	
	private Player currentPlayer;
	
	public String resetCurrentPlayer(int playerID) {
		try {
			currentPlayer = playerService.findPlayer(playerID);
			return navigationBean.redirectToPlayer();
		} catch (PlayerNotFoundException e) {
			return navigationBean.redirectToCurrentPlayersOverview();
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
