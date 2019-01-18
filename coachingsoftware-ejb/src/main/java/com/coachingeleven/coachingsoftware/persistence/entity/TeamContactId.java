package com.coachingeleven.coachingsoftware.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;

public class TeamContactId implements Serializable {

	private static final long serialVersionUID = -9062539073700206859L;
	
	private Integer team;
	private Integer contact;
	private Calendar joinDate;
	
	public TeamContactId() {
		
	}
	
	public TeamContactId(Integer teamId, Integer contactId, Calendar joinDate) {
		team = teamId;
		contact = contactId;
		this.joinDate = joinDate;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((joinDate == null) ? 0 : joinDate.hashCode());
        return result;
    }
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TeamContactId other = (TeamContactId) obj;
        if (team == null) {
            if (other.team != null)
                return false;
        } else if (!team.equals(other.team))
            return false;
        if (contact == null) {
            if (other.contact != null)
                return false;
        } else if (!contact.equals(other.contact))
            return false;
        if (joinDate == null) {
            if (other.joinDate != null)
                return false;
        } else if (!joinDate.equals(other.joinDate))
            return false;
        return true;
    }

}
