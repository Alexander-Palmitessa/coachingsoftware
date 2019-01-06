package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.ArenaNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.GameAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.GameNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamContactServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Arena;
import com.coachingeleven.coachingsoftware.persistence.entity.Card;
import com.coachingeleven.coachingsoftware.persistence.entity.ChangeIn;
import com.coachingeleven.coachingsoftware.persistence.entity.ChangeOut;
import com.coachingeleven.coachingsoftware.persistence.entity.Game;
import com.coachingeleven.coachingsoftware.persistence.entity.GameSystem;
import com.coachingeleven.coachingsoftware.persistence.entity.Goal;
import com.coachingeleven.coachingsoftware.persistence.entity.LineUpPlayer;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.PlayerGameStats;
import com.coachingeleven.coachingsoftware.persistence.entity.PostGameReport;
import com.coachingeleven.coachingsoftware.persistence.entity.PreGameReport;
import com.coachingeleven.coachingsoftware.persistence.entity.TIPS;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.enumeration.CardType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Foot;
import com.coachingeleven.coachingsoftware.persistence.enumeration.GameType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.GoalType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.LineUpType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.MissingType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Standard;
import com.coachingeleven.coachingsoftware.persistence.enumeration.System;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Zone;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;


@Named("gameBean")
@SessionScoped
public class GameBean implements Serializable {

	private static final long serialVersionUID = 5882022000372307828L;

	private static final Logger logger = Logger.getLogger(GameBean.class.getName());

	@EJB
	private GameServiceRemote gameService;
	@EJB
	private TeamClubServiceRemote teamClubService;
	@EJB
	private ArenaServiceRemote arenaService;
	@EJB
	private PlayerServiceRemote playerService;
	@EJB
	private TeamContactServiceRemote teamContactService;
	@Inject
	private NavigationBean navigationBean;
	@Inject
	private LoginBean loginBean;

	private int teamAway;
	private int teamHome;
	private int selectedArena;
	private List<Team> teams;
	private List<Arena> arenas;
	private int day;
	private int newDay;
	private int month;
	private int newMonth;
	private int year;
	private int newYear;
	private int minute;
	private int newMinute;
	private int hour;
	private int newHour;

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

	private int resGoalsHome;
	private int resGoalsAway;

	private int gameTypeNumber;
	private int gameID;

