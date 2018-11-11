package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.ContactNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ContactService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@RequestScoped
@Named("duplicateMailValidator")
public class DuplicateEmailValidator implements Validator {

	@EJB
	private ContactService contactService;

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
		if(value != null) {
			try {
				contactService.findContact(value.toString());
				FacesMessage msg =
						new FacesMessage("Email already exists",
								"Email already exists!");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);

				throw new ValidatorException(msg);
			} catch (ContactNotFoundException e) {
				//do nothing
			}
		}
	}
}
