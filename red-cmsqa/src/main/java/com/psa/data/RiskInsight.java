package com.psa.data;

public class RiskInsight {

    private Risk risk;
    private RiskInformationType riskInsightType;
    private String riskInsight;
    private ToggleOption furtherSteps;
    private String recommendation;

    public RiskInsight(Risk risk, RiskInformationType riskInsightType, String riskInsight, ToggleOption furtherSteps, String recommendation) {
        this.risk = risk;
        this.riskInsightType = riskInsightType;
        this.riskInsight = riskInsight;
        this.furtherSteps = furtherSteps;
        this.recommendation = recommendation;
    }

    public RiskInsight(Risk risk, RiskInformationType riskInsightType, String riskInsight, ToggleOption furtherSteps) {
        this.risk = risk;
        this.riskInsightType = riskInsightType;
        this.riskInsight = riskInsight;
        this.furtherSteps = furtherSteps;
    }

    public RiskInsight(Risk risk, RiskInformationType riskInsightType, String riskInsight) {
        this.risk = risk;
        this.riskInsightType = riskInsightType;
        this.riskInsight = riskInsight;
    }

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public RiskInformationType getRiskInsightType() {
        return riskInsightType;
    }

    public void setRiskInsightType(RiskInformationType riskInsightType) {
        this.riskInsightType = riskInsightType;
    }

    public String getRiskInsight() {
        return riskInsight;
    }

    public void setRiskInsight(String riskInsight) {
        this.riskInsight = riskInsight;
    }

    public ToggleOption getFurtherSteps() {
        return furtherSteps;
    }

    public void setFurtherSteps(ToggleOption furtherSteps) {
        this.furtherSteps = furtherSteps;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
