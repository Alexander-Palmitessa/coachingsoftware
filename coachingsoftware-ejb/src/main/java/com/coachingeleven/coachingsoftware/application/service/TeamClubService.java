package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.ClubAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.repository.ClubRepository;
import com.coachingeleven.coachingsoftware.persistence.repository.TeamRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import java.util.List;
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
    public Team createTeam(Team team) throws TeamAlreadyExistsException {
        logger.log(Level.INFO, "Creating team with id ''{0}''", team.getID());
        if(teamRepository.find(Team.class, team.getID()) != null){
            logger.log(Level.INFO, "Team with id " + team.getID()+ " already exists");
            throw new TeamAlreadyExistsException();
        }
        return teamRepository.persist(team);
    }

    @Override
    public Club createClub(Club club) throws ClubAlreadyExistsException {
        logger.log(Level.INFO, "Creating club with name " + club.getName() + " and id ''{0}''", club.getID());
        if(clubRepository.find(Club.class, club.getID()) != null){
            logger.log(Level.INFO, "Club with id " + club.getID()+ " already exists");
            throw new ClubAlreadyExistsException();
        }
        else if(clubRepository.find(club.getName()) != null){
            logger.log(Level.INFO, "Club with name " + club.getName() + " already exists");
            throw new ClubAlreadyExistsException();
        }
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
    public List<Club> findAllClubs() {
        return clubRepository.findAll(Club.class);
    }

    @Override
    public List<Team> findAllTeams() {
        return teamRepository.findAll(Team.class);
    }

    @Override
    public Club updateClub(Club club) {
        return clubRepository.update(club);
    }
}
