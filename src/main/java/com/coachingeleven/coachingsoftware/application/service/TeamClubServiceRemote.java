package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

import javax.ejb.Remote;

@Remote
public interface TeamClubServiceRemote {
    Team createTeam(Team team);
    Club createClub(Club club);
    Team findTeam(String name) throws TeamNotFoundException;
    Club findClub(String name) throws ClubNotFoundException;
    boolean deleteTeam(Team team);
    boolean deleteClub(Club club);
}
