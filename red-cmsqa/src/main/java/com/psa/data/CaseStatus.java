package com.psa.data;

public enum  CaseStatus {

    NEW ("New"),
    DRAFT ("Draft"),
    FOR_REVIEW ("For Review"),
    IN_REVIEW ("In Review"),
    FOR_REVISION ("For Revisions"),
    PUBLISHED ("Published");

    private String caseStatus;

    CaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }
}
