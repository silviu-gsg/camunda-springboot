package com.gsg.demo.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.gsg.demo.util.EmailNotificationConstants.*;
import static com.gsg.demo.util.OnboardingProcessConstants.*;

@Slf4j
@Component
public class OnboardingNotificationDelegate implements JavaDelegate {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String customerEmailAddress = execution.getVariable("emailAddress").toString();
        String customerPhoneNumber = execution.getVariable("phoneNumber").toString();
        String processInstanceId = execution.getProcessInstanceId();
        String currentTaskId = execution.getCurrentActivityId();

        String recipientEmailAddress;
        String mailSubject;
        String mailBody;

        switch (currentTaskId) {
            case DENY_TERMS_TASK_ID:
                recipientEmailAddress = SERVICE_DESK_ADDRESS;
                mailSubject = String.format(DENY_TERMS_SUBJECT, processInstanceId);
                mailBody = String.format(DENY_TERMS_BODY, processInstanceId, customerEmailAddress, customerPhoneNumber);
                break;
            case VALIDATION_ERROR_TASK_ID:
                recipientEmailAddress = SERVICE_DESK_ADDRESS;
                mailSubject = String.format(VALIDATION_ERROR_SUBJECT, processInstanceId);
                mailBody = String.format(VALIDATION_ERROR_BODY, processInstanceId, customerPhoneNumber);
                break;
            case WELCOME_ABOARD_TASK_ID:
                recipientEmailAddress = customerEmailAddress;
                mailSubject = WELCOME_ABOARD_SUBJECT;
                mailBody = WELCOME_ABOARD_BODY;
                break;
            default:
                log.error(String.format("Invalid service task configuration encountered for process with id: %s and " +
                        "process definition with id: %s", processInstanceId, execution.getProcessDefinitionId()));
                throw new RuntimeException("Invalid service task configuration");
        }

        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom(DEFAULT_FROM_ADDRESS);
            email.setTo(recipientEmailAddress);
            email.setSubject(mailSubject);
            email.setText(mailBody);
            email.setSentDate(new Date());
            emailSender.send(email);

            log.info(String.format("Email sent successfully to: %s for process with id: %s", recipientEmailAddress, processInstanceId));
        } catch (Exception e) {
            log.warn(String.format("Error encountered while sending email notification for process with id: %s", processInstanceId), e);
        }
    }

}
