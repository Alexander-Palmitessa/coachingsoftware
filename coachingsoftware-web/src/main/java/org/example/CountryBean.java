package org.example;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named("countryBean")
@SessionScoped
public class CountryBean implements Serializable {

    @Inject
    CountryServiceRemote countryService;
    private String countryName;

    private void createCountry() throws CountryAlreadyExistsException {
        countryService.createCountry(new Country(this.countryName));
    }

    private String[] getCountryCodes(){
        return Locale.getISOCountries();
    }

    public String getCountryName() {
        return countryName;
    }
}
