package org.example;

import com.coachingeleven.coachingsoftware.application.exception.*;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.*;
import com.coachingeleven.coachingsoftware.persistence.enumeration.*;
import com.coachingeleven.coachingsoftware.persistence.enumeration.System;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named("gameBean")
@RequestScoped
public class GameBean implements Serializable {

	@EJB
	private GameServiceRemote gameService;
	@EJB
	private TeamClubServiceRemote teamClubService;
	@EJB
	private ArenaServiceRemote arenaService;
	@EJB
	private PlayerServiceRemote playerService;
	@Inject
	private NavigationBean navigationBean;

	private String teamAway;
	private String teamHome;
	private String selectedArena;
	private List<Team> teams;
	private List<Arena> arenas;
	private int day;
	private int month;
	private int year;
	private int minute;
	private int hour;

	private List<Player> players;

	private System selectedSystem;

	private List<Game> allGames;

	private System[] systems;
	private MissingType[] missingTypes;
	private LineUpType[] lineUpTypes;
	private Position[] positions;

	private Game currentGame;
	private Game newGame;

	@PostConstruct
	public void init() {
		teams = teamClubService.findAllTeams();
		arenas = arenaService.findAll();
		players = playerService.findAllPlayers();
		allGames = gameService.findAllGames();
		systems = System.values();
		lineUpTypes = LineUpType.values();
		missingTypes = MissingType.values();
		positions = Position.values();
	}

	public String createGame() {
		try {
			newGame.setArena(arenaService.findArena(selectedArena));
			newGame.setTeamHome(teamClubService.findTeam(teamHome));
			newGame.setTeamAway(teamClubService.findTeam(teamAway));
		} catch (ArenaNotFoundException | TeamNotFoundException e) {
			e.printStackTrace();
			//TODO
		}
		newGame.getDate().set(Calendar.YEAR, year);
		newGame.getDate().set(Calendar.MONTH, month - 1);
		newGame.getDate().set(Calendar.DATE, day);
		newGame.getTime().set(Calendar.HOUR_OF_DAY, hour);
		newGame.getTime().set(Calendar.MINUTE, minute);
		newGame.getTime().set(Calendar.SECOND, 0);

		try {
			currentGame = gameService.createGame(newGame);
		} catch (GameAlreadyExistsException e) {
			currentGame = gameService.update(newGame);
		}
		return getGameFromContext(currentGame.getID());
	}

	public void updateGame(){
		currentGame = gameService.update(currentGame);
	}


	public void updateLineUp(){
		gameService.update(currentGame.getLineUp());
	}

	private void setGameType(int gameTypeNumber) {
		switch (gameTypeNumber) {
			case 0:
				newGame.setGameType(GameType.CHAMPIONSHIP);
				break;
			case 1:
				newGame.setGameType(GameType.CUP);
				break;
			case 2:
				newGame.setGameType(GameType.TEST);
				break;
			case 3:
				newGame.setGameType(GameType.COUNTRY);
				break;
		}
	}

	public String getGameFromContext(int id) {
		try{
			currentGame = gameService.findGame(id);
			year = currentGame.getDate().get(Calendar.YEAR);
			month = currentGame.getDate().get(Calendar.MONTH) + 1;
			day = currentGame.getDate().get(Calendar.DATE);
			minute = currentGame.getTime().get(Calendar.MINUTE);
			hour = currentGame.getTime().get(Calendar.HOUR_OF_DAY);
			teamAway = currentGame.getTeamAway().getName();
			teamHome = currentGame.getTeamHome().getName();
			selectedArena = currentGame.getArena().getName();
			selectedSystem = currentGame.getLineUp().getSystem();
		} catch (GameNotFoundException e) {
			e.printStackTrace();
			//TODO
		}
		return navigationBean.redirectToUpdateGameForm();
	}

	public String setUpNewGame(int gameTypeNumber){
		newGame = new Game();
		if(selectedSystem != null)newGame.getLineUp().setSystem(selectedSystem);
		if (newGame.getLineUp() == null) {
			LineUp lineUp = new LineUp();
			lineUp.setGame(newGame);
			lineUp.setLineUpPlayers(new HashSet<LineUpPlayer>());
			for (Player p : players) {
				LineUpPlayer lup = new LineUpPlayer();
				lup.setPlayer(p);
				lup.setLineUp(lineUp);
				lineUp.getLineUpPlayers().add(lup);
			}
			newGame.setLineUp(lineUp);
		}
		setGameType(gameTypeNumber);
		newGame.setDate(Calendar.getInstance());
		newGame.setTime(Calendar.getInstance());
		newGame.setResultGoalsHome(0);
		newGame.setGetResultGoalsAway(0);
		return navigationBean.redirectToNewGameForm();
	}

	public String dateToString(Calendar date) {
		return Integer.toString(date.get(Calendar.DATE)) + "." + Integer.toString(date.get(Calendar.MONTH) + 1) + "." +
				Integer.toString(date.get(Calendar.YEAR));
	}

	public String timeToString(Calendar time) {
		String timeString;
		if (time.get(Calendar.HOUR_OF_DAY) < 10) timeString = "0" + Integer.toString(hour) +":";
		else timeString = Integer.toString(time.get(Calendar.HOUR_OF_DAY)) + ":";
		if (time.get(Calendar.MINUTE) < 10) timeString += "0" + Integer.toString(time.get(Calendar.MINUTE));
		else timeString += Integer.toString(time.get(Calendar.MINUTE));
		return timeString;
	}

	public String getTeamAway() {
		return teamAway;
	}

	public void setTeamAway(String teamAway) {
		this.teamAway = teamAway;
	}

	public String getTeamHome() {
		return teamHome;
	}

	public void setTeamHome(String teamHome) {
		this.teamHome = teamHome;
	}

	public String getSelectedArena() {
		return selectedArena;
	}

	public void setSelectedArena(String selectedArena) {
		this.selectedArena = selectedArena;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Arena> getArenas() {
		return arenas;
	}

	public void setArenas(List<Arena> arenas) {
		this.arenas = arenas;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public System getSelectedSystem() {
		return selectedSystem;
	}

	public void setSelectedSystem(System selectedSystem) {
		this.selectedSystem = selectedSystem;
	}

	public List<Game> getAllGames() {
		return allGames;
	}

	public void setAllGames(List<Game> allGames) {
		this.allGames = allGames;
	}

	public System[] getSystems() {
		return systems;
	}

	public void setSystems(System[] systems) {
		this.systems = systems;
	}

	public MissingType[] getMissingTypes() {
		return missingTypes;
	}

	public void setMissingTypes(MissingType[] missingTypes) {
		this.missingTypes = missingTypes;
	}

	public LineUpType[] getLineUpTypes() {
		return lineUpTypes;
	}

	public void setLineUpTypes(LineUpType[] lineUpTypes) {
		this.lineUpTypes = lineUpTypes;
	}

	public Position[] getPositions() {
		return positions;
	}

	public void setPositions(Position[] positions) {
		this.positions = positions;
	}

	public Game getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

	public Game getNewGame() {
		return newGame;
	}

	public void setNewGame(Game newGame) {
		this.newGame = newGame;
	}
}
