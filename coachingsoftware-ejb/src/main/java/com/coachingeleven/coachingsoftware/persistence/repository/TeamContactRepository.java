package com.coachingeleven.coachingsoftware.persistence.repository;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;

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
    public List<Team> findUntrainedTeams() {
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
    					+ "JOIN Contact AS c "
    						+ "ON tc.CONTACT_ID = c.CONTACT_ID "
    					+ "WHERE c.ROLE = ? AND (tc.LEAVEDATE IS NULL OR tc.LEAVEDATE >= ?))", Team.class);
    		query.setParameter(1, Role.TRAINER.toString());
    		query.setParameter(1, new Date(Calendar.getInstance().getTime().getTime()));
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
    }
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(SUPPORTS)
    public List<Player> findUnassingnedPlayers() {
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
				+ "WHERE c.CONTACT_ID "
				+ "NOT IN "
					+ "(SELECT tc.CONTACT_ID "
					+ "FROM Team_Contact AS tc "
					+ "WHERE tc.LEAVEDATE IS NULL OR tc.LEAVEDATE >= ?)", Player.class);
    		query.setParameter(1, new Date(Calendar.getInstance().getTime().getTime()));
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
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(SUPPORTS)
    public List<Team> findAssignedTeams(int contactID) {
    	try {
    		Query query = entityManager.createNativeQuery("SELECT "
	    				+ "tc.TEAM_ID, "
	    				+ "tc.CONTACT_ID, "
	    				+ "tc.JOINDATE, "
	    				+ "tc.LEAVEDATE "
    				+ "FROM Team_Contact AS tc "
    				+ "WHERE tc.CONTACT_ID = ? AND tc.LEAVEDATE IS NULL", Team.class);
			query.setParameter(1, contactID);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
    }
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(SUPPORTS)
    public List<TeamContact> findAssignedTeamContacts(int teamID, int contactID) {
    	try {
    		Query query = entityManager.createNativeQuery("SELECT "
	    				+ "tc.TEAM_ID, "
	    				+ "tc.CONTACT_ID, "
	    				+ "tc.JOINDATE, "
	    				+ "tc.LEAVEDATE "
    				+ "FROM Team_Contact AS tc "
    				+ "WHERE tc.TEAM_ID = ? AND tc.LEAVEDATE IS NULL AND tc.CONTACT_ID = ?", TeamContact.class);
			query.setParameter(1, teamID);
			query.setParameter(2, contactID);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
    }
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(SUPPORTS)
	public List<Player> findPlayersByTeamAndSeason(int teamID, Season season) {
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
				+ "WHERE c.CONTACT_ID "
				+ "IN "
					+ "(SELECT tc.CONTACT_ID "
					+ "FROM Team_Contact AS tc "
					+ "WHERE tc.TEAM_ID = ? AND tc.JOINDATE <= ? AND (tc.LEAVEDATE IS NULL OR tc.LEAVEDATE >= ?))", Player.class);
    		query.setParameter(1, teamID);
    		query.setParameter(2, new Date(season.getEndDate().getTime().getTime()));
    		query.setParameter(3, new Date(season.getStartDate().getTime().getTime()));
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
