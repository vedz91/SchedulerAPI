package com.hubspot.testapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.hubspot.testapi.api.ScheduleRequest;
import com.hubspot.testapi.api.PartnersListResponse;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InvitationServiceTest {

    @Test
    public void test() {
    }

    @Test
    public void testPrepareInvitation() throws IOException {
        ObjectMapper mapper = Jackson.newObjectMapper();
        PartnersListResponse ps = mapper.readValue(Resources.toString(Resources.getResource("stubs/partners_response.json"), StandardCharsets.UTF_8), PartnersListResponse.class);

        InvitationService service = new InvitationService(null);
        ScheduleRequest invitations = service.prepareInvitations(ps);

        assertTrue(invitations != null);

        //validate US
        invitations.getCountries()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase("United States"))
                .forEach(x -> {
                    Long l = 0l;
                    assertEquals(l, x.getAttendeeCount());
                    assertEquals(0, x.getAttendees().size());
                    assertNull(x.getStartDate());
                });

        //validate Ireland
        invitations.getCountries()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase("Ireland"))
                .forEach(x -> {
                    Long l = 1l;
                    assertEquals(l, x.getAttendeeCount());
                    assertEquals(1, x.getAttendees().size());
                    assertEquals("cbrenna@hubspotpartners.com", x.getAttendees().get(0));
                    assertNotNull(x.getStartDate());
                    assertEquals("2017-04-29", x.getStartDate());
                });

        //validate Spain
        List<String> spanishPartner = Arrays.asList("omajica@hubspotpartners.com", "taffelt@hubspotpartners.com", "tmozie@hubspotpartners.com");
        invitations.getCountries()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase("Spain"))
                .forEach(x -> {
                    Long l = 3l;
                    assertEquals(l, x.getAttendeeCount());
                    assertEquals(3, x.getAttendees().size());
                    x.getAttendees()
                            .stream()
                            .forEach(attendee -> assertTrue(spanishPartner.contains(attendee)));
                    assertNotNull(x.getStartDate());
                    assertEquals("2017-04-28", x.getStartDate());
                });
    }
}