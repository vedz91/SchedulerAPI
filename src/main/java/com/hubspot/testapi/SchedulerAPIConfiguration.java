package com.hubspot.testapi;

import com.hubspot.testapi.core.retrofit.RetrofitConfiguration;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class SchedulerAPIConfiguration extends Configuration {
    @JsonProperty("swagger")
    SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty("hubspotApi")
    RetrofitConfiguration hubspotApiConfig;
}
