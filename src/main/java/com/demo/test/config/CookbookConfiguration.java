package com.demo.test.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class CookbookConfiguration extends Configuration {
    @JsonProperty("database")
    @NotNull
    private DbConfig dbConfig;

    public DbConfig getDbConfig() {
        return dbConfig;
    }
}
