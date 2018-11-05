package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.*;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	@EJB
	private CountryServiceRemote countryService;
	@EJB
	private SeasonServiceRemote seasonService;

	public void init() throws ArenaAlreadyExistsException, ClubAlreadyExistsException, TeamAlreadyExistsException, PlayerAlreadyExistsException, GameAlreadyExistsException, CountryAlreadyExistsException, SeasonAlreadyExistsException, ParseException {
		Arena arena = new Arena();
		arena.setName("Arena One");
		arena = arenaService.createArena(arena);
		Club club = new Club();
		club.setName("Club One");
		Country clubCountry = new Country("CH");
		clubCountry = countryService.createCountry(clubCountry);
		Address clubAddres = new Address("Bern", "Teststrasse", "32", 3001, clubCountry);
		club.setAddress(clubAddres);
		club = teamClubService.createClub(club);

		Club club2 = new Club();
		club2.setName("Club Two");
		Country club2Country = new Country("DE");
		club2Country = countryService.createCountry(club2Country);
		Address club2Addres = new Address("Lyss", "Juraweg", "10", 3250, club2Country);
		club2.setAddress(club2Addres);
		club2 = teamClubService.createClub(club2);

		Player player1 = new Player();
		player1.setFirstName("Hans");
		player1.setLastName("Muller");
		player1.setEmail("hans@muller.com");
		player1.setAvatarUrl("images/Default-avatar.jpg");
		player1 = playerServiceRemote.createPlayer(player1);

		Team team = new Team();
		team.setName("Team One");
		team.setTeamPictureURL("images/sfv_u19.jpg");
		team.setClub(club);
		HashSet<Player> currentPlayers = new HashSet<Player>();
		currentPlayers.add(player1);
		team = teamClubService.createTeam(team);

		Player player2 = new Player();
		player2.setFirstName("Rudolf");
		player2.setLastName("Meier");
		player2.setEmail("ruedi@meier.com");
		HashSet<Team> historyTeams = new HashSet<Team>();
		historyTeams.add(team);
		player2.setAvatarUrl("images/Default-avatar.jpg");
		player2 = playerServiceRemote.createPlayer(player2);

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
            game.setLineUpPlayers(new HashSet<LineUpPlayer>());
            for (Player p : currentPlayers) {
                LineUpPlayer lup = new LineUpPlayer();
                lup.setPlayer(p);
                lup.setGame(game);
                game.getLineUpPlayers().add(lup);
        }
        game = gameService.createGame(game);

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		Calendar endDate1Calendar = Calendar.getInstance();
		Calendar endDate2Calendar = Calendar.getInstance();
		Calendar endDate3Calendar = Calendar.getInstance();
		Calendar startDate1Calendar = Calendar.getInstance();
		Calendar startDate2Calendar = Calendar.getInstance();
		Calendar startDate3Calendar = Calendar.getInstance();
		endDate1Calendar.setTime(dateFormatter.parse("12.05.2014"));
		endDate2Calendar.setTime(dateFormatter.parse("12.05.2015"));
		endDate3Calendar.setTime(dateFormatter.parse("12.05.2016"));
		startDate1Calendar.setTime(dateFormatter.parse("28.11.2014"));
		startDate2Calendar.setTime(dateFormatter.parse("28.11.2015"));
		startDate3Calendar.setTime(dateFormatter.parse("28.11.2016"));

		HashSet<Team> season1Teams = new HashSet<Team>();
		season1Teams.add(team);
		HashSet<Game> season1Games = new HashSet<Game>();
		season1Games.add(game);

		Season season1 = new Season("Season One", startDate1Calendar, endDate1Calendar);
		Season season2 = new Season("Season Two", startDate2Calendar, endDate2Calendar);
		Season season3 = new Season("Season Three", startDate3Calendar, endDate3Calendar);
		season1 = seasonService.createSeason(season1);
		season2 = seasonService.createSeason(season2);
		season3 = seasonService.createSeason(season3);

		teamClubService.updateTeam(team);
	}

}
