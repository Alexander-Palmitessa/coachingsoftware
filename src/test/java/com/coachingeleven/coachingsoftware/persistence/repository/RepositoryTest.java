package java.com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Arena;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.repository.ArenaRepository;
import com.coachingeleven.coachingsoftware.persistence.repository.ClubRepository;
import com.coachingeleven.coachingsoftware.persistence.repository.CountryRepository;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class RepositoryTest {
	ArenaRepository arenaRepository;
	CountryRepository countryRepository;
	ClubRepository clubRepository;
	Arena arena;
	Country country;
	Club club;

	@BeforeClass
	public void initData() {
		arenaRepository = new ArenaRepository();
		countryRepository = new CountryRepository();
		clubRepository = new ClubRepository();
		country = new Country("SWITZERLAND");
		arena = new Arena("Bobs Arena", new Address("Biel", "Alicestreet", "12a", 1234, country));
		club = new Club("FC Biel");
	}

	@Test
	public void persistCountry(){
		countryRepository.persist(country);
		assertEquals(country, countryRepository.find(country.getName()));
	}

	@Test(dependsOnMethods = "persistCountry")
	public void persistArena() {
		arenaRepository.persist(arena);
		assertNotNull(arenaRepository.find(arena.getName()));
	}

	@Test(dependsOnMethods = "persistArena")
	public void searchArena() {
		assertFalse(arenaRepository.search("Bob").isEmpty());
		assertFalse(arenaRepository.search("Alice").isEmpty());
	}

	@Test
	public void persistClub(){
		clubRepository.persist(club);
		assertNotNull(clubRepository.find(club.getName()));
	}
}
