package com.coachingeleven.coachingsoftware.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("dateFormatterBean")
@RequestScoped
public class DateFormatterBean implements Serializable {

	private static final long serialVersionUID = -1512682309204193092L;
	
	private SimpleDateFormat dateFormatter;
	
	@PostConstruct
	public void init() {
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	}
	
	public Calendar getCalendar(String date) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormatter.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		return calendar;
	}

}
