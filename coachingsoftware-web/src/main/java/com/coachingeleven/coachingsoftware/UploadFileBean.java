package com.coachingeleven.coachingsoftware;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

@Named(value="uploadBean")
@RequestScoped
public class UploadFileBean {
	
	private Part file;
	
	@EJB
    private TeamClubServiceRemote teamClubService;
	@EJB
	private PlayerServiceRemote playerService;
	
	@Inject
	private LoginBean loginBean;
	@Inject
	private CurrentPlayerBean currentPlayerBean;
	
	public void saveTeamPicture() {
		try (InputStream input = file.getInputStream()) {
			// TODO: Filepath must be changed according to the operating system on which the application will be deployed
	        Files.copy(input, new File("C:\\Users\\Elias\\Desktop\\Programs\\glassfish5\\glassfish\\domains\\coachingeleven\\applications\\coachingsoftware-app\\coachingsoftware-web_war\\resources\\images", file.getSubmittedFileName()).toPath());
	        Team currentTeam = loginBean.getLoggedInUser().getTeam();
	        currentTeam.setTeamPictureURL("images/" + file.getSubmittedFileName());
	        teamClubService.updateTeam(currentTeam);
	    } catch (IOException e) {
	        // Show faces message?
	    }
	}
	
	public void updatePlayerPicture() {
		try (InputStream input = file.getInputStream()) {
			// TODO: Filepath must be changed according to the operating system on which the application will be deployed
	        Files.copy(input, new File("C:\\Users\\Elias\\Desktop\\Programs\\glassfish5\\glassfish\\domains\\coachingeleven\\applications\\coachingsoftware-app\\coachingsoftware-web_war\\resources\\images", file.getSubmittedFileName()).toPath());
	        Player currentPlayer = currentPlayerBean.getCurrentPlayer();
	        currentPlayer.setAvatarUrl("images/" + file.getSubmittedFileName());
	        playerService.update(currentPlayer);
	    } catch (IOException e) {
	        // Show faces message?
	    }
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}
