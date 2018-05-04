package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.application.exception.ArenaNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import javax.naming.Context;
import javax.naming.InitialContext;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class RepositoryTest {

	private static final String JNDI_BASE_NAME = "java:global/coachingsoftware-ejb/";

	private ArenaServiceRemote arenaService;
	private CountryServiceRemote countryService;
	private TeamClubServiceRemote teamClubService;

	private Country country;
	private Arena arena;
	private Club club;
	private Team team;

	@BeforeClass
	public void lookupServices() throws Exception {
		Context jndiContext = new InitialContext();
		arenaService = (ArenaServiceRemote) jndiContext.lookup(
				JNDI_BASE_NAME + "ArenaService!" + ArenaServiceRemote.class.getName());
		countryService = (CountryServiceRemote) jndiContext.lookup(
				JNDI_BASE_NAME + "CountryService!" + CountryServiceRemote.class.getName());
		teamClubService = (TeamClubServiceRemote) jndiContext.lookup(
				JNDI_BASE_NAME + "TeamClubService!" + TeamClubServiceRemote.class.getName());

	}


	@BeforeClass
	public void initData() {
		country = new Country("SWITZERLAND");
		club = new Club("FC Biel");
	}

	@Test
	public void createCountry() throws CountryNotFounException {
			country = countryService.createCountry(country);
			assertNotNull(countryService.findCountry(country.getName()));
	}

	@Test(dependsOnMethods = "createCountry")
	public void createArena() throws ArenaNotFoundException {
		arena = new Arena("Bobs Arena", new Address("Biel", "Alicestreet", "12a", 1234, country));
		arena = arenaService.createArena(arena);
		assertNotNull(arenaService.findArena(arena.getName()));
	}

	@Test(dependsOnMethods = "createArena")
	public void searchArena() throws ArenaNotFoundException{
		assertNotNull(arenaService.searchArena("Bob"));
	}

	@Test
	public void createClub() throws ClubNotFoundException {
		club = teamClubService.createClub(club);
		assertNotNull(teamClubService.findClub(club.getName()));
	}

	@Test(dependsOnMethods = "createClub")
	public void createTeam() throws TeamNotFoundException {
		team = new Team("FC Biel Junioren", club);
		team = teamClubService.createTeam(team);
		assertNotNull(teamClubService.findTeam(team.getName()));
	}

	@AfterClass
	public void clean(){
		arenaService.deleteArena(arena);
		countryService.deleteCountry(country);
		teamClubService.deleteTeam(team);
		teamClubService.deleteClub(club);
	}
}