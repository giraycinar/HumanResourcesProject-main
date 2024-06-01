package org.example.constants;

public class RestApiUrls {
    private static final String VERSION = "/v1";
    private static final String DEV = "/dev";
    private static final String PROD = "/prod";

    private static final String ROOT = DEV + VERSION;

    public static final String AUTH = ROOT + "/auth";

    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String ADMINLOGIN = "/admin-login";
    public static final String EMPLOYEELOGIN = "/employee-login";
    public static final String MANAGERLOGIN = "/manager-login";
    public static final String UPDATE = "/update";
    public static final String ACTIVATE_STATUS = "/activate";
    public static final String DELETE_BY_TOKEN="/delete";
    public static final String FIND_BY_ROLE = "/find-by-role";
    public static final String REGISTER_WITH_RABBITMQ = "/register-with-rabbitmq";





}
