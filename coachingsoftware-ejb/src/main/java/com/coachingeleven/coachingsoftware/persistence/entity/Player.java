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

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable, Comparator<Player> {
	
	private static final long serialVersionUID = -645290838661524061L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID")
    private int ID;
    @Column(name = "DRAFT")
    @Enumerated(EnumType.STRING)
    private Draft draft;
    @Column(name = "POSITION")
    @Enumerated(EnumType.STRING)
    private Position position;
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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PlayerGameStats> gameStats;
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<PerformanceDiagnostics> performanceDiagnostics;
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<EvaluationTalk> evaluationTalks;
    @OneToMany(mappedBy = "player")
    private Set<ObserveTIPS> observeTIPS;
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<ExtendedTIPS> extendedTIPS;
	@OneToMany(mappedBy = "player")
    private Set<LineUpPlayer> lineUps;
	@JoinColumn(name = "CONTACT_ID", unique = true)
	@OneToOne
	private Contact contact;


	/**
	 * JPA required default constructor
	 */
	public Player() {

	}
	
	public Player(Position position) {
		this.position = position;
	}
	
	// TODO: Player constructors with parameters equals to input form (tbd)

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
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

    public Set<ObserveTIPS> getObserveTIPS() {
        return observeTIPS;
    }

    public void setObserveTIPS(Set<ObserveTIPS> observeTIPS) {
        this.observeTIPS = observeTIPS;
    }
  
	public Set<ExtendedTIPS> getExtendedTIPS() {
		return extendedTIPS;
	}

	public void setExtendedTIPS(Set<ExtendedTIPS> extendedTIPS) {
		this.extendedTIPS = extendedTIPS;
	}

	public Set<LineUpPlayer> getLineUps() {
		return lineUps;
	}

	public void setLineUps(Set<LineUpPlayer> lineUps) {
		this.lineUps = lineUps;
	}

	public void addEvaluationTalk (EvaluationTalk evaluationTalk){
		this.evaluationTalks.add(evaluationTalk);
	}

	public void addExtendedTIPS(ExtendedTIPS ExtendedTIPS) {
		this.extendedTIPS.add(ExtendedTIPS);
	}

	public void addPerformanceDiagnostics(PerformanceDiagnostics performanceDiagnostics) {
		this.performanceDiagnostics.add(performanceDiagnostics);
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public int compare(Player p1, Player p2) {
		return p1.contact.getLastName().compareTo(p2.contact.getLastName());
	}
}
