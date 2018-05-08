package org.example;

import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Arena;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Named("arenaBean")
@SessionScoped
public class ArenaBean {

    @Pattern(regexp = "^[A-Za-z0-9]")
    @NotNull
    private String arenaName;

    @EJB
    private ArenaServiceRemote arenaService;


    public Arena createArena(Address address){
        Arena arena = new Arena(arenaName, address);
        arena = arenaService.createArena(arena);
        return arena;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }
}
