package com.hubspot.testapi;

import com.hubspot.testapi.client.HubspotClient;
import com.hubspot.testapi.core.exception.mapper.HubspotAPIExceptionMapper;
import com.hubspot.testapi.core.retrofit.RetrofitClient;
import com.hubspot.testapi.health.ConfigurationHealthCheck;
import com.hubspot.testapi.resources.InvitationResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SchedulerAPIApplication extends Application<SchedulerAPIConfiguration> {

    private RetrofitClient<HubspotClient> hubspotRetrofitClient = RetrofitClient.retrofitClient(HubspotClient.class);

    public static void main(final String[] args) throws Exception {
        new SchedulerAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "SchedulerAPI";
    }

    @Override
    public void initialize(final Bootstrap<SchedulerAPIConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<SchedulerAPIConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(final SchedulerAPIConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final SchedulerAPIConfiguration configuration,
                    final Environment environment) {
        // HEALTH Check
        environment.healthChecks()
                .register("ConfigurationHealthCheck", new ConfigurationHealthCheck(new Object()));

        //Exception Mapper
        environment.jersey().register(new HubspotAPIExceptionMapper());

        //Resources
        environment.jersey().register(new InvitationResource(hubspotRetrofitClient.get(configuration.hubspotApiConfig)));
    }

}
