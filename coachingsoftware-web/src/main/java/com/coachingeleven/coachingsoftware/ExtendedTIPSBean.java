package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.ExtendedTIPSNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ExtendedTIPSServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.EvaluationTalk;
import com.coachingeleven.coachingsoftware.persistence.entity.ExtendedTIPS;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Named(value = "extendedTIPSBean")
@RequestScoped
public class ExtendedTIPSBean {

	@Inject
	private CurrentPlayerBean currentPlayerBean;

	@EJB
	private PlayerServiceRemote playerService;

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
			// Set new date for the extended TIPS
			if(newExtendedTIPSDateFormatted != null && !newExtendedTIPSDateFormatted.isEmpty()) {
				Calendar startDateCalendar = Calendar.getInstance();
				startDateCalendar.setTime(dateFormatter.parse(newExtendedTIPSDateFormatted));
				newExtendedTIPS.setDate(startDateCalendar);
				newExtendedTIPS.setPlayer(currentPlayerBean.getCurrentPlayer());
				newExtendedTIPS = extendedTIPSService.createExtendedTIPS(newExtendedTIPS);
				currentPlayerBean.getCurrentPlayer().addExtendedTIPS(newExtendedTIPS);
				playerService.update(currentPlayerBean.getCurrentPlayer());
				newExtendedTIPS = new ExtendedTIPS();
			}
		} catch (ParseException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			// TODO: localization
			FacesMessage facesMessage = new FacesMessage("Invalid date format", "Date format must be in dd.MM.yyyy!");
			facesContext.addMessage("createExtendedTIPS:datePickerExtendedTIPSDate", facesMessage);
		} catch (ExtendedTIPSNotFoundException e) {
			e.printStackTrace();
			//TODO: Logger
		}
	}

	public String formatDate(Calendar date){
		return Integer.toString(date.get(Calendar.DATE)) + "." + Integer.toString(date.get(Calendar.MONTH)) + "." +
				Integer.toString(date.get(Calendar.YEAR));
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
