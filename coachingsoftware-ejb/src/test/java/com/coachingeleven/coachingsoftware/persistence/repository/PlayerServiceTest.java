package com.coachingeleven.coachingsoftware.persistence.repository;

import static org.testng.Assert.assertNotNull;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coachingeleven.coachingsoftware.application.exception.PlayerAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;

public class PlayerServiceTest {

	private static final String JNDI_BASE_NAME = "java:global/coachingsoftware-app/coachingsoftware-ejb/";
	
	private PlayerServiceRemote playerService;
	
	private Player player;
	private Player duplicatePlayerEmail;

	@BeforeClass
	public void lookupServices() throws NamingException {
		Context jndiContext = new InitialContext();
		
		playerService = (PlayerServiceRemote) jndiContext.lookup(
				JNDI_BASE_NAME + "PlayerService!" + PlayerServiceRemote.class.getName());
	}
	
	@BeforeClass
	public void initPlayers() {
		player = new Player();
		duplicatePlayerEmail = new Player();
	}
	
	@Test
	public void createPlayerTest() throws Exception {
		player = playerService.createPlayer(player);
		assertNotNull(playerService.findPlayer(player.getID()));
	}
	
	@Test(dependsOnMethods = "createPlayerTest",  expectedExceptions = {PlayerAlreadyExistsException.class})
	public void createDuplicatePlayerTest() throws Exception {
		duplicatePlayerEmail = playerService.createPlayer(duplicatePlayerEmail);
	}
	
	@Test(dependsOnMethods = "createDuplicatePlayerTest")
	public void clean() throws Exception {
		playerService.deletePlayer(player);
	}
}
