package org.example;

import com.coachingeleven.coachingsoftware.application.exception.*;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.*;
import com.coachingeleven.coachingsoftware.persistence.enumeration.CardType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.GameType;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Calendar;
import java.util.List;

@Named("gameBean")
@RequestScoped
public class GameBean {

    @EJB
    private GameServiceRemote gameService;
    @EJB
    private TeamClubServiceRemote teamClubService;
    @EJB
    private ArenaServiceRemote arenaService;
    @EJB
    private PlayerServiceRemote playerService;

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

    private ChangeIn[] changeIn;
    private ChangeOut changeOut;
    private List<Player> players;
    private int selectedPlayerOutID;
    private int[] selectedPlayerInID;
    private Card card;
    private CardType[] cardTypes;

    private List<Game> allGames;

    private Objective[] gameObjectives;

    private GameReport gameReport;

    private int pathGameID;

    private int gameTypeNumber;

    public void viewActionInit() {
        try {
            game = gameService.findGame(pathGameID);
            year = game.getDate().get(Calendar.YEAR);
            month = game.getDate().get(Calendar.MONTH) + 1;
            day = game.getDate().get(Calendar.DATE);
            minute = game.getTime().get(Calendar.MINUTE);
            hour = convertHour(game.getTime().get(Calendar.HOUR));
            teamAway = game.getTeamAway().getName();
            teamHome = game.getTeamHome().getName();
            selectedArena = game.getArena().getName();

        } catch (GameNotFoundException e) {
            game = new Game();
        }
        calendar = Calendar.getInstance();
        setGameType();
    }


    @PostConstruct
    public void init() {
        card = new Card();
        teams = teamClubService.findAllTeams();
        arenas = arenaService.findAll();
        calendar = Calendar.getInstance();
        changeOut = new ChangeOut();
        players = playerService.findAllPlayers();
        cardTypes = CardType.values();
        allGames = gameService.findAllGames();
        gameReport = new GameReport();
        changeIn = new ChangeIn[3];
        gameObjectives = new Objective[2];
        selectedPlayerInID = new int[3];
    }

    public Game createGame() throws GameNotFoundException, ArenaNotFoundException, TeamNotFoundException {
        game.setArena(arenaService.findArena(selectedArena));
        game.setTeamHome(teamClubService.findTeam(teamHome));
        game.setTeamAway(teamClubService.findTeam(teamAway));
        calendar.set(year, month - 1, day, hour, minute, 0);
        game.setDate(calendar);
        game.setTime(calendar);
        try {
            game = gameService.createGame(game);
        } catch (GameAlreadyExistsException e) {
            game = gameService.findGame(game.getID());
        }
        return game;
    }

    public ChangeOut createChangeOut() throws GameNotFoundException, PlayerNotFoundException {
        changeOut.setPlayer(playerService.findPlayer(selectedPlayerOutID));
        changeOut.setGame(gameService.findGame(game.getID()));
        return gameService.createChangeOut(changeOut);
    }

    public void createChangeIn() throws GameNotFoundException, PlayerNotFoundException {
        for (int i = 0; i < changeIn.length; i++) {
            if (changeIn[i] != null) {
                changeIn[i].setPlayer(playerService.findPlayer(selectedPlayerInID[i]));
                changeIn[i].setGame(gameService.findGame(game.getID()));
                gameService.createChangeIn(changeIn[i]);
            }
        }
    }

    public void createObjectives() throws GameNotFoundException {
        for (Objective gameObjective : gameObjectives) {
            if (gameObjective != null) {
                gameObjective.setGame(gameService.findGame(game.getID()));
                gameService.createObjective(gameObjective);
            }
        }
    }

    public void createGameReport() throws GameNotFoundException {
        gameReport.setGame(gameService.findGame(game.getID()));
        gameService.createGameReport(gameReport);
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

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
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

    public ChangeIn[] getChangeIn() {
        return changeIn;
    }

    public void setChangeIn(ChangeIn[] changeIn) {
        this.changeIn = changeIn;
    }

    public ChangeOut getChangeOut() {
        return changeOut;
    }

    public void setChangeOut(ChangeOut changeOut) {
        this.changeOut = changeOut;
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

    public int[] getSelectedPlayerInID() {
        return selectedPlayerInID;
    }

    public void setSelectedPlayerInID(int[] selectedPlayerInID) {
        this.selectedPlayerInID = selectedPlayerInID;
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

    public Objective[] getGameObjectives() {
        return gameObjectives;
    }

    public void setGameObjectives(Objective[] gameObjectives) {
        this.gameObjectives = gameObjectives;
    }

    public GameReport getGameReport() {
        return gameReport;
    }

    public void setGameReport(GameReport gameReport) {
        this.gameReport = gameReport;
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

    public String dateToString(Calendar date) {
        return Integer.toString(date.get(Calendar.DATE)) + "." + Integer.toString(date.get(Calendar.MONTH)+1) + "." +
                Integer.toString(date.get(Calendar.YEAR));
    }

    public String timeToString(Calendar time){
        int hour = convertHour(time.get(Calendar.HOUR));
        return Integer.toString(hour)+":"+Integer.toString(time.get(Calendar.MINUTE));
    }

    private int convertHour(int hour){
        return hour = hour != 0 ? hour : 12;
    }
}
