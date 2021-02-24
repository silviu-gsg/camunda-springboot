package com.gsg.demo.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import java.util.UUID;

public class OriginationBusinessKeyListener implements ExecutionListener {

    public static final String ORIGINATION_PROCESS_PREFIX = "origination-";

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        execution.setProcessBusinessKey(ORIGINATION_PROCESS_PREFIX + UUID.randomUUID().toString());
    }

}
