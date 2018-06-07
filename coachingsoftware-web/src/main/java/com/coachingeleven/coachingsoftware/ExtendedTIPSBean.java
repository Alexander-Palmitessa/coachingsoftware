package com.coachingeleven.coachingsoftware;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.ExtendedTIPSNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ExtendedTIPSServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.ExtendedTIPS;

@Named(value = "extendedTIPSBean")
@RequestScoped
public class ExtendedTIPSBean {
	
	@Inject
	private CurrentPlayerBean currentPlayerBean;
	
	@EJB
	private ExtendedTIPSServiceRemote extendedTIPSService;
	
	private ExtendedTIPS newExtendedTIPS;
	private String newExtendedTIPSDateFormatted;
	
	private SimpleDateFormat dateFormatter;
	
	@PostConstruct
	public void init() {
		newExtendedTIPS = new ExtendedTIPS();
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	}
	
	public void createExtendedTIPS() {
		try {
			// Set date for evaluation talk
			if(newExtendedTIPSDateFormatted != null && !newExtendedTIPSDateFormatted.isEmpty()) {
				Calendar evaluationDateCalendar = Calendar.getInstance();
				evaluationDateCalendar.setTime(dateFormatter.parse(newExtendedTIPSDateFormatted));
				newExtendedTIPS.setDate(evaluationDateCalendar);
				newExtendedTIPS.setPlayer(currentPlayerBean.getCurrentPlayer());
				extendedTIPSService.createExtendedTIPS(newExtendedTIPS);
			}
		} catch (ParseException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			// TODO: localization
			FacesMessage facesMessage = new FacesMessage("Invalid date format", "Date format must be in dd.MM.yyyy!");
			facesContext.addMessage("createPlayerRanking:datePickerPlayerRankingDate", facesMessage);
		} catch (ExtendedTIPSNotFoundException e) {
			// TODO 
		}
	}

	public ExtendedTIPS getNewExtendedTIPS() {
		return newExtendedTIPS;
	}

	public void setNewExtendedTIPS(ExtendedTIPS newExtendedTIPS) {
		this.newExtendedTIPS = newExtendedTIPS;
	}

	public String getNewExtendedTIPSDateFormatted() {
		return newExtendedTIPSDateFormatted;
	}

	public void setNewExtendedTIPSDateFormatted(String newExtendedTIPSDateFormatted) {
		this.newExtendedTIPSDateFormatted = newExtendedTIPSDateFormatted;
	}

}
