package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.PerformanceDiagnosticsAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.PlayerEvaluationServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.PerformanceDiagnostics;

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
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "performanceDiagnosticsBean")
@RequestScoped
public class PerformanceDiagnosticsBean {
	private static final Logger logger = Logger.getLogger(EvaluationTalkBean.class.getName());

	@Inject
	private CurrentPlayerBean currentPlayerBean;

	@EJB
	private PlayerEvaluationServiceRemote evaluationService;
	@EJB
	private PlayerServiceRemote playerService;
	@Inject
	private NavigationBean navigationBean;

	private PerformanceDiagnostics newPerfDiag;
	private String newTalkDateFormatted;

	private SimpleDateFormat dateFormatter;

	@PostConstruct
	public void init() {
		newPerfDiag = new PerformanceDiagnostics();
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	}

	public String createPlayerPerformanceDiagnostics() {
		try {
			if (newTalkDateFormatted != null && !newTalkDateFormatted.isEmpty()) {
				Calendar startDateCalendar = Calendar.getInstance();
				startDateCalendar.setTime(dateFormatter.parse(newTalkDateFormatted));
				newPerfDiag.setDate(startDateCalendar);
				newPerfDiag.setPlayer(currentPlayerBean.getCurrentPlayer());
				newPerfDiag = evaluationService.createPerformanceDiagnostics(newPerfDiag);
				currentPlayerBean.getCurrentPlayer().addPerformanceDiagnostics(newPerfDiag);
				playerService.update(currentPlayerBean.getCurrentPlayer());
				newPerfDiag = new PerformanceDiagnostics();
			}
		} catch (ParseException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			// TODO: localization
			FacesMessage facesMessage = new FacesMessage("Invalid date format", "Date format must be in dd.MM.yyyy!");
			facesContext.addMessage("createPlayerTalk:datePickerPlayerTalkDate", facesMessage);
		} catch (PerformanceDiagnosticsAlreadyExistsException e) {
			logger.log(Level.INFO, e.getMessage());
		}
		return navigationBean.redirectToEvaluationTalkForm();
	}

	public PerformanceDiagnostics getNewPerfDiag() {
		return newPerfDiag;
	}

	public void setNewPerfDiag(PerformanceDiagnostics newPerfDiag) {
		this.newPerfDiag = newPerfDiag;
	}

	public String getNewPerfDiagDateFormatted() {
		return newTalkDateFormatted;
	}

	public void setPerfDiagDateFormatted(String newTalkDateFormatted) {
		this.newTalkDateFormatted = newTalkDateFormatted;
	}

	public String formatDate(Calendar date) {
		return Integer.toString(date.get(Calendar.DATE)) + "." + Integer.toString(date.get(Calendar.MONTH)) + "." +
				Integer.toString(date.get(Calendar.YEAR));
	}
}
