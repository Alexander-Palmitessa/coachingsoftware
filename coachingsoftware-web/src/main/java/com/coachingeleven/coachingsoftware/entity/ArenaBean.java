package com.coachingeleven.coachingsoftware.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.ArenaAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.ArenaServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.CreateBean;
import com.coachingeleven.coachingsoftware.entity.base.EntityBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Arena;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;

@Named("arenaBean")
@RequestScoped
public class ArenaBean implements EntityBean<Arena>, CreateBean<Arena>, Serializable {

	private static final long serialVersionUID = -3090308778651069142L;

	@EJB
    private ArenaServiceRemote arenaService;
	
	private Arena entity;
	private List<Arena> entities;
	private String successClass;
	private boolean createSuccess;
	
	@PostConstruct
	public void init() {
		Address newAddress = new Address();
		Country newCountry = new Country();
		newAddress.setCountry(newCountry);
		entity = new Arena();
		entity.setAddress(newAddress);
		entities = arenaService.findAll();
	}
	
	@Override
	public void create(Arena entity) {
		if(entity != null) {
			try {
				arenaService.createArena(entity);
	        	successClass = "create-success";
	        	createSuccess = true;
	        } catch (ArenaAlreadyExistsException e) {
	        	successClass = "create-failure";
	        	createSuccess = false;
	        }
		}
	}

	@Override
	public String getSuccessClass() {
		return successClass;
	}

	@Override
	public boolean getCreateSuccess() {
		return createSuccess;
	}

	@Override
	public Arena getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Arena entity) {
		this.entity = entity;
	}

	@Override
	public List<Arena> getEntities() {
		return entities;
	}

	@Override
	public void setEntities(List<Arena> entities) {
		this.entities = entities;
	}

}
