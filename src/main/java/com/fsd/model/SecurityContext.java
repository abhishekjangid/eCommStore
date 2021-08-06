package com.fsd.model;

public class SecurityContext {
    private String id;
    private String userName;
    private String userType;

    private static SecurityContext INSTANCE;

    private SecurityContext (String id, String userName, String userType) {
        this.id = id;
        this.userName = userName;
        this.userType = userType;
    }

    public static SecurityContext getContext(String id, String userName, String userType) {
        if(INSTANCE == null) {
            INSTANCE = new SecurityContext(id, userName, userType);
        }
        return INSTANCE;
    }

    public static void clearContext() {
        if(INSTANCE != null) {
            INSTANCE = null;
        }
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

}
