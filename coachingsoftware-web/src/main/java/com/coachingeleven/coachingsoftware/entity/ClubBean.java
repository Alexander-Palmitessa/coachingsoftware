package com.coachingeleven.coachingsoftware.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.ClubAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.CreateBean;
import com.coachingeleven.coachingsoftware.entity.base.EntityBean;
import com.coachingeleven.coachingsoftware.entity.base.UpdateBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;

@Named("clubBean")
@RequestScoped
public class ClubBean implements EntityBean<Club>, UpdateBean<Club>, CreateBean<Club>, Serializable {

	private static final long serialVersionUID = 3701685529777074161L;
	
	@EJB
    private TeamClubServiceRemote teamClubService;
	
	private Club entity;
	private List<Club> entities;
	private String successClass;
	private boolean createSuccess;
	
	@PostConstruct
    public void init() {
		successClass = "";
		createSuccess = false;
		Address newAddress = new Address();
		Country newCountry = new Country();
    	entity = new Club();
    	newAddress.setCountry(newCountry);
    	entity.setAddress(newAddress);
    	entities = teamClubService.findAllClubs();
    }

	@Override
	public void create(Club entity) {
		if(entity != null) {
			try {
	        	teamClubService.createClub(entity);
	        	successClass = "create-success";
	        	createSuccess = true;
	        } catch (ClubAlreadyExistsException e) {
	        	successClass = "create-failure";
	        	createSuccess = false;
	        }
		}
	}
	
	@Override
	public void update(Club entity) {
		successClass = "create-failure";
    	createSuccess = false;
    	teamClubService.updateClub(entity);
    	successClass = "create-success";
    	createSuccess = true;
	}

	@Override
	public Club getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Club entity) {
		this.entity = entity;
	}

	@Override
	public List<Club> getEntities() {
		return entities;
	}

	@Override
	public void setEntities(List<Club> entities) {
		this.entities = entities;
	}

	@Override
	public String getSuccessClass() {
		return successClass;
	}

	@Override
	public boolean getCreateSuccess() {
		return createSuccess;
	}

}
