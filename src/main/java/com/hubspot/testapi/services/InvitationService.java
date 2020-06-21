package com.hubspot.testapi.services;

import com.hubspot.testapi.api.ScheduleRequest;
import com.hubspot.testapi.api.PartnersListResponse;
import com.hubspot.testapi.api.ScheduleResponse;
import com.hubspot.testapi.client.HubspotClient;
import com.hubspot.testapi.core.exception.HubspotAPIException;
import com.hubspot.testapi.models.Invitation;
import com.hubspot.testapi.models.Partner;
import org.joda.time.LocalDate;
import retrofit2.Call;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

public class InvitationService {

    HubspotClient hubspotClient;

    public InvitationService(HubspotClient hubspotClient) {
        this.hubspotClient = hubspotClient;
    }

    public ScheduleRequest eventScheduler() {
        PartnersListResponse partnersListResponse = fetchPartners();
        ScheduleRequest invitations = prepareInvitations(partnersListResponse);
        return updateInvitations(invitations);
    }

    /**
     * fetches list of partners from Hubspot API
     *
     * @return
     */
    private PartnersListResponse fetchPartners() {
        try {
            Call<PartnersListResponse> availabilityResponse = this.hubspotClient.getAvailability();
            return availabilityResponse.execute().body();
        } catch (IOException e) {
            throw new HubspotAPIException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates availability of partners per country on Hubspot API
     *
     * @param invitations
     * @return
     */
    private ScheduleRequest updateInvitations(ScheduleRequest invitations) {
        try {
            Call<ScheduleResponse> availabilityResponse = this.hubspotClient.updateInvitation(invitations);
            ScheduleResponse scheduleResponse = availabilityResponse.execute().body();
            if (scheduleResponse == null || (scheduleResponse.getStatus() != null && scheduleResponse.getStatus().equalsIgnoreCase("error"))) {
                throw new HubspotAPIException("Exception from Hubspot API", Response.Status.EXPECTATION_FAILED);
            }
            return invitations;
        } catch (IOException e) {
            throw new HubspotAPIException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find the availability date for the maximum partners per country
     * @param partnersListResponse
     * @return
     */
    public ScheduleRequest prepareInvitations(PartnersListResponse partnersListResponse) {

        //Step 1: divide the partners based on the country
        Map<String, List<Partner>> countryMap = new HashMap<>();
        for (Partner partner : partnersListResponse.getPartners()) {
            List<Partner> countryPartners = countryMap.getOrDefault(partner.getCountry(), new ArrayList<>());
            countryPartners.add(partner);
            countryMap.put(partner.getCountry(), countryPartners);
        }

        //Step 2: Loop through each countries
        List<Invitation> invitations = new ArrayList<>();
        for (Map.Entry<String, List<Partner>> entrySet : countryMap.entrySet()) {
            List<Partner> partnersInACountry = entrySet.getValue();
            Map<LocalDate, List<Partner>> partnerByFeasibleDatesMap = new HashMap<>();
            Integer maxCount = 0;
            LocalDate maxDate = null;
            //Step 2a: for each partners look for feasible date
            for (Partner partner : partnersInACountry) {
                for (LocalDate availableDate : partner.feasibleDates()) {
                    List<Partner> partners = partnerByFeasibleDatesMap.getOrDefault(availableDate, new ArrayList<>());
                    partners.add(partner);
                    if (partners.size() > maxCount) {
                        maxCount = partners.size();
                        maxDate = availableDate;
                    }
                    partnerByFeasibleDatesMap.put(availableDate, partners);
                }
            }
            invitations.add(Invitation.buildWithPartners(Optional.ofNullable(maxDate), entrySet.getKey(), partnerByFeasibleDatesMap.getOrDefault(maxDate, new ArrayList<>())));
        }

        return new ScheduleRequest.Builder()
                .withCountries(invitations)
                .build();
    }

}
