package com.coachingeleven.coachingsoftware.util;

import com.coachingeleven.coachingsoftware.application.service.StatisticsServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

public class SystemCount {

	//scored goals per system
	private int T442s;
	private int T424s;
	private int T343s;
	private int T433s;
	private int T532s;
	private int T352s;
	private int T451s;
	private int T4231s;
	private int T4321s;
	private int T4141s;
	private int T3313s;
	private int T4222s;
	private int T541s;
	private int T334s;
	private int T460s;

	//goals taken per system
	private int T442t;
	private int T424t;
	private int T343t;
	private int T433t;
	private int T532t;
	private int T352t;
	private int T451t;
	private int T4231t;
	private int T4321t;
	private int T4141t;
	private int T3313t;
	private int T4222t;
	private int T541t;
	private int T334t;
	private int T460t;

	public SystemCount(StatisticsServiceRemote statisticsService, Season currentSeason, Team currentTeam) {
		T442s = statisticsService.get442GoalsScored(currentSeason, currentTeam.getID());
		T424s = statisticsService.get424GoalsScored(currentSeason, currentTeam.getID());
		T343s = statisticsService.get343GoalsScored(currentSeason, currentTeam.getID());
		T433s = statisticsService.get433GoalsScored(currentSeason, currentTeam.getID());
		T532s = statisticsService.get532GoalsScored(currentSeason, currentTeam.getID());
		T352s = statisticsService.get352GoalsScored(currentSeason, currentTeam.getID());
		T451s = statisticsService.get451GoalsScored(currentSeason, currentTeam.getID());
		T4231s = statisticsService.get4231GoalsScored(currentSeason, currentTeam.getID());
		T4321s = statisticsService.get4321GoalsScored(currentSeason, currentTeam.getID());
		T4141s = statisticsService.get4141GoalsScored(currentSeason, currentTeam.getID());
		T3313s = statisticsService.get3313GoalsScored(currentSeason, currentTeam.getID());
		T4222s = statisticsService.get4222GoalsScored(currentSeason, currentTeam.getID());
		T541s = statisticsService.get541GoalsScored(currentSeason, currentTeam.getID());
		T334s = statisticsService.get334GoalsScored(currentSeason, currentTeam.getID());
		T460s = statisticsService.get460GoalsScored(currentSeason, currentTeam.getID());

		T442t = statisticsService.get442GoalsTaken(currentSeason, currentTeam.getID());
		T424t = statisticsService.get424GoalsTaken(currentSeason, currentTeam.getID());
		T343t = statisticsService.get343GoalsTaken(currentSeason, currentTeam.getID());
		T433t = statisticsService.get433GoalsTaken(currentSeason, currentTeam.getID());
		T532t = statisticsService.get532GoalsTaken(currentSeason, currentTeam.getID());
		T352t = statisticsService.get352GoalsTaken(currentSeason, currentTeam.getID());
		T451t = statisticsService.get451GoalsTaken(currentSeason, currentTeam.getID());
		T4231t = statisticsService.get4231GoalsTaken(currentSeason, currentTeam.getID());
		T4321t = statisticsService.get4321GoalsTaken(currentSeason, currentTeam.getID());
		T4141t = statisticsService.get4141GoalsTaken(currentSeason, currentTeam.getID());
		T3313t = statisticsService.get3313GoalsTaken(currentSeason, currentTeam.getID());
		T4222t = statisticsService.get4222GoalsTaken(currentSeason, currentTeam.getID());
		T541t = statisticsService.get541GoalsTaken(currentSeason, currentTeam.getID());
		T334t = statisticsService.get334GoalsTaken(currentSeason, currentTeam.getID());
		T460t = statisticsService.get460GoalsTaken(currentSeason, currentTeam.getID());
	}

	public int getT442s() {
		return T442s;
	}

	public void setT442s(int t442s) {
		T442s = t442s;
	}

	public int getT424s() {
		return T424s;
	}

	public void setT424s(int t424s) {
		T424s = t424s;
	}

	public int getT343s() {
		return T343s;
	}

	public void setT343s(int t343s) {
		T343s = t343s;
	}

	public int getT433s() {
		return T433s;
	}

	public void setT433s(int t433s) {
		T433s = t433s;
	}

	public int getT532s() {
		return T532s;
	}

	public void setT532s(int t532s) {
		T532s = t532s;
	}

	public int getT352s() {
		return T352s;
	}

	public void setT352s(int t352s) {
		T352s = t352s;
	}

	public int getT451s() {
		return T451s;
	}

	public void setT451s(int t451s) {
		T451s = t451s;
	}

	public int getT4231s() {
		return T4231s;
	}

	public void setT4231s(int t4231s) {
		T4231s = t4231s;
	}

	public int getT4321s() {
		return T4321s;
	}

	public void setT4321s(int t4321s) {
		T4321s = t4321s;
	}

	public int getT4141s() {
		return T4141s;
	}

	public void setT4141s(int t4141s) {
		T4141s = t4141s;
	}

	public int getT3313s() {
		return T3313s;
	}

	public void setT3313s(int t3313s) {
		T3313s = t3313s;
	}

	public int getT4222s() {
		return T4222s;
	}

	public void setT4222s(int t4222s) {
		T4222s = t4222s;
	}

	public int getT541s() {
		return T541s;
	}

	public void setT541s(int t541s) {
		T541s = t541s;
	}

	public int getT334s() {
		return T334s;
	}

	public void setT334s(int t334s) {
		T334s = t334s;
	}

	public int getT460s() {
		return T460s;
	}

	public void setT460s(int t460s) {
		T460s = t460s;
	}

	public int getT442t() {
		return T442t;
	}

	public void setT442t(int t442t) {
		T442t = t442t;
	}

	public int getT424t() {
		return T424t;
	}

	public void setT424t(int t424t) {
		T424t = t424t;
	}

	public int getT343t() {
		return T343t;
	}

	public void setT343t(int t343t) {
		T343t = t343t;
	}

	public int getT433t() {
		return T433t;
	}

	public void setT433t(int t433t) {
		T433t = t433t;
	}

	public int getT532t() {
		return T532t;
	}

	public void setT532t(int t532t) {
		T532t = t532t;
	}

	public int getT352t() {
		return T352t;
	}

	public void setT352t(int t352t) {
		T352t = t352t;
	}

	public int getT451t() {
		return T451t;
	}

	public void setT451t(int t451t) {
		T451t = t451t;
	}

	public int getT4231t() {
		return T4231t;
	}

	public void setT4231t(int t4231t) {
		T4231t = t4231t;
	}

	public int getT4321t() {
		return T4321t;
	}

	public void setT4321t(int t4321t) {
		T4321t = t4321t;
	}

	public int getT4141t() {
		return T4141t;
	}

	public void setT4141t(int t4141t) {
		T4141t = t4141t;
	}

	public int getT3313t() {
		return T3313t;
	}

	public void setT3313t(int t3313t) {
		T3313t = t3313t;
	}

	public int getT4222t() {
		return T4222t;
	}

	public void setT4222t(int t4222t) {
		T4222t = t4222t;
	}

	public int getT541t() {
		return T541t;
	}

	public void setT541t(int t541t) {
		T541t = t541t;
	}

	public int getT334t() {
		return T334t;
	}

	public void setT334t(int t334t) {
		T334t = t334t;
	}

	public int getT460t() {
		return T460t;
	}

	public void setT460t(int t460t) {
		T460t = t460t;
	}
}
