package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.*;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.*;
import com.coachingeleven.coachingsoftware.persistence.enumeration.*;
import com.coachingeleven.coachingsoftware.persistence.enumeration.System;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named("gameBean")
@SessionScoped
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
	@Inject
	private LoginBean loginBean;

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
	private CardType[] cardTypes;
	private GoalType[] goalTypes;
	private Zone[] zones;
	private Foot[] footTypes;
	private Standard[] standards;

	private Game currentGame;
	private Game newGame;

	private Team homeTeam;
	private Team awayTeam;

	private int resGoalsHome;
	private int resGoalsAway;

	private int gameTypeNumber;
	private int gameID;

	public void newGameInit() {
		init();
		newGame = new Game();

		//add LineUp to the game and LineUpPlayers to the LineUp
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

		//add PlayerGameStats to the Game
		if (newGame.getPlayerGameStats() == null) {
			newGame.setPlayerGameStats(new HashSet<PlayerGameStats>());
			for (LineUpPlayer lineUpPlayer : newGame.getLineUp().getLineUpPlayers()) {
				PlayerGameStats playerGameStats = new PlayerGameStats();
				playerGameStats.setGame(newGame);
				playerGameStats.setChangeIn(new ChangeIn());
				playerGameStats.setChangeOut(new ChangeOut());
				playerGameStats.setTips(new TIPS());
				playerGameStats.setPlayer(lineUpPlayer.getPlayer());
				playerGameStats.getPlayer().getGameStats().add(playerGameStats);
				newGame.getPlayerGameStats().add(playerGameStats);
			}
		}
		setGameType(gameTypeNumber);
		newGame.setDate(Calendar.getInstance());
		newGame.setTime(Calendar.getInstance());
		newGame.setPreGameReport(new PreGameReport());
		newGame.getPreGameReport().setGame(newGame);
		newGame.setPostGameReport(new PostGameReport());
		newGame.getPostGameReport().setGame(newGame);
		newGame.setGoals(new TreeSet<Goal>());
		newGame.setResultGoalsHome(0);
		newGame.setResultGoalsAway(0);
	}

	public void getGameInit() {
		init();
		try {
			currentGame = gameService.findGame(gameID);
			year = currentGame.getDate().get(Calendar.YEAR);
			month = currentGame.getDate().get(Calendar.MONTH) + 1;
			day = currentGame.getDate().get(Calendar.DATE);
			minute = currentGame.getTime().get(Calendar.MINUTE);
			hour = currentGame.getTime().get(Calendar.HOUR_OF_DAY);
			teamAway = currentGame.getTeamAway().getName();
			teamHome = currentGame.getTeamHome().getName();
			selectedArena = currentGame.getArena().getName();
			selectedSystem = currentGame.getLineUp().getSystem();
			resGoalsHome = currentGame.getResultGoalsHome();
			resGoalsAway = currentGame.getResultGoalsAway();
		} catch (GameNotFoundException e) {
			createGame();
			e.printStackTrace();
			//TODO
		}
	}

	public void init() {
		teams = teamClubService.findAllTeams();
		arenas = arenaService.findAll();

		/*
		players = playerService.findAllPlayers();
		allGames = gameService.findAllGames();
		*/
		try {
			Team currentTeam = teamClubService.findTeam(loginBean.getUserTeam());
			if (currentTeam.getPlayers() != null)
				players = new ArrayList<>(currentTeam.getPlayers());
			else players = new ArrayList<>();
			if (currentTeam.getGamesHome() != null)
				allGames = new ArrayList<>(currentTeam.getGamesHome());
			else allGames = new ArrayList<>();
			if (currentTeam.getGamesAway() != null)
				allGames.addAll(currentTeam.getGamesAway());
		} catch (TeamNotFoundException e) {
			e.printStackTrace();
		}

		systems = System.values();
		lineUpTypes = LineUpType.values();
		missingTypes = MissingType.values();
		positions = Position.values();
		cardTypes = CardType.values();
		goalTypes = GoalType.values();
		zones = Zone.values();
		footTypes = Foot.values();
		standards = Standard.values();
	}

	public String createGame() {
		newGame = setArenaAndTeam(newGame);
		newGame.getDate().set(Calendar.YEAR, year);
		newGame.getDate().set(Calendar.MONTH, month - 1);
		newGame.getDate().set(Calendar.DATE, day);
		newGame.getTime().set(Calendar.HOUR_OF_DAY, hour);
		newGame.getTime().set(Calendar.MINUTE, minute);
		newGame.getTime().set(Calendar.SECOND, 0);
		try {
			currentGame = gameService.createGame(newGame);
			teamClubService.updateTeam(currentGame.getTeamHome());
			teamClubService.updateTeam(currentGame.getTeamAway());
		} catch (GameAlreadyExistsException e) {
			currentGame = gameService.update(newGame);
		}
		allGames = gameService.findAllGames();
		return navigationBean.redirectToGameOverview();
	}

	private Game setArenaAndTeam(Game game) {
		try {
			homeTeam = teamClubService.findTeam(teamHome);
			homeTeam.getGamesHome().add(newGame);
			awayTeam = teamClubService.findTeam(teamAway);
			awayTeam.getGamesAway().add(game);
			game.setArena(arenaService.findArena(selectedArena));
			game.setTeamHome(homeTeam);
			game.setTeamAway(awayTeam);
		} catch (ArenaNotFoundException | TeamNotFoundException e) {
			e.printStackTrace();
			//TODO
		}
		return game;
	}

	/**
	 * Call addAnotherGoal() if the Set Goals of the currentGame is empty
	 */
	public void addGoal() {
		if (currentGame.getGoals().isEmpty()) {
			addAnotherGoal();
		}
		setNullPlayersToNewPlayers();
	}

	/**
	 * Add another Goal to the Set of Goals of the currentGame
	 */
	public void addAnotherGoal() {
		Goal goal = new Goal();
		goal.setScorer(new Player());
		goal.setAssistant(new Player());
		goal.setGame(currentGame);
		currentGame.getGoals().add(goal);
		setNullPlayersToNewPlayers();
	}

	/**
	 * Set to Scorer and/or Assistant of the Goal to a new Player if they are null
	 * to avoid unreachable Player.ID
	 */
	private void setNullPlayersToNewPlayers() {
		for (Goal goal : currentGame.getGoals()) {
			if (goal.getScorer() == null) goal.setScorer(new Player());
			if (goal.getAssistant() == null) goal.setAssistant(new Player());
		}
	}

	public String updateGame() {
		//Prepare Goals
		for (Goal goal : currentGame.getGoals()) {
			if (goal.getScorer() != null) {
				if (goal.getScorer().getLastName() == null && goal.getScorer().getID() != 0) {
					try {
						goal.setScorer(playerService.findPlayer(goal.getScorer().getID()));
					} catch (PlayerNotFoundException e) {
						//TODO
						e.printStackTrace();
					}
				} else if (goal.getScorer().getID() == 0) goal.setScorer(null);
			}
			if (goal.getAssistant() != null) {
				if (goal.getAssistant().getLastName() == null && goal.getAssistant().getID() != 0) {
					try {
						goal.setAssistant(playerService.findPlayer(goal.getAssistant().getID()));
					} catch (PlayerNotFoundException e) {
						//TODO
						e.printStackTrace();
					}
				} else if (goal.getAssistant().getID() == 0) goal.setAssistant(null);
			}
		}

		currentGame = setArenaAndTeam(currentGame);
		currentGame.getDate().set(Calendar.YEAR, year);
		currentGame.getDate().set(Calendar.MONTH, month - 1);
		currentGame.getDate().set(Calendar.DATE, day);
		currentGame.getTime().set(Calendar.HOUR_OF_DAY, hour);
		currentGame.getTime().set(Calendar.MINUTE, minute);
		currentGame.getTime().set(Calendar.SECOND, 0);
		try {
			currentGame.setArena(arenaService.findArena(selectedArena));
		} catch (ArenaNotFoundException e) {
			e.printStackTrace();
		}
		currentGame = gameService.update(currentGame);
		try {
			teamClubService.findTeam(teamHome).getGamesHome().add(currentGame);
			teamClubService.findTeam(teamAway).getGamesAway().add(currentGame);

		} catch (TeamNotFoundException e) {
			e.printStackTrace();
		}
		allGames = gameService.findAllGames();
		gameService.update(currentGame);
		return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true&includeViewParams=true";
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

	public String dateToString(Calendar date) {
		return Integer.toString(date.get(Calendar.DATE)) + "." + Integer.toString(date.get(Calendar.MONTH) + 1) + "." +
				Integer.toString(date.get(Calendar.YEAR));
	}

	public String timeToString(Calendar time) {
		String timeString;
		if (time.get(Calendar.HOUR_OF_DAY) < 10)
			timeString = "0" + Integer.toString(time.get(Calendar.HOUR_OF_DAY)) + ":";
		else timeString = Integer.toString(time.get(Calendar.HOUR_OF_DAY)) + ":";
		if (time.get(Calendar.MINUTE) < 10) timeString += "0" + Integer.toString(time.get(Calendar.MINUTE));
		else timeString += Integer.toString(time.get(Calendar.MINUTE));
		return timeString;
	}

	public void reset() {
		year = month = day = hour = minute = 0;
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

	public int getGameTypeNumber() {
		return gameTypeNumber;
	}

	public void setGameTypeNumber(int gameTypeNumber) {
		this.gameTypeNumber = gameTypeNumber;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getResGoalsHome() {
		return resGoalsHome;
	}

	public void setResGoalsHome(int resGoalsHome) {
		this.resGoalsHome = resGoalsHome;
	}

	public int getResGoalsAway() {
		return resGoalsAway;
	}

	public void setResGoalsAway(int resGoalsAway) {
		this.resGoalsAway = resGoalsAway;
	}

	public CardType[] getCardTypes() {
		return cardTypes;
	}

	public void setCardTypes(CardType[] cardTypes) {
		this.cardTypes = cardTypes;
	}

	public GoalType[] getGoalTypes() {
		return goalTypes;
	}

	public void setGoalTypes(GoalType[] goalTypes) {
		this.goalTypes = goalTypes;
	}

	public Zone[] getZones() {
		return zones;
	}

	public void setZones(Zone[] zones) {
		this.zones = zones;
	}

	public Foot[] getFootTypes() {
		return footTypes;
	}

	public void setFootTypes(Foot[] footTypes) {
		this.footTypes = footTypes;
	}

	public Standard[] getStandards() {
		return standards;
	}

	public void setStandards(Standard[] standards) {
		this.standards = standards;
	}
}
