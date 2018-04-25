/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.ArenaNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Arena;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ArenaServiceRemote {
	void createArena(Arena arena);
	Arena findArena(int ID) throws ArenaNotFoundException;
	Arena findArena(String name) throws ArenaNotFoundException;
	List<Arena> searchArena(String keywords) throws ArenaNotFoundException;
}
