package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.PlayerNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.PlayerService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named("duplicateMailValidator")
public class DuplicateEmailValidator implements Validator {

	@EJB
	private PlayerService playerService;

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
		try {
			playerService.findPlayer(value.toString());
			FacesMessage msg =
					new FacesMessage("Email already exists",
							"Email already exists!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		} catch (PlayerNotFoundException e) {
			//do nothing
		}
	}
}
