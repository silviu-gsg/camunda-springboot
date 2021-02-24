package com.gsg.demo.service;

import com.gsg.demo.dto.UserAuditDto;
import com.gsg.demo.mybatis.MyBatisQueryExecutor;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuditService {

    @SuppressWarnings(value = "unchecked")
    public List<UserAuditDto> getUserAuditByUserName(String userName) {
        return (List<UserAuditDto>) getUserAuditWithQuery("userAuditSelect", userName);
    }

    private static Object getUserAuditWithQuery(String queryId, String queryParam) {
        if (queryParam == null) {
            return Collections.emptyList();
        }

        ProcessEngineImpl processEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
        ProcessEngineConfigurationImpl processEngineConfiguration = processEngine.getProcessEngineConfiguration();

        MyBatisQueryExecutor commandExecutor = new MyBatisQueryExecutor(processEngineConfiguration, "my-batis-config.xml");
        return commandExecutor.executeQueryCommand(commandContext -> commandContext.getDbSqlSession().selectList(queryId, queryParam));
    }

}
