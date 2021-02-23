package com.psa.data;

public enum RiskCategory {

    INTEGRITY_RISKS ("Integrity Risks"),
    IDENTITY_RISKS ("Identity Risks"),
    ESG_RISKS ("Environment, Social and Governance Risks"),
    DATA_AND_CYBER_RISKS ("Data and Cyber Risks"),
    OAQ_RISKS ("Operational and Quality Risks"),
    FINANCIAL_RISKS ("Financial Risks"),
    ENGAGEMENT_RISKS ("Engagement Risks");

    private String riskCategory;
    private int riskCategoryScore;

    RiskCategory(String riskCategory) {
        this.riskCategory = riskCategory;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    public int getRiskCategoryScore() {
        return riskCategoryScore;
    }

    public void setRiskCategoryScore(int riskCategoryScore) {
        this.riskCategoryScore = riskCategoryScore;
    }
}
