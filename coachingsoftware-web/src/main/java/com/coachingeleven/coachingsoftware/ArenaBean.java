package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.ArenaAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Arena;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("arenaBean")
@RequestScoped
public class ArenaBean implements Serializable {

	private static final long serialVersionUID = -1619226320759939284L;

	private Arena arena;
	
	private List<Arena> allArenas;

    @EJB
    private ArenaServiceRemote arenaService;

    @Inject
    private AddressBean addressBean;


    @PostConstruct
    public void init() {
        arena = new Arena();
        allArenas = arenaService.findAll();
    }

    public Arena getArena() {
        return arena;
    }

    public void createArena(Address address) {
        try{
            arena.setAddress(address);
            arena = arenaService.createArena(arena);
        } catch (ArenaAlreadyExistsException e){
            arena = arenaService.update(arena);
        }
        finally {
            arena = new Arena();
            addressBean.init();
        }
    }

	public List<Arena> getAllArenas() {
		return allArenas;
	}

	public void setAllArenas(List<Arena> allArenas) {
		this.allArenas = allArenas;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}
}
