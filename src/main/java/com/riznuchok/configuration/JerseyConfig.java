package com.riznuchok.configuration;

import com.riznuchok.resources.FormulaResources;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(FormulaResources.class);
        register(UserResources.class);
    }
}