package com.coachingeleven.coachingsoftware;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.coachingeleven.coachingsoftware.application.exception.ContactNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ContactServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.entity.PlayerBean;
import com.coachingeleven.coachingsoftware.entity.TeamBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

@Named(value="uploadBean")
@RequestScoped
public class UploadFileBean {
	
	private static final Logger logger = Logger.getLogger(UploadFileBean.class.getName());
	
	private Part file;
	
	@Inject
	private TeamBean teamBean;
	@Inject
	private PlayerBean playerBean;
	
	@EJB
	private PlayerServiceRemote playerService;
	@EJB
	private ContactServiceRemote contactService;
	
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
			// TODO: Needs to be specified
	    } catch (IOException e) {
	        // Show faces message?
	    }
	}
	
	public void importPlayers() {
		logger.warning("Importing players");
		if(file != null) {
			try (InputStream input = file.getInputStream()) {
				XSSFWorkbook wb = new XSSFWorkbook(input);
			    XSSFSheet sheet = wb.getSheetAt(0);
			    XSSFRow row;
			    XSSFCell cell;
			    
			    logger.warning("HSSFWorkbook loaded");

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

			    for(int r = 0; r < rows; r++) {
			    	try {
			    		row = sheet.getRow(r);
				        if(row != null) {
				        	Contact contact = new Contact();
		            		Address address = new Address();
		                	Player player = new Player();
		                	boolean isPlayer = false;
				            for(int c = 0; c < cols; c++) {
				                cell = row.getCell((short)c);
				                if(cell != null) {
				                	switch (c) {
				                	case 1:
				                		if(cell.getStringCellValue().equals("Kaderspieler") || cell.getStringCellValue().equals("Sichtung")) {
				                			isPlayer = true;
				                		}
				                		break;
									case 4:
										if(isPlayer) {
											contact.setFirstName(cell.getStringCellValue());
										}
										break;
									case 5:
										if(isPlayer) {
											contact.setLastName(cell.getStringCellValue());
										}
										break;
									case 9:
										if(isPlayer) {
											address.setStreet(cell.getStringCellValue());
										}
										break;
									case 10:
										if(isPlayer) {
											if(cell.getCellType().equals(CellType.STRING)) {
												address.setZip(Integer.parseInt(cell.getStringCellValue()));
											} else if(cell.getCellType().equals(CellType.NUMERIC)) {
												address.setZip((int) cell.getNumericCellValue());
											}
										}
										break;
									case 11:
										if(isPlayer) {
											address.setCity(cell.getStringCellValue());
										}
										break;
									case 15:
										if(isPlayer) {
											logger.warning("Email: " + cell.getStringCellValue());
											contact.setEmail(cell.getStringCellValue());
										}
										break;
									default:
										break;
									}
				                }
				            }
				            if(isPlayer) {
				            	contact.setAddress(address);
			                	player.setContact(contact);
			                	try {
			                		if(contact.getEmail() != null) {
			                			contactService.findContact(contact.getEmail());
			                		} else {
			                			logger.warning("Could not import player '" + contact.getFirstName() + " " + contact.getLastName() +  "' because player has no EMAIL!");
			                		}
			                	} catch (ContactNotFoundException ce) {
			                		playerBean.create(player);
			                	}
				            }
				        }
			    	} catch (EJBTransactionRolledbackException ce) {
			    		logger.warning("Could not import player: " + ce.getMessage());
			    	} catch (NumberFormatException ne) {
			    		logger.warning("Could not import player: " + ne.getMessage());
			    	}
			    }
			    wb.close();
		    } catch (IOException e) {
		        logger.warning("Could not import players: " + e.getMessage());
		    }
		}
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}
