package com.psa.data;

public enum ProfileType {

    COMPANY ("Company"),
    INDIVIDUAL ("Individual");

    private String profileType;

    ProfileType(String profileType) {
        this.profileType = profileType;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }
}