	/**
	 * Initialise all needed data and set up a new game
	 */
	public void newGameInit() {
		init();
		newGame = new Game();

		//add LineUp to the game and LineUpPlayers to the LineUp
		newGame.setLineUpPlayers(new LinkedHashSet<LineUpPlayer>());
		for (Player p : players) {
			LineUpPlayer lup = new LineUpPlayer();
			lup.setPlayer(p);
			lup.setGame(newGame);
			newGame.getLineUpPlayers().add(lup);
		}

		//add PlayerGameStats to the Game
		if (newGame.getPlayerGameStats() == null) {
			newGame.setPlayerGameStats(new LinkedHashSet<PlayerGameStats>());
			for (LineUpPlayer lineUpPlayer : newGame.getLineUpPlayers()) {
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

	/**
	 * Initialise all needed object and get the game from  the database.
	 * Called when redirected from gameList.xhtml
	 */
	public void getGameInit() {
		init();
		try {
			currentGame = gameService.findGame(gameID);
			year = currentGame.getDate().get(Calendar.YEAR);
			month = currentGame.getDate().get(Calendar.MONTH) + 1;
			day = currentGame.getDate().get(Calendar.DATE);
			minute = currentGame.getTime().get(Calendar.MINUTE);
			hour = currentGame.getTime().get(Calendar.HOUR_OF_DAY);
			teamAway = currentGame.getTeamAway().getID();
			teamHome = currentGame.getTeamHome().getID();
			selectedArena = currentGame.getArena().getID();
			resGoalsHome = currentGame.getResultGoalsHome();
			resGoalsAway = currentGame.getResultGoalsAway();
		} catch (GameNotFoundException e) {
			createGame();
			logger.log(Level.INFO, e.getMessage());
			//TODO: DISPLAY ERROR PAGE?
		}
	}

	/**
	 * Initialise all needed objects
	 */
	public void init() {
		teams = teamClubService.findAllTeams();
		arenas = arenaService.findAll();
		Team currentTeam = loginBean.getLoggedInUserTeam();
		if (loginBean.getLoggedInUserTeam() != null && loginBean.getLoggedInUserSeason() != null)
			players = teamContactService.findPlayersByTeamAndSeason(loginBean.getLoggedInUserTeam().getID(), loginBean.getLoggedInUserSeason());
		else players = new ArrayList<>();
		if (currentTeam.getGamesHome() != null)
			allGames = new ArrayList<>(currentTeam.getGamesHome());
		else allGames = new ArrayList<>();
		if (currentTeam.getGamesAway() != null)
			allGames.addAll(currentTeam.getGamesAway());

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

	/**
	 * Create a new Game and store it in the database
	 * Update the list of all games of the current Team
	 *
	 * @return redirect to the game overview
	 */
	public String createGame() {
		setArenaAndTeam(newGame);
		newGame.getDate().set(Calendar.YEAR, newYear);
		newGame.getDate().set(Calendar.MONTH, newMonth - 1);
		newGame.getDate().set(Calendar.DATE, newDay);
		newGame.getTime().set(Calendar.HOUR_OF_DAY, newHour);
		newGame.getTime().set(Calendar.MINUTE, newMinute);
		newGame.getTime().set(Calendar.SECOND, 0);
		try {
			currentGame = gameService.createGame(newGame);
			teamClubService.updateTeam(currentGame.getTeamHome());
			teamClubService.updateTeam(currentGame.getTeamAway());
		} catch (GameAlreadyExistsException e) {
			currentGame = gameService.update(newGame);
		}
		allGames = new ArrayList<>(loginBean.getLoggedInUserTeam().getGamesHome());
		allGames.addAll(loginBean.getLoggedInUserTeam().getGamesAway());
		reset();
		return navigationBean.redirectToGameOverview();
	}

	/**
	 * Get the Arena and the home and away Team from the database, set the Games to the Team and the Teams to the games.
	 *
	 * @param game The Game which the Arena and Teams should be set
	 */
	private void setArenaAndTeam(Game game) {
		try {
			Team homeTeam = teamClubService.findTeam(teamHome);
			homeTeam.getGamesHome().add(game);
			Team awayTeam = teamClubService.findTeam(teamAway);
			awayTeam.getGamesAway().add(game);
			game.setArena(arenaService.findArena(selectedArena));
			game.setTeamHome(homeTeam);
			game.setTeamAway(awayTeam);
		} catch (ArenaNotFoundException | TeamNotFoundException e) {
			logger.log(Level.INFO, e.getMessage());
			//TODO: DISPLAY ERROR PAGE?
		}
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
	//TODO: --> JSF CONVERTER
	private void setNullPlayersToNewPlayers() {
		for (Goal goal : currentGame.getGoals()) {
			if (goal.getScorer() == null) goal.setScorer(new Player());
			if (goal.getAssistant() == null) goal.setAssistant(new Player());
		}
	}

	/**
	 * Update a game and all it's children
	 *
	 * @return redirect to the same page
	 */
	//TODO: Reduce cognitive complexity, --> JSF CONVERTER
	public String updateGame() {
		//Prepare Goals
		for (Goal goal : currentGame.getGoals()) {
			if (goal.getScorer() != null) {
				if (goal.getScorer().getContact().getLastName() == null && goal.getScorer().getID() != 0) {
					try {
						goal.setScorer(playerService.findPlayer(goal.getScorer().getID()));
					} catch (PlayerNotFoundException e) {
						//TODO: DISPLAY ERROR PAGE?
						logger.log(Level.INFO, e.getMessage());
					}
				} else if (goal.getScorer().getID() == 0) goal.setScorer(null);
			}
			if (goal.getAssistant() != null) {
				if (goal.getAssistant().getContact().getLastName() == null && goal.getAssistant().getID() != 0) {
					try {
						goal.setAssistant(playerService.findPlayer(goal.getAssistant().getID()));
					} catch (PlayerNotFoundException e) {
						//TODO: DISPLAY ERROR PAGE?
						logger.log(Level.INFO, e.getMessage());
					}
				} else if (goal.getAssistant().getID() == 0) goal.setAssistant(null);
			}
		}
		//Prepare other attributes
		setArenaAndTeam(currentGame);
		currentGame.getDate().set(Calendar.YEAR, year);
		currentGame.getDate().set(Calendar.MONTH, month - 1);
		currentGame.getDate().set(Calendar.DATE, day);
		currentGame.getTime().set(Calendar.HOUR_OF_DAY, hour);
		currentGame.getTime().set(Calendar.MINUTE, minute);
		currentGame.getTime().set(Calendar.SECOND, 0);
		try {
			currentGame.setArena(arenaService.findArena(selectedArena));
		} catch (ArenaNotFoundException e) {
			logger.log(Level.INFO, e.getMessage());
			//TODO: ERROR PAGE
		}
		currentGame = gameService.update(currentGame);
		try {
			teamClubService.findTeam(teamHome).getGamesHome().add(currentGame);
			teamClubService.updateTeam(teamClubService.findTeam(teamHome));
			teamClubService.findTeam(teamAway).getGamesAway().add(currentGame);
			teamClubService.updateTeam(teamClubService.findTeam(teamAway));
		} catch (TeamNotFoundException e) {
			logger.log(Level.INFO, e.getMessage());
			//TODO: ERROR PAGE
		}
		allGames = gameService.findAllGames();
		gameService.update(currentGame);
		return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true&includeViewParams=true";
	}

	/**
	 * Delete the currentGame
	 *
	 * @return redirect to the game overview
	 */
	public String deleteGame() {
		Team homeTeam = currentGame.getTeamHome();
		Team awayTeam = currentGame.getTeamAway();
		homeTeam.getGamesHome().remove(currentGame);
		awayTeam.getGamesAway().remove(currentGame);
		teamClubService.updateTeam(homeTeam);
		teamClubService.updateTeam(awayTeam);
		gameService.deleteGame(currentGame);
		return navigationBean.redirectToGameOverview();
	}

	/**
	 * Delete a goal
	 *
	 * @param goal the goal to delete
	 * @return redirect to the goal form
	 */
	public String deleteGoal(Goal goal) {
		currentGame.getGoals().remove(goal);
		gameService.update(currentGame);
		gameService.delete(goal);
		return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true&includeViewParams=true";
	}

	/**
	 * Set the GameType of the new game
	 *
	 * @param gameTypeNumber the integer which represents the GameType
	 */
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
			default:
				newGame.setGameType(GameType.TEST);
				break;
		}
	}


	public void addAnotherSystem() {
		GameSystem newGameSystem = new GameSystem();
		newGameSystem.setGame(currentGame);
		currentGame.addGameSystem(newGameSystem);
	}

	/**
	 * Convert a Calendar to a String which represents a date
	 *
	 * @param date The Calendar to convert
	 * @return the converted Calendar's date as a string
	 */
	public String dateToString(Calendar date) {
		return Integer.toString(date.get(Calendar.DATE)) + "." + Integer.toString(date.get(Calendar.MONTH) + 1) + "." +
				Integer.toString(date.get(Calendar.YEAR));
	}

	/**
	 * Convert a Calendar to a String which represents the time
	 *
	 * @param time The calendar to convert
	 * @return the converted Calendar's time as a string
	 */
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
		newYear = newMonth = newDay = newHour = newMinute = 0;
	}

	public int getTeamAway() {
		return teamAway;
	}

	public void setTeamAway(int teamAway) {
		this.teamAway = teamAway;
	}

	public int getTeamHome() {
		return teamHome;
	}

	public void setTeamHome(int teamHome) {
		this.teamHome = teamHome;
	}

	public int getSelectedArena() {
		return selectedArena;
	}

	public void setSelectedArena(int selectedArena) {
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

	public int getNewDay() {
		return newDay;
	}

	public void setNewDay(int newDay) {
		this.newDay = newDay;
	}

	public int getNewMonth() {
		return newMonth;
	}

	public void setNewMonth(int newMonth) {
		this.newMonth = newMonth;
	}

	public int getNewYear() {
		return newYear;
	}

	public void setNewYear(int newYear) {
		this.newYear = newYear;
	}

	public int getNewMinute() {
		return newMinute;
	}

	public void setNewMinute(int newMinute) {
		this.newMinute = newMinute;
	}

	public int getNewHour() {
		return newHour;
	}

	public void setNewHour(int newHour) {
		this.newHour = newHour;
	}

	public void addAnotherCard(PlayerGameStats playerGameStats) {
		Card newCard = new Card();
		newCard.setPlayerGameStats(playerGameStats);
		playerGameStats.addCard(newCard);
	}

	public void removeCard(Card card, PlayerGameStats playerGameStats) {
		playerGameStats.removeCard(card);
		gameService.deleteCard(card);
	}
}
