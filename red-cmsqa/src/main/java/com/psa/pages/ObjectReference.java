/*
 * Copyright 2018 The Red Flag Group (http://www.redflaggroup.com)
 *
 * You may not use this file except in compliance with the terms of your service agreement with The Red Flag Group
 *
 */

package com.psa.pages;

import com.psa.library.ReadXmlData;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class ObjectReference {
    static ReadXmlData rxml = new ReadXmlData();

    public enum Bys {
        XPATH, CSS, ID, NAME, XPATH_LOCATE, CSS_LOCATE, ID_LOCATE, NAME_LOCATE, LABEL
    }

    // Results
    public enum ResultCount {
        PASS, FAIL, ERROR, SKIP
    }

    // ~ Email Report ------------------------------------------------------
    public String projectName = rxml.data("project");

    // ~ Docker Parameter ------------------------------------------------------
    public String sendEmailToList = System.getenv("EMAIL_TO") != null ? System.getenv("EMAIL_TO") : rxml.data("sendEmailToList");
    // public static String testURL = System.getenv("TEST_URL") != null ? System.getenv("TEST_URL") : rxml.data(
    //         "devSandboxURL");
    // public static String nightly = System.getenv("NIGHTLY_URL") != null ? System.getenv("NIGHTLY_URL") : rxml.data("nightly");
    // public static String environment = System.getenv("TEST_ENVIRONMENT") != null ? System.getenv("TEST_ENVIRONMENT") : rxml.data("environment");

    // Klov Parameters Docker
    protected static String klovReporterDB = System.getenv("KLOV_REPORTER_DB") != null ? System.getenv("KLOV_REPORTER_DB") : rxml.data("klov_reporter_db");
    protected String klovReporterDBPort = System.getenv("KLOV_REPORTER_DB_PORT") != null ? System.getenv("KLOV_REPORTER_DB_PORT") : rxml.data("klov_reporter_db_port");
    protected static String klovReporterURL = System.getenv("KLOV_REPORTER_URL") != null ? System.getenv("KLOV_REPORTER_URL") : rxml.data("klov_reporter_url");
    protected String klovReporterProjectName = System.getenv("KLOV_REPORTER_PROJECTNAME") != null ? System.getenv("KLOV_REPORTER_PROJECTNAME") : rxml.data("klov_reporter_projectname");

    // Common Objects
    public static final By IMG_AVATAR = xpath("//*[@data-qid='Image-avatar']");
    public static final By LBL_PROFILE_FIRST_NAME = xpath("//*[@data-qid='Label-profileFirstName']//span");
    public static final By BTN_LOGOUT = xpath("//*[@data-qid='Button-logout']");
    public static final By LBL_PAGINATION = xpath("//*[@data-qid='Label-pagination']//p");
    public static final By BTN_PAGINATION_NEXT = xpath("//*[@data-qid='Button-paginationNext']");
    public static final By BTN_PAGINATION_BACK = xpath("//*[@data-qid='Button-paginationBack']");
    public static final By ICO_MY_CASES = xpath("//*[@data-qid='Icon-myCases']");
    public static final By ICO_ALL_CASES = xpath("//*[@data-qid='Icon-allCases']");
    public static final By CH_CASE_ID = xpath("//*[@data-qid='ColumnHeader - caseId']");
    public static final By CH_SUBJECT = xpath("//*[@data-qid='ColumnHeader - subject']");
    public static final By CH_SCOPE = xpath("//*[@data-qid='ColumnHeader - scope']");
    public static final By CH_DIP = xpath("//*[@data-qid='ColumnHeader - dip']");
    public static final By CH_LAST_UPDATED = xpath("//*[@data-qid='ColumnHeader - lastUpdated']");
    public static final By CH_CLIENT = xpath("//*[@data-qid='ColumnHeader - client']");
    public static final By CH_CASE_OWNER = xpath("//*[@data-qid='ColumnHeader - caseOwner']");
    public static final By CH_ASSIGNED_TO = xpath("//*[@data-qid='ColumnHeader - assignedTo']");
    public static final By CH_REGION = xpath("//*[@data-qid='ColumnHeader - region']");
    public static final By CH_STATUS = xpath("//*[@data-qid='ColumnHeader - status']");
    public static final By TBL_CASE_ID_COLUMN = xpath("//tbody//tr//td[1]");
    public static final By TBL_SUBJECT_COLUMN = xpath("//tbody//tr//td[2]");
    public static final By TBL_LAST_UPDATED_COLUMN = xpath("//tbody//tr//td[5]");
    public static final String TBL_CASE_ID_SPECIFIC_CELL = "//tbody//tr[%s]//td[1]";
    public static final String TBL_ICO_CASE_DETAILS_VIEW = "//tbody//tr[%s]//td[11]//button";
    public static final By LBL_NO_CASES_AVAILABLE = xpath("//*[@data-qid='Label-noCasesAvailable']");
    public static final By IMG_SKELETON_LOADER = xpath("//*[contains(@class,'Skeleton')]");
    public static final By TXT_SEARCH = xpath("//*[@name='search']");

    // Login Page
    public static final By IMG_LOGIN_LOGO = xpath("//*[@data-qid='Header-button-logo']");
    public static final By LBL_LOGIN_TITLE = xpath("//*[@data-qid='Header-title']");
    public static final By TXT_EMAIL = xpath("//*[@id='EmailField-Login-email']");
    public static final By TXT_PASSWORD = xpath("//*[@id='PasswordField-Login-password']");
    public static final By BTN_LOGIN = xpath("//*[@data-qid='Login-button']");
    public static final By ALERT_LOGIN = xpath("//*[contains(@class,'Content-message')]");

    // My Cases Page
    public static final By LBL_MY_CASES = xpath("//*[@data-qid='PageTitle-myCases']");
    public static final By BTN_CREATE_NEW_CASE = xpath("//*[@data-qid='Button-createNewCase']");

    // All Cases Page
    public static final By LBL_ALL_CASES = xpath("//*[@data-qid='PageTitle-allCases']");

    // Case Details Preview
    public static final By MODAL_CASE_PREVIEW = xpath("//*[@data-qid='Modal-casePreview']");
    public static final By LBL_CASE_ID = xpath("//*[@data-qid='Label-caseId']");
    public static final By LBL_CREATED = xpath("//*[@data-qid='Label-created']");
    public static final By LBL_CREATED_VALUE = xpath("//*[@data-qid='Label-createdValue']");
    public static final By LBL_LAST_UPDATED = xpath("//*[@data-qid='Label-lastUpdated']");
    public static final By LBL_LAST_UPDATED_VALUE = xpath("//*[@data-qid='Label-lastUpdatedValue']");
    public static final By LBL_DAYS_IN_PROGRESS = xpath("//*[@data-qid='Label-daysInProgress']");
    public static final By LBL_DAYS_IN_PROGRESS_VALUE = xpath("//*[@data-qid='Label-daysInProgressValue']");
    public static final By LBL_SUBJECT = xpath("//*[@data-qid='Label-subject']");
    public static final By LBL_SUBJECT_VALUE = xpath("//*[@data-qid='Label-subjectValue']");
    public static final By LBL_PROFILE_ID = xpath("//*[@data-qid='Label-profileId']");
    public static final By LBL_STATUS = xpath("//*[@data-qid='Label-status']");
    public static final By LBL_STATUS_VALUE = xpath("//*[@data-qid='Label-statusValue']");
    public static final By LBL_CLIENT_NAME = xpath("//*[@data-qid='Label-clientName']");
    public static final By LBL_SCOPE = xpath("//*[@data-qid='Label-scope']");
    public static final By LBL_SCOPE_VALUE = xpath("//*[@data-qid='Label-scopeValue']");
    public static final By LBL_ADDITIONAL_SCOPE = xpath("//*[@data-qid='Label-additionalScope']");
    public static final By LBL_ADDITIONAL_SCOPE_VALUE = xpath("//*[@data-qid='Label-additionalScopeValue']");
    public static final By LBL_WRITER = xpath("//*[@data-qid='Label-writer']");
    public static final String LBL_WRITER_VALUE = "//*[@data-qid='Label-writerValue'][%s]";
    public static final By LBL_REVIEWER = xpath("//*[@data-qid='Label-reviewer']");
    public static final String LBL_REVIEWER_VALUE = "//*[@data-qid='Label-reviewerValue'][%s]";
    public static final By LBL_ATTACHMENTS = xpath("//*[@data-qid='Label-attachments']");
    public static final By LBL_LAST_ASSIGNED_BY = xpath("//*[@data-qid='Label-lastAssignedBy']");
    public static final By LBL_LAST_ASSIGNED_BY_VALUE = xpath("//*[@data-qid='Label-lastAssignedByValue']");
    public static final String ICO_EDIT = "//*[text()='%s']//following::*[@data-qid='Icon-assignedTo']";

    // Create New Case Search Form
    public static final By LBL_CREATE_NEW_CASE = xpath("//*[@data-qid='ModalTitle-createNewCase']");
    public static final By TXT_SUBJECT_PROFILE_NAME = xpath("//*[@data-qid='TextField-subjectProfileName']//input");
    public static final By BTN_CLEAR = xpath("//*[@data-qid='Button-clear']//span");
    public static final By RDO_PROFILE_TYPE_COMPANY = xpath("//*[@data-qid='Radio-company']");
    public static final By RDO_PROFILE_TYPE_INDIVIDUAL = xpath("//*[@data-qid='Radio-individual']");
    public static final By BTN_CREATE_NEW_PROFILE = xpath("//*[@data-qid='Button-createNewProfile']");
    public static final By BTN_NEXT = xpath("//*[@data-qid='Button-next']");
    public static final By LBL_PROFILE_FOUND_COUNT = xpath("//*[@data-qid='Label-profileFoundCount']");
    public static final String LBL_INDIVIDUAL_SUBJECT_PROFILE_RESULTS = "//*[@data-qid='Icon-individual']//following::*[@data-qid='Label-subjectProfileName' and text()='%s']";
    public static final String LBL_COMPANY_SUBJECT_PROFILE_RESULTS = "//*[@data-qid='Icon-company']//following::*[@data-qid='Label-subjectProfileName' and text()='%s']";
    public static final By LBL_WATERMARK = xpath("//*[@data-qid='Label-waterMark']");

    // Add Associate Profile Search Form
    public static final By LBL_ADD_ASSOCIATE_PROFILE = xpath("//*[@data-qid='ModalTitle-addAssociateProfile']");

    // Create New Associate Profile Form
    public static final By LBL_CREATE_NEW_PROFILE = xpath("//*[@data-qid='ModalTitle-createNewProfile']");
    //TODO update data-qid
    public static final By LBL_TYPE_VALUE = xpath("//*[@data-qid='Label-type']//following::span");
    public static final By LBL_CREATE_NEW_PROFILE_COUNTRY = xpath("//*[@data-qid='ModalTitle-createNewProfile']/following::*[@data-qid='Label-country']");
    public static final By BTN_BACK = xpath("//*[@data-qid='Button-back']");
    public static final By BTN_CREATE_PROFILE = xpath("//*[@data-qid='Button-createProfile']");

    // Create New Case New Profile Form
    public static final By LBL_PROFILE_TYPE = xpath("//*[@data-qid='Label-profileType']");
    public static final By LBL_PROFILE_TYPE_VALUE = xpath("//*[@data-qid='Label-profileTypeValue']");
    public static final By TXT_FULL_NAME = xpath("//*[@data-qid='TextField-fullName']//input");
    public static final By ERR_FULL_NAME = xpath("//*[@data-qid='ErrorMessage-fullName']");
    public static final By TXT_SHORT_NAME = xpath("//*[@data-qid='TextField-shortName']//input");
    public static final By LBL_COUNTRY = xpath("//*[@data-qid='Label-country']");
    public static final By TXT_COUNTRY = xpath("//*[@data-qid='TextField-country']//input");
    public static final String LST_COUNTRY_VALUE = "//*[@data-qid='List-country']//span[text()=\"%s\"]";
    public static final By ERR_COUNTRY = xpath("//*[@data-qid='ErrorMessage-country']");
    public static final By DD_SCOPE = xpath("//*[@data-qid='DropDown-scope']");
    public static final String LST_SCOPE_VALUE = "//*[@data-qid='List-scope']//*[@data-value='%s']";
    public static final By ERR_SCOPE = xpath("//*[@data-qid='ErrorMessage-scope']");
    public static final By TXT_ADDITIONAL_SCOPE_CONDITIONS = xpath("//*[@data-qid='TextField-additionalScopeConditions']//textarea");
    public static final By ICO_UPLOAD = xpath("//*[@data-qid='Icon-upload']");
    public static final By TXT_CASE_ID = xpath("//*[@data-qid='TextField-caseId']//input");
    public static final By ERR_CASE_ID = xpath("//*[@data-qid='ErrorMessage-caseId']");
    public static final By TXT_CLIENT_NAME = xpath("//*[@data-qid='TextField-clientName']//input");
    public static final By ERR_CLIENT_NAME = xpath("//*[@data-qid='ErrorMessage-clientName']");
    public static final String LST_CLIENT_NAME_VALUE = "//*[@data-qid='List-clientName']//span[text()='%s']";
    public static final By TXT_CLIENT_CODE = xpath("//*[@data-qid='TextField-clientCode']//input");
    public static final By ERR_CLIENT_CODE = xpath("//*[@data-qid='ErrorMessage-clientCode']");
    public static final By LBL_REGION = xpath("//*[@data-qid='Label-region']");
    public static final By LBL_REGION_VALUE = xpath("//*[@data-qid='Label-regionValue']");
    public static final By LBL_CASE_OWNER = xpath("//*[@data-qid='Label-caseOwner']");
    public static final By LBL_CASE_OWNER_VALUE = xpath("//*[@data-qid='Label-caseOwnerValue']");
    public static final By TXT_WRITER_NAME = xpath("//*[@data-qid='TextField-writerName']//input");
    public static final String LST_WRITER_NAME_VALUE = "//*[@data-qid='Label-writerName']//following::*[text()='%s']";
    public static final By TXT_REVIEWER_NAME = xpath("//*[@data-qid='TextField-reviewerName']//input");
    public static final String LST_REVIEWER_NAME_VALUE = "//*[@data-qid='Label-reviewerName']//following::*[text()='%s']";
    public static final By BTN_CREATE_CASE = xpath("//*[@data-qid='Button-createCase']");
    public static final By TXT_FIRST_NAME = xpath("//*[@data-qid='TextField-firstName']//input");
    public static final By ERR_FIRST_NAME = xpath("//*[@data-qid='ErrorMessage-firstName']");
    public static final By TXT_MIDDLE_NAME = xpath("//*[@data-qid='TextField-middleName']//input");
    public static final By ERR_MIDDLE_NAME = xpath("//*[@data-qid='ErrorMessage-middleName']");
    public static final By TXT_LAST_NAME = xpath("//*[@data-qid='TextField-lastName']//input");
    public static final By ERR_LAST_NAME = xpath("//*[@data-qid='ErrorMessage-lastName']");

    // Create New Case Existing Profile Form
    public static final By LBL_FULL_NAME_VALUE = xpath("//*[@data-qid='Label-fullNameValue']");
    public static final By LBL_SHORT_NAME_VALUE = xpath("//*[@data-qid='Label-shortNameValue']");
    public static final By LBL_PROFILE_ID_VALUE = xpath("//*[@data-qid='Label-profileIdValue']");
    public static final By LBL_COUNTRY_VALUE = xpath("//*[@data-qid='Label-countryValue']");

    // Case Settings Modal
    public static final By TXT_CASE_OWNER = xpath("//*[@data-qid='TextField-caseOwnerName']//input");
    public static final By LBL_CASE_SETTINGS = xpath("//*[@data-qid='ModalTitle-caseSettings']");
    public static final By LBL_CASE_SETTINGS_COUNTRY = xpath("//*[@data-qid='ModalTitle-caseSettings']//following::*[@data-qid='Label-country']");
    public static final By LBL_CASE_ID_VALUE = xpath("//*[@data-qid='Label-caseIdValue']");
    public static final By LBL_CLIENT_NAME_VALUE = xpath("//*[@data-qid='Label-clientNameValue']");
    public static final By LBL_CLIENT_CODE_VALUE = xpath("//*[@data-qid='Label-clientCodeValue']");
    public static final String TXT_WRITER_NAME_VALUE = "//*[@data-qid='Label-writerName']//following::*[@data-tag-index='%s'][1]";
    public static final String TXT_REVIEWER_NAME_VALUE = "//*[@data-qid='Label-reviewerName']//following::*[@data-tag-index='%s'][1]";
    public static final By BTN_CLOSE = xpath("//*[@data-qid='Button-close']");
    public static final By BTN_DOWNLOAD_REPORT = xpath("//*[@data-qid='Button-downloadReport']");
    public static final String ICO_CASE_SETTINGS_ASSIGNED_TO = "//*[text()='%s']//preceding::*[@data-qid='Icon-assignedTo']";

    // Case Editor Page
    public static final By SECTION_SUBJECT_PROFILE = xpath("//*[@data-qid='Section-subjectProfile']");
    public static final By SECTION_RISKS = xpath("//*[@data-qid='Section-risks']");
    public static final By SECTION_SCORING = xpath("//*[@data-qid='Section-scoring']");
    public static final By SECTION_SOURCES = xpath("//*[@data-qid='Section-sources']");
    public static final By OUTLINER_SUBJECT_PROFILE = xpath("//*[@data-qid='PanelSection-subjectProfile']");
    public static final By OUTLINER_RISKS = xpath("//*[@data-qid='PanelSection-risks']");
    public static final By OUTLINER_SCORING = xpath("//*[@data-qid='PanelSection-scoring']");
    public static final By OUTLINER_SOURCES = xpath("//*[@data-qid='PanelSection-sources']");
    public static final By OH_SUBJECT_PROFILE = xpath("//*[@data-qid='PanelSection-subjectProfile']//*[@data-qid='Label-subjectProfile']");
    public static final String LNK_SUBJECT_PROFILE_VALUE = "//*[@data-qid='PanelSection-subjectProfile']//*[text()='%s']";
    public static final By BTN_CASE_SETTINGS = xpath("//*[@data-qid='Button-caseSettings']");
    public static final By BTN_PUBLISH = xpath("//*[@data-qid='Icon-publish']");
    public static final By BTN_ASSIGN_FOR_REVISION = xpath("//*[@data-qid='Button-assignForRevision']");
    public static final By BTN_ASSIGN_FOR_REVIEW = xpath("//*[@data-qid='Button-assignForReview']");
    public static final By LBL_ASSIGNED_TO_VALUE = xpath("//*[@data-qid='Label-assignedToValue']");
    public static final By BTN_ADD = xpath("//*[@data-qid='Button-add']");
    public static final By MENU_ASSOCIATE_PROFILE = xpath("//*[@data-qid='Menu-associateProfile']");
    public static final By MENU_RISK = xpath("//*[@data-qid='Menu-risk']");
    //TODO update data-qid
    public static final By TXT_OUTLINER_RISK = xpath("//*[@data-cid='RiskSelect-input-text-riskType']//input");
    //TODO update data-qid
    public static final String LST_OUTLINER_RISK_VALUE = "//li//*[text()='%s']";

    // Assign For Revision or Review Modal
    public static final By LBL_ASSIGN_FOR_REVISION = xpath("//*[@data-qid='ModalTitle-assignForRevision']");
    public static final By LBL_ASSIGN_FOR_REVIEW = xpath("//*[@data-qid='ModalTitle-assignForReview']");
    public static final String RDO_ASSIGN_TO_SELECTION = "//*[@data-qid='Radio-']//following::span[text()='%s']";
    public static final By BTN_ASSIGN = xpath("//*[@data-qid='Button-assign']");

    // Publish Modal
    public static final By LBL_PUBLISH = xpath("//*[@data-qid='ModalTitle-publish']");
    public static final By MSG_PUBLISH = xpath("//*[@data-qid='Label-publishMessage']");
    public static final By BTN_PUBLISH_CASE = xpath("//*[@data-qid='Button-publishCase']");

    // Profile Data Common Section
    //TODO update data-qid
    public static final By BTN_ADD_PROFILE_DATA = xpath("//button//*[@data-cid='IconPlusBox']");
    //TODO update data-qid
    public static final By BTN_EDIT_DATA = xpath("//button//*[@data-cid='IconSourceEdit']");
    //TODO update data-qid
    public static final By BTN_REMOVE_DATA = xpath("//button//*[@data-cid='IconSourceRemove']");
    //TODO update data-qid
    public static final By BTN_COMMENT_DATA = xpath("//button//*[@data-cid='IconSourceComment']");
    //TODO update data-qid
    public static final By TXT_SEARCH_PROFILE_DATA = xpath("//*[@data-cid='StyledGroupedAutocomplete-input-text']//input");
    public static final By LBL_SUBJECT_PROFILE = xpath("//*[@data-qid='Section-subjectProfile']//*[@data-qid='Label-subjectProfile']");
    public static final String LBL_SUBJECT_PROFILE_COUNTRY_VALUE = "//*[@data-qid='Label-countryValue'][%s]";
    //TODO update data-qid
    public static final By LBL_SUBJECT_PROFILE_PROFILE_TYPE_VALUE = xpath("//*[text()='Type']//following::div[1]");
    //TODO update data-qid
    public static final By LBL_SHORT_NAME_NAME_VALUE = xpath("//*[text()='Short Name']//following::*//*[@data-qid='Label-nameValue']");
    //TODO update data-qid
    public static final String LBL_CASE_EDITOR_HEADER = "//h2[text()='%s']";
    //TODO update data-qid
    public static final String BTN_ADD_SOURCE_RECORD = "//h2[text()='%s']//following::button[1]";
    //TODO update data-qid
    public static final String LST_PROFILE_DATA_VALUE = "//*[@data-qid='List-undefined']//*[text()='%s']";
    public static final By BTN_SOURCE_TYPES = xpath("//*[@data-qid='Button-sourceTypes']");
    public static final String LBL_DATA_VALUE = "//*[text()='%s']";
    public static final By LBL_SOURCE_TYPE_HEADER = xpath("//*[@data-qid='Section-sources']//h2");
    public static final By SECTION_GENERAL_INFORMATION = xpath("//*[@data-qid='Section-generalInformation']");
    public static final String LBL_DATA_ENTRY_SOURCE_VALUE = "//*[text()='%s']/parent::*//*[@data-qid='Label-sourceValue']";
    public static final By LBL_PROFILE_DATA_CATEGORY_GENERAL_INFORMATION = xpath("//h2[@data-qid='Label-generalInformation']");
    public static final String LBL_GENERAL_INFORMATION_PROFILE_DATA = "//*[@data-qid='Section-generalInformation']//*[text()='%s']";
    public static final By SECTION_NAMES = xpath("//*[@data-qid='Section-name']");
    public static final By LBL_PROFILE_DATA_CATEGORY_NAMES = xpath("//h2[@data-qid='Label-name']");
    public static final String LBL_NAMES_PROFILE_DATA = "//*[@data-qid='Section-name']//*[text()='%s']";
    public static final By SECTION_KEY_INDUSTRY = xpath("//*[@data-qid='Section-generalInformation']//*[@data-qid='Section-keyIndustry']");

    // Remove Data Entry Modal
    public static final By LBL_REMOVE_DATA_ENTRY = xpath("//*[contains(@data-qid,'ModalTitle')]");

    // Profile Data: Company Background, Compliance And Ethics, Individual Background
    public static final By TXT_DETAILS = xpath("//*[@data-qid='TextField-details']//textarea");
    public static final String LBL_DETAILS_VALUE = "//*[@data-qid='Label-detailsValue' and text()='%s']";

    // Profile Data: Part of Group of Companies
    public static final By RDO_PART_OF_GROUP_OF_COMPANIES_YES = xpath("//*[@value='true']/ancestor::*[@data-qid='Radio-partOfGroupOfCompanies']");
    public static final By RDO_PART_OF_GROUP_OF_COMPANIES_NO = xpath("//*[@value='false']/ancestor::*[@data-qid='Radio-partOfGroupOfCompanies']");
    public static final By TXT_NAME_OF_GROUP = xpath("//*[@data-qid='TextField-nameOfGroup']//input");
    //TODO update data-qid
    public static final By TXT_PROFILE_DATA_COMMENT = xpath("//*[text()='Comment']/following::*[@class='fr-element fr-view']");
    public static final String LBL_PART_OF_GROUP_OF_COMPANIES_VALUE = "//*[@data-qid='Label-partOfGroupOfCompaniesValue' and text()='%s']";
    public static final String LBL_NAME_OF_GROUP_VALUE = "//*[@data-qid='Label-nameOfGroupValue' and text()='%s']";
    public static final String LBL_COMMENTS_VALUE = "//*[@data-qid='Label-commentsValue']//*[text()='%s']";

    // Profile Data: Website
    public static final By TXT_WEBSITE_DESCRIPTION = xpath("//*[@data-qid='TextField-websiteDescription']//input");
    public static final By TXT_URL = xpath("//*[@data-qid='TextField-url']//input");
    public static final String LBL_WEBSITE_DESCRIPTION_VALUE = "//*[@data-qid='Label-websiteDescriptionValue' and text()='%s']";
    public static final By ERR_URL = xpath("//*[@data-qid='ErrorMessage-url']");
    public static final String LBL_URL_VALUE = "//*[@data-qid='Label-urlValue' and text()='%s']";

    // Profile Data: Email
    public static final By TXT_EMAIL_ADDRESS = xpath("//*[@data-qid='TextField-emailAddress']//input");

    // Profile Data: Key Industry
    public static final By TXT_INDUSTRY = xpath("//*[@data-qid='TextField-industry']//input");
    //TODO update data-qid fix in integrating and key industry and Risk Summary Risk Insight Form
    public static final By BTN_SAVE = xpath("//*[contains(@data-qid,'save')]");
    public static final String LST_INDUSTRY_VALUE = "//*[@data-qid='List-industry']//*[text()='%s']";
    public static final String TXT_SOURCE = "(//*[@data-qid='TextField-source']//input)[%s]";
    public static final String LST_SOURCE_VALUE = "//*[contains(@data-qid,'List-source')]//*[contains(text(),'%s')]";
    //TODO update data-qid when fix in profile data forms
    public static final By BTN_ADD_SOURCE = xpath("//*[text()='Add Source']/parent::button");
    public static final By LBL_INDUSTRY_VALUE = xpath("//*[@data-qid='Label-industryValue']");

    // Profile Data: Full Name
    public static final By TXT_NAME = xpath("//*[@data-qid='TextField-name']//input");
    public static final By TXT_FROM_DAY = xpath("//*[text()='From']/parent::div[1]//*[@data-qid='TextField-datePickerDay']//input");
    public static final By TXT_FROM_MONTH = xpath("//*[text()='From']/parent::div[1]//*[@data-qid='TextField-datePickerMonth']//input");
    public static final By TXT_FROM_YEAR = xpath("//*[text()='From']/parent::div[1]//*[@data-qid='TextField-datePickerYear']//input");
    public static final By CHK_FROM_PRESENT = xpath("//*[text()='From']/parent::div[1]//*[@data-qid='CheckBox-Present']");
    public static final By TXT_TO_DAY = xpath("//*[text()='To']/parent::div[1]//*[@data-qid='TextField-datePickerDay']//input");
    public static final By TXT_TO_MONTH = xpath("//*[text()='To']/parent::div[1]//*[@data-qid='TextField-datePickerMonth']//input");
    public static final By TXT_TO_YEAR = xpath("//*[text()='To']/parent::div[1]//*[@data-qid='TextField-datePickerYear']//input");
    public static final By CHK_TO_PRESENT = xpath("//*[text()='To']/parent::div[1]//*[@data-qid='CheckBox-Present']");
    public static final String LBL_NAMES_NAME_VALUE = "//*[@data-qid='Label-nameValue' and text()='%s']";
    public static final String LBL_DATA_ENTRY_FROM_VALUE = "//*[@data-qid='Label-fromValue' and text()='%s']";
    public static final String LBL_DATA_ENTRY_TO_VALUE = "//*[@data-qid='Label-toValue' and text()='%s']";


    // Associate Profile Section
    public static final By OH_ASSOCIATE_PROFILE = xpath("//*[@data-qid='PanelSection-associateProfile']//*[@data-qid='Label-associateProfile']");
    public static final String LNK_PERSONAL_DETAILS = "(//*[@data-qid='PanelSection-associateProfile']//*[@data-qid='Label-associateProfile'])[%s]//following::*[@data-qid='Link-personalDetails']";
    public static final String LNK_LOCATION = "(//*[@data-qid='PanelSection-associateProfile']//*[@data-qid='Label-associateProfile'])[%s]//following::*[@data-qid='Link-location']";
    public static final String LNK_NAMES = "(//*[@data-qid='PanelSection-associateProfile']//*[@data-qid='Label-associateProfile'])[%s]//following::*[@data-qid='Link-names']";
    //TODO update data-qid
    public static final String LBL_ASSOCIATE_PROFILE = "//h1[contains(text(),'%s')]";
    //TODO update data-qid
    public static final String BTN_ASSOCIATE_PROFILE_SOURCE_TYPES = "//h1[contains(text(),'%s')]//following::*[@data-qid='Button-sourceTypes']";
    //TODO update data-qid
    public static final String LBL_ASSOCIATE_PROFILE_TYPE_VALUE = "//h1[contains(text(),'%s')]//following::*[text()='Type']//following::div";
    //TODO update data-qid
    public static final String LBL_ASSOCIATE_PROFILE_FULL_NAME_VALUE = "//h1[contains(text(),'%s')]//following::*[@data-qid='Label-nameValue']";
    //TODO update data-qid
    public static final String LBL_ASSOCIATE_PROFILE_SHORT_NAME_VALUE = "//h1[contains(text(),'%s')]//following::*[text()='Short Name']//following::*[@data-qid='Label-nameValue']";
    //TODO update data-qid
    public static final String LBL_ASSOCIATE_PROFILE_COUNTRY_VALUE = "//h1[contains(text(),'%s')]//following::*[@data-qid='Label-countryValue']";

    // Source Type Modal
    //TODO update data-qid
    public static final String CHK_SOURCE_TYPE = "//*[text()='%s']//parent::span//preceding-sibling::span";
    //TODO update data-qid
    public static final String CHK_SOURCE_CHECKED = "//*[@data-cid='IconCheckboxChecked']//following::*[text()='%s']";

    // Risks Section
    public static final String BTN_ADD_RISK_SUMMARY = "//*[@data-qid='Label-%s']//following::*[@data-qid='Button-addRiskSummary']";
    public static final String BTN_ADD_RISK_INSIGHT = "//*[@data-qid='Label-%s']//following::*[@data-qid='Button-addRiskInsight']";
    public static final By LBL_INTEGRITY_RISKS = xpath("//*[@data-qid='Label-integrityRisks']");
    public static final By LBL_ESG_RISKS = xpath("//*[@data-qid='Label-environmentSocialAndGovernanceRisksEsg']");
    public static final By LBL_OAQ_RISKS = xpath("//*[@data-qid='Label-operationalAndQualityRisks']");
    public static final By LBL_FINANCIAL_RISKS = xpath("//*[@data-qid='Label-financialRisks']");
    public static final By LBL_ENGAGEMENT_RISKS = xpath("//*[@data-qid='Label-engagementRisks']");
    public static final String LBL_INTEGRITY_RISKS_RISK_AREA = "//*[@data-qid='Label-integrityRisks']//following::*[@data-qid='Label-%s']";
    public static final String LBL_ESG_RISKS_RISK_AREA = "//*[@data-qid='Label-environmentSocialAndGovernanceRisksEsg']//following::*[@data-qid='Label-%s']";
    public static final String LBL_OAQ_RISKS_RISK_AREA = "//*[@data-qid='Label-operationalAndQualityRisks']//following::*[@data-qid='Label-%s']";
    public static final String LBL_FINANCIAL_RISKS_RISK_AREA = "//*[@data-qid='Label-financialRisks']//following::*[@data-qid='Label-%s']";
    public static final String LBL_ENGAGEMENT_RISKS_RISK_AREA = "//*[@data-qid='Label-engagementRisks']//following::*[@data-qid='Label-%s']";

    // Risk SummaryForm
    public static final By DD_TYPE = xpath("//*[@data-qid='DropDown-type']");
    public static final String LST_TYPE_VALUE = "//*[@data-qid='List-type']//*[@data-value='%s']";
    public static final By TXT_RISK_SUMMARY = xpath("//*[@data-qid='TextField-summary']//*[@class='fr-element fr-view']");
    public static final By ERR_RISK_SUMMARY = xpath("//*[@data-qid='ErrorMessage-summary']");

    // Risk SummaryRead View
    public static final String LBL_RISK_SUMMARY_PUBLIC_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-summaryPublicValue']//p";
    public static final String LBL_RISK_SUMMARY_CLIENT_SPECIFIC_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-summaryClientSpecificValue']//p";
    public static final String LBL_RISK_SUMMARY_SOURCES_VALUE = "//*[text()='%s']/following::*[@data-qid='Label-sourceValue']";

    // Risk Insight Form
    public static final By TXT_RISK_INSIGHT = xpath("//*[@data-qid='TextField-riskInsight']//*[@class='fr-element fr-view']");
    public static final By ERR_RISK_INSIGHT = xpath("//*[@data-qid='ErrorMessage-riskInsight']");
    public static final By SWTCH_FURTHER_STEPS = xpath("//*[@data-qid='Radio-furtherSteps']");
    public static final By TT_FURTHER_STEPS = xpath("//*[@role='tooltip']//*[contains(@class,'tooltip')]");
    public static final By TXT_RECOMMENDATION = xpath("//*[@data-qid='TextField-recommendation']//*[@class='fr-element fr-view']");
    public static final By ERR_RECOMMENDATION = xpath("//*[@data-qid='ErrorMessage-recommendation']");

    // Risk Inisght Read View
    public static final String LBL_RISK_INSIGHT_PUBLIC_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-riskInsightPublicValue']//p";
    public static final String LBL_RISK_INSIGHT_CLIENT_SPECIFIC_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-riskInsightClientSpecificValue']//p";
    public static final String LBL_RISK_INSIGHT_RECOMMENDATION_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-recommendationValue']//p";

    // Remove Risk Summary Modal
    public static final By LBL_REMOVE_RISK_SUMMARY =  xpath("//*[@data-qid='ModalTitle-removeRiskSummary']");
    public static final By BTN_REMOVE =  xpath("//*[@data-qid='Button-remove']");
    public static final By MSG_CASE_EDITOR_MODAL =  xpath("//*[@data-qid='Modal-message']");

    // Remove Risk Insight Modal
    public static final By LBL_REMOVE_RISK_INSIGHT =  xpath("//*[@data-qid='ModalTitle-removeRiskInsight']");

    // Risks Outliner
    public static final By LNK_RISKS_OUTLINER_INTEGRITY_RISKS = xpath("//*[@data-qid='PanelSection-risks']//*[@data-qid='Link-integrityRisks']");
    public static final By LNK_RISKS_OUTLINER_ESG_RISKS = xpath("//*[@data-qid='PanelSection-risks']//*[@data-qid='Link-environmentSocialAndGovernanceRisksEsg']");
    public static final By LNK_RISKS_OUTLINER_OAQ_RISKS = xpath("//*[@data-qid='PanelSection-risks']//*[@data-qid='Link-operationalAndQualityRisks']");
    public static final By LNK_RISKS_OUTLINER_FINANCIAL_RISKS = xpath("//*[@data-qid='PanelSection-risks']//*[@data-qid='Link-financialRisks']");
    public static final By LNK_RISKS_OUTLINER_ENGAGEMENT_RISKS = xpath("//*[@data-qid='PanelSection-risks']//*[@data-qid='Link-engagementRisks']");
    public static final String LNK_RISKS_OUTLINER_INTEGRITY_RISKS_RISK_AREA = "//*[@data-qid='Link-integrityRisks']//*[text()='%s']";
    public static final String LNK_RISKS_OUTLINER_ESG_RISKS_RISK_AREA = "//*[@data-qid='Link-environmentSocialAndGovernanceRisksEsg']//*[text()='%s']";
    public static final String LNK_RISKS_OUTLINER_OAQ_RISKS_RISK_AREA = "//*[@data-qid='Link-operationalAndQualityRisks']//*[text()='%s']";
    public static final String LNK_RISKS_OUTLINER_FINANCIAL_RISKS_RISK_AREA = "//*[@data-qid='Link-financialRisks']//*[text()='%s']";
    public static final String LNK_RISKS_OUTLINER_ENGAGEMENT_RISKS_RISK_AREA = "//*[@data-qid='Link-engagementRisks']//*[text()='%s']";

    // Overall Risk Rating Modal
    //TODO change data-qid
    public static final By TXT_OVERALL_RISK_RATING = xpath("//*[@data-cid='IntegraRatingEdit-input-score']//input");
    //TODO change data-qid
    public static final By TXT_EXECUTIVE_SUMMARY_PUBLIC = xpath("//*[text()='Executive Summary - Public']//following::*[@class='fr-element fr-view']");
    //TODO change data-qid
    public static final By TXT_EXECUTIVE_SUMMARY_CLIENT = xpath("//*[text()='Executive Summary - Client Specific']//following::*[@class='fr-element fr-view']");

    // Overall Risk Rating Section
    //TODO update data-qid
    public static final By LBL_OVER_ALL_RISK_RATING = xpath("//h2[text()='Overall Risk Rating']");
    //TODO update data-qid
    public static final By LBL_OVER_ALL_RISK_RATING_VALUE = xpath("//h2[text()='Overall Risk Rating']//following::div[1]");
    //TODO update data-qid
    public static final By LBL_PUBLIC_SUMMARY_VALUE = xpath("//*[text()='- Public']//following::p[1]");
    //TODO update data-qid
    public static final By LBL_CLIENT_SPECIFIC_SUMMARY_VALUE = xpath("//*[text()='- Client Specific']//following::p[1]");

    // Scoring - Risk Categories Section
    public static final By LBL_SCORING_INTEGRITY_RISKS = xpath("//*[@data-qid='Section-riskCategories']//*[@data-qid='Label-integrityRisks']");
    public static final By LBL_SCORING_INTEGRITY_RISKS_SCORE = xpath("//*[@data-qid='Label-integrityRisksScore']");
    public static final String LBL_SCORING_INTEGRITY_RISKS_RISK_AREA = "//*[@data-qid='Section-integrityRisks']//*[text()='%s']";
    public static final String LBL_SCORING_INTEGRITY_RISKS_RISK_AREA_SCORE = "//*[@data-qid='Section-integrityRisks']//*[text()='%s']//following-sibling::*[@data-qid='Label-integrityRisksAreaScore']";
    public static final By LBL_SCORING_ESG_RISKS = xpath("//*[@data-qid='Section-riskCategories']//*[@data-qid='Label-environmentSocialAndGovernanceRisksEsg']");
    public static final By LBL_SCORING_ESG_RISKS_SCORE = xpath("//*[@data-qid='Label-environmentSocialAndGovernanceRisksEsgScore']");
    public static final String LBL_SCORING_ESG_RISKS_RISK_AREA = "//*[@data-qid='Section-environmentSocialAndGovernanceRisksEsg']//*[text()='%s']";
    public static final String LBL_SCORING_ESG_RISKS_RISK_AREA_SCORE = "//*[@data-qid='Section-environmentSocialAndGovernanceRisksEsg']//*[text()='%s']//following-sibling::*[@data-qid='Label-environmentSocialAndGovernanceRisksEsgAreaScore']";
    public static final By LBL_SCORING_OAQ_RISKS = xpath("//*[@data-qid='Section-riskCategories']//*[@data-qid='Label-operationalAndQualityRisks']");
    public static final By LBL_SCORING_OAQ_RISKS_SCORE = xpath("//*[@data-qid='Label-operationalAndQualityRisksScore']");
    public static final String LBL_SCORING_OAQ_RISKS_RISK_AREA = "//*[@data-qid='Section-operationalAndQualityRisks']//*[text()='%s']";
    public static final String LBL_SCORING_OAQ_RISKS_RISK_AREA_SCORE = "//*[@data-qid='Section-operationalAndQualityRisks']//*[text()='%s']//following-sibling::*[@data-qid='Label-operationalAndQualityRisksAreaScore']";
    public static final By LBL_SCORING_FINANCIAL_RISKS = xpath("//*[@data-qid='Section-riskCategories']//*[@data-qid='Label-financialRisks']");
    public static final By LBL_SCORING_FINANCIAL_RISKS_SCORE = xpath("//*[@data-qid='Label-financialRisksScore']");
    public static final String LBL_SCORING_FINANCIAL_RISKS_RISK_AREA = "//*[@data-qid='Section-financialRisks']//*[text()='%s']";
    public static final String LBL_SCORING_FINANCIAL_RISKS_RISK_AREA_SCORE = "//*[@data-qid='Section-financialRisks']//*[text()='%s']//following-sibling::*[@data-qid='Label-financialRisksAreaScore']";
    public static final By LBL_SCORING_ENGAGEMENT_RISKS = xpath("//*[@data-qid='Section-riskCategories']//*[@data-qid='Label-engagementRisks']");
    public static final By LBL_SCORING_ENGAGEMENT_RISKS_SCORE = xpath("//*[@data-qid='Label-engagementRisksScore']");
    public static final String LBL_SCORING_ENGAGEMENT_RISKS_RISK_AREA = "//*[@data-qid='Section-engagementRisks']//*[text()='%s']";
    public static final String LBL_SCORING_ENGAGEMENT_RISKS_RISK_AREA_SCORE = "//*[@data-qid='Section-engagementRisks']//*[text()='%s']//following-sibling::*[@data-qid='Label-engagementRisksAreaScore']";

    // Scoring - Risk Categories Form
    public static final By TXT_INTEGRITY_RISKS = xpath("//*[@data-qid='TextField-integrityRisksTotal']//input");
    public static final By TXT_ESG_RISKS = xpath("//*[@data-qid='TextField-environmentSocialAndGovernanceRisksEsgTotal']//input");
    public static final By TXT_OAQ_RISKS = xpath("//*[@data-qid='TextField-operationalAndQualityRisksTotal']//input");
    public static final By TXT_FINANCIAL_RISKS = xpath("//*[@data-qid='TextField-financialRisksTotal']//input");
    public static final By TXT_ENGAGEMENT_RISKS = xpath("//*[@data-qid='TextField-engagementRisksTotal']//input");
    public static final String TXT_RISK_AREA = "//*[@data-qid='TextField-%s']//input";
    public static final By ERR_RISK_SCORE = xpath("//*[contains(@data-qid,'ErrorMessage-')]");

    // Common Source Record Modal
    public static final By LBL_RECORD_ID_VALUE = xpath("//*[@data-qid='Modal-sourceRecord']//*[@data-qid='Label-recordIdValue']");
    public static final By TXT_DAY = xpath("//*[@data-qid='TextField-datePickerDay']//input");
    public static final String TXT_DAY_VALUE = "//*[@data-qid='List-datePickerDay']//*[text()='%s']";
    public static final By TXT_MONTH = xpath("//*[@data-qid='TextField-datePickerMonth']//input");
    public static final String TXT_MONTH_VALUE = "//*[@data-qid='List-datePickerMonth']//*[text()='%s']";
    public static final By TXT_YEAR = xpath("//*[@data-qid='TextField-datePickerYear']//input");
    public static final String TXT_YEAR_VALUE = "//*[@data-qid='List-datePickerYear']//*[text()='%s']";
    public static final By CHK_PRESENT = xpath("//*[@data-qid='CheckBox-Present']");
    public static final By RDO_NO_HIT_NO_INFO = xpath("//*[contains(@data-qid,'Radio-no')]");
    public static final By LBL_NO_HIT_NO_INFO = xpath("//*[contains(@data-qid,'Label-no')]");
    public static final By ERR_SOURCE_RECORD_SOURCE_LINK = xpath("//*[contains(@data-qid,'ErrorMessage-')]");
    public static final String TXT_INVOLVING = "(//*[@data-qid='TextField-involving']//input)[%s]";
    public static final String LST_INVOLVING_VALUE = "//*[@data-qid='List-involving']//*[text()='%s']";
    public static final By BTN_ADD_ENTRY = xpath("//*[@data-qid='Button-addEntry']");
    public static final String TXT_RISK = "(//*[@data-qid='TextField-risk']//input)[%s]";
    public static final String LST_RISK_VALUE = "//*[@data-qid='List-risk']//*[text()='%s']";
    public static final By BTN_ADD_RISK = xpath("//*[@data-qid='Button-addRisk']");
    public static final By TXT_COMMENT = xpath("//*[@data-qid='TextField-comment']//textarea");
    //TODO update data-qid fix in integrating and profile data forms
    public static final By BTN_CANCEL = xpath("//*[contains(@data-qid,'cancel')]");
    public static final String BTN_REMOVE_INFORMATION = "//*[@value='%s']//following::*[@data-cid='IconClose']";
    public static final By LBL_NO_MATCHES_FOUND = xpath("//*[@data-qid='Message-noMatchesFound']");

    // Relevancy Irrelevant Modal
    //TODO change data-qid incorrect name
    public static final By LBL_SET_TO_IRRELEVANT = xpath("//*[contains(@data-qid,'ToIrrelevant')]");

    // Client Documentation Modal
    public static final By TXT_DOCUMENT_SUMMARY = xpath("//*[@data-qid='TextField-documentSummary']//textarea");

    // Global Compliance Checks
    public static final By TXT_GCSOURCE = xpath("//*[@data-qid='TextField-gcSource']//input");
    public static final String TXT_PRIMARY_SOURCE = "(//*[@data-qid='TextField-primarySource']//input)[%s]";
    public static final String ERR_PRIMARY_SOURCE = "(//*[@data-qid='ErrorMessage-primarySource'])[%s]";
    public static final By BTN_ADD_PRIMARY_SOURCE = xpath("//*[@data-qid='Button-addPrimarySource']");

    // Interviews Modal
    public static final By TXT_ROLE = xpath("//*[@data-qid='TextField-role']//input");

    // Media, Website, Litigation Modal
    public static final By TXT_LINK = xpath("//*[@data-qid='TextField-link']//input");

    // Reputational Intelligence Modal
    public static final By TXT_INTERVIEWEE = xpath("//*[@data-qid='TextField-interviewee']//input");
    public static final By TXT_COMPANY = xpath("//*[@data-qid='TextField-company']//input");
    public static final By TXT_POSITION = xpath("//*[@data-qid='TextField-position']//input");
    public static final By DD_TYPE_OF_SOURCE = xpath("//*[@data-qid='DropDown-typeOfSource']");
    public static final String LST_TYPE_OF_SOURCE_VALUE = "//*[@data-qid='List-typeOfSource']//*[@data-value='%s']";
    public static final By TXT_SUMMARY = xpath("//*[@data-qid='TextField-summary']//textarea");

    // Site Visit Modal
    public static final By TXT_ADDRESS = xpath("//*[@data-qid='TextField-address']//textarea");
    public static final By RDO_CONFIRMED = xpath("//*[@data-qid='Radio-confirmed']");
    public static final By TXT_DETAILS_OF_SITE_VISIT = xpath("//*[@data-qid='TextField-detailsOfSiteVisit']//textarea");
    public static final String TXT_PHOTO_DESCRIPTION = "(//*[@data-qid='TextField-photoDescription']//input)[%s]";
    public static final By BTN_ADD_PHOTO = xpath("//*[@data-qid='Button-addPhoto']");

    // Source Record Read View Section
    public static final String RDO_RELEVANCY = "//*[@data-qid='Section-%s']//*[@data-qid='Radio-relevancy']";
    //TODO change data-qid incorrect name
    public static final By BTN_CONTINUE = xpath("//*[@data-qid='Button-leaveThisPage']");
    public static final By BTN_PROCEED = xpath("//*[@data-qid='Button-proceed']");
    public static final String LBL_SOURCE_RECORD_DATE_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-dateValue']";
    public static final String MSG_NO_HIT = "//*[@data-qid='Section-%s']//*[@data-qid='Label-noHitMessage']";
    public static final String LBL_SOURCE_RECORD_INVOLVING_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-involvingValue']";
    public static final String LBL_SOURCE_RECORD_RISK_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-riskValue']";
    public static final String LBL_SOURCE_RECORD_COMMENT_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-commentValue']";
    public static final By IMG_LOADER = xpath("//*[@data-qid='Image-loader']");

    // Client Documentation Source Section
    public static final String LBL_SOURCE_RECORD_DOCUMENT_SUMMARY_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-documentSummaryValue']";

    // Global Compliance Checks Section
    public static final String LBL_SOURCE_RECORD_GC_SOURCE_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-gcSourceValue']";
    public static final String LBL_SOURCE_RECORD_PRIMARY_SOURCE_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-primarySourceValue']";

    // Interviews Source Section
    public static final String LBL_SOURCE_RECORD_ROLE_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-roleValue']";

    // Media, Website, Litigation Modal Source Section
    public static final String LBL_SOURCE_RECORD_LINK_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-linkValue']";

    // Reputation Source Section
    public static final String LBL_SOURCE_RECORD_INTERVIEWEE_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-intervieweeValue']";
    public static final String LBL_SOURCE_RECORD_COMPANY_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-companyValue']";
    public static final String LBL_SOURCE_RECORD_POSITION_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-positionValue']";
    public static final String LBL_SOURCE_RECORD_TYPE_OF_SOURCE_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-typeOfSourceValue']";
    public static final String LBL_SOURCE_RECORD_SUMMARY_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-summaryValue']";

    // Site Visit Source Section
    public static final String LBL_SOURCE_RECORD_ADDRESS_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-addressValue']";
    public static final String LBL_SOURCE_RECORD_CONFIRMED_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-confirmedValue']";
    public static final String LBL_SOURCE_RECORD_DETAILS_OF_THE_VISIT_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-detailsOfSiteVisitValue']";
    public static final String LBL_SOURCE_RECORD_PHOTO_VALUE = "//*[@data-qid='Section-%s']//*[@data-qid='Label-photoValue']";

    // Scoring Outliner Section
    public static final By LNK_RISK_CATEGORIES = xpath("//*[@data-qid='PanelSection-scoring']//*[@data-qid='Link-riskCategories']");
    public static final By LNK_SCORING_OUTLINER_INTEGRITY_RISKS = xpath("//*[@data-qid='PanelSection-scoring']//*[@data-qid='Link-integrityRisks']");
    public static final By LNK_SCORING_OUTLINER_ESG_RISKS = xpath("//*[@data-qid='PanelSection-scoring']//*[@data-qid='Link-environmentSocialAndGovernanceRisksEsg']");
    public static final By LNK_SCORING_OUTLINER_OAQ_RISKS = xpath("//*[@data-qid='PanelSection-scoring']//*[@data-qid='Link-operationalAndQualityRisks']");
    public static final By LNK_SCORING_OUTLINER_FINANCIAL_RISKS = xpath("//*[@data-qid='PanelSection-scoring']//*[@data-qid='Link-financialRisks']");
    public static final By LNK_SCORING_OUTLINER_ENGAGEMENT_RISKS = xpath("//*[@data-qid='PanelSection-scoring']//*[@data-qid='Link-engagementRisks']");

    // Sources Outliner Section
    public static final By LNK_SOURCES_GLOBAL_COMPLIANCE_CHECKS = xpath("//*[@data-qid='Link-globalComplianceChecks']");
    public static final By LNK_SOURCES_MEDIA = xpath("//*[@data-qid='Link-media']");
    public static final By LNK_SOURCES_WEBSITE = xpath("//*[@data-qid='Link-website']");
    public static final By LNK_SOURCES_CLIENT_DOCUMENTATION = xpath("//*[@data-qid='Link-clientDocumentation']");
    public static final By LNK_SOURCES_OFFICIAL_DOCUMENTATION = xpath("//*[@data-qid='Link-officialDocumentation']");
    public static final By LNK_SOURCES_LITIGATION = xpath("//*[@data-qid='Link-litigationBankruptcyRegulatoryLawEnforcementLbrl']");
    public static final By LNK_SOURCES_REPUTATION = xpath("//*[@data-qid='Link-reputationalIntelligence']");
    public static final By LNK_SOURCES_INTERVIEWS = xpath("//*[@data-qid='Link-interviews']");
}