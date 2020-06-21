package com.hubspot.testapi;

import com.hubspot.testapi.client.HubspotClient;
import com.hubspot.testapi.core.exception.mapper.HubspotAPIExceptionMapper;
import com.hubspot.testapi.core.retrofit.RetrofitClient;
import com.hubspot.testapi.resources.InvitationResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: application initialization
    }

    @Override
    public void run(final SchedulerAPIConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application

        environment.jersey().register(new HubspotAPIExceptionMapper());
        environment.jersey().register(new InvitationResource(hubspotRetrofitClient.get(configuration.hubspotApiConfig)));
    }

}
