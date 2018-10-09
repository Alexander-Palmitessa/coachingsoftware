package com.coachingeleven.coachingsoftware;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.EvaluationTalkAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.PlayerEvaluationServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.EvaluationTalk;

@Named(value = "evaluationTalkBean")
@RequestScoped
public class EvaluationTalkBean {
	
	@Inject
	private CurrentPlayerBean currentPlayerBean;
	
	@EJB
	private PlayerEvaluationServiceRemote evaluationTalkService;
	@EJB
	private PlayerServiceRemote playerService;
	
	private EvaluationTalk newTalk;
	private String newTalkDateFormatted;
	
	private SimpleDateFormat dateFormatter;
	
	@PostConstruct
	public void init() {
		newTalk = new EvaluationTalk();
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	}
	
	public void createPlayerTalk() {
		try {
			// Set new date for the evaluation talk
			if(newTalkDateFormatted != null && !newTalkDateFormatted.isEmpty()) {
				Calendar startDateCalendar = Calendar.getInstance();
				startDateCalendar.setTime(dateFormatter.parse(newTalkDateFormatted));
				newTalk.setDate(startDateCalendar);
				newTalk.setPlayer(currentPlayerBean.getCurrentPlayer());
				currentPlayerBean.getCurrentPlayer().addEvaluationTalk(newTalk);
				playerService.update(currentPlayerBean.getCurrentPlayer());
				newTalk = new EvaluationTalk();
			}
		} catch (ParseException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			// TODO: localization
			FacesMessage facesMessage = new FacesMessage("Invalid date format", "Date format must be in dd.MM.yyyy!");
			facesContext.addMessage("createPlayerTalk:datePickerPlayerTalkDate", facesMessage);
		}
	}

	public EvaluationTalk getNewTalk() {
		return newTalk;
	}

	public void setNewTalk(EvaluationTalk newTalk) {
		this.newTalk = newTalk;
	}

	public String getNewTalkDateFormatted() {
		return newTalkDateFormatted;
	}

	public void setNewTalkDateFormatted(String newTalkDateFormatted) {
		this.newTalkDateFormatted = newTalkDateFormatted;
	}

	public String formatDate(Calendar date){
		return Integer.toString(date.get(Calendar.DATE)) + "." + Integer.toString(date.get(Calendar.MONTH)) + "." +
				Integer.toString(date.get(Calendar.YEAR));
	}

}
