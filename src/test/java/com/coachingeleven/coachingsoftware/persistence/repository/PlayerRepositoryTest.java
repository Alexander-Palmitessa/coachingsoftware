package com.coachingeleven.coachingsoftware.persistence.repository;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coachingeleven.coachingsoftware.persistence.entity.Player;

public class PlayerRepositoryTest {

	private static final String JNDI_NAME = "java:global/coachingsoftware/";
	private static PlayerRepository playerRepository;
	
	private List<Player> players;

	@BeforeClass
	public void setUpClass() throws NamingException {
		Context jndiContext = new InitialContext();
		playerRepository = (PlayerRepository) jndiContext.lookup(JNDI_NAME + "PlayerRepository");
	}

	@BeforeClass
	public void initData() {
		players = Arrays.asList(
				new Player(),
				new Player());
	}
	
	@Test
	public void test() {
		assertNotNull(playerRepository);
	}
}
