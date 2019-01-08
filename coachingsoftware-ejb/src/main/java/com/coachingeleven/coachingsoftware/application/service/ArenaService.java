/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.ArenaAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.ArenaNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Arena;
import com.coachingeleven.coachingsoftware.persistence.repository.ArenaRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@LocalBean
@Stateless(name = "ArenaService")
@TransactionAttribute(REQUIRED)
public class ArenaService implements ArenaServiceRemote {

	private static final Logger logger = Logger.getLogger(ArenaService.class.getName());

	@EJB
	private ArenaRepository arenaRepository;

	@Override
	public Arena createArena(Arena arena) throws ArenaAlreadyExistsException {
		logger.log(Level.INFO, "Creating arena with name " + arena.getName());
		if(arenaRepository.find(arena.getName()) != null){
			logger.log(Level.INFO, "Arena with name " + arena.getName() + " already exists");
			throw new ArenaAlreadyExistsException();
		}
		return arenaRepository.persist(arena);
	}

	@Override
	public Arena findArena(int ID) throws ArenaNotFoundException {
		logger.log(Level.INFO, "Finding Arena with id ''{0}''", ID);
		Arena arena = arenaRepository.find(Arena.class, ID);
		if (arena == null) {
			logger.log(Level.INFO, "Arena not found");
			throw new ArenaNotFoundException();
		}
		return arena;
	}

	@Override
	public Arena findArena(String name) throws ArenaNotFoundException {
		logger.log(Level.INFO, "Finding Arena with name " + name);
		Arena arena = arenaRepository.find(name);
		if (arena == null) {
			logger.log(Level.INFO, "Arena not found");
			throw new ArenaNotFoundException();
		}
		return arena;
	}

	@Override
	public List<Arena> searchArena(String keywords) throws ArenaNotFoundException {
		logger.log(Level.INFO, "Finding Arena with keywords: " + keywords);
		List<Arena> arenas = arenaRepository.search(keywords);
		if (arenas == null) {
			logger.log(Level.INFO, "Arena not found");
			throw new ArenaNotFoundException();
		}
		return arenas;
	}

	@Override
	public void deleteArena(Arena arena) {
		arenaRepository.delete(Arena.class, arena.getID());
	}

	@Override
	public List<Arena> findAll() {
		return arenaRepository.findAll(Arena.class);
	}

	@Override
	public Arena update(Arena arena) {
		return arenaRepository.update(arena);
	}
}
