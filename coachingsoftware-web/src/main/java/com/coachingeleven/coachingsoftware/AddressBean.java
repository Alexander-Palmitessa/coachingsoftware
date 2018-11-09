package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Locale;

@Named("addressBean")
@RequestScoped
public class AddressBean {

    @EJB
    private CountryServiceRemote countryService;

    private Address address;

    private Country country;

    @PostConstruct
    public void init(){
        address = new Address();
        country = new Country();
    }

    public Address getAddress() {
        return address;
    }

    public Country getCountry() {
        return country;
    }

    public ISOCountry[] getCountryValue() {
        String[] isoCountries = Locale.getISOCountries();
        ISOCountry[] countryList = new ISOCountry[isoCountries.length];
        for (int i = 0; i < isoCountries.length; i++) {
            countryList[i] = new ISOCountry(isoCountries[i], isoCountries[i]);
        }
        return countryList;
    }

    public Address createAddress() throws CountryNotFounException {
        try {
            country = countryService.createCountry(country);
        }
        catch (Exception e){
            country = countryService.findCountry(country.getName());
        }
        address.setCountry(country);
        return address;
    }

    public static class ISOCountry {
        private String countryValue;
        private String countryLabel;

        private ISOCountry(String countryValue, String countryLabel) {
            this.countryValue = countryValue;
            this.countryLabel = countryLabel;
        }

        public String getCountryValue() {
            return countryValue;
        }

        public void setCountryValue(String countryValue) {
            this.countryValue = countryValue;
        }

        public String getCountryLabel() {
            return countryLabel;
        }

        public void setCountryLabel(String countryLabel) {
            this.countryLabel = countryLabel;
        }
    }
}
