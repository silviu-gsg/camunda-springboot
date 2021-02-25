package com.gsg.demo.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import java.util.UUID;

public class OnboardingBusinessKeyListener implements ExecutionListener {

    public static final String ONBOARDING_PROCESS_PREFIX = "onboarding-";

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        execution.setProcessBusinessKey(ONBOARDING_PROCESS_PREFIX + UUID.randomUUID().toString());
    }

}
