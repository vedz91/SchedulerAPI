package com.hubspot.testapi.resources;

import com.hubspot.testapi.client.HubspotClient;
import com.hubspot.testapi.services.InvitationService;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/invitations")
public class InvitationResource {

    InvitationService invitationService;

    public InvitationResource(HubspotClient hubspotClient) {
        this.invitationService = new InvitationService(hubspotClient);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendInvitations() {
       return Response.ok(invitationService.eventScheduler()).build();
    }
}
