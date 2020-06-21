package com.hubspot.testapi.health;

import com.codahale.metrics.health.HealthCheck;

public class ConfigurationHealthCheck extends HealthCheck {
    Object serverConfiguration;

    public ConfigurationHealthCheck(Object serverConfiguration) {
        this.serverConfiguration = serverConfiguration;
    }

    @Override
    protected Result check() throws Exception {
        return this.serverConfiguration != null ? Result.healthy() : Result.unhealthy("No Server Configuration");
    }
}
