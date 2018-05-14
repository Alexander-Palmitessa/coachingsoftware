package org.example;

import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Arena;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("arenaBean")
@RequestScoped
public class ArenaBean implements Serializable {

    private Arena arena;

    @EJB
    private ArenaServiceRemote arenaService;


    @PostConstruct
    public void init() {
        arena = new Arena();
    }

    public Arena getArena() {
        return arena;
    }

    public Arena createArena(Address address) {
        arena.setAddress(address);
        arena = arenaService.createArena(arena);
        return arena;
    }
}
