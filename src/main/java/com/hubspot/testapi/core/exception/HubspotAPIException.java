package com.hubspot.testapi.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class HubspotAPIException  extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(HubspotAPIException.class);
    private static final long serialVersionUID = -6703944242501662760L;


    private Response.Status statusCode;


    public HubspotAPIException(final String message) {
        super(String.format("HubspotAPIException: %s", message));
        this.statusCode = Response.Status.INTERNAL_SERVER_ERROR;
        LOGGER.error("HubspotAPIException: " + message);
    }

    public HubspotAPIException(final String message, final Response.Status code) {
        super(String.format("HubspotAPIException: %s", message));
        this.statusCode = code;
        LOGGER.error("HubspotAPIException: " + message);
    }

    public Response.Status getStatusCode() {
        return statusCode;
    }
}
