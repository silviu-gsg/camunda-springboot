package com.gsg.demo.delegate;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static com.gsg.demo.util.EmailServiceProperties.*;
import static com.gsg.demo.util.EmailContentConstants.*;
import static com.gsg.demo.util.OriginationActivityConstants.*;

@Slf4j
public class OriginationNotificationDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String customerEmailAddress = execution.getVariable("emailAddress").toString();
        String customerPhoneNumber = execution.getVariable("phoneNumber").toString();
        String processInstanceId = execution.getProcessInstanceId();
        String currentActivityId = execution.getCurrentActivityId();

        String recipientEmailAddress;
        String mailSubject;
        String mailBody;

        switch (currentActivityId) {
            case DENY_TERMS_AND_CONDITIONS:
                recipientEmailAddress = SERVICE_DESK_ADDRESS;
                mailSubject = String.format(DENY_TERMS_SUBJECT, processInstanceId);
                mailBody = String.format(DENY_TERMS_BODY, processInstanceId, customerEmailAddress, customerPhoneNumber);
                break;
            case VALIDATION_ERROR_NOTIFICATION:
                recipientEmailAddress = SERVICE_DESK_ADDRESS;
                mailSubject = String.format(VALIDATION_ERROR_SUBJECT, processInstanceId);
                mailBody = String.format(VALIDATION_ERROR_BODY, processInstanceId, customerPhoneNumber);
                break;
            case WELCOME_ABOARD_NOTIFICATION:
                recipientEmailAddress = customerEmailAddress;
                mailSubject = WELCOME_ABOARD_SUBJECT;
                mailBody = WELCOME_ABOARD_BODY;
                break;
            default:
                log.error(String.format("Invalid service task configuration encountered for process with id: %s and " +
                        "process definition with id: %s", processInstanceId, execution.getProcessDefinitionId()));
                throw new RuntimeException("Invalid service task configuration");
        }

        Email email = new SimpleEmail();
        email.setHostName(HOST);
        email.setSmtpPort(PORT);
        email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
        email.setSSLCheckServerIdentity(true);
        email.setSSLOnConnect(true);

        try {
            email.setFrom(DEFAULT_FROM_ADDRESS);

            email.addTo(recipientEmailAddress);
            email.setSubject(mailSubject);
            email.setMsg(mailBody);

            email.send();

            log.info(String.format("Email sent successfully to: %s for process with id: %s",
                    recipientEmailAddress, processInstanceId));
        } catch (Exception e) {
            log.warn(String.format("Error encountered while sending email notification for process with id: %s",
                    processInstanceId), e);
        }
    }

}
