package com.coachingeleven.coachingsoftware;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.ContactNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ContactService;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;

@RequestScoped
@Named("emailValidator")
public class EmailValidator implements Validator {

	@Inject
	private LoginBean loginBean;
	
	@EJB
	private ContactService contactService;

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
		if(value != null) {
			try {
				Contact contact = contactService.findContact(value.toString());
				Contact loggedInUserContact = loginBean.getLoggedInUser().getContact();
				if(contact.getID() != loggedInUserContact.getID()) {
					FacesMessage msg =
							new FacesMessage("Email already exists",
									"Email already exists!");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);

					throw new ValidatorException(msg);
				}
			} catch (ContactNotFoundException e) {
				//do nothing
			}
		}
	}
}

