package com.psa.data;

public class RiskSummary {

    private Risk risk;
    private RiskInformationType riskSummaryType;
    private String summary;
    private SourceRecord[] sourceRecord;

    public RiskSummary(Risk risk, RiskInformationType riskSummaryType, String summary) {
        this.risk = risk;
        this.riskSummaryType = riskSummaryType;
        this.summary = summary;
    }

    public RiskSummary(Risk risk, RiskInformationType riskSummaryType, String summary, SourceRecord... sourceRecord) {
        this.risk = risk;
        this.riskSummaryType = riskSummaryType;
        this.summary = summary;
        this.sourceRecord = sourceRecord;
    }

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public RiskInformationType getRiskSummaryType() {
        return riskSummaryType;
    }

    public void setRiskSummaryType(RiskInformationType riskSummaryType) {
        this.riskSummaryType = riskSummaryType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public SourceRecord[] getSourceRecord() {
        return sourceRecord;
    }

    public void setSourceRecord(SourceRecord... sourceRecord) {
        this.sourceRecord = sourceRecord;
    }
}