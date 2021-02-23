package com.psa.data;

public enum Risk {

    COR ("Corruption and Bribery"),
    SER ("Serious and Organised Crime"),
    TER ("Terror and Related Matters"),
    ANT ("Anti-Competitive Behaviour"),
    GOC ("Government Connections"),
    FRA ("Fraud"),
    MON ("Money Laundering"),
    TAX ("Tax Non-Compliance"),
    SAN ("Sanctions and Restrictions"),

    TRA ("Transparency"),
    SOU ("Source of Wealth"),

    ENV ("Environmental Degradation"),
    ANI ("Animal Welfare"),
    SAL ("Sales and Marketing Practices"),
    HEA ("Health and Safety"),
    HUM ("Human Rights"),
    MOD ("Modern Slavery"),
    EMP ("Employment Practices"),
    GOV ("Governance and Management"),
    REG ("Regulatory Enforcement"),

    INT ("Intellectual Property"),
    PER ("Personal Data Privacy"),
    DAT ("Data Security"),

    FIN ("Financial Stability"),
    FII ("Financial Irregularities"),

    IND ("Industry Presence"),
    PRO ("Product and Service Quality"),
    OPE ("Operational Quality"),
    BUS ("Business Continuity"),

    SRC ("Sourcing"),
    CTR ("Contracts"),
    COI ("Conflicts of Interest");

    private String risk;
    private int riskScore;
    private int riskSummaryCount;

    Risk(String risk) {
        this.risk = risk;
    }

    public String getRisk() {
        return risk;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public int getRiskSummaryCount() {
        return riskSummaryCount;
    }

    public void setRiskSummaryCount(int riskSummaryCount) {
        this.riskSummaryCount = riskSummaryCount;
    }

    public static Risk getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
