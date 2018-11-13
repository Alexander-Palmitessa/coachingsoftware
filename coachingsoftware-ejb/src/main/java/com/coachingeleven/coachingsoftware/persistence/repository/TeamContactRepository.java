package com.coachingeleven.coachingsoftware.persistence.repository;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;

@Stateless
public class TeamContactRepository extends Repository<TeamContact> {

	@TransactionAttribute(SUPPORTS)
    public List<Team> findTeamsByContact(int contactID) {
    	try {
			TypedQuery<Team> query = entityManager.createNamedQuery("findTeamsByContact", Team.class);
			query.setParameter("contactID", contactID);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
    }
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(SUPPORTS)
    public List<Team> findUnmanagedTeamsByContact(int contactID) {
    	try {
    		Query query = entityManager.createNativeQuery("SELECT t.TEAM_ID, t.TEAM_NAME, t.TEAMLOGOURL, t.TEAMPICTUREURL, t.CLUB_ID FROM Team AS t LEFT OUTER JOIN Team_Contact AS tc ON t.TEAM_ID = tc.TEAM_ID WHERE tc.CONTACT_ID IS NULL OR tc.CONTACT_ID != ?", Team.class);
			query.setParameter(1, contactID);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
    }
	
}
