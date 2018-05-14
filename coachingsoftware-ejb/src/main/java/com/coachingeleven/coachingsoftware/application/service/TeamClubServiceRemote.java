package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.ClubAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface TeamClubServiceRemote {
    Team createTeam(Team team) throws TeamAlreadyExistsException;
    Club createClub(Club club) throws ClubAlreadyExistsException;
    Team findTeam(String name) throws TeamNotFoundException;
    Club findClub(String name) throws ClubNotFoundException;
    boolean deleteTeam(Team team);
    boolean deleteClub(Club club);
    List<Club> findAllClubs();
    List<Team> findAllTeams();
    Club updateClub(Club club);
}
