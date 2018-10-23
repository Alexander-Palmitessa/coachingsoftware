package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.System;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table(name = "GAME_SYS")
public class GameSystem implements Serializable {

	@Id
	@Column(name = "GAME_SYS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@ManyToOne
	@JoinColumn(name = "GAME_ID", nullable = false)
	private Game game;
	@Enumerated(EnumType.STRING)
	private System system;
	@Column(name = "START_MINUTE")
	@Min(value = 0, message = "{min.zero}")
	private int startMinute;
	@Column(name = "END_MINUTE")
	@Min(value = 0, message = "{min.zero}")
	private int endMinute;

	public GameSystem() {
	}

	public int getID() {
		return ID;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public int getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

	public int getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}
}
