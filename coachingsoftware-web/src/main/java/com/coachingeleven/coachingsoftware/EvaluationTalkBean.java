package com.coachingeleven.coachingsoftware;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.EvaluationTalkAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.PlayerEvaluationServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.EvaluationTalk;

@Named(value = "evaluationTalkBean")
@RequestScoped
public class EvaluationTalkBean {
	
	@Inject
	private PlayerViewBean playerViewBean;
	
	private PlayerEvaluationServiceRemote evaluationTalkService;
	
	private EvaluationTalk newTalk;
	private String newTalkDate;
	
	private SimpleDateFormat dateFormatter;
	
	@PostConstruct
	public void init() {
		newTalk = new EvaluationTalk();
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	}
	
	public void createPlayerTalk() {
		try {
			Calendar startDateCalendar = Calendar.getInstance();
			startDateCalendar.setTime(dateFormatter.parse(newTalkDate));
			newTalk.setPlayer(playerViewBean.getCurrentPlayer());
			evaluationTalkService.createEvaluationTalk(newTalk);
		} catch (ParseException e) {
			// TODO 
		} catch (EvaluationTalkAlreadyExistsException e) {
			// TODO 
		}
		
	}

}
