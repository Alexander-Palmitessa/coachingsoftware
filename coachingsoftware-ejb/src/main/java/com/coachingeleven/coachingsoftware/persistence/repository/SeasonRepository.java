package com.coachingeleven.coachingsoftware.persistence.repository;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.coachingeleven.coachingsoftware.persistence.entity.Season;

@Stateless
public class SeasonRepository extends Repository<Season> {
	
	@TransactionAttribute(SUPPORTS)
	public Season find(int id) {
		return super.find(Season.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(SUPPORTS)
	public List<Season> findSeasonsForAssignedTeam(int teamID, int contactID) {
		try {
    		Query query = entityManager.createNativeQuery("SELECT "
	    				+ "s.SEASON_ID, "
	    				+ "s.NAME, "
	    				+ "s.STARTDATE, "
	    				+ "s.ENDDATE "
    				+ "FROM Season AS s "
    				+ "WHERE s.STARTDATE <= "
	    				+ "(SELECT tc.JOINDATE "
						+ "FROM Team_Contact AS tc "
						+ "WHERE tc.TEAM_ID = ? AND tc.LEAVEDATE IS NULL AND tc.CONTACT_ID = ?) "
    				+ "AND s.ENDDATE >= "
	    				+ "(SELECT tc.JOINDATE "
						+ "FROM Team_Contact AS tc "
						+ "WHERE tc.TEAM_ID = ? AND tc.LEAVEDATE IS NULL AND tc.CONTACT_ID = ?)", Season.class);
    		query.setParameter(1, teamID);
    		query.setParameter(2, contactID);
    		query.setParameter(3, teamID);
    		query.setParameter(4, contactID);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
