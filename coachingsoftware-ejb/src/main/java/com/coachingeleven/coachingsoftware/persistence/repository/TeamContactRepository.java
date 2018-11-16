package com.coachingeleven.coachingsoftware.persistence.repository;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
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
    public List<Team> findUnassingnedTeamsByContact(int trainerContactID) {
    	try {
    		Query query = entityManager.createNativeQuery("SELECT "
	    				+ "t.TEAM_ID, "
	    				+ "t.TEAM_NAME, "
	    				+ "t.TEAMLOGOURL, "
	    				+ "t.TEAMPICTUREURL, "
	    				+ "t.CLUB_ID "
    				+ "FROM Team AS t "
    				+ "WHERE t.TEAM_ID "
    				+ "NOT IN "
    					+ "(SELECT tc.TEAM_ID "
    					+ "FROM Team_Contact AS tc "
    					+ "WHERE tc.CONTACT_ID = ?)", Team.class);
			query.setParameter(1, trainerContactID);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
    }
	
	@TransactionAttribute(SUPPORTS)
    public List<Contact> findContactsByTeam(int teamID) {
    	try {
			TypedQuery<Contact> query = entityManager.createNamedQuery("findContactsByTeam", Contact.class);
			query.setParameter("teamID", teamID);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
    }
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(SUPPORTS)
    public List<Player> findPlayersByTeam(int teamID) {
    	try {
    		Query query = entityManager.createNativeQuery("SELECT "
	    				+ "p.PLAYER_ID, "
	    				+ "p.DRAFT, "
	    				+ "p.POSITION, "
	    				+ "p.SIZE_CM, "
	    				+ "p.WEIGHT_KG, "
	    				+ "p.CONTRACT_COMMENT, "
	    				+ "p.CONTRACT_END, "
	    				+ "p.CONTRACT_START, "
	    				+ "p.COUNTRY_PERMISSION_ID, "
	    				+ "p.CONTACT_ID "
    				+ "FROM Player AS p "
    				+ "JOIN Contact AS c "
    					+ "ON p.CONTACT_ID = c.CONTACT_ID "
    				+ "JOIN Team_Contact tc "
    					+ "ON tc.CONTACT_ID = c.CONTACT_ID "
    				+ "WHERE tc.TEAM_ID = ?", Player.class);
			query.setParameter(1, teamID);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
    }
	
}
