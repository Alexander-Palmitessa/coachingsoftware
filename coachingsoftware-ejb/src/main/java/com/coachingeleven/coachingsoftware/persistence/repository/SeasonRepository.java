package com.coachingeleven.coachingsoftware.persistence.repository;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.coachingeleven.coachingsoftware.persistence.entity.Season;

@Stateless
public class SeasonRepository extends Repository<Season> {
	
	@TransactionAttribute(SUPPORTS)
	public Season find(int id) {
		return super.find(Season.class, id);
	}
}
