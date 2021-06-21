package com.demo.test.health;

import com.codahale.metrics.health.HealthCheck;

public class CookbookHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
