package com.hubspot.testapi.resources;

import com.hubspot.testapi.client.HubspotClient;
import com.hubspot.testapi.models.literals.SwaggerLiterals;
import com.hubspot.testapi.services.InvitationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/invitations")
@Api(SwaggerLiterals.SCHEDULER_API)
public class InvitationResource {

    InvitationService invitationService;

    public InvitationResource(HubspotClient hubspotClient) {
        this.invitationService = new InvitationService(hubspotClient);
    }

    @PUT
    @ApiOperation(SwaggerLiterals.SCHEDULER_PUT_INVITATIONS)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendInvitations() {
       return Response.ok(invitationService.eventScheduler()).build();
    }
}
