package com.demo.test;

import com.demo.test.config.CookbookConfiguration;
import com.demo.test.config.DbDataSource;
import com.demo.test.health.CookbookHealthCheck;
import com.demo.test.util.JsonUtils;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookbookApplication extends Application<CookbookConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookbookApplication.class);

    public static void main(String[] args) throws Exception {
        new CookbookApplication().run(args);
    }

    @Override
    public String getName() {
        return "CookbookService";
    }

    @Override
    public void initialize(Bootstrap<CookbookConfiguration> bootstrap) {
        // associate .yml files when application starting
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(CookbookConfiguration configuration, Environment environment) {
        LOGGER.info("configuration=" + JsonUtils.toJson(configuration.getDbConfig()));
        // init datasource here
        new DbDataSource(configuration.getDbConfig());

        // scan resources
        environment.jersey().packages("com.demo.test.resource");

        // register health check
        CookbookHealthCheck healthCheck = new CookbookHealthCheck();
        environment.healthChecks().register("health", healthCheck);
    }
}
