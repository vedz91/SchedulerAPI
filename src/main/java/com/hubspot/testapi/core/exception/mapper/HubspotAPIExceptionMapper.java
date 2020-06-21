package com.hubspot.testapi.core.exception.mapper;

import com.hubspot.testapi.core.exception.HubspotAPIException;
import io.dropwizard.jersey.errors.ErrorMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class HubspotAPIExceptionMapper implements ExceptionMapper<HubspotAPIException> {
    @Override
    public Response toResponse(HubspotAPIException e) {
        return Response.status(e.getStatusCode())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new ErrorMessage(e.getStatusCode().getStatusCode(), e.getMessage()))
                .build();
    }
}
