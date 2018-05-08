package org.example;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Locale;

@Named("addressBean")
@SessionScoped
public class AddressBean implements Serializable {

    @EJB
    private CountryServiceRemote countryService; //Maybe wrong

    @Pattern(regexp = "[A-Za-z]")
    @NotNull
    private String street;
    @Pattern(regexp = "^[A-Za-z0-9]")
    @NotNull
    private String streetNr;
    @Pattern(regexp = "[A-Za-z]")
    @NotNull
    private String city;
    private int zip;

    private String country;
    private Country[] countryList;
    private String selectedCountry;


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Country[] getCountryValue() {
        String[] isoCountries = Locale.getISOCountries();
        countryList = new Country[isoCountries.length];
        for(int i = 0; i < isoCountries.length; i++){
            countryList[i] = new Country(isoCountries[i], isoCountries[i]);
        }
        return countryList;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
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

    public Address createAddress() throws CountryNotFounException, CountryAlreadyExistsException {
        com.coachingeleven.coachingsoftware.persistence.entity.Country country =
                countryService.findCountry(selectedCountry);
        if(country == null){
            country = countryService.createCountry(new com.coachingeleven.coachingsoftware.persistence.entity.Country(selectedCountry));
        }
        return new Address(city, street, streetNr, zip, country);
    }
}
