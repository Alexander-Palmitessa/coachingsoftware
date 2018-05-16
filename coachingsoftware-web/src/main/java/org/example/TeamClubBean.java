package org.example;

import com.coachingeleven.coachingsoftware.application.exception.ClubAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.ClubNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named("teamClubBean")
@RequestScoped
public class TeamClubBean {

    @EJB
    private TeamClubServiceRemote teamClubService;

    private Team team;
    private Club club;
    private List<Club> clubs;
    private String selectedClubName;

    @PostConstruct
    public void init() {
        team = new Team();
        club = new Club();
        clubs = teamClubService.findAllClubs();
    }

    public Club createClub() throws ClubNotFoundException {
        try {
            club = teamClubService.createClub(club);
        } catch (ClubAlreadyExistsException e) {
            club = teamClubService.findClub(club.getName());
        }
        return club;
    }

    public Team createTeam() throws ClubNotFoundException, TeamAlreadyExistsException {
        club = teamClubService.findClub(selectedClubName);
        team.setClub(club);
        return teamClubService.createTeam(team);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public String getSelectedClubName() {
        return selectedClubName;
    }

    public void setSelectedClubName(String selectedClubName) {
        this.selectedClubName = selectedClubName;
    }
}
