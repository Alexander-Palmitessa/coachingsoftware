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
import javax.annotation.PreDestroy;
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

	private Game game;
	private Calendar calendar;
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

	private Set<ChangeIn> changeIns;
	private Set<ChangeOut> changeOuts;
	private List<Player> players;
	private int selectedPlayerOutID;
	private Card card;
	private CardType[] cardTypes;

	private System selectedSystem;

	private List<Game> allGames;

	private PostGameReport postGameReport;

	private System[] systems;
	private MissingType[] missingTypes;
	private LineUpType[] lineUpTypes;
	private Position[] positions;

	private int pathGameID;

	private int gameTypeNumber;

	public void viewActionInit() {
		try {
			game = gameService.findGame(pathGameID);
			year = game.getDate().get(Calendar.YEAR);
			month = game.getDate().get(Calendar.MONTH) + 1;
			day = game.getDate().get(Calendar.DATE);
			minute = game.getTime().get(Calendar.MINUTE);
			hour = game.getTime().get(Calendar.HOUR_OF_DAY);
			teamAway = game.getTeamAway().getName();
			teamHome = game.getTeamHome().getName();
			selectedArena = game.getArena().getName();
			selectedSystem = game.getLineUp().getSystem();
		} catch (GameNotFoundException e) {
			game = new Game();
			game.setDate(Calendar.getInstance());
			game.setTime(Calendar.getInstance());
		}
		calendar = Calendar.getInstance();
		if (game.getGameType() == null) setGameType();
		changeOuts.add(new ChangeOut());
	}

	@PostConstruct
	public void init() {
		card = new Card();
		teams = teamClubService.findAllTeams();
		arenas = arenaService.findAll();
		calendar = Calendar.getInstance();
		changeOuts = new HashSet<>();
		players = playerService.findAllPlayers();
		cardTypes = CardType.values();
		allGames = gameService.findAllGames();
		postGameReport = new PostGameReport();
		changeIns = new HashSet<>();
		changeOuts.add(new ChangeOut());
		systems = System.values();
		lineUpTypes = LineUpType.values();
		missingTypes = MissingType.values();
		positions = Position.values();
	}

	public void createGame() {
		try {
			game.setArena(arenaService.findArena(selectedArena));
			game.setTeamHome(teamClubService.findTeam(teamHome));
			game.setTeamAway(teamClubService.findTeam(teamAway));
		} catch (ArenaNotFoundException | TeamNotFoundException e) {
			e.printStackTrace();
			//TODO
		}
		game.getDate().set(Calendar.YEAR, year);
		game.getDate().set(Calendar.MONTH, month - 1);
		game.getDate().set(Calendar.DATE, day);
		game.getTime().set(Calendar.HOUR_OF_DAY, hour);
		game.getTime().set(Calendar.MINUTE, minute);
		game.getTime().set(Calendar.SECOND, 0);
		if(selectedSystem != null)game.getLineUp().setSystem(selectedSystem);
		if (game.getLineUp() == null) {
			LineUp lineUp = new LineUp();
			lineUp.setGame(game);
			lineUp.setLineUpPlayers(new HashSet<LineUpPlayer>());
			for (Player p : players) {
				LineUpPlayer lup = new LineUpPlayer();
				lup.setPlayer(p);
				lup.setLineUp(lineUp);
				lineUp.getLineUpPlayers().add(lup);
			}
			game.setLineUp(lineUp);
		}
		try {
			game = gameService.createGame(game);
		} catch (GameAlreadyExistsException e) {
			game = gameService.update(game);
			gameService.flush();
		}
	}

	public void createGameReport() throws GameNotFoundException {
		postGameReport.setGame(gameService.findGame(game.getID()));
		gameService.createGameReport(postGameReport);
	}

	public void updateLineUp(){
		gameService.update(game.getLineUp());
		game.getLineUp().setSystem(selectedSystem);
		gameService.flush();
	}

	private void setGameType() {
		switch (gameTypeNumber) {
			case 0:
				game.setGameType(GameType.CHAMPIONSHIP);
				break;
			case 1:
				game.setGameType(GameType.CUP);
				break;
			case 2:
				game.setGameType(GameType.TEST);
				break;
			case 3:
				game.setGameType(GameType.COUNTRY);
				break;
		}
	}

	public Card createCard() {
		return gameService.createCard(card);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
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

	public Set<ChangeIn> getChangeIns() {
		return changeIns;
	}

	public void setChangeIns(Set<ChangeIn> changeIns) {
		this.changeIns = changeIns;
	}

	public Set<ChangeOut> getChangeOuts() {
		return changeOuts;
	}

	public void setChangeOuts(Set<ChangeOut> changeOuts) {
		this.changeOuts = changeOuts;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getSelectedPlayerOutID() {
		return selectedPlayerOutID;
	}

	public void setSelectedPlayerOutID(int selectedPlayerOutID) {
		this.selectedPlayerOutID = selectedPlayerOutID;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public CardType[] getCardTypes() {
		return cardTypes;
	}

	public void setCardTypes(CardType[] cardTypes) {
		this.cardTypes = cardTypes;
	}

	public List<Game> getAllGames() {
		return allGames;
	}

	public void setAllGames(List<Game> allGames) {
		this.allGames = allGames;
	}

	public PostGameReport getPostGameReport() {
		return postGameReport;
	}

	public void setPostGameReport(PostGameReport postGameReport) {
		this.postGameReport = postGameReport;
	}

	public int getPathGameID() {
		return pathGameID;
	}

	public void setPathGameID(int pathGameID) {
		this.pathGameID = pathGameID;
	}

	public int getGameTypeNumber() {
		return gameTypeNumber;
	}

	public void setGameTypeNumber(int gameTypeNumber) {
		this.gameTypeNumber = gameTypeNumber;
	}

	public System[] getSystems() {
		return systems;
	}

	public void setSystems(System[] systems) {
		this.systems = systems;
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

	public PlayerServiceRemote getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerServiceRemote playerService) {
		this.playerService = playerService;
	}

	public Position[] getPositions() {
		return positions;
	}

	public void setPositions(Position[] positions) {
		this.positions = positions;
	}

	public System getSelectedSystem() {
		return selectedSystem;
	}

	public void setSelectedSystem(System selectedSystem) {
		this.selectedSystem = selectedSystem;
	}
}
