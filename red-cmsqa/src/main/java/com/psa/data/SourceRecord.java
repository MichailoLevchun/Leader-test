package com.psa.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.psa.data.GenericData.*;

public class SourceRecord {

    private SourceType sourceType;
    private String recordID;
    private int day;
    private String month;
    private int year;
    private ToggleOption presentToggle;
    private ToggleOption noHitToggle;
    private String gcSource;
    private String link;
    private String interviewee;
    private String role;
    private String company;
    private String position;
    private String address;
    private ToggleOption confirmedToggle;
    private String detailsOfSiteVisit;
    private String[] photoDescription;
    private ReputationSourceType reputationSourceType;
    private String[] primarySource;
    private String[] involving;
    private Risk[] risk;
    private String documentSummary;
    private String summary;
    private String comment;
    private boolean relevancy;
    private ProfileData referencedProfileData;

    public SourceRecord(SourceType sourceType, String... involving) {
        this.sourceType = sourceType;
        this.involving = involving;
    }

    // Constructor for all source records
    public SourceRecord(SourceType sourceType, ToggleOption presentToggle, ToggleOption noHitToggle, String[] involving,
                        Risk[] risk, String comment) {
        this.sourceType = sourceType;
        this.presentToggle = presentToggle;
        this.noHitToggle = noHitToggle;
        this.involving = involving;
        this.risk = risk;
        this.comment = comment;
    }

    // Constructor for Global Compliance Checks
    public SourceRecord(SourceType sourceType, ToggleOption presentToggle, ToggleOption noHitToggle, String[] involving,
                        Risk[] risk, String comment, String source, String[] primarySource) {
        this.sourceType = sourceType;
        this.presentToggle = presentToggle;
        this.noHitToggle = noHitToggle;
        this.involving = involving;
        this.risk = risk;
        this.comment = comment;
        this.gcSource = source;
        this.link = source;
        this.primarySource = primarySource;
    }

    // Constructor for Interviews
    public SourceRecord(SourceType sourceType, ToggleOption presentToggle, ToggleOption noHitToggle, String[] involving,
                        Risk[] risk, String comment, String interviewee, String role, String link) {
        this.sourceType = sourceType;
        this.presentToggle = presentToggle;
        this.noHitToggle = noHitToggle;
        this.involving = involving;
        this.risk = risk;
        this.comment = comment;
        this.interviewee = interviewee;
        this.role = role;
        this.link = link;
    }

    // Constructor for Media, Website and Litigation, Official Documentation, Client Documentation
    public SourceRecord(SourceType sourceType, ToggleOption presentToggle, ToggleOption noHitToggle, String[] involving,
                        Risk[] risk, String comment, String any) {
        this.sourceType = sourceType;
        this.presentToggle = presentToggle;
        this.noHitToggle = noHitToggle;
        this.involving = involving;
        this.risk = risk;
        this.comment = comment;
        this.link = any;
        this.documentSummary = any;
    }

    // Constructor for Reputation
    public SourceRecord(SourceType sourceType, ToggleOption presentToggle, ToggleOption noHitToggle, String[] involving,
                        Risk[] risk, String comment, String interviewee, String company, String position, ReputationSourceType reputationSourceType, String summary) {
        this.sourceType = sourceType;
        this.presentToggle = presentToggle;
        this.noHitToggle = noHitToggle;
        this.involving = involving;
        this.risk = risk;
        this.comment = comment;
        this.interviewee = interviewee;
        this.company = company;
        this.position = position;
        this.reputationSourceType = reputationSourceType;
        this.summary = summary;
    }

    // Constructor for Site Visit
    public SourceRecord(SourceType sourceType, ToggleOption presentToggle, ToggleOption noHitToggle, String[] involving,
                        Risk[] risk, String comment, String address, ToggleOption confirmedToggle, String detailsOfSiteVisit, String[] photoDescription) {
        this.sourceType = sourceType;
        this.presentToggle = presentToggle;
        this.noHitToggle = noHitToggle;
        this.involving = involving;
        this.risk = risk;
        this.comment = comment;
        this.address = address;
        this.confirmedToggle = confirmedToggle;
        this.detailsOfSiteVisit = detailsOfSiteVisit;
        this.photoDescription = photoDescription;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ToggleOption getPresentToggle() {
        return presentToggle;
    }

    public void setPresentToggle(ToggleOption presentToggle) {
        this.presentToggle = presentToggle;
    }

    public ToggleOption getNoHitToggle() {
        return noHitToggle;
    }

    public void setNoHitToggle(ToggleOption noHitToggle) {
        this.noHitToggle = noHitToggle;
    }

    public String getGcSource() {
        return gcSource;
    }

    public void setGcSource(String gcSource) {
        this.gcSource = gcSource;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getInterviewee() {
        return interviewee;
    }

    public void setInterviewee(String interviewee) {
        this.interviewee = interviewee;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ToggleOption getConfirmedToggle() {
        return confirmedToggle;
    }

    public void setConfirmedToggle(ToggleOption confirmedToggle) {
        this.confirmedToggle = confirmedToggle;
    }

    public String getDetailsOfSiteVisit() {
        return detailsOfSiteVisit;
    }

    public void setDetailsOfSiteVisit(String detailsOfSiteVisit) {
        this.detailsOfSiteVisit = detailsOfSiteVisit;
    }

    public String[] getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String... photoDescription) {
        this.photoDescription = photoDescription;
    }

    public ReputationSourceType getReputationSourceType() {
        return reputationSourceType;
    }

    public void setReputationSourceType(ReputationSourceType reputationSourceType) {
        this.reputationSourceType = reputationSourceType;
    }

    public String[] getPrimarySource() {
        return primarySource;
    }

    public void setPrimarySource(String... primarySource) {
        this.primarySource = primarySource;
    }

    public String[] getInvolving() {
        return involving;
    }

    public void setInvolving(String... involving) {
        this.involving = involving;
    }

    public Risk[] getRisk() {
        return risk;
    }

    public void setRisk(Risk... risk) {
        this.risk = risk;
    }

    public String getDocumentSummary() {
        return documentSummary;
    }

    public void setDocumentSummary(String documentSummary) {
        this.documentSummary = documentSummary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isRelevancy() {
        return relevancy;
    }

    public void setRelevancy(boolean relevancy) {
        this.relevancy = relevancy;
    }

    public ProfileData getReferencedProfileData() {
        return referencedProfileData;
    }

    public void setReferencedProfileData(ProfileData referencedProfileData) {
        this.referencedProfileData = referencedProfileData;
    }

    public static void setSourceRecordDate(SourceRecord sourceRecord) {
        sourceRecord.setPresentToggle(ToggleOption.OFF);
        LocalDate randomDate = randomDateInBetween(START_DATE, END_DATE);
        String formattedDate = randomDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String[] splitDate = formattedDate.split(" ");
        sourceRecord.setDay(Integer.parseInt(splitDate[0]));
        sourceRecord.setMonth(splitDate[1]);
        sourceRecord.setYear(Integer.parseInt(splitDate[2]));
    }
}