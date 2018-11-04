package com.coachingeleven.coachingsoftware.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;

public class PlayerTeamId implements Serializable {

	private static final long serialVersionUID = -9062539073700206859L;
	
	private Integer team;
	private Integer player;
	private Calendar joinDate;
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
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
        PlayerTeamId other = (PlayerTeamId) obj;
        if (team == null) {
            if (other.team != null)
                return false;
        } else if (!team.equals(other.team))
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        if (joinDate == null) {
            if (other.joinDate != null)
                return false;
        } else if (!joinDate.equals(other.joinDate))
            return false;
        return true;
    }

}
