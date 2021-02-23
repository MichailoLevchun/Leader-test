package com.psa.data;

public enum RiskInformationType {

    PUBLIC ("Public"),
    CLIENT_SPECIFIC ("Client Specific");

    private String riskSummaryType;

    RiskInformationType(String riskSummaryType) {
        this.riskSummaryType = riskSummaryType;
    }

    public String getRiskSummaryType() {
        return riskSummaryType;
    }

    public static RiskInformationType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
