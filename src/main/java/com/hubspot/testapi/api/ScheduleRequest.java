
package com.hubspot.testapi.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hubspot.testapi.models.Invitation;

import java.util.List;

@SuppressWarnings("unused")
@JsonSerialize
public class ScheduleRequest {

    private List<Invitation> countries;

    public List<Invitation> getCountries() {
        return countries;
    }

    public static class Builder {

        private List<Invitation> countries;

        public ScheduleRequest.Builder withCountries(List<Invitation> countries) {
            this.countries = countries;
            return this;
        }

        public ScheduleRequest build() {
            ScheduleRequest invitations = new ScheduleRequest();
            invitations.countries = countries;
            return invitations;
        }

    }

}
