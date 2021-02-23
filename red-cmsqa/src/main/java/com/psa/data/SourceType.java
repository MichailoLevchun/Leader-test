package com.psa.data;

public enum SourceType {

    GLOBAL_COMPLIANCE_CHECKS ("Global Compliance Checks"),
    MEDIA ("Media"),
    WEBSITE ("Website"),
    CLIENT_DOCUMENTATION ("Client Documentation"),
    OFFICIAL_DOCUMENTATION ("Official Documentation"),
    LITIGATION ("Litigation, Bankruptcy, Regulatory, Law Enforcement (LBRL)"),
    REPUTATION ("Reputational Intelligence"),
    INTERVIEWS ("Interviews"),
    SITE_VISIT ("Site Visit");

    private String sourceType;

    SourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public static SourceType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
