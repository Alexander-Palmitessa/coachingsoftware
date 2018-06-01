package com.coachingeleven.coachingsoftware.persistence.repository;

import static org.testng.Assert.assertNotNull;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.coachingeleven.coachingsoftware.application.service.GameServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Game;

public class GameServiceTest {

	private static final String JNDI_BASE_NAME = "java:global/coachingsoftware-app/coachingsoftware-ejb/";
	
	private GameServiceRemote gameService;
	
	private Game game;

	@BeforeClass
	public void lookupServices() throws NamingException {
		Context jndiContext = new InitialContext();
		
		gameService = (GameServiceRemote) jndiContext.lookup(
				JNDI_BASE_NAME + "GameService!" + GameServiceRemote.class.getName());
	}
	
	@BeforeClass
	public void initPlayers() {
		game = new Game();
	}
	
	@Test
	public void createGameTest() throws Exception {
		game = gameService.createGame(game);
		assertNotNull(gameService.findGame(game.getID()));
	}
	
	@Test(dependsOnMethods = "createGameTest")
	public void clean() throws Exception {
		gameService.deleteGame(game);
	}
	
}
