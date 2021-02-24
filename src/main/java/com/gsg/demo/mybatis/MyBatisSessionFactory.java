package com.gsg.demo.mybatis;

import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.interceptor.CommandContextInterceptor;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;
import org.camunda.bpm.engine.impl.interceptor.LogInterceptor;
import org.camunda.bpm.engine.impl.util.ReflectUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyBatisSessionFactory extends StandaloneProcessEngineConfiguration {

    private String resourceName;

    protected void init() {
        throw new IllegalArgumentException("Normal 'init' on process engine only used for extended MyBatis mappings is not allowed.");
    }

    public void initFromProcessEngineConfiguration(ProcessEngineConfigurationImpl processEngineConfiguration,
                                                   String resourceName) {
        this.resourceName = resourceName;

        setDataSource(processEngineConfiguration.getDataSource());
        initDataSource();
        initCommandContextFactory();
        initTransactionFactory();
        initTransactionContextFactory();
        initCommandExecutors();
        initSqlSessionFactory();
        initIncidentHandlers();
        initIdentityProviderSessionFactory();
        initSessionFactories();
    }

    @Override
    protected InputStream getMyBatisXmlConfigurationSteam() {
        return ReflectUtil.getResourceAsStream(resourceName);
    }

    @Override
    protected Collection<? extends CommandInterceptor> getDefaultCommandInterceptorsTxRequired() {
        List<CommandInterceptor> defaultCommandInterceptorsTxRequired = new ArrayList<>();
        defaultCommandInterceptorsTxRequired.add(new LogInterceptor());
        defaultCommandInterceptorsTxRequired.add(new CommandContextInterceptor(commandContextFactory, this, true));
        return defaultCommandInterceptorsTxRequired;
    }

}
