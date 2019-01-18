package com.coachingeleven.coachingsoftware.util;

import com.coachingeleven.coachingsoftware.application.service.StatisticsServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

public class BlockCount {
	private int b0To15s;
	private int b15To30s;
	private int b30To45s;
	private int b45To60s;
	private int b60To75s;
	private int b75To90s;

	private int b0To15t;
	private int b15To30t;
	private int b30To45t;
	private int b45To60t;
	private int b60To75t;
	private int b75To90t;


	public BlockCount(StatisticsServiceRemote statisticsService, Season season, Team currentTeam) {
		b0To15s = statisticsService.getBlock0To15Scored(season, currentTeam.getID());
		b15To30s = statisticsService.getBlock15To30Scored(season, currentTeam.getID());
		b30To45s = statisticsService.getBlock30to45Scored(season, currentTeam.getID());
		b45To60s = statisticsService.getBlock45to60Scored(season, currentTeam.getID());
		b60To75s = statisticsService.getBlock60to75Scored(season, currentTeam.getID());
		b75To90s = statisticsService.getBlock75to90Scored(season, currentTeam.getID());

		b0To15t = statisticsService.getBlock0To15Taken(season, currentTeam.getID());
		b15To30t = statisticsService.getBlock15To30Taken(season, currentTeam.getID());
		b30To45t = statisticsService.getBlock30to45Taken(season, currentTeam.getID());
		b45To60t = statisticsService.getBlock45to60Taken(season, currentTeam.getID());
		b60To75t = statisticsService.getBlock60to75Taken(season, currentTeam.getID());
		b75To90t = statisticsService.getBlock75to90Taken(season, currentTeam.getID());
	}

	public int getB0To15s() {
		return b0To15s;
	}

	public void setB0To15s(int b0To15s) {
		this.b0To15s = b0To15s;
	}

	public int getB15To30s() {
		return b15To30s;
	}

	public void setB15To30s(int b15To30s) {
		this.b15To30s = b15To30s;
	}

	public int getB30To45s() {
		return b30To45s;
	}

	public void setB30To45s(int b30To45s) {
		this.b30To45s = b30To45s;
	}

	public int getB45To60s() {
		return b45To60s;
	}

	public void setB45To60s(int b45To60s) {
		this.b45To60s = b45To60s;
	}

	public int getB60To75s() {
		return b60To75s;
	}

	public void setB60To75s(int b60To75s) {
		this.b60To75s = b60To75s;
	}

	public int getB75To90s() {
		return b75To90s;
	}

	public void setB75To90s(int b75To90s) {
		this.b75To90s = b75To90s;
	}

	public int getB0To15t() {
		return b0To15t;
	}

	public void setB0To15t(int b0To15t) {
		this.b0To15t = b0To15t;
	}

	public int getB15To30t() {
		return b15To30t;
	}

	public void setB15To30t(int b15To30t) {
		this.b15To30t = b15To30t;
	}

	public int getB30To45t() {
		return b30To45t;
	}

	public void setB30To45t(int b30To45t) {
		this.b30To45t = b30To45t;
	}

	public int getB45To60t() {
		return b45To60t;
	}

	public void setB45To60t(int b45To60t) {
		this.b45To60t = b45To60t;
	}

	public int getB60To75t() {
		return b60To75t;
	}

	public void setB60To75t(int b60To75t) {
		this.b60To75t = b60To75t;
	}

	public int getB75To90t() {
		return b75To90t;
	}

	public void setB75To90t(int b75To90t) {
		this.b75To90t = b75To90t;
	}
}
