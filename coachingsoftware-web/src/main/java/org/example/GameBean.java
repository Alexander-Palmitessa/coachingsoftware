package org.example;

import com.coachingeleven.coachingsoftware.application.exception.*;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.*;
import com.coachingeleven.coachingsoftware.persistence.enumeration.CardType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.GameType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.System;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.*;

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

    private Set<ChangeIn> changeIns;
    private Set<ChangeOut> changeOuts;
    private List<Player> players;
    private int selectedPlayerOutID;
    private Card card;
    private CardType[] cardTypes;

    private List<Game> allGames;

    private Objective[] gameObjectives;

    private PostGameReport postGameReport;

    private System[] systems;
    private LineUp lineUp;
    private Player[] startingPlayers;
    private Player[] benchedPlayers;
    private System system;

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
            if (game.getLineUp() != null) {
                lineUp = game.getLineUp();
            } else {
                lineUp = new LineUp();
            }
            if (lineUp.getStartingPlayers() != null)
                startingPlayers = (Player[]) game.getLineUp().getStartingPlayers().toArray();
            else {
                startingPlayers = new Player[11];
                for (Player p : startingPlayers) p = new Player();
            }
            if (lineUp.getBenchedPlayers() != null)
                benchedPlayers = (Player[]) game.getLineUp().getBenchedPlayers().toArray();
            else {
                benchedPlayers = new Player[7];
                for (Player p : benchedPlayers) p = new Player();
            }
        } catch (GameNotFoundException e) {
            game = new Game();
            lineUp = new LineUp();
            startingPlayers = new Player[11];
            benchedPlayers = new Player[7];
        }
        calendar = Calendar.getInstance();
        setGameType();
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
        gameObjectives = new Objective[2];
        changeOuts.add(new ChangeOut());
        systems = System.values();
    }

    public Game createGame() throws ArenaNotFoundException, TeamNotFoundException {
        game.setArena(arenaService.findArena(selectedArena));
        game.setTeamHome(teamClubService.findTeam(teamHome));
        game.setTeamAway(teamClubService.findTeam(teamAway));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        game.setDate(calendar);
        game.setTime(calendar);
        try {
            game = gameService.createGame(game);
        } catch (GameAlreadyExistsException e) {
            game = gameService.update(game);
        } finally {
            game = new Game();
            resetValues();
        }
        return game;
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
        postGameReport.setGame(gameService.findGame(game.getID()));
        gameService.createGameReport(postGameReport);
    }

    public void createLineUp() {
        lineUp.setGame(game);
        try {
            for (Player p : startingPlayers) {
                if(p != null) p = playerService.findPlayer(p.getID());
            }
            for (Player p : benchedPlayers) {
                if(p != null) p = playerService.findPlayer(p.getID());
            }
        } catch (PlayerNotFoundException e) {
            //TODO
            e.printStackTrace();
        }
        lineUp.setStartingPlayers(new HashSet<Player>(Arrays.asList(startingPlayers)));
        lineUp.setBenchedPlayers(new HashSet<Player>(Arrays.asList(benchedPlayers)));
        lineUp.setSystem(system);
        try {
            gameService.createLineUp(lineUp);
        } catch (LineUpAlreadyExistsException e) {
            gameService.update(lineUp);
        }
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

    private void resetValues() {
        hour = 0;
        minute = 0;
        month = 0;
        year = 0;
        day = 0;
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

    public Objective[] getGameObjectives() {
        return gameObjectives;
    }

    public void setGameObjectives(Objective[] gameObjectives) {
        this.gameObjectives = gameObjectives;
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

    public LineUp getLineUp() {
        return lineUp;
    }

    public void setLineUp(LineUp lineUp) {
        this.lineUp = lineUp;
    }

    public Player[] getStartingPlayers() {
        return startingPlayers;
    }

    public void setStartingPlayers(Player[] startingPlayers) {
        this.startingPlayers = startingPlayers;
    }

    public Player[] getBenchedPlayers() {
        return benchedPlayers;
    }

    public void setBenchedPlayers(Player[] benchedPlayers) {
        this.benchedPlayers = benchedPlayers;
    }

    public String dateToString(Calendar date) {
        return Integer.toString(date.get(Calendar.DATE)) + "." + Integer.toString(date.get(Calendar.MONTH) + 1) + "." +
                Integer.toString(date.get(Calendar.YEAR));
    }

    public String timeToString(Calendar time) {
        return Integer.toString(time.get(Calendar.HOUR_OF_DAY)) + ":" + Integer.toString(time.get(Calendar.MINUTE));
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }
}
