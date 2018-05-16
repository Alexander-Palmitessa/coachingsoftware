package org.example;

import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named("addressBean")
@RequestScoped
public class AddressBean implements Serializable {

    @EJB
    private CountryServiceRemote countryService;

    private Address address;

    private com.coachingeleven.coachingsoftware.persistence.entity.Country country;

    @PostConstruct
    public void init(){
        address = new Address();
        country = new com.coachingeleven.coachingsoftware.persistence.entity.Country();
    }

    public Address getAddress() {
        return address;
    }

    public com.coachingeleven.coachingsoftware.persistence.entity.Country getCountry() {
        return country;
    }

    public Country[] getCountryValue() {
        String[] isoCountries = Locale.getISOCountries();
        Country[] countryList = new Country[isoCountries.length];
        for (int i = 0; i < isoCountries.length; i++) {
            countryList[i] = new Country(isoCountries[i], isoCountries[i]);
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

    public static class Country {
        private String countryValue;
        private String countryLabel;

        private Country(String countryValue, String countryLabel) {
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
