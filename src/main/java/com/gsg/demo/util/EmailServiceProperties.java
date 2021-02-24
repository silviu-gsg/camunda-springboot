package com.gsg.demo.util;

public final class EmailServiceProperties {

    private EmailServiceProperties() {
    }

    // TODO replace Apache Mail with Spring Mail OR Camunda Mail Plugin, with environment based properties
    public static final String USERNAME = "mail_sender_address@gmail.com";
    public static final String PASSWORD = "mail_sender_password";
    public static final String HOST = "smtp.gmail.com";
    public static final int PORT = 465;

    public static final String DEFAULT_FROM_ADDRESS = "noreply_camunda_origination@gmail.com";
    public static final String SERVICE_DESK_ADDRESS = "service_desk_camunda_origination@gmail.com";

}
