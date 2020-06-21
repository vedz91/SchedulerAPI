package com.hubspot.testapi;

import com.hubspot.testapi.core.retrofit.RetrofitConfiguration;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class SchedulerAPIConfiguration extends Configuration {
    // TODO: implement service configuration
    @JsonProperty("hubspotApi")
    RetrofitConfiguration hubspotApiConfig;
}
