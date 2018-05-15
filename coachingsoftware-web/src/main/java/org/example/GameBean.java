package org.example;

import com.coachingeleven.coachingsoftware.application.exception.ArenaNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.GameAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.GameNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
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

    private Game game;
    private Calendar dateCalendar;
    private Calendar timeCalendar;
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

    @PostConstruct
    public void init(){
        game = new Game();
        teams = teamClubService.findAllTeams();
        arenas = arenaService.findAll();
        dateCalendar = Calendar.getInstance();
        timeCalendar = Calendar.getInstance();
    }

    public Game createGame() throws GameNotFoundException, ArenaNotFoundException, TeamNotFoundException {
        game.setArena(arenaService.findArena(selectedArena));
        game.setTeamHome(teamClubService.findTeam(teamHome));
        game.setTeamAway(teamClubService.findTeam(teamAway));
        dateCalendar.set(year, month, day);
        timeCalendar.set(0, 0, 0, hour, minute);
        game.setDate(dateCalendar);
        game.setTime(timeCalendar);
        try {
            game = gameService.createGame(game);
        } catch (GameAlreadyExistsException e) {
            game = gameService.findGame(game.getID());
        }
        return game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Calendar getDateCalendar() {
        return dateCalendar;
    }

    public void setDateCalendar(Calendar dateCalendar) {
        this.dateCalendar = dateCalendar;
    }

    public Calendar getTimeCalendar() {
        return timeCalendar;
    }

    public void setTimeCalendar(Calendar timeCalendar) {
        this.timeCalendar = timeCalendar;
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
}
