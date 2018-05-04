package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.repository.ClubRepository;
import com.coachingeleven.coachingsoftware.persistence.repository.TeamRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@LocalBean
@Stateless(name = "TeamClubService")
@TransactionAttribute(REQUIRED)
public class TeamClubService implements TeamClubServiceRemote {

    private static final Logger logger = Logger.getLogger(TeamClubService.class.getName());

    @EJB
    private ClubRepository clubRepository;
    @EJB
    private TeamRepository teamRepository;


    @Override
    public Team createTeam(Team team) {
        return teamRepository.persist(team);
    }

    @Override
    public Club createClub(Club club) {
        return clubRepository.persist(club);
    }

    @Override
    public Team findTeam(String name) throws TeamNotFoundException {
        logger.log(Level.INFO, "Finding team with name " + name);
        Team team = teamRepository.find(name);
        if (team == null) {
            logger.log(Level.INFO, "Team not found");
            throw new TeamNotFoundException();
        }
        return team;
    }

    @Override
    public Club findClub(String name) throws ClubNotFoundException {
        logger.log(Level.INFO, "Finding club with name " + name);
        Club club = clubRepository.find(name);
        if (club == null) {
            logger.log(Level.INFO, "Team not found");
            throw new ClubNotFoundException();
        }
        return club;
    }

    @Override
    public boolean deleteTeam(Team team) {
        return teamRepository.delete(Team.class, team.getID());
    }

    @Override
    public boolean deleteClub(Club club) {
        return clubRepository.delete(Club.class, club.getID());
    }

    @Override
    public Club addTeamToClub(Club club, Team team) {
        if(!club.getTeams().contains(team)){
            club.getTeams().add(team);
            clubRepository.update(club);
        }
        return club;
    }
}
