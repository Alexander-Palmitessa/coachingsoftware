package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.CardType;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CLUB")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CARD_ID")
	private int ID;
	
	@Column(name = "CARD_TYPE")
	@Enumerated(EnumType.STRING)
	private CardType type;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToMany(mappedBy = "causeCard")
	private Set<ChangeOut> changeOuts;
	
	public Card() {}

	/**
	 * Class constructor
	 * @param type The type of the card, either yellow or red
	 * @param description
	 */
	public Card(CardType type, String description) {
		this.type = type;
		this.description = description;
	}


	public int getID() {
		return ID;
	}


	public CardType getType() {
		return type;
	}


	public void setType(CardType type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
}
