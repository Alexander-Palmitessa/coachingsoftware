package com.coachingeleven.coachingsoftware.persistence.repository;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

@Stateless
public class SeasonRepository extends Repository<Season> {
	
	@TransactionAttribute(SUPPORTS)
	public Season find(int id) {
		return super.find(Season.class, id);
	}
	
	@TransactionAttribute(SUPPORTS)
	public List<Season> findSeasonsByTeam(int teamId) {
		try {
			TypedQuery<Season> query = entityManager.createNamedQuery("findSeasonByTeamID", Season.class);
			query.setParameter("teamId", teamId);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	@TransactionAttribute(SUPPORTS)
	public Season addTeamToSeason(int seasonID, Team team) {
		Season season = entityManager.find(Season.class, seasonID);
		season.getTeams().add(team);
		return entityManager.merge(season);
	}
}
