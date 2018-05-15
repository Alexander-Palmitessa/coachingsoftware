package org.example;

import com.coachingeleven.coachingsoftware.application.exception.*;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.*;

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

    private ChangeIn changeIn;
    private ChangeOut changeOut;
    private List<Player> players;
    private int selectedPlayerOutID;
    private int selectedPlayerInID;

    @PostConstruct
    public void init(){
        game = new Game();
        teams = teamClubService.findAllTeams();
        arenas = arenaService.findAll();
        calendar = Calendar.getInstance();
        changeOut = new ChangeOut();
        changeIn = new ChangeIn();
        players = playerService.findAllPlayers();
    }

    public Game createGame() throws GameNotFoundException, ArenaNotFoundException, TeamNotFoundException {
        game.setArena(arenaService.findArena(selectedArena));
        game.setTeamHome(teamClubService.findTeam(teamHome));
        game.setTeamAway(teamClubService.findTeam(teamAway));
        calendar.set(year, month, day, hour, minute, 0);
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

    public ChangeIn createChangeIn() throws GameNotFoundException, PlayerNotFoundException {
        changeIn.setPlayer(playerService.findPlayer(selectedPlayerInID));
        changeIn.setGame(gameService.findGame(game.getID()));
        return gameService.createChangeIn(changeIn);
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

    public ChangeIn getChangeIn() {
        return changeIn;
    }

    public void setChangeIn(ChangeIn changeIn) {
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

    public int getSelectedPlayerInID() {
        return selectedPlayerInID;
    }

    public void setSelectedPlayerInID(int selectedPlayerInID) {
        this.selectedPlayerInID = selectedPlayerInID;
    }
}
