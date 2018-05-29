package org.example;

import com.coachingeleven.coachingsoftware.application.exception.GameNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.HashSet;
import java.util.Set;

@Named("playerGameStatsBean")
@RequestScoped
public class PlayerGameStatsBean {

    @EJB
    private GameServiceRemote gameService;
    @EJB
    private PlayerServiceRemote playerService;

    private int pathGameID;

    private Set<Goal> goals;
    private Game game;


    public void viewActionInit() {
        try {
            game = gameService.findGame(pathGameID);

        } catch (GameNotFoundException e) {
            game = new Game();
        }

    }

    public void persistPlayerGameStats() {
        gameService.update(game);
    }

    public int getPathGameID() {
        return pathGameID;
    }

    public void setPathGameID(int pathGameID) {
        this.pathGameID = pathGameID;
    }

    public Set<Goal> getGoals() {
        return goals;
    }

    public void setGoals(Set<Goal> goals) {
        this.goals = goals;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
