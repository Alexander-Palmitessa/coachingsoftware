package com.coachingeleven.coachingsoftware;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "languageBean")
@SessionScoped
public class LanguageBean implements Serializable {
	
	private static final long serialVersionUID = 5321640953026718663L;
	
	private static Map<String,Object> countries;
	
	private Locale locale;
	
	static{
		countries = new LinkedHashMap<String,Object>();
		countries.put("English", Locale.ENGLISH);
		countries.put("German", Locale.GERMAN);
	}
	
	@PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	public void changeLanguage(String localeCode){
		//loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet()) {
			if(entry.getValue().toString().equals(localeCode)){
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
				locale = (Locale)entry.getValue();
			}
		}
	}

	public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }
}
