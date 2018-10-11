package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamContactNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;

public interface TeamContactServiceRemote {
	TeamContact createTeamContact(TeamContact teamContact) throws TeamContactAlreadyExistsException;
	TeamContact findTeamContact(int id) throws TeamContactNotFoundException;
	TeamContact findTeamContact(String email) throws TeamContactNotFoundException;
}
