package com.gsg.demo.config;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.spring.boot.starter.configuration.Ordering;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Ordering.DEFAULT_ORDER + 1)
public class EngineConfig implements ProcessEnginePlugin {

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        // add pre processing configurations here
    }

    @Override
    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        processEngineConfiguration.setHistory(ProcessEngineConfiguration.HISTORY_FULL);
        processEngineConfiguration.setRestrictUserOperationLogToAuthenticatedUsers(false);
        processEngineConfiguration.setGeneralResourceWhitelistPattern(".+");
    }

    @Override
    public void postProcessEngineBuild(ProcessEngine processEngine) {
        // add post processing configurations here
    }
}
