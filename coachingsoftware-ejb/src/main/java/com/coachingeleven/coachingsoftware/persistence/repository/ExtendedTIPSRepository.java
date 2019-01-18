package com.coachingeleven.coachingsoftware.persistence.repository;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.coachingeleven.coachingsoftware.persistence.entity.ExtendedTIPS;

@Stateless
public class ExtendedTIPSRepository extends Repository<ExtendedTIPS> {
	
	@TransactionAttribute(SUPPORTS)
	public ExtendedTIPS find(int id) {
		return super.find(ExtendedTIPS.class, id);
	}

}
