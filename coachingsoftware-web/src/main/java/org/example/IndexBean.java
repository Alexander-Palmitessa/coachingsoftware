package org.example;

import com.coachingeleven.coachingsoftware.application.exception.*;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Calendar;
import java.util.HashSet;

@Named
@RequestScoped
public class IndexBean {
    @EJB
    private TeamClubServiceRemote teamClubService;
    @EJB
    private ArenaServiceRemote arenaService;
    @EJB
    private PlayerServiceRemote playerServiceRemote;
    @EJB
    private GameServiceRemote gameService;

    public void init() throws ArenaAlreadyExistsException, ClubAlreadyExistsException, TeamAlreadyExistsException, PlayerAlreadyExistsException, GameAlreadyExistsException {
        Arena arena = new Arena();
        arena.setName("Arena One");
        arena = arenaService.createArena(arena);
        Club club = new Club();
        club.setName("Club One");
        club = teamClubService.createClub(club);
        Team team = new Team();
        team.setName("Team One");
        team.setClub(club);
        team = teamClubService.createTeam(team);

        Player player1 = new Player();
        player1.setFirstName("Hans");
        player1.setLastName("Muller");
        player1.setEmail("hans@muller.com");
        player1 = playerServiceRemote.createPlayer(player1);

        Player player2 = new Player();
        player2.setFirstName("Rudolf");
        player2.setLastName("Meier");
        player2.setEmail("ruedi@meier.com");
        player2 = playerServiceRemote.createPlayer(player2);

        HashSet<Player> players = new HashSet<Player>();
		players.add(player1);
		players.add(player2);
		team.setPlayers(players);
		teamClubService.updateTeam(team);
		club.addTeam(team);
		teamClubService.updateClub(club);

        Game game = new Game();
        game.setArena(arena);
        game.setTeamAway(team);
        game.setTeamHome(team);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, 12, 12, 17, 45, 0);
        game.setDate(calendar);
        game.setTime(calendar);
        game = gameService.createGame(game);

    }

}
