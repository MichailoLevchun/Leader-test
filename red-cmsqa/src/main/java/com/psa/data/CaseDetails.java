package com.psa.data;

import com.psa.library.FunctionReference;

public class CaseDetails extends FunctionReference {

    private Scope scope;
    private String caseId;
    private String clientName;
    private String clientCode;
    private String additionalScope;
    private String caseRegion;
    private String caseOwner;
    private String caseOwnerCountry;
    private UserAccount[] writer;
    private UserAccount[] reviewer;
    private String created;
    private CaseStatus caseStatus;
    private String lastUpdated;
    private String lastUpdatedBy;
    private String specificClientName;
    private String assignedTo;

    CaseDetails(Scope scope) {
        this.scope = scope;
    }

    CaseDetails(Scope scope, String additionalScope, String specificClientName, UserAccount[] writer, UserAccount[] reviewer) {
        this.scope = scope;
        this.additionalScope = additionalScope;
        this.specificClientName = specificClientName;
        this.writer = writer;
        this.reviewer = reviewer;
    }

    CaseDetails(Scope scope, UserAccount[] writer, UserAccount[] reviewer) {
        this.scope = scope;
        this.writer = writer;
        this.reviewer = reviewer;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getAdditionalScope() {
        return additionalScope;
    }

    public void setAdditionalScope(String additionalScope) {
        this.additionalScope = additionalScope;
    }

    public UserAccount[] getWriter() {
        return writer;
    }

    public void setWriter(UserAccount... writer) {
        this.writer = writer;
    }

    public UserAccount[] getReviewer() {
        return reviewer;
    }

    public void setReviewer(UserAccount... reviewer) {
        this.reviewer = reviewer;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public CaseStatus getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(CaseStatus caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getCaseOwner() {
        return caseOwner;
    }

    public void setCaseOwner(String caseOwner) {
        this.caseOwner = caseOwner;
    }

    public String getCaseOwnerCountry() {
        return caseOwnerCountry;
    }

    public void setCaseOwnerCountry(String caseOwnerCountry) {
        this.caseOwnerCountry = caseOwnerCountry;
    }

    public String getCaseRegion() {
        return caseRegion;
    }

    public void setCaseRegion(String caseRegion) {
        this.caseRegion = caseRegion;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getSpecificClientName() {
        return specificClientName;
    }

    public void setSpecificClientName(String specificClientName) {
        this.specificClientName = specificClientName;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public static void setCaseDetails(CaseDetails caseDetails) {
        String randomString = randomString(5);
        caseDetails.setCaseId("CI" + randomString);
        caseDetails.setClientCode("CC" + randomString);
        caseDetails.setClientName("CN" + randomString);
    }
}