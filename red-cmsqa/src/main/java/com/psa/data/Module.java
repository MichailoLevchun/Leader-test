package com.psa.data;

public enum Module {

    CREATE_NEW_CASE ("Create New Case"),
    CREATE_NEW_PROFILE_COMPANY ("Create New Profile - Company"),
    CREATE_NEW_PROFILE_INDIVIDUAL ("Create New Profile - Individual"),
    ADD_NEW_ASSOCIATE_PROFILE_COMPANY ("Add New Associate Profile - Company"),
    ADD_NEW_ASSOCIATE_PROFILE_INDIVIDUAL ("Add New Associate Profile - Individual"),
    LOGIN ("Login"),
    LOGOUT ("Logout"),
    MY_CASES ("My Cases"),
    ALL_CASES ("All Cases"),
    CASE_EDITOR ("Case Editor"),
    CASE_SETTINGS ("Case Settings");

    private String module;

    Module(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
