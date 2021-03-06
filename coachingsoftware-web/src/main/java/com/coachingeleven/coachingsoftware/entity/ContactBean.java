package com.coachingeleven.coachingsoftware.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.ContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.ContactServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.CreateBean;
import com.coachingeleven.coachingsoftware.entity.base.EntityBean;
import com.coachingeleven.coachingsoftware.entity.base.UpdateBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;
import com.coachingeleven.coachingsoftware.util.DateFormatterBean;

@Named("contactBean")
@RequestScoped
public class ContactBean implements EntityBean<Contact>, CreateBean<Contact>, UpdateBean<Contact>, Serializable {

	private static final long serialVersionUID = 4964788456617570504L;

	@Inject
	private DateFormatterBean dataFormatterBean;
	
	@EJB
	private ContactServiceRemote contactService;
	
	private Contact entity;
	private List<Contact> entities;
	private String successClass;
	private boolean createSuccess;
	
	private Role[] roles;
	
	private String birthday;
	
	@PostConstruct
	public void init() {
		Address newAddress = new Address();
		Country newCountry = new Country();
		newAddress.setCountry(newCountry);
		entity = new Contact();
		entity.setAddress(newAddress);
		entities = contactService.findAll();
		roles = Role.values();
		
	}
	
	@Override
	public void create(Contact entity) {
		if(entity != null) {
			try {
				if(birthday != null) entity.setBirthdate(dataFormatterBean.getCalendar(birthday));
				contactService.createContact(entity);
	        	successClass = "create-success";
	        	createSuccess = true;
	        } catch (ContactAlreadyExistsException e) {
	        	successClass = "create-failure";
	        	createSuccess = false;
	        }
		}
	}
	
	@Override
	public void update(Contact entity) {
		successClass = "create-failure";
    	createSuccess = false;
    	if(birthday != null) entity.setBirthdate(dataFormatterBean.getCalendar(birthday));
		contactService.update(entity);
		successClass = "create-success";
    	createSuccess = true;
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
	public Contact getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Contact entity) {
		this.entity = entity;
	}

	@Override
	public List<Contact> getEntities() {
		return entities;
	}

	@Override
	public void setEntities(List<Contact> entities) {
		this.entities = entities;
	}
	
	public Role[] getRoles() {
		return roles;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
