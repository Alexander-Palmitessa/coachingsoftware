package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.CardType;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CARD")
public class Card implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CARD_ID")
	private int ID;

	@Column(name = "CARD_TYPE")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "{not.null}")
	private CardType type;
	@ManyToOne
	@JoinColumn(name = "PLAYER_GAMESTATS_ID", nullable = false)
	private PlayerGameStats playerGameStats;
	@Min(value = 0, message = "{min.zero}")
	@Column(name = "CARD_MINUTE")
	private int minute;


	public Card() {
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

	public PlayerGameStats getPlayerGameStats() {
		return playerGameStats;
	}

	public void setPlayerGameStats(PlayerGameStats playerGameStats) {
		this.playerGameStats = playerGameStats;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
}
