package com.coachingeleven.coachingsoftware.application.service;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.ExtendedTIPSNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.ExtendedTIPS;

@Remote
public interface ExtendedTIPSServiceRemote {
	public ExtendedTIPS createExtendedTIPS(ExtendedTIPS extendedTIPS) throws ExtendedTIPSNotFoundException;
}
