/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.Draft;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "PLAYER")
@NamedQueries({
	@NamedQuery(name = "findPlayer",
			query = "SELECT p FROM Player p WHERE LOWER(p.email) = LOWER(:email)"),
	@NamedQuery(name = "findPlayerByCurrentTeamId",
			query = "SELECT p FROM Player p JOIN p.currentTeams t WHERE t.ID = :teamId"),
	@NamedQuery(name = "findHistoryPlayerByTeamId",
			query = "SELECT p FROM Player p JOIN p.historyTeams t WHERE t.ID = :teamId")
})
public class Player implements Serializable {
	
	private static final long serialVersionUID = -645290838661524061L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID")
    private int ID;
    @Column(name = "FIRST_NAME", nullable = false)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{pattern.letter.space}")
    @NotNull
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{pattern.letter.space}")
    @NotNull
    private String lastName;
    @Embedded
    private Address address;
    @Column(name = "FIRST_EMAIL", unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "{pattern.email}")
    private String email;
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private Role type;
    @Column(name = "DRAFT")
    @Enumerated(EnumType.STRING)
    private Draft draft;
    @Column(name = "POSITION")
    @Enumerated(EnumType.STRING)
    private Position position;
    @Column(name = "PRIVATE_NUMBER")
    @Pattern(regexp = "^[0-9\\s]+$", message = "{pattern.number.space}")
    private String privateNumber;
    @Column(name = "WORKING_NUMBER")
    @Pattern(regexp = "^[0-9\\s]+$", message = "{pattern.number.space}")
    private String workingNumber;
    @Column(name = "MOBILE_NUMBER")
    @Pattern(regexp = "^[0-9\\s]+$", message = "{pattern.number.space}")
    private String mobileNumber;
    @Column(name = "BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Calendar birthdate;
    @Column(name = "SIZE_CM")
    @Min(value = 0, message = "{min.zero}")
    @Max(value = 300, message = "{max.value}")
    private int size;
    @Column(name = "WEIGHT_KG")
    @Min(value = 0, message = "{min.zero}")
    @Max(value = 500, message = "{max.value}")
    private int weight;
    @Embedded
    private Contract contract;
    @JoinColumn(name = "COUNTRY_PERMISSION_ID")
    @ManyToOne
    private Country countryPermission;
    @ManyToMany(mappedBy = "players")
    private Set<Team> teams;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PlayerGameStats> gameStats;
    @OneToMany(mappedBy = "player")
    private Set<PerformanceDiagnostics> performanceDiagnostics;
    @OneToMany(mappedBy = "player")
    private Set<EvaluationTalk> evaluationTalks;
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{pattern.letter.space}")
    private String avatarUrl;
    @OneToMany(mappedBy = "player")
    private Set<ObserveTIPS> observeTIPS;
    @OneToMany(mappedBy = "player")
    private Set<ExtendedTIPS> extendedTIPS;
	@ManyToMany(mappedBy = "currentPlayers")
    private Set<Team> currentTeams;
	@ManyToMany
	@JoinTable(
			name = "PLAYER_TEAM_HISTORY",
			joinColumns = @JoinColumn(name = "PLAYER_ID", referencedColumnName = "PLAYER_ID"),
			inverseJoinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID")
	)
	private Set<Team> historyTeams;


	/**
	 * JPA required default constructor
	 */
	public Player() {

	}
	
	/**
	 * @param firstName first name of player
	 * @param lastName last name of player
	 * @param email email of player
	 * */
	public Player(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public Player(String firstName, String lastName, String email, String mobilePhone, Address address, Position position) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobilePhone;
		this.address = address;
		this.position = position;
	}
	
	// TODO: Player constructors with parameters equals to input form (tbd)

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Role getType() {
		return type;
	}

	public void setType(Role type) {
		this.type = type;
	}

	public Draft getDraft() {
		return draft;
	}

	public void setDraft(Draft draft) {
		this.draft = draft;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getPrivateNumber() {
		return privateNumber;
	}

	public void setPrivateNumber(String privateNumber) {
		this.privateNumber = privateNumber;
	}

	public String getWorkingNumber() {
		return workingNumber;
	}

	public void setWorkingNumber(String workingNumber) {
		this.workingNumber = workingNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getWeight() {
		return weight;
	}

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Country getCountryPermission() {
        return countryPermission;
    }
  
    public void setCountryPermission(Country countryPermission) {
        this.countryPermission = countryPermission;
    }

    public Set<PlayerGameStats> getGameStats() {
        return gameStats;
    }

    public void setGameStats(Set<PlayerGameStats> gameStats) {
        this.gameStats = gameStats;
    }

    public Set<PerformanceDiagnostics> getPerformanceDiagnostics() {
        return performanceDiagnostics;
    }

    public void setPerformanceDiagnostics(Set<PerformanceDiagnostics> performanceDiagnostics) {
        this.performanceDiagnostics = performanceDiagnostics;
    }

    public Set<EvaluationTalk> getEvaluationTalks() {
        return evaluationTalks;
    }

    public void setEvaluationTalks(Set<EvaluationTalk> evaluationTalks) {
        this.evaluationTalks = evaluationTalks;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ObserveTIPS> getObserveTIPS() {
        return observeTIPS;
    }

    public void setObserveTIPS(Set<ObserveTIPS> observeTIPS) {
        this.observeTIPS = observeTIPS;
    }

	public Set<Team> getHistoryTeams() {
		return historyTeams;
	}

	public void setHistoryTeams(Set<Team> historyTeams) {
		this.historyTeams = historyTeams;
	}

	public Set<Team> getCurrentTeams() {
		return currentTeams;
	}

	public void setCurrentTeams(Set<Team> currentTeams) {
		this.currentTeams = currentTeams;
	}
  
  public Set<ExtendedTIPS> getExtendedTIPS() {
      return extendedTIPS;
  }

  public void setExtendedTIPS(Set<ExtendedTIPS> extendedTIPS) {
      this.extendedTIPS = extendedTIPS;
  }
}
