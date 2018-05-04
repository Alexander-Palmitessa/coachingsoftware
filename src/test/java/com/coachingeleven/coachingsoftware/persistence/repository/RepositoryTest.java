package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.application.exception.ArenaNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Arena;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import static org.testng.Assert.assertNotNull;

public class RepositoryTest {

	private static final String JNDI_BASE_NAME = "java:global/coachingsoftware-ejb/";

	private ArenaServiceRemote arenaService;
	private CountryServiceRemote countryService;

	private Country country;
	private Arena arena;
	private Club club;

	@BeforeClass
	public void lookupServices() throws Exception {
		Context jndiContext = new InitialContext();
		arenaService = (ArenaServiceRemote) jndiContext.lookup(
				JNDI_BASE_NAME + "ArenaService!" + ArenaServiceRemote.class.getName());
		countryService = (CountryServiceRemote) jndiContext.lookup(
				JNDI_BASE_NAME + "CountryService!" + CountryServiceRemote.class.getName());
	}


	@BeforeClass
	public void initData() {
		country = new Country("SWITZERLAND");
		club = new Club("FC Biel");
	}

	@Test
	public void addCountry() throws CountryNotFounException {
			country = countryService.createCountry(country);
			assertNotNull(countryService.findCountry(country.getName()));
	}

	@Test(dependsOnMethods = "addCountry")
	public void addArena() throws ArenaNotFoundException {
		arena = new Arena("Bobs Arena", new Address("Biel", "Alicestreet", "12a", 1234, country));
		arena = arenaService.createArena(arena);
		assertNotNull(arenaService.findArena(arena.getName()));
	}

	@Test(dependsOnMethods = "addArena")
	public void searchArena() throws ArenaNotFoundException{
		assertNotNull(arenaService.searchArena("Bob"));
	}

	@AfterClass
	public void clean(){
		arenaService.deleteArena(arena);
		countryService.deleteCountry(country);
	}
}