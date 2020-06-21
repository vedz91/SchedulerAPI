
package com.hubspot.testapi.models;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Invitation {

    private Long attendeeCount;
    private List<String> attendees;
    private String name;
    private String startDate;

    public Long getAttendeeCount() {
        return attendeeCount;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public static Invitation buildWithPartners(LocalDate date, String country, List<Partner> partners) {
        return new Builder()
                .withName(country)
                .withStartDate(date != null ? date.toString() : null)
                .withAttendeeCount(Long.valueOf(partners.size()))
                .withAttendees(partners.stream().map(Partner::getEmail).collect(Collectors.toList()))
                .build();
    }

    public static class Builder {

        private Long attendeeCount;
        private List<String> attendees;
        private String name;
        private String startDate;

        public Invitation.Builder withAttendeeCount(Long attendeeCount) {
            this.attendeeCount = attendeeCount;
            return this;
        }

        public Invitation.Builder withAttendees(List<String> attendees) {
            this.attendees = attendees;
            return this;
        }

        public Invitation.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Invitation.Builder withStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Invitation build() {
            Invitation invitation = new Invitation();
            invitation.attendeeCount = attendeeCount;
            invitation.attendees = attendees;
            invitation.name = name;
            invitation.startDate = startDate;
            return invitation;
        }

    }

}
