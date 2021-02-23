package com.psa.data;

import com.psa.data.SubjectProfile.KeyIndustry;
import com.psa.library.FunctionReference;


public class ProfileDetails extends FunctionReference {

    private ProfileType profileType;
    private String fullName;
    private String firstName;
    private String middleName;
    private String lastName;
    private Country[] country;
    private String shortName;
    private String profileID;
    private KeyIndustry keyIndustry;
    private int overallRiskRatingScore;
    private String publicSummary;
    private String clientSummary;

    ProfileDetails(ProfileType profileType, String fullName) {
        this.profileType = profileType;
        this.fullName = fullName;
    }

    ProfileDetails(ProfileType profileType, String fullName, Country[] country) {
        this.profileType = profileType;
        this.fullName = fullName;
        this.country = country;
    }

    ProfileDetails(ProfileType profileType, String fullName, Country[] country, int overallRiskRatingScore, String publicSummary, String clientSummary) {
        this.profileType = profileType;
        this.fullName = fullName;
        this.country = country;
        this.overallRiskRatingScore = overallRiskRatingScore;
        this.publicSummary = publicSummary;
        this.clientSummary = clientSummary;
    }

    ProfileDetails(ProfileType profileType, String fullName, Country[] country, int overallRiskRatingScore, String publicSummary, String clientSummary, KeyIndustry keyIndustry) {
        this.profileType = profileType;
        this.fullName = fullName;
        this.country = country;
        this.overallRiskRatingScore = overallRiskRatingScore;
        this.publicSummary = publicSummary;
        this.clientSummary = clientSummary;
        this.keyIndustry = keyIndustry;
    }

    ProfileDetails(ProfileType profileType, String fullName, Country[] country, String shortName) {
        this.profileType = profileType;
        this.fullName = fullName;
        this.country = country;
        this.shortName = shortName;
    }

    ProfileDetails(ProfileType profileType, String firstName, String middleName, String lastName, Country[] country, String fullName) {
        this.profileType = profileType;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.country = country;
        this.fullName = fullName;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Country[] getCountry() {
        return country;
    }

    public void setCountry(Country... country) {
        this.country = country;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getProfileID() {
        return profileID;
    }

    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    public KeyIndustry getKeyIndustry() {
        return keyIndustry;
    }

    public void setKeyIndustry(KeyIndustry keyIndustry) {
        this.keyIndustry = keyIndustry;
    }

    public int getOverallRiskRatingScore() {
        return overallRiskRatingScore;
    }

    public void setOverallRiskRatingScore(int overallRiskRatingScore) {
        this.overallRiskRatingScore = overallRiskRatingScore;
    }

    public String getPublicSummary() {
        return publicSummary;
    }

    public void setPublicSummary(String publicSummary) {
        this.publicSummary = publicSummary;
    }

    public String getClientSummary() {
        return clientSummary;
    }

    public void setClientSummary(String clientSummary) {
        this.clientSummary = clientSummary;
    }
}
