package org.example;

import com.coachingeleven.coachingsoftware.application.exception.GameAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.GameNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
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
    private String teamAway;
    private String teamHome;
    private String Arena;
    private List<Team> teams;
    private List<Arena> arenas;

    @PostConstruct
    public void init(){
        game = new Game();
        teams = teamClubService.findAllTeams();
        arenas = arenaService.findAll();
    }

    public Game createGame() throws GameNotFoundException {
        try {
            game = gameService.createGame(game);
        } catch (GameAlreadyExistsException e) {
            game = gameService.findGame(game.getID());
        }
        return game;
    }
}
