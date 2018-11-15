package com.coachingeleven.coachingsoftware.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.ContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.ContactServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.CreateBean;
import com.coachingeleven.coachingsoftware.entity.base.EntityBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;
import com.coachingeleven.coachingsoftware.util.DateFormatterBean;

@Named("playerBean")
@RequestScoped
public class PlayerBean implements EntityBean<Player>, CreateBean<Player>, Serializable {

	private static final long serialVersionUID = 1019344357166717271L;
	
	@Inject
	private DateFormatterBean dataFormatterBean;
	
	@EJB
    private PlayerServiceRemote playerService;
	@EJB
	private ContactServiceRemote contactService;
	
	private Player entity;
	private List<Player> entities;
	private String successClass;
	private boolean createSuccess;
	
	private String birthday;

	@PostConstruct
	public void init() {
		Contact newContact = new Contact();
		Address newAddress = new Address();
		Country newCountry = new Country();
		Country newCountryPermission = new Country();
		newAddress.setCountry(newCountry);
		newContact.setAddress(newAddress);
		entity = new Player();
		entity.setContact(newContact);
		entity.setCountryPermission(newCountryPermission);
		entities = playerService.findAllPlayers();
	}
	
	@Override
	public void create(Player entity) {
		if(entity != null) {
			try {
				if(birthday != null) entity.getContact().setBirthdate(dataFormatterBean.getCalendar(birthday));
				entity.getContact().setRole(Role.PLAYER);
				Contact newContact = contactService.createContact(entity.getContact());
				entity.setContact(newContact);
				playerService.createPlayer(entity);
	        	successClass = "create-success";
	        	createSuccess = true;
	        } catch (PlayerAlreadyExistsException | ContactAlreadyExistsException e) {
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
	public Player getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Player entity) {
		this.entity = entity;
	}

	@Override
	public List<Player> getEntities() {
		return entities;
	}

	@Override
	public void setEntities(List<Player> entities) {
		this.entities = entities;
	}
	
	public Position[] getPositions() {
		return Position.values();
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
