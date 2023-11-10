package org.elis.eventsmanager.util;

public class UtilPath {
    //RUOLI
    private static final String ADMIN = "/admin";
    private static final String CUSTOMER = "/customer";
    private static final String ALL="/all";
    //CONTROLLER
    private static final String USERS_CONTROLLER="/user";
    private static final String EVENT_CONTROLLER="/event";


    //COMMANDS
    public static final String BLOCK_USER = ADMIN+USERS_CONTROLLER + "/blockUser";
    public static final String UNBLOCK_USER = ADMIN+USERS_CONTROLLER + "/unblockUser";
    public static final String LOGIN = ALL+USERS_CONTROLLER+"/login";
    public static final String SIGNUP = ALL+USERS_CONTROLLER+"/signup";

    public static final String CREATE_EVENT = ADMIN + EVENT_CONTROLLER + "/createEvent";
}
