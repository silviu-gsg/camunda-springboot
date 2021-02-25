package com.gsg.demo.util;

public final class EmailNotificationConstants {

    private EmailNotificationConstants() {
    }

    public static final String DEFAULT_FROM_ADDRESS = "noreply_camunda_onboarding@gmail.com";
    public static final String SERVICE_DESK_ADDRESS = "service_desk_camunda_onboarding@gmail.com";

    // TODO replace with Thymeleaf templates with HTML content
    public static final String DENY_TERMS_SUBJECT = "Terms and conditions denied by the customer for process with id: %s";
    public static final String DENY_TERMS_BODY = "The terms and conditions for process with id: %s were not accepted by " +
            "the customer with email address: %s. Please contact the customer for clarifications via phone number: %s";

    public static final String VALIDATION_ERROR_SUBJECT = "Validation errors encountered for process with id: %s";
    public static final String VALIDATION_ERROR_BODY = "Validation errors were encountered for process with id: %s . " +
            "Please connect with the customer for clarifications via phone: %s";

    public static final String WELCOME_ABOARD_SUBJECT = "Welcome aboard our platform!";
    public static final String WELCOME_ABOARD_BODY = "Thank you for registering on our platform!";

}
