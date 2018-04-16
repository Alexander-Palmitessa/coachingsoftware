package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Arena;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class ArenaRepositoryTest {
	ArenaRepository arenaRepository;
	CountryRepository countryRepository;
	Arena arena;
	Country country;

	@BeforeClass
	public void initData() {
		arenaRepository = new ArenaRepository();
		countryRepository = new CountryRepository();
		country = new Country("SWITZERLAND");
		countryRepository.persist(country);
		arena = new Arena("Bobs Arena", new Address("Biel", "Alicestreet", "12a", 1234, country));
	}

	@Test
	public void persistArena() {
		arenaRepository.persist(arena);
		assertNotNull(arenaRepository.find(arena.getName()));
	}

	@Test(dependsOnMethods = "persistArena")
	public void searchArena() {
		assertFalse(arenaRepository.search("Bob").isEmpty());
	}
}
