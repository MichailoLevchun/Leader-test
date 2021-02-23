package com.psa.data;

public enum ReputationSourceType {

    INDUSTRY ("Industry"),
    GOV_REG ("Government / Regulatory"),
    COMPETITOR ("Competitor");

    private String reputationSourceType;

    ReputationSourceType(String reputationSourceType) {
        this.reputationSourceType = reputationSourceType;
    }

    public String getReputationSourceType() {
        return reputationSourceType;
    }

    public void setReputationSourceType(String reputationSourceType) {
        this.reputationSourceType = reputationSourceType;
    }

    public static ReputationSourceType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
