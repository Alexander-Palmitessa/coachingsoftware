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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.entity.PlayerBean;
import com.coachingeleven.coachingsoftware.entity.TeamBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

@Named(value="uploadBean")
@RequestScoped
public class UploadFileBean {
	
	private Part file;
	
	@Inject
	private TeamBean teamBean;
	@Inject
	private PlayerBean playerBean;
	
	@EJB
	private PlayerServiceRemote playerService;
	
	@Inject
	private LoginBean loginBean;
//	@Inject
//	private CurrentPlayerBean currentPlayerBean;
	
	public void saveTeamPicture() {
		try (InputStream input = file.getInputStream()) {
			// TODO: Filepath must be changed according to the operating system on which the application will be deployed
	        Files.copy(input, new File("C:\\Users\\Elias\\Desktop\\Programs\\glassfish5\\glassfish\\domains\\coachingeleven\\applications\\coachingsoftware-app\\coachingsoftware-web_war\\resources\\images", file.getSubmittedFileName()).toPath());
	        Team currentTeam = loginBean.getLoggedInUserTeam();
	        currentTeam.setTeamPictureURL("images/" + file.getSubmittedFileName());
	        teamBean.update(currentTeam);
	    } catch (IOException e) {
	        // Show faces message?
	    }
	}
	
	public void updatePlayerPicture() {
		try (InputStream input = file.getInputStream()) {
			// TODO: Filepath must be changed according to the operating system on which the application will be deployed
	        Files.copy(input, new File("C:\\Users\\Elias\\Desktop\\Programs\\glassfish5\\glassfish\\domains\\coachingeleven\\applications\\coachingsoftware-app\\coachingsoftware-web_war\\resources\\images", file.getSubmittedFileName()).toPath());
//	        Player currentPlayer = currentPlayerBean.getCurrentPlayer();
//	        currentPlayer.setAvatarUrl("images/" + file.getSubmittedFileName());
//	        playerService.update(currentPlayer);
	    } catch (IOException e) {
	        // Show faces message?
	    }
	}
	
	public void importPlayers() {
		try (InputStream input = file.getInputStream()) {
			try {
			    HSSFWorkbook wb = new HSSFWorkbook(input);
			    HSSFSheet sheet = wb.getSheetAt(0);
			    HSSFRow row;
			    HSSFCell cell;

			    int rows; // No of rows
			    rows = sheet.getPhysicalNumberOfRows();

			    int cols = 0; // No of columns
			    int tmp = 0;

			    // This trick ensures that we get the data properly even if it doesn't start from first few rows
			    for(int i = 0; i < 10 || i < rows; i++) {
			        row = sheet.getRow(i);
			        if(row != null) {
			            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
			            if(tmp > cols) cols = tmp;
			        }
			    }

			    for(int r = 21; r < 22; r++) {
			        row = sheet.getRow(r);
			        if(row != null) {
			            for(int c = 5; c < cols; c++) {
			                cell = row.getCell((short)c);
			                if(cell != null) {
			                	Player player = new Player();
			                	Contact contact = new Contact();
			                	switch (c) {
								case 5:
									contact.setFirstName(cell.getStringCellValue());
									break;
								case 6:
									contact.setLastName(cell.getStringCellValue());
									break;
								default:
									break;
								}
			                	player.setContact(contact);
			                	playerBean.create(player);
			                }
			            }
			        }
			    }
			    wb.close();
			} catch(Exception ioe) {
				// Show faces message?
			}
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
