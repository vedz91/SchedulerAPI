
package com.hubspot.testapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class Partner {
    @JsonDeserialize(contentUsing= LocalDateDeserializer.class)
    private List<LocalDate> availableDates;
    private String country;
    private String email;
    private String firstName;
    private String lastName;


    /**
     * Will return list of dates from which a user is available for two days
      */
    @JsonIgnore
    public List<LocalDate> feasibleDates() {
        if(this.availableDates == null || this.availableDates.size() == 0) return new ArrayList<>();
        Collections.sort(this.availableDates);

        List<LocalDate> feasibleDates = new ArrayList<>();
        for(int i = 1; i < this.availableDates.size(); i++) {
            LocalDate prevDate = this.availableDates.get(i-1);
            LocalDate currentDate = this.availableDates.get(i);
            if(Days.daysBetween(prevDate, currentDate).getDays() == 1) {
                feasibleDates.add(prevDate);
            }
        }
        return feasibleDates;
    }

    public List<LocalDate> getAvailableDates() {
        return availableDates;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class Builder {

        private List<LocalDate> availableDates;
        private String country;
        private String email;
        private String firstName;
        private String lastName;

        public Partner.Builder withAvailableDates(List<LocalDate> availableDates) {
            this.availableDates = availableDates;
            return this;
        }

        public Partner.Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Partner.Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Partner.Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Partner.Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Partner build() {
            Partner partner = new Partner();
            partner.availableDates = availableDates;
            partner.country = country;
            partner.email = email;
            partner.firstName = firstName;
            partner.lastName = lastName;
            return partner;
        }

    }

}
