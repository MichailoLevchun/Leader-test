package com.psa.data;

import com.psa.data.SubjectProfile.KeyIndustry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.psa.data.GenericData.*;

public class DataEntry {

    private ProfileData profileData;
    private String details;
    private boolean partOfGroupOfCompanies;
    private String nameOfGroup;
    private String websiteDescription;
    private String url;
    private String comment;
    private String name;
    private int fromDay;
    private String fromMonth;
    private int fromYear;
    private String fromDate;
    private ToggleOption fromPresentToggle;
    private int toDay;
    private String toMonth;
    private int toYear;
    private String toDate;
    private ToggleOption toPresentToggle;
    private KeyIndustry keyIndustry;
    private SourceRecord[] sourceRecord;

    public DataEntry(ProfileData profileData, String details) {
        this.profileData = profileData;
        this.details = details;
    }

    public DataEntry(ProfileData profileData, String details, SourceRecord... sourceRecord) {
        this.profileData = profileData;
        this.details = details;
        this.sourceRecord = sourceRecord;
    }

    public DataEntry(ProfileData profileData, boolean partOfGroupOfCompanies, String nameOfGroup, String comment) {
        this.profileData = profileData;
        this.partOfGroupOfCompanies = partOfGroupOfCompanies;
        this.nameOfGroup = nameOfGroup;
        this.comment = comment;
    }

    public DataEntry(ProfileData profileData, boolean partOfGroupOfCompanies, String nameOfGroup, String comment, SourceRecord... sourceRecord) {
        this.profileData = profileData;
        this.partOfGroupOfCompanies = partOfGroupOfCompanies;
        this.nameOfGroup = nameOfGroup;
        this.comment = comment;
        this.sourceRecord = sourceRecord;
    }

    public DataEntry(ProfileData profileData, String websiteDescription, String url) {
        this.profileData = profileData;
        this.websiteDescription = websiteDescription;
        this.url = url;
    }

    public DataEntry(ProfileData profileData, String websiteDescription, String url, SourceRecord... sourceRecord) {
        this.profileData = profileData;
        this.websiteDescription = websiteDescription;
        this.url = url;
        this.sourceRecord = sourceRecord;
    }

    public DataEntry(ProfileData profileData, String name, ToggleOption fromPresentToggle, ToggleOption toPresentToggle) {
        this.profileData = profileData;
        this.name = name;
        this.fromPresentToggle = fromPresentToggle;
        this.toPresentToggle = toPresentToggle;
    }

    public DataEntry(ProfileData profileData, String name, ToggleOption fromPresentToggle, ToggleOption toPresentToggle, SourceRecord... sourceRecord) {
        this.profileData = profileData;
        this.name = name;
        this.fromPresentToggle = fromPresentToggle;
        this.toPresentToggle = toPresentToggle;
        this.sourceRecord = sourceRecord;
    }

    public ProfileData getProfileData() {
        return profileData;
    }

    public void setProfileData(ProfileData profileData) {
        this.profileData = profileData;
    }

    public SourceRecord[] getSourceRecord() {
        return sourceRecord;
    }

    public void setSourceRecord(SourceRecord[] sourceRecord) {
        this.sourceRecord = sourceRecord;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean getPartOfGroupOfCompanies() {
        return partOfGroupOfCompanies;
    }

    public void setPartOfGroupOfCompanies(boolean partOfGroupOfCompanies) {
        this.partOfGroupOfCompanies = partOfGroupOfCompanies;
    }

    public String getNameOfGroup() {
        return nameOfGroup;
    }

    public void setNameOfGroup(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
    }

    public String getWebsiteDescription() {
        return websiteDescription;
    }

    public void setWebsiteDescription(String websiteDescription) {
        this.websiteDescription = websiteDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comments) {
        this.comment = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KeyIndustry getKeyIndustry() {
        return keyIndustry;
    }

    public void setKeyIndustry(KeyIndustry keyIndustry) {
        this.keyIndustry = keyIndustry;
    }

    public int getFromDay() {
        return fromDay;
    }

    public void setFromDay(int fromDay) {
        this.fromDay = fromDay;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public ToggleOption getFromPresentToggle() {
        return fromPresentToggle;
    }

    public void setFromPresentToggle(ToggleOption fromPresentToggle) {
        this.fromPresentToggle = fromPresentToggle;
    }

    public int getToDay() {
        return toDay;
    }

    public void setToDay(int toDay) {
        this.toDay = toDay;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public ToggleOption getToPresentToggle() {
        return toPresentToggle;
    }

    public void setToPresentToggle(ToggleOption toPresentToggle) {
        this.toPresentToggle = toPresentToggle;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public static void setRandomFromDate(DataEntry dataEntry) {
        dataEntry.setFromPresentToggle(ToggleOption.OFF);
        LocalDate randomDate = randomDateInBetween(START_DATE, LocalDate.now());
        String formattedDate = randomDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String[] splitDate = formattedDate.split(" ");
        dataEntry.setFromDay(Integer.parseInt(splitDate[0]));
        dataEntry.setFromMonth(splitDate[1]);
        dataEntry.setFromYear(Integer.parseInt(splitDate[2]));
        dataEntry.setFromDate((dataEntry.getFromDay() + " " + dataEntry.getFromMonth() + " " + dataEntry.getFromYear()).trim());
    }

    public static void setRandomToDate(DataEntry dataEntry) {
        dataEntry.setToPresentToggle(ToggleOption.OFF);
        LocalDate randomDate = randomDateInBetween(LocalDate.now(), END_DATE);
        String formattedDate = randomDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String[] splitDate = formattedDate.split(" ");
        dataEntry.setToDay(Integer.parseInt(splitDate[0]));
        dataEntry.setToMonth(splitDate[1]);
        dataEntry.setToYear(Integer.parseInt(splitDate[2]));
        dataEntry.setToDate((dataEntry.getToDay() + " " + dataEntry.getToMonth() + " " + dataEntry.getToYear()).trim());
    }
}