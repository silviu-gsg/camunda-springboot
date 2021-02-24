package com.gsg.demo.mybatis;

import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.Command;


public class MyBatisQueryExecutor {
    private MyBatisSessionFactory myBatisSessionFactory;

    public MyBatisQueryExecutor(ProcessEngineConfigurationImpl processEngineConfiguration,
                                String mappingResourceName) {
        myBatisSessionFactory = new MyBatisSessionFactory();
        myBatisSessionFactory.initFromProcessEngineConfiguration(processEngineConfiguration,
                mappingResourceName);
    }

    public <T> T executeQueryCommand(Command<T> command) {
        return myBatisSessionFactory.getCommandExecutorTxRequired().execute(command);
    }

}
