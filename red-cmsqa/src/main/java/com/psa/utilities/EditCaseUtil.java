package com.psa.utilities;

import com.psa.data.CaseDetails;
import com.psa.data.CaseStatus;
import com.psa.data.DataEntry;
import com.psa.data.ProfileData;
import com.psa.data.ProfileDetails;
import com.psa.data.ProfileType;
import com.psa.data.ReputationSourceType;
import com.psa.data.Risk;
import com.psa.data.RiskCategory;
import com.psa.data.RiskInformationType;
import com.psa.data.RiskInsight;
import com.psa.data.RiskSummary;
import com.psa.data.SourceRecord;
import com.psa.data.SourceType;
import com.psa.data.ToggleOption;
import com.psa.data.YesNo;
import com.psa.data.SubjectProfile.KeyIndustry;
import com.psa.data.Module;
import com.psa.library.TestInitReference;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.psa.data.GenericData.*;
import static com.psa.pages.ObjectReference.Bys.XPATH;

public class EditCaseUtil extends TestInitReference {

    /**
     * Use this function to publish case
     */
    public void publishCase() {
        click(BTN_PUBLISH);
        click(BTN_PUBLISH_CASE);
        waitElementToBeInvisible(BTN_PUBLISH_CASE);
        waitElementToBeVisible(BTN_CREATE_NEW_CASE);
    }

    /**
     * Use this function to publish case and set status of case details to published
     */
    public void publishCase(CaseDetails caseDetails) {
        this.publishCase();
        caseDetails.setCaseStatus(CaseStatus.PUBLISHED);
    }

    /**
     * Use this function to assign case for revision
     * @param caseDetails details of case to assign for revision
     * @param fullNameToAssign assigned to
     */
    public void assignForRevision(CaseDetails caseDetails, String fullNameToAssign) throws IllegalAccessException {
        click(BTN_ASSIGN_FOR_REVISION);
        click((createDynamicLocator(XPATH, RDO_ASSIGN_TO_SELECTION, fullNameToAssign)));
        click(BTN_ASSIGN);
        waitElementToBeInvisible(BTN_ASSIGN);
        caseDetails.setAssignedTo(fullNameToAssign);
        if (fullNameToAssign.equals(caseDetails.getCaseOwner())) {
            caseDetails.setCaseStatus(CaseStatus.DRAFT);
        } else {
            caseDetails.setCaseStatus(CaseStatus.FOR_REVISION);
        }
    }

    /**
     * Use this function to assign case for review
     * @param caseDetails details of case to assign for review
     * @param fullNameToAssign assigned to
     */
    public void assignForReview(CaseDetails caseDetails, String fullNameToAssign) throws IllegalAccessException {
        click(BTN_ASSIGN_FOR_REVIEW);
        click((createDynamicLocator(XPATH, RDO_ASSIGN_TO_SELECTION, fullNameToAssign)));
        click(BTN_ASSIGN);
        waitElementToBeInvisible(BTN_ASSIGN);
        if (fullNameToAssign.equals(caseDetails.getCaseOwner())) {
            caseDetails.setCaseStatus(CaseStatus.IN_REVIEW);
        } else {
            caseDetails.setCaseStatus(CaseStatus.FOR_REVIEW);
        }
    }

    /**
     * Use this function verify all elements in Case Settings Form
     */
    public void verifyCaseSettingsForm(ProfileDetails profileDetails, CaseDetails caseDetails) throws IllegalAccessException {

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CASE_SETTINGS), Module.CASE_SETTINGS.getModule()));
            this.verifyNonEditableValuesInCaseSettingsForm(profileDetails, caseDetails);
            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, ICO_CASE_SETTINGS_ASSIGNED_TO, caseDetails.getAssignedTo())));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SCOPE_VALUE), caseDetails.getScope().getScope()));
            click(LBL_CASE_SETTINGS);
            if (caseDetails.getAdditionalScope() != null) {
                Assert.assertTrue(isActualValueEqualsValue(getText(TXT_ADDITIONAL_SCOPE_CONDITIONS), caseDetails.getAdditionalScope()));
            }
            Assert.assertTrue(isElementEnabled(TXT_ADDITIONAL_SCOPE_CONDITIONS));
            Assert.assertTrue(isElementEnabled(ICO_UPLOAD));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CASE_OWNER_VALUE), caseDetails.getCaseOwner()));
            if (caseDetails.getWriter() != null) {
                for (int writerCount = 0; writerCount <= caseDetails.getWriter().length - 1; writerCount++) {
                    Assert.assertTrue(isActualValueContainsValue(getText(createDynamicLocator(XPATH, TXT_WRITER_NAME_VALUE, writerCount)), caseDetails.getWriter()[writerCount].getFullName()));
                }
            }
            Assert.assertTrue(isElementEnabled(TXT_WRITER_NAME));
            if (caseDetails.getReviewer() != null) {
                for (int reviewerCount = 0; reviewerCount <= caseDetails.getReviewer().length - 1; reviewerCount++) {
                    Assert.assertTrue(isActualValueContainsValue(getText(createDynamicLocator(XPATH, TXT_REVIEWER_NAME_VALUE, reviewerCount)), caseDetails.getReviewer()[reviewerCount].getFullName()));
                }
            }
            Assert.assertTrue(isElementEnabled(TXT_REVIEWER_NAME));
            Assert.assertTrue(isElementEnabled(BTN_SAVE));
            Assert.assertTrue(isElementEnabled(BTN_DOWNLOAD_REPORT));
            pass("All elements were verified in the Case Settings Form. - verifyCaseSettingsForm");
        } catch (AssertionError e) {
            fail("All elements should be verified in the Case Settings Form. - verifyCaseSettingsForm");
            throw e;
        }
    }

    /**
     * Use this function to verify non editable values in case settings form
     * @param profileDetails details of profile used
     * @param caseDetails details of case selected or created
     */
    private void verifyNonEditableValuesInCaseSettingsForm(ProfileDetails profileDetails, CaseDetails caseDetails) {

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_FULL_NAME_VALUE), profileDetails.getFullName()));
            Assert.assertNotNull(getText(LBL_PROFILE_ID_VALUE));
            if (profileDetails.getProfileType() == ProfileType.COMPANY) {
                if (profileDetails.getShortName() != null) {
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SHORT_NAME_VALUE), profileDetails.getShortName()));
                }
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_TYPE_VALUE), ProfileType.COMPANY.getProfileType()));
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CASE_SETTINGS_COUNTRY), countryOfRegistration));
            } else {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_TYPE_VALUE), ProfileType.INDIVIDUAL.getProfileType()));
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CASE_SETTINGS_COUNTRY), country));
            }
            for (int count = 0; count <= profileDetails.getCountry().length - 1; count++) {
                Assert.assertTrue(isActualValueContainsValue(getText(LBL_COUNTRY_VALUE), profileDetails.getCountry()[count].getCountry().trim()));
            }
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CASE_ID_VALUE), caseDetails.getCaseId()));
            if (caseDetails.getSpecificClientName() != null) {
                String[] splitClientName = caseDetails.getSpecificClientName().split((" {2}- {2}"));
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CLIENT_NAME_VALUE), splitClientName[1]));
            } else {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CLIENT_NAME_VALUE), caseDetails.getClientName()));
            }
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CLIENT_CODE_VALUE), caseDetails.getClientCode()));
            if (caseDetails.getCaseRegion().contains(", ")) {
                String[] splitRegion = caseDetails.getCaseRegion().split((", "));
                for (int regionCount = 0; regionCount <= splitRegion.length - 1; regionCount++) {
                    Assert.assertTrue(isActualValueContainsValue(getText(LBL_REGION_VALUE), splitRegion[regionCount]));
                }
            }
            pass(profileDetails.getFullName() + " profile was pulled together with correct details. - verifyNonEditableValuesInCaseSettingsForm");
        } catch (AssertionError e) {
            fail(profileDetails.getFullName() + " profile should be pulled together with correct details - verifyNonEditableValuesInCaseSettingsForm");
            throw e;
        }
    }

    /**
     * Use this function to go to Add Profile Data Modal via Subject Profile Section
     */
    public void goToAddProfileDataModal() {
        int index = 0;
        do {
            hover(LBL_SUBJECT_PROFILE);
            click(LBL_SUBJECT_PROFILE);
            if (index == 10) {
                Assert.assertTrue(isElementVisible(BTN_ADD_PROFILE_DATA), "Add Button should be visible.");
            }
            index++;
        } while (!isElementVisible(BTN_ADD_PROFILE_DATA));
        click(BTN_ADD_PROFILE_DATA);
    }

    /**
     * Use this function to go to Add Profile Data Modal via Profile Data Category
     * @param profileDataCategoryLabel object of profile data category label
     */
    public void goToAddProfileDataModal(By profileDataCategoryLabel) {
        int index = 0;
        do {
            hover(profileDataCategoryLabel);
            click(profileDataCategoryLabel);
            if (index == 10) {
                Assert.assertTrue(isElementVisible(BTN_ADD_PROFILE_DATA), "Add Button should be visible.");
            }
            index++;
        } while (!isElementVisible(BTN_ADD_PROFILE_DATA));
        click(BTN_ADD_PROFILE_DATA);
    }

    /**
     * Use this function to search profile data in Subject Profile Section
     * @param profileData any profile data as string to search
     */
    public void searchProfileData(String profileData) {
        this.goToAddProfileDataModal();
        type(TXT_SEARCH_PROFILE_DATA, profileData);
    }

    /**
     * Use this function to search profile data in Profile Data Category Section
     * @param profileDataCategoryLabel object of profile data category label
     * @param profileData any profile data to search
     */
    public void searchProfileData(By profileDataCategoryLabel, ProfileData profileData) {
        this.goToAddProfileDataModal(profileDataCategoryLabel);
        type(TXT_SEARCH_PROFILE_DATA, profileData.getProfileData());
    }

    /**
     * Use this function to search profile data in Subject Profile Section
     * @param profileData any profile data
     */
    public void searchProfileData(ProfileData profileData) {
        this.searchProfileData(profileData.getProfileData());
    }

    /**
     * Use this function to add profile data in Subject Profile Section
     * @param profileData any profile data to add
     */
    public void addProfileData(ProfileData profileData) throws IllegalAccessException {
        this.searchProfileData(profileData);
        click(createDynamicLocator(XPATH, LST_PROFILE_DATA_VALUE, profileData.getProfileData()));
    }

    /**
     * Use this function to add profile data in Subject Profile Section
     * @param profileDataCategoryLabel object of profile data category label
     * @param profileData any profile data to add
     */
    public void addProfileData(By profileDataCategoryLabel, ProfileData profileData) throws IllegalAccessException {
        this.searchProfileData(profileDataCategoryLabel, profileData);
        click(createDynamicLocator(XPATH, LST_PROFILE_DATA_VALUE, profileData.getProfileData()));
    }

    /**
     * Use this function to add profile data in Subject Profile Section and save
     * @param profileData any profile data to add
     */
    public void addProfileDataAndSave(ProfileData profileData) throws IllegalAccessException {
        this.addProfileData(profileData);
        click(BTN_SAVE);
        waitElementToBeInvisible(BTN_SAVE);
        profileData.setProfileDataEntryCount(profileData.getProfileDataEntryCount() + 1);
    }

    /**
     * Use this function to edit profile data
     * @param objectXpath section where to edit data
     */
    public void editData(By objectXpath) {
        int index = 0;
        doubleClick(LBL_PROFILE_FIRST_NAME);
        do {
            hover(objectXpath);
            click(objectXpath);
            hover(objectXpath);
            if (index == 10) {
                Assert.assertTrue(isElementVisible(BTN_EDIT_DATA), "Edit Button should be visible.");
            }
            index++;
        } while (!isElementVisible(BTN_EDIT_DATA));
        click(BTN_EDIT_DATA);
        if (!isElementNotVisible(BTN_CONTINUE)) {
            click(BTN_CONTINUE);
            waitElementToBeInvisible(BTN_CONTINUE);
        }
    }

    /**
     * Use this function to edit data
     * @param value data value where you want to edit
     */
    public void editData(String value) throws IllegalAccessException {
        int index = 0;
        do {
            hover(createDynamicLocator(XPATH, LBL_DATA_VALUE, value));
            click(LBL_PROFILE_FIRST_NAME);
            hover(createDynamicLocator(XPATH, LBL_DATA_VALUE, value));
            if (index == 10) {
                Assert.assertTrue(isElementVisible(BTN_EDIT_DATA), "Edit Button should be visible.");
            }
            index++;
        } while (!isElementVisible(BTN_EDIT_DATA));
        click(BTN_EDIT_DATA);
        if (!isElementNotVisible(BTN_CONTINUE)) {
            click(BTN_CONTINUE);
            waitElementToBeInvisible(BTN_CONTINUE);
        }
    }

    /*** Use this function to reference source record/s
     * @param sourceRecord details of source record/s to reference
     */
    public void referenceSourceRecord(SourceRecord... sourceRecord) throws IllegalAccessException {
        for (int sourceRecordCount = 0; sourceRecordCount <= sourceRecord.length - 1; sourceRecordCount++) {
            click(createDynamicLocator(XPATH, TXT_SOURCE, sourceRecordCount + 1));
            click(createDynamicLocator(XPATH, LST_SOURCE_VALUE, sourceRecord[sourceRecordCount].getRecordID()));
            if (sourceRecordCount != sourceRecord.length - 1) {
                click(BTN_ADD_SOURCE);
            }
        }
    }

    /**
     * Use this function to reference source record/s and save
     * @param sourceRecord details of source record/s to reference
     */
    public void referenceSourceRecordAndSave(SourceRecord... sourceRecord) throws IllegalAccessException {
        this.referenceSourceRecord(sourceRecord);
        click(BTN_SAVE);
        waitElementToBeInvisible(BTN_SAVE);
    }

    /**
     * Use this function to populate and save changes in Key Industry form
     * @param keyIndustry value to set
     */
    public void populateAndSaveKeyIndustry(KeyIndustry keyIndustry) throws IllegalAccessException {
        type(TXT_INDUSTRY, keyIndustry.getKeyIndustry());
        click(createDynamicLocator(XPATH, LST_INDUSTRY_VALUE, keyIndustry.getKeyIndustry()));
        click(BTN_SAVE);
        waitElementToBeInvisible(BTN_SAVE);
    }

    /**
     * Use this function to populate and save changes in overallRiskRating form
     * @param profileDetails overallRiskRating score, public summary and client summary
     */
    public void populateAndSaveoverallRiskRating(ProfileDetails profileDetails) {
        if (profileDetails.getOverallRiskRatingScore() > 0) {
            type(TXT_OVERALL_RISK_RATING, Integer.toString(profileDetails.getOverallRiskRatingScore()));
        }
        if (profileDetails.getPublicSummary() != null) {
            type(TXT_EXECUTIVE_SUMMARY_PUBLIC, profileDetails.getPublicSummary());
        }
        if (profileDetails.getClientSummary() != null) {
            type(TXT_EXECUTIVE_SUMMARY_CLIENT, profileDetails.getClientSummary());
        }
        click(BTN_SAVE);
        waitElementToBeInvisible(BTN_SAVE);
    }

    /**
     * Use this function to click source record in a particular source type
     * @param sourceRecord section to add source record
     */
    public void addSourceRecord(SourceRecord sourceRecord) throws IllegalAccessException {
        this.addSourceType(sourceRecord.getSourceType());
        click(createDynamicLocator(XPATH, BTN_ADD_SOURCE_RECORD, sourceRecord.getSourceType().getSourceType()));
        waitElementToBeVisible(BTN_SAVE);
        sourceRecord.setRecordID(getText(LBL_RECORD_ID_VALUE));
    }

    /**
     * Use this function to verify Source Record Form
     * @param sourceRecord details of source record to verify
     */
    public void verifySourceRecordForm(SourceRecord sourceRecord) throws IllegalAccessException {
        String labelNoHit = "No " + sourceRecord.getSourceType().getSourceType() + " Hit";
        String labelNoInfo = "No Information Found / Lack of Information";

        try {
            switch (sourceRecord.getSourceType()) {
                case CLIENT_DOCUMENTATION:
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_RECORD_ID_VALUE).substring(0,3), "CD-"));
                    if (getAttributeValue(RDO_NO_HIT_NO_INFO, CLASS).contains(CHECKED)) {
                        Assert.assertTrue(isElementNotVisible(ICO_UPLOAD));
                        Assert.assertTrue(isElementNotVisible(TXT_DOCUMENT_SUMMARY));
                    } else {
                        Assert.assertTrue(isElementEnabled(ICO_UPLOAD));
                        Assert.assertTrue(isElementEnabled(TXT_DOCUMENT_SUMMARY));
                    }
                    break;
                case GLOBAL_COMPLIANCE_CHECKS:
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_RECORD_ID_VALUE).substring(0,3), "GC-"));
                    if (getAttributeValue(RDO_NO_HIT_NO_INFO, CLASS).contains(CHECKED)) {
                        Assert.assertTrue(isElementNotVisible(TXT_GCSOURCE));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, TXT_PRIMARY_SOURCE, 1)));
                        Assert.assertTrue(isElementNotVisible(BTN_ADD_PRIMARY_SOURCE));
                    } else {
                        Assert.assertTrue(isElementEnabled(TXT_GCSOURCE));
                        Assert.assertTrue(isElementEnabled(createDynamicLocator(XPATH, TXT_PRIMARY_SOURCE, 1)));
                        Assert.assertTrue(isElementEnabled(BTN_ADD_PRIMARY_SOURCE));
                    }
                    break;
                case INTERVIEWS:
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_RECORD_ID_VALUE).substring(0,3), "IN-"));
                    if (getAttributeValue(RDO_NO_HIT_NO_INFO, CLASS).contains(CHECKED)) {
                        Assert.assertTrue(isElementNotVisible(TXT_INTERVIEWEE));
                        Assert.assertTrue(isElementNotVisible(TXT_ROLE));
                        Assert.assertTrue(isElementNotVisible(TXT_LINK));
                        Assert.assertTrue(isElementNotVisible(ICO_UPLOAD));
                    } else {
                        Assert.assertTrue(isElementEnabled(TXT_INTERVIEWEE));
                        Assert.assertTrue(isElementEnabled(TXT_ROLE));
                        Assert.assertTrue(isElementEnabled(TXT_LINK));
                        Assert.assertTrue(isElementEnabled(ICO_UPLOAD));
                    }
                    break;
                case LITIGATION:
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_RECORD_ID_VALUE).substring(0,3), "LI-"));
                    if (getAttributeValue(RDO_NO_HIT_NO_INFO, CLASS).contains(CHECKED)) {
                        Assert.assertTrue(isElementNotVisible(TXT_LINK));
                    } else {
                        Assert.assertTrue(isElementEnabled(TXT_LINK));
                    }
                    break;
                case MEDIA:
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_RECORD_ID_VALUE).substring(0,3), "ME-"));
                    if (getAttributeValue(RDO_NO_HIT_NO_INFO, CLASS).contains(CHECKED)) {
                        Assert.assertTrue(isElementNotVisible(TXT_LINK));
                    } else {
                        Assert.assertTrue(isElementEnabled(TXT_LINK));
                    }
                    break;
                case OFFICIAL_DOCUMENTATION:
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_RECORD_ID_VALUE).substring(0,3), "OD-"));
                    if (getAttributeValue(RDO_NO_HIT_NO_INFO, CLASS).contains(CHECKED)) {
                        Assert.assertTrue(isElementNotVisible(TXT_LINK));
                        Assert.assertTrue(isElementNotVisible(ICO_UPLOAD));
                    } else {
                        Assert.assertTrue(isElementEnabled(TXT_LINK));
                        Assert.assertTrue(isElementEnabled(ICO_UPLOAD));
                    }
                    break;
                case REPUTATION:
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_RECORD_ID_VALUE).substring(0,3), "RE-"));
                    if (getAttributeValue(RDO_NO_HIT_NO_INFO, CLASS).contains(CHECKED)) {
                        Assert.assertTrue(isElementNotVisible(TXT_INTERVIEWEE));
                        Assert.assertTrue(isElementNotVisible(TXT_COMPANY));
                        Assert.assertTrue(isElementNotVisible(TXT_POSITION));
                        Assert.assertTrue(isElementNotVisible(DD_TYPE_OF_SOURCE));
                        Assert.assertTrue(isElementNotVisible(TXT_SUMMARY));
                    } else {
                        Assert.assertTrue(isElementEnabled(TXT_INTERVIEWEE));
                        Assert.assertTrue(isElementEnabled(TXT_COMPANY));
                        Assert.assertTrue(isElementEnabled(TXT_POSITION));
                        Assert.assertTrue(isElementEnabled(DD_TYPE_OF_SOURCE));
                        click(DD_TYPE_OF_SOURCE);
                        for (ReputationSourceType reputationSourceType : ReputationSourceType.values()) {
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LST_TYPE_OF_SOURCE_VALUE, reputationSourceType.getReputationSourceType())));
                        }
                        doubleClick(LBL_RECORD_ID_VALUE);
                        Assert.assertTrue(isElementEnabled(TXT_SUMMARY));
                    }
                    break;
                case WEBSITE:
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_RECORD_ID_VALUE).substring(0,3), "WS-"));
                    if (getAttributeValue(RDO_NO_HIT_NO_INFO, CLASS).contains(CHECKED)) {
                        Assert.assertTrue(isElementNotVisible(TXT_LINK));
                    } else {
                        Assert.assertTrue(isElementEnabled(TXT_LINK));
                    }
                    break;
                case SITE_VISIT:
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_RECORD_ID_VALUE).substring(0,3), "SV-"));
                    if (getAttributeValue(RDO_NO_HIT_NO_INFO, CLASS).contains(CHECKED)) {
                        Assert.assertTrue(isElementNotVisible(TXT_ADDRESS));
                        Assert.assertTrue(isElementNotVisible(RDO_CONFIRMED));
                        Assert.assertTrue(isElementNotVisible(TXT_DETAILS_OF_SITE_VISIT));
                        Assert.assertTrue(isElementNotVisible(ICO_UPLOAD));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, TXT_PHOTO_DESCRIPTION, 1)));
                        Assert.assertTrue(isElementNotVisible(BTN_ADD_PHOTO));
                    } else {
                        Assert.assertTrue(isElementEnabled(TXT_ADDRESS));
                        Assert.assertTrue(isElementEnabled(RDO_CONFIRMED));
                        Assert.assertTrue(isElementEnabled(TXT_DETAILS_OF_SITE_VISIT));
                        Assert.assertTrue(isElementEnabled(ICO_UPLOAD));
                        Assert.assertTrue(isElementEnabled(createDynamicLocator(XPATH, TXT_PHOTO_DESCRIPTION, 1)));
                        Assert.assertTrue(isElementEnabled(BTN_ADD_PHOTO));
                    }
                    break;
            }
            Assert.assertTrue(isElementVisible(TXT_DAY));
            Assert.assertTrue(isElementVisible(TXT_MONTH));
            Assert.assertTrue(isElementVisible(TXT_YEAR));
            Assert.assertTrue(isElementEnabled(CHK_PRESENT));
            if (sourceRecord.getSourceType() == SourceType.GLOBAL_COMPLIANCE_CHECKS) {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_NO_HIT_NO_INFO), labelNoHit));
            } else {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_NO_HIT_NO_INFO), labelNoInfo));
            }

            if (sourceRecord.getInvolving() != null) {
                click(createDynamicLocator(XPATH, TXT_INVOLVING, 1));
                for (int involvingCount = 0; involvingCount <= sourceRecord.getInvolving().length - 1; involvingCount++) {
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LST_INVOLVING_VALUE, sourceRecord.getInvolving()[involvingCount])));
                }
            }
            Assert.assertTrue(isElementEnabled(BTN_ADD_ENTRY));
            Assert.assertTrue(isElementEnabled(createDynamicLocator(XPATH, TXT_RISK, 1)));
            Assert.assertTrue(isElementEnabled(BTN_ADD_RISK));
            Assert.assertTrue(isElementEnabled(TXT_COMMENT));
            Assert.assertTrue(isElementEnabled(BTN_SAVE));
            Assert.assertTrue(isElementEnabled(BTN_CANCEL));
            pass("All elements in " + sourceRecord.getSourceType().getSourceType() + " Form was verified. - verifySourceRecordForm");
        } catch (AssertionError e) {
            fail("All elements in " + sourceRecord.getSourceType().getSourceType() + " Form should be verified. - verifySourceRecordForm");
            throw e;
        }
    }

    /**
     * Use this function to modify source record form
     * @param sourceRecord details of source record to update
     */
    public void modifySourceRecordForm(SourceRecord sourceRecord) throws IllegalAccessException {
        if (sourceRecord.getPresentToggle() != null && sourceRecord.getPresentToggle() == ToggleOption.ON) {
            toggleOn(CHK_PRESENT);
        } else {
            toggleOff(CHK_PRESENT);
            if (sourceRecord.getDay() > 0 || sourceRecord.getMonth() != null || sourceRecord.getYear() > 0) {
                this.setSpecificDateInForm(sourceRecord.getDay(), sourceRecord.getMonth(), sourceRecord.getYear());
            }
        }
        if (sourceRecord.getNoHitToggle() != null && sourceRecord.getNoHitToggle() == ToggleOption.ON) {
            toggleOn(RDO_NO_HIT_NO_INFO);
        } else {
            toggleOff(RDO_NO_HIT_NO_INFO);
            switch (sourceRecord.getSourceType()) {
                case CLIENT_DOCUMENTATION:
                    if (sourceRecord.getDocumentSummary() != null) {
                        type(TXT_DOCUMENT_SUMMARY, sourceRecord.getDocumentSummary());
                    }
                    break;
                case GLOBAL_COMPLIANCE_CHECKS:
                    if (sourceRecord.getGcSource() != null) {
                        type(TXT_GCSOURCE, sourceRecord.getGcSource());
                    }
                    if (sourceRecord.getPrimarySource() != null) {
                        for (int primarySourceCount = 0; primarySourceCount <= sourceRecord.getPrimarySource().length - 1; primarySourceCount++) {
                            type(createDynamicLocator(XPATH, TXT_PRIMARY_SOURCE, primarySourceCount + 1), sourceRecord.getPrimarySource()[primarySourceCount]);
                            if (primarySourceCount != sourceRecord.getPrimarySource().length - 1) {
                                click(BTN_ADD_PRIMARY_SOURCE);
                            }
                        }
                    }
                    break;
                case INTERVIEWS:
                    if (sourceRecord.getInterviewee() != null) {
                        type(TXT_INTERVIEWEE, sourceRecord.getInterviewee());
                    }
                    if (sourceRecord.getRole() != null) {
                        type(TXT_ROLE, sourceRecord.getRole());
                    }
                    if (sourceRecord.getLink() != null) {
                        type(TXT_LINK, sourceRecord.getLink());
                    }
                    break;
                case LITIGATION: case MEDIA: case OFFICIAL_DOCUMENTATION: case WEBSITE:
                    if (sourceRecord.getLink() != null) {
                        type(TXT_LINK, sourceRecord.getLink());
                    }
                    break;
                case REPUTATION:
                    if (sourceRecord.getInterviewee() != null) {
                        type(TXT_INTERVIEWEE, sourceRecord.getInterviewee());
                    }
                    if (sourceRecord.getCompany() != null) {
                        type(TXT_COMPANY, sourceRecord.getCompany());
                    }
                    if (sourceRecord.getPosition() != null) {
                        type(TXT_POSITION, sourceRecord.getPosition());
                    }
                    if (sourceRecord.getReputationSourceType() != null) {
                        click(DD_TYPE_OF_SOURCE);
                        click(createDynamicLocator(XPATH, LST_TYPE_OF_SOURCE_VALUE, sourceRecord.getReputationSourceType().getReputationSourceType()));
                    }
                    if (sourceRecord.getSummary() != null) {
                        type(TXT_SUMMARY, sourceRecord.getSummary());
                    }
                    break;
                case SITE_VISIT:
                    if (sourceRecord.getAddress() != null) {
                        type(TXT_ADDRESS, sourceRecord.getAddress());
                    }
                    if (sourceRecord.getConfirmedToggle() != null && sourceRecord.getConfirmedToggle() == ToggleOption.ON) {
                        toggleOn(RDO_CONFIRMED);
                    } else {
                        toggleOff(RDO_CONFIRMED);
                    }
                    if (sourceRecord.getDetailsOfSiteVisit() != null) {
                        type(TXT_DETAILS_OF_SITE_VISIT, sourceRecord.getDetailsOfSiteVisit());
                    }
                    if (sourceRecord.getPhotoDescription() != null) {
                        for (int photoCount = 0; photoCount <= sourceRecord.getPhotoDescription().length - 1; photoCount++) {
                            type(createDynamicLocator(XPATH, TXT_PHOTO_DESCRIPTION, photoCount + 1), sourceRecord.getPhotoDescription()[photoCount]);
                            if (photoCount != sourceRecord.getPhotoDescription().length - 1) {
                                click(BTN_ADD_PHOTO);
                            }
                        }
                    }
                    break;
            }
        }
        doubleClick(LBL_RECORD_ID_VALUE);
        if (sourceRecord.getInvolving() != null) {
            for (int involvingCount = 0; involvingCount <= sourceRecord.getInvolving().length - 1; involvingCount++) {
                type(createDynamicLocator(XPATH, TXT_INVOLVING, involvingCount + 1), sourceRecord.getInvolving()[involvingCount]);
                click(createDynamicLocator(XPATH, LST_INVOLVING_VALUE, sourceRecord.getInvolving()[involvingCount]));
                if (involvingCount != sourceRecord.getInvolving().length - 1) {
                    click(BTN_ADD_ENTRY);
                }
            }
        }
        if (sourceRecord.getRisk() != null) {
            for (int riskCount = 0; riskCount <= sourceRecord.getRisk().length - 1; riskCount++) {
                if (!getValue(createDynamicLocator(XPATH, TXT_RISK, riskCount + 1)).equals(sourceRecord.getRisk()[riskCount].getRisk())) {
                    type(createDynamicLocator(XPATH, TXT_RISK, riskCount + 1), sourceRecord.getRisk()[riskCount].getRisk());
                    click(createDynamicLocator(XPATH, LST_RISK_VALUE, sourceRecord.getRisk()[riskCount].getRisk()));
                }
                if (riskCount != sourceRecord.getRisk().length - 1) {
                    click(BTN_ADD_RISK);
                }
            }
        }
        if (sourceRecord.getComment() != null) {
            type(TXT_COMMENT, sourceRecord.getComment());
        }
        doubleClick(LBL_RECORD_ID_VALUE);
    }

    /**
     * Use this function to modify and save source record form
     * @param sourceRecord details of source record to update
     */
    public void modifyAndSaveSourceRecordForm(SourceRecord sourceRecord) throws IllegalAccessException {
        this.modifySourceRecordForm(sourceRecord);
        sourceRecord.setRelevancy(true);
        click(BTN_SAVE);
    }

    /**
     * Use this function to verify Data in form Retained
     * @param sourceRecord source records to validate
     */
    public void verifyDataInFormRetained(SourceRecord sourceRecord) throws IllegalAccessException {

        try {
            if (sourceRecord.getPresentToggle() != null && sourceRecord.getPresentToggle() == ToggleOption.ON) {
                Assert.assertTrue(getAttributeValue(CHK_PRESENT, CLASS).contains(CHECKED));
            } else {
                if (sourceRecord.getDay() > 0) {
                    if (getValue(TXT_DAY).length() < 2) {
                        Assert.assertTrue(isActualValueEqualsValue("0" + getValue(TXT_DAY), Integer.toString(sourceRecord.getDay())));
                    } else {
                        Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_DAY), Integer.toString(sourceRecord.getDay())));
                    }
                }
                if (sourceRecord.getMonth() != null) {
                    Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_MONTH), sourceRecord.getMonth()));
                }
                if (sourceRecord.getYear() > 0) {
                    Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_YEAR), Integer.toString(sourceRecord.getYear())));
                }
            }
            if (!getAttributeValue(RDO_NO_HIT_NO_INFO, CLASS).contains(CHECKED)) {
                switch (sourceRecord.getSourceType()) {
                    case CLIENT_DOCUMENTATION:
                        if (sourceRecord.getDocumentSummary() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_DOCUMENT_SUMMARY), sourceRecord.getDocumentSummary()));
                        }
                        break;
                    case GLOBAL_COMPLIANCE_CHECKS:
                        if (sourceRecord.getGcSource() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_GCSOURCE), sourceRecord.getGcSource()));
                        }
                        if (sourceRecord.getPrimarySource() != null) {
                            for (int primarySourceCount = 0; primarySourceCount <= sourceRecord.getPrimarySource().length - 1; primarySourceCount++) {
                                Assert.assertTrue(isActualValueEqualsValue(getValue(createDynamicLocator(XPATH, TXT_PRIMARY_SOURCE, primarySourceCount + 1)), sourceRecord.getPrimarySource()[primarySourceCount]));
                            }
                        }
                        break;
                    case INTERVIEWS:
                        if (sourceRecord.getInterviewee() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_INTERVIEWEE), sourceRecord.getInterviewee()));
                        }
                        if (sourceRecord.getRole() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_ROLE), sourceRecord.getRole()));
                        }
                        if (sourceRecord.getLink() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_LINK), sourceRecord.getLink()));
                        }
                        break;
                    case LITIGATION: case MEDIA: case OFFICIAL_DOCUMENTATION: case WEBSITE:
                        if (sourceRecord.getLink() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_LINK), sourceRecord.getLink()));
                        }
                        break;
                    case REPUTATION:
                        if (sourceRecord.getInterviewee() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_INTERVIEWEE), sourceRecord.getInterviewee()));
                        }
                        if (sourceRecord.getCompany() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_COMPANY), sourceRecord.getCompany()));
                        }
                        if (sourceRecord.getPosition() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getValue(TXT_POSITION), sourceRecord.getPosition()));
                        }
                        if (sourceRecord.getReputationSourceType() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(DD_TYPE_OF_SOURCE), sourceRecord.getReputationSourceType().getReputationSourceType()));
                        }
                        if (sourceRecord.getSummary() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(TXT_SUMMARY), sourceRecord.getSummary()));
                        }
                        break;
                    case SITE_VISIT:
                        if (sourceRecord.getAddress() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(TXT_ADDRESS), sourceRecord.getAddress()));
                        }
                        if (sourceRecord.getConfirmedToggle() != null && sourceRecord.getConfirmedToggle() == ToggleOption.ON) {
                            Assert.assertTrue(getAttributeValue(RDO_CONFIRMED, CLASS).contains(CHECKED));
                        } else {
                            Assert.assertFalse(getAttributeValue(RDO_CONFIRMED, CLASS).contains(CHECKED));
                        }
                        if (sourceRecord.getDetailsOfSiteVisit() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(TXT_DETAILS_OF_SITE_VISIT), sourceRecord.getDetailsOfSiteVisit()));
                        }
                        if (sourceRecord.getPhotoDescription() != null) {
                            for (int photoCount = 0; photoCount <= sourceRecord.getPhotoDescription().length - 1; photoCount++) {
                                Assert.assertTrue(isActualValueEqualsValue(getValue(createDynamicLocator(XPATH, TXT_PHOTO_DESCRIPTION, photoCount + 1)), sourceRecord.getPhotoDescription()[photoCount]));
                            }
                        }
                        break;
                }
            }
            if (sourceRecord.getInvolving() != null) {
                for (int inolvingCount = 0; inolvingCount <= sourceRecord.getInvolving().length - 1; inolvingCount++) {
                    Assert.assertTrue(isActualValueEqualsValue(getValue(createDynamicLocator(XPATH, TXT_INVOLVING, inolvingCount + 1)), sourceRecord.getInvolving()[inolvingCount]));
                }
            }
            if (sourceRecord.getRisk() != null) {
                for (int riskCount = 0; riskCount <= sourceRecord.getRisk().length - 1; riskCount++) {
                    Assert.assertTrue(isActualValueEqualsValue(getValue(createDynamicLocator(XPATH, TXT_RISK, riskCount + 1)), sourceRecord.getRisk()[riskCount].getRisk()));
                }
            }
            if (sourceRecord.getComment() != null) {
                Assert.assertTrue(isActualValueEqualsValue(getText(TXT_COMMENT), sourceRecord.getComment()));
            }
            pass("All data in " + sourceRecord.getSourceType().getSourceType() + " Form was retained. - verifyDataInFormRetained");
        } catch (AssertionError e) {
            fail("All data in " + sourceRecord.getSourceType().getSourceType() + " Form should be retained. - verifyDataInFormRetained");
            throw e;
        }
    }

    /**
     * Use this function to remove information in form
     * @param information fields with multiple values
     */
    public void removeInformationInForm(String... information) throws IllegalAccessException {
        for (int informationCount = 0; informationCount <= information.length - 1; informationCount++) {
            click(createDynamicLocator(XPATH, BTN_REMOVE_INFORMATION, information[informationCount]));
        }
    }

    /**
     * Use this function to verify invalid link error message displayed
     * @param sourceRecord parameter where to check links
     */
    public void verifyErrorInvalidLinkDisplayed(SourceRecord sourceRecord) throws IllegalAccessException {

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_SOURCE_RECORD_SOURCE_LINK), ERR_INVALID_LINK));
            if (sourceRecord.getSourceType() == SourceType.GLOBAL_COMPLIANCE_CHECKS) {
                click(BTN_ADD_PRIMARY_SOURCE);
                for (int primarySourceCount = 0; primarySourceCount <= sourceRecord.getPrimarySource().length - 1; primarySourceCount++) {
                    Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, ERR_PRIMARY_SOURCE, primarySourceCount + 1)), ERR_INVALID_LINK));
                }
            }
            Assert.assertTrue(isElementVisible(BTN_SAVE), "User should not be allowed to Save with Error.");
            pass("Link/s was verified in " + sourceRecord.getSourceType().getSourceType() + " form. - verifyErrorInvalidLinkDisplayed");
        } catch (AssertionError e) {
            fail("Link/s should be verified in " + sourceRecord.getSourceType().getSourceType() + " form. - verifyErrorInvalidLinkDisplayed");
            throw e;
        }
    }

    /**
     * Use this function to verify source record read view
     * @param sourceRecord details of source record
     */
    public void verifySourceRecordReadView(SourceRecord sourceRecord) throws IllegalAccessException, ParseException {
        String msgNoHit = "No " + sourceRecord.getSourceType().getSourceType() + " hit";
        String msgNoInfo = "No Information Found / Lack of Information";

        try {
            waitElementToBeInvisible(BTN_SAVE);
            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_DATA_VALUE, sourceRecord.getRecordID())));
            if (sourceRecord.getPresentToggle() != null && sourceRecord.getPresentToggle() == ToggleOption.ON) {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_DATE_VALUE, sourceRecord.getRecordID())), PRESENT));
            } else if (sourceRecord.getDay() == 0 && sourceRecord.getMonth() == null && sourceRecord.getYear() == 0) {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_DATE_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
                Date sourceDate = dateFormat.parse((sourceRecord.getDay() + " " + sourceRecord.getMonth() + " " + sourceRecord.getYear()).trim());
                Date actualDate = dateFormat.parse(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_DATE_VALUE, sourceRecord.getRecordID())));
                Assert.assertTrue(isActualValueEqualsValue(actualDate.toString(), sourceDate.toString()));
            }
            switch (sourceRecord.getSourceType()) {
                case CLIENT_DOCUMENTATION:
                    if (sourceRecord.getNoHitToggle() == null || sourceRecord.getNoHitToggle() != ToggleOption.ON) {
                        if (sourceRecord.getDocumentSummary() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_DOCUMENT_SUMMARY_VALUE, sourceRecord.getRecordID())), sourceRecord.getDocumentSummary()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_DOCUMENT_SUMMARY_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                    } else {
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_DOCUMENT_SUMMARY_VALUE, sourceRecord.getRecordID())));
                    }
                    break;
                case GLOBAL_COMPLIANCE_CHECKS:
                    if (sourceRecord.getNoHitToggle() == null || sourceRecord.getNoHitToggle() != ToggleOption.ON) {
                        if (sourceRecord.getGcSource() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_GC_SOURCE_VALUE, sourceRecord.getRecordID())), sourceRecord.getGcSource()));
                        }
                        if (sourceRecord.getPrimarySource() != null) {
                            for (int sourceTypeCount = 0; sourceTypeCount <= sourceRecord.getPrimarySource().length - 1; sourceTypeCount++) {
                                Assert.assertTrue(isValueDisplayedForDuplicateElements(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_PRIMARY_SOURCE_VALUE, sourceRecord.getRecordID()), sourceRecord.getPrimarySource()[sourceTypeCount]));
                            }
                        }
                    } else {
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_GC_SOURCE_VALUE, sourceRecord.getRecordID())));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_PRIMARY_SOURCE_VALUE, sourceRecord.getRecordID())));
                    }
                    break;
                case INTERVIEWS:
                    if (sourceRecord.getNoHitToggle() == null || sourceRecord.getNoHitToggle() != ToggleOption.ON) {
                        if (sourceRecord.getInterviewee() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_INTERVIEWEE_VALUE, sourceRecord.getRecordID())), sourceRecord.getInterviewee()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_INTERVIEWEE_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                        if (sourceRecord.getRole() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_ROLE_VALUE, sourceRecord.getRecordID())), sourceRecord.getRole()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_ROLE_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                        if (sourceRecord.getLink() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_LINK_VALUE, sourceRecord.getRecordID())), sourceRecord.getLink()));
                        }
                    } else {
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_INTERVIEWEE_VALUE, sourceRecord.getRecordID())));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_ROLE_VALUE, sourceRecord.getRecordID())));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_LINK_VALUE, sourceRecord.getRecordID())));
                    }
                    break;
                case LITIGATION: case MEDIA: case OFFICIAL_DOCUMENTATION: case WEBSITE:
                    if (sourceRecord.getNoHitToggle() == null || sourceRecord.getNoHitToggle() != ToggleOption.ON) {
                        if (sourceRecord.getLink() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_LINK_VALUE, sourceRecord.getRecordID())), sourceRecord.getLink()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_LINK_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                    } else {
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_LINK_VALUE, sourceRecord.getRecordID())));
                    }
                    break;
                case REPUTATION:
                    if (sourceRecord.getNoHitToggle() == null || sourceRecord.getNoHitToggle() != ToggleOption.ON) {
                        if (sourceRecord.getInterviewee() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_INTERVIEWEE_VALUE, sourceRecord.getRecordID())), sourceRecord.getInterviewee()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_INTERVIEWEE_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                        if (sourceRecord.getCompany() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_COMPANY_VALUE, sourceRecord.getRecordID())), sourceRecord.getCompany()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_COMPANY_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                        if (sourceRecord.getPosition() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_POSITION_VALUE, sourceRecord.getRecordID())), sourceRecord.getPosition()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_POSITION_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                        if (sourceRecord.getReputationSourceType() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_TYPE_OF_SOURCE_VALUE, sourceRecord.getRecordID())), sourceRecord.getReputationSourceType().getReputationSourceType()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_TYPE_OF_SOURCE_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                        if (sourceRecord.getSummary() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_SUMMARY_VALUE, sourceRecord.getRecordID())), sourceRecord.getSummary()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_SUMMARY_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                    } else {
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_INTERVIEWEE_VALUE, sourceRecord.getRecordID())));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_COMPANY_VALUE, sourceRecord.getRecordID())));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_POSITION_VALUE, sourceRecord.getRecordID())));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_TYPE_OF_SOURCE_VALUE, sourceRecord.getRecordID())));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_SUMMARY_VALUE, sourceRecord.getRecordID())));
                    }
                    break;
                case SITE_VISIT:
                    if (sourceRecord.getNoHitToggle() == null || sourceRecord.getNoHitToggle() != ToggleOption.ON) {
                        if (sourceRecord.getAddress() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_ADDRESS_VALUE, sourceRecord.getRecordID())), sourceRecord.getAddress()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_ADDRESS_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                        if (sourceRecord.getConfirmedToggle() != null && sourceRecord.getConfirmedToggle() == ToggleOption.ON) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_CONFIRMED_VALUE, sourceRecord.getRecordID())), YesNo.Y.getYesNo()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_CONFIRMED_VALUE, sourceRecord.getRecordID())), YesNo.N.getYesNo()));
                        }
                        if (sourceRecord.getDetailsOfSiteVisit() != null) {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_DETAILS_OF_THE_VISIT_VALUE, sourceRecord.getRecordID())), sourceRecord.getDetailsOfSiteVisit()));
                        } else {
                            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_DETAILS_OF_THE_VISIT_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
                        }
                        if (sourceRecord.getPhotoDescription() != null) {
                            for (int photoDescCount = 0; photoDescCount <= sourceRecord.getPhotoDescription().length - 1; photoDescCount++) {
                                Assert.assertTrue(isValueDisplayedForDuplicateElements(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_PHOTO_VALUE, sourceRecord.getRecordID()), sourceRecord.getPhotoDescription()[photoDescCount]));
                            }
                        }
                    } else {
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_ADDRESS_VALUE, sourceRecord.getRecordID())));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_CONFIRMED_VALUE, sourceRecord.getRecordID())));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_DETAILS_OF_THE_VISIT_VALUE, sourceRecord.getRecordID())));
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_PHOTO_VALUE, sourceRecord.getRecordID())));
                    }
                    break;
            }
            if (sourceRecord.getNoHitToggle() != null && sourceRecord.getNoHitToggle() == ToggleOption.ON && sourceRecord.getSourceType() == SourceType.GLOBAL_COMPLIANCE_CHECKS) {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, MSG_NO_HIT, sourceRecord.getRecordID())), msgNoHit));
            } else if (sourceRecord.getNoHitToggle() != null && sourceRecord.getNoHitToggle() == ToggleOption.ON && sourceRecord.getSourceType() != SourceType.GLOBAL_COMPLIANCE_CHECKS) {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, MSG_NO_HIT, sourceRecord.getRecordID())), msgNoInfo));
            }
            if (sourceRecord.getInvolving() != null) {
                for (int involvingCount = 0; involvingCount <= sourceRecord.getInvolving().length - 1; involvingCount++) {
                    Assert.assertTrue(isValueDisplayedForDuplicateElements(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_INVOLVING_VALUE, sourceRecord.getRecordID()), sourceRecord.getInvolving()[involvingCount]));
                }
            } else {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_INVOLVING_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
            }
            if (sourceRecord.getRisk() != null) {
                for (int riskCount = 0; riskCount <= sourceRecord.getRisk().length - 1; riskCount++) {
                    Assert.assertTrue(isValueDisplayedForDuplicateElements(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_RISK_VALUE, sourceRecord.getRecordID()), sourceRecord.getRisk()[riskCount].getRisk()));
                }
            } else {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_RISK_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
            }
            if (sourceRecord.getComment() != null) {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_COMMENT_VALUE, sourceRecord.getRecordID())), sourceRecord.getComment()));
            } else {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SOURCE_RECORD_COMMENT_VALUE, sourceRecord.getRecordID())), DOUBLE_DASH));
            }
            pass("Details for source record: " + sourceRecord.getRecordID() + " was validated. - verifySourceRecordReadView");
        } catch (AssertionError e) {
            fail("Details for source record: " + sourceRecord.getRecordID() + " should be validated. - verifySourceRecordReadView");
            throw e;
        }
    }

    /**
     * Use this function to set specific date in form
     * @param day   any day from 1-31
     * @param month any month Jan - Dec
     * @param year  any year ex 1989
     */
    public void setSpecificDateInForm(int day, String month, int year) throws IllegalAccessException {
        toggleOff(CHK_PRESENT);
        if (day > 0) {
            type(TXT_DAY, Integer.toString(day));
            click(createDynamicLocator(XPATH, TXT_DAY_VALUE, day));
        }
        if (!month.isEmpty()) {
            type(TXT_MONTH, month);
            click(createDynamicLocator(XPATH, TXT_MONTH_VALUE, month));
        }
        if (year > 0) {
            type(TXT_YEAR, Integer.toString(year));
            click(createDynamicLocator(XPATH, TXT_YEAR_VALUE, year));
        }
    }

    /**
     * Use this function to toggle ON relevancy button of source record
     * @param sourceRecord where relevancy button is part of
     */
    public void toggleOnRelevancySourceRecord(SourceRecord sourceRecord) throws IllegalAccessException {
        waitElementToBeInvisible(BTN_SAVE);
        toggleOn(createDynamicLocator(XPATH, RDO_RELEVANCY, sourceRecord.getRecordID()));
        sourceRecord.setRelevancy(true);
        waitElementToBeInvisible(IMG_LOADER);
    }

    /**
     * Use this function to toggle OFF relevancy button of source record
     * @param sourceRecord where relevancy button is part of
     */
    public void toggleOffRelevancySourceRecord(SourceRecord sourceRecord) throws IllegalAccessException {
        waitElementToBeInvisible(BTN_SAVE);
        toggleOff(createDynamicLocator(XPATH, RDO_RELEVANCY, sourceRecord.getRecordID()));
        sourceRecord.setRelevancy(false);
        click(BTN_PROCEED);
        waitElementToBeInvisible(BTN_PROCEED);
        waitElementToBeInvisible(IMG_LOADER);
        int index = 0;
        do {
            if (index == 30) {
                Assert.assertFalse(getAttributeValue(createDynamicLocator(XPATH, RDO_RELEVANCY, sourceRecord.getRecordID()), CLASS).contains(CHECKED));
            }
            index++;
        } while (getAttributeValue(createDynamicLocator(XPATH, RDO_RELEVANCY, sourceRecord.getRecordID()), CLASS).contains(CHECKED));
    }

    /**
     * Use this function to toggle OFF relevancy button of source record
     * @param sourceRecord where relevancy button is part of
     */
    public void verifySourceRecordRelevant(SourceRecord sourceRecord, boolean status) throws IllegalAccessException {
        try {
            waitElementToBeInvisible(BTN_SAVE);
            waitElementToBeInvisible(BTN_CANCEL);
            int counter = 0;
            if (status) {
                do {
                    if (counter == 30) {
                        Assert.assertTrue(getAttributeValue(createDynamicLocator(XPATH, RDO_RELEVANCY, sourceRecord.getRecordID()), CLASS).contains(CHECKED));
                    }
                    counter++;
                } while (!getAttributeValue(createDynamicLocator(XPATH, RDO_RELEVANCY, sourceRecord.getRecordID()), CLASS).contains(CHECKED));
            } else {
                do {
                    if (counter == 30) {
                        Assert.assertFalse(getAttributeValue(createDynamicLocator(XPATH, RDO_RELEVANCY, sourceRecord.getRecordID()), CLASS).contains(CHECKED));
                    }
                    counter++;
                } while (getAttributeValue(createDynamicLocator(XPATH, RDO_RELEVANCY, sourceRecord.getRecordID()), CLASS).contains(CHECKED));
            }
            pass(sourceRecord.getRecordID() + " relevancy was expected to be " + status + " - verifySourceRecordRelevant");
        } catch (AssertionError e) {
            fail(sourceRecord.getRecordID() + " relevancy should be " + status + " - verifySourceRecordRelevant");
            throw e;
        }
    }

    /**
     * Use this function to add source type
     * @param sourceType sourceType/s to add
     */
    public void addSourceType(SourceType... sourceType) throws IllegalAccessException {
        click(BTN_SOURCE_TYPES);
        waitElementToBeVisible(BTN_SAVE);
        for (int sourceTypeCount = 0; sourceTypeCount <= sourceType.length - 1; sourceTypeCount++) {
            toggleOn(createDynamicLocator(XPATH, CHK_SOURCE_TYPE, sourceType[sourceTypeCount].getSourceType()));
        }
        waitElementToBeVisible(BTN_SAVE);
        click(BTN_SAVE);
        waitElementToBeInvisible(BTN_SAVE);
        waitElementToBeInvisible(IMG_LOADER);
    }

    /**
     * Use this function to verify no matches found
     */
    public void verifyNoMatchesFound() {
        String msgNoMatchesFound = "No matches found";

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_NO_MATCHES_FOUND), msgNoMatchesFound));
            pass("Option was not found. - verifyNoMatchesFound");
        } catch (AssertionError e) {
            fail("Option should not be found. - verifyNoMatchesFound");
            throw e;
        }
    }

    /**
     * Use this function to verify no matches found
     */
    public void verifyProfileDataFound(ProfileData... profileData) throws IllegalAccessException {
        int profileDataCount = 0;

        try {
            for (profileDataCount = 0; profileDataCount <= profileData.length - 1; profileDataCount++) {
                Assert.assertTrue(isElementNotVisible(LBL_NO_MATCHES_FOUND));
                Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LST_PROFILE_DATA_VALUE, profileData[profileDataCount].getProfileData())));
                pass("Profile Data " + profileData[profileDataCount].getProfileData() + " was found. - verifyProfileDataFound");
            }
        } catch (AssertionError e) {
            fail("Profile Data " + profileData[profileDataCount].getProfileData() + " should be found. - verifyProfileDataFound");
            throw e;
        }
    }

    /**
     * Use this function to verify profile found
     * @param fullName of profile to find
     */
    public void verifyProfileAddedInSourceRecordInvolvingDropDown(String fullName) throws IllegalAccessException {

        try {
            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LST_INVOLVING_VALUE, fullName)));
            pass(fullName + " option was found. - verifyProfileAddedInSourceRecordInvolvingDropDown");
        } catch (AssertionError e) {
            fail(fullName + " should be found. - verifyProfileAddedInSourceRecordInvolvingDropDown");
            throw e;
        }
    }

    /**
     * Use this function to verify risk values selection
     */
    public void verifyRiskValuesSelectionComplete() throws IllegalAccessException {

        try {
            for (Risk risk : Risk.values()) {
                Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LST_RISK_VALUE, risk.getRisk())));
            }
            pass("All risk values were available. - verifyRiskValuesSelectionComplete");
        } catch (AssertionError e) {
            fail("All risk values should be available. - verifyRiskValuesSelectionComplete");
            throw e;
        }
    }

    /**
     * Use this function to verify source types added accordingly
     */
    public void verifySourceTypesAddedAccordingly(SourceType... sourceType) {
        int sourceTypeCount = 0;

        try {
            for (sourceTypeCount = 0; sourceTypeCount <= sourceType.length - 1; sourceTypeCount++) {
                for (WebElement element : findElements(LBL_SOURCE_TYPE_HEADER)) {
                    int index = 1;
                    if (element.getText().equals(sourceType[sourceTypeCount].getSourceType())) {
                        info(sourceType[sourceTypeCount].getSourceType() + " was added. - verifySourceTypesAddedAccordingly");
                        break;
                    } else if (index == findElements(LBL_SOURCE_TYPE_HEADER).size()) {
                        Assert.fail();
                    }
                }
            }
            pass("All source types were added accordingly. - verifySourceTypesAddedAccordingly");
        } catch (AssertionError e) {
            fail(sourceType[sourceTypeCount].getSourceType() + " source type should be added accordingly. - verifySourceTypesAddedAccordingly");
            throw e;
        }
    }

    /**
     * Use this function to verify open tabs and close
     */
    public void verifyLinkOpensNewTabAndClose() {

        try {
            Assert.assertTrue(isActualValueEqualsValue(getTabsCount(), 2));
            switchWindowTabAndClose(1);
            switchWindowTab(0);
            Assert.assertTrue(isActualValueEqualsValue(getTabsCount(), 1));
            pass("Link opens new tab and was able to close. - verifyLinkOpensNewTabAndClose");
        } catch (AssertionError e) {
            fail("Link should opens new tab and should be able to close. - verifyLinkOpensNewTabAndClose");
            throw e;
        }
    }

    /**
     * Use this function to verify source value of specific profile data
     * @param profileData  that links source record
     * @param sourceRecord that links to profile data
     */
    public void verifySourceValueOfProfileData(String profileData, SourceRecord... sourceRecord) throws IllegalAccessException {
        int sourceRecordCount = 0;
        try {
            waitElementToBeInvisible(BTN_CANCEL);
            for (sourceRecordCount = 0; sourceRecordCount <= sourceRecord.length - 1; sourceRecordCount++) {
                Assert.assertTrue(isActualValueContainsValue(getText(createDynamicLocator(XPATH, LBL_DATA_ENTRY_SOURCE_VALUE, profileData)), sourceRecord[sourceRecordCount].getRecordID()));
                pass(sourceRecord[sourceRecordCount].getRecordID() + " was referenced to profile data: " + profileData + " - verifySourceValueOfProfileData");
            }
        } catch (AssertionError e) {
            fail(sourceRecord[sourceRecordCount].getRecordID() + " should be referenced to profile data: " + profileData + " - verifySourceValueOfProfileData");
            throw e;
        }
    }

    /**
     * Use this function to verify source value not included for specific profile data
     * @param profileData  that links source record
     * @param sourceRecord that links to profile data
     */
    public void verifySourceValueNotIncludedInProfileData(String profileData, SourceRecord... sourceRecord) throws IllegalAccessException {
        int sourceRecordCount = 0;
        try {
            for (sourceRecordCount = 0; sourceRecordCount <= sourceRecord.length - 1; sourceRecordCount++) {
                Assert.assertFalse(isActualValueContainsValue(getText(createDynamicLocator(XPATH, LBL_DATA_ENTRY_SOURCE_VALUE, profileData)), sourceRecord[sourceRecordCount].getRecordID()));
                pass(sourceRecord[sourceRecordCount].getRecordID() + " was not referenced to profile data: " + profileData + " - verifySourceValueNotIncludedInProfileData");
            }
        } catch (AssertionError e) {
            fail(sourceRecord[sourceRecordCount].getRecordID() + " should not be referenced to profile data: " + profileData + " - verifySourceValueNotIncludedInProfileData");
            throw e;
        }
    }

    /**
     * Use this function to verify set to Irrelevant Modal of Source Record
     * @param sourceRecord that was set to irrelevant
     */
    public void verifySourceRecordSetToIrrelevantModal(SourceRecord sourceRecord) {
        String titleSetToIrrelevant = "Set " + sourceRecord.getRecordID() + " to \"Irrelevant\"";
        String msgSetToIrrelevant = "Are you sure you want to set this source record to \"Irrelevant\"?";
        String referencedMsgSetToIrrelevant = sourceRecord.getRecordID() + " is currently referenced by the ff:";

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SET_TO_IRRELEVANT), titleSetToIrrelevant));
            Assert.assertTrue(isActualValueContainsValue(getText(MSG_CASE_EDITOR_MODAL), msgSetToIrrelevant));
            if (sourceRecord.getRisk() != null) {
                Assert.assertTrue(isActualValueContainsValue(getText(MSG_CASE_EDITOR_MODAL), referencedMsgSetToIrrelevant));
                for (int riskCount = 0; riskCount <= sourceRecord.getRisk().length - 1; riskCount++) {
                    Assert.assertTrue(isActualValueContainsValue(getText(MSG_CASE_EDITOR_MODAL), sourceRecord.getRisk()[riskCount].getRisk()));
                }
            }
            if (sourceRecord.getReferencedProfileData() != null) {
                Assert.assertTrue(isActualValueContainsValue(getText(MSG_CASE_EDITOR_MODAL), referencedMsgSetToIrrelevant));
                Assert.assertTrue(isActualValueContainsValue(getText(MSG_CASE_EDITOR_MODAL), sourceRecord.getReferencedProfileData().getProfileData()));
            }
            Assert.assertTrue(isElementVisible(BTN_CLOSE));
            Assert.assertTrue(isElementVisible(BTN_CANCEL));
            Assert.assertTrue(isElementVisible(BTN_PROCEED));
            pass("All elements in Set To Irrelevant Modal was verified. - verifySourceRecordSetToIrrelevantModal");
        } catch (AssertionError e) {
            fail("All elements in Set To Irrelevant Modal should be verified. - verifySourceRecordSetToIrrelevantModal");
            throw e;
        }
    }

    /**
     * Use this function to edit specific profile data and reference a source record and save
     * @param profileData      to be edited
     * @param profileDataValue value of profile data
     * @param sourceRecord     source record/s to referenced
     */
    public void editProfileDataReferenceSourceRecordAndSave(ProfileData profileData, String profileDataValue, SourceRecord... sourceRecord) throws IllegalAccessException {
        this.editData(profileDataValue);
        this.referenceSourceRecord(sourceRecord);
        click(BTN_SAVE);
        waitElementToBeInvisible(BTN_SAVE);
        for (int sourceRecordCount = 0; sourceRecordCount <= sourceRecord.length - 1; sourceRecordCount++) {
            sourceRecord[sourceRecordCount].setReferencedProfileData(profileData);
        }
    }

    /**
     * Use this function to verify Risk Areas tag to a relevant source record is visible
     * @param sourceRecord source record that is relevant and has tag of risk area
     */
    public void verifyRiskAreaTagToRelevantSourceRecordVisible(SourceRecord sourceRecord) throws IllegalAccessException {
        int riskCount = 0;
        try {
            if (sourceRecord.isRelevancy() && sourceRecord.getRisk() != null) {
                for (riskCount = 0; riskCount <= sourceRecord.getRisk().length - 1; riskCount++) {
                    Risk riskArea = sourceRecord.getRisk()[riskCount];
                    switch (riskArea) {
                        case COR: case SER: case TER: case ANT: case GOC: case FRA: case MON: case TAX: case SAN:
                            Assert.assertTrue(isElementVisible(LBL_INTEGRITY_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_INTEGRITY_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LNK_RISKS_OUTLINER_INTEGRITY_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_INTEGRITY_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LBL_SCORING_INTEGRITY_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_SCORING_INTEGRITY_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LNK_SCORING_OUTLINER_INTEGRITY_RISKS));
                            break;

                        case ENV: case ANI: case SAL: case HEA: case HUM: case MOD: case EMP: case GOV: case REG:
                            Assert.assertTrue(isElementVisible(LBL_ESG_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_ESG_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LNK_RISKS_OUTLINER_ESG_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_ESG_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LBL_SCORING_ESG_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_SCORING_ESG_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LNK_SCORING_OUTLINER_ESG_RISKS));
                            break;

                        case FIN: case FII:
                            Assert.assertTrue(isElementVisible(LBL_FINANCIAL_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_FINANCIAL_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LNK_RISKS_OUTLINER_FINANCIAL_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_FINANCIAL_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LBL_SCORING_FINANCIAL_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_SCORING_FINANCIAL_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LNK_SCORING_OUTLINER_FINANCIAL_RISKS));
                            break;

                        case IND: case PRO: case OPE: case BUS:
                            Assert.assertTrue(isElementVisible(LBL_OAQ_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_OAQ_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LNK_RISKS_OUTLINER_OAQ_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_OAQ_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LBL_SCORING_OAQ_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_SCORING_OAQ_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LNK_SCORING_OUTLINER_OAQ_RISKS));
                            break;

                        case SRC: case CTR: case COI:
                            Assert.assertTrue(isElementVisible(LBL_ENGAGEMENT_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_ENGAGEMENT_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LNK_RISKS_OUTLINER_ENGAGEMENT_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_ENGAGEMENT_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LBL_SCORING_ENGAGEMENT_RISKS));
                            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_SCORING_ENGAGEMENT_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementVisible(LNK_SCORING_OUTLINER_ENGAGEMENT_RISKS));
                            break;
                    }
                    Assert.assertTrue(isElementVisible(LNK_RISK_CATEGORIES));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, BTN_ADD_RISK_SUMMARY, sourceRecord.getRisk()[riskCount].getRisk())));
                    pass(sourceRecord.getRisk()[riskCount].getRisk() + " risk area was added accordingly. - verifyRiskAreaTagToRelevantSourceRecordVisible");
                }
            }
        } catch (AssertionError e) {
            fail(sourceRecord.getRisk()[riskCount].getRisk() + " risk area should be added accordingly. - verifyRiskAreaTagToRelevantSourceRecordVisible");
            throw e;
        }
    }

    /**
     * Use this function to verify Risk Areas tag to a irrelevant source record is not visible
     * @param sourceRecord source record that is irrelevant and has tag of risk area
     */
    public void verifyRiskAreaTagToIrrelevantSourceRecordNotVisible(SourceRecord sourceRecord) throws IllegalAccessException {
        int riskCount = 0;
        try {
            if (!sourceRecord.isRelevancy() && sourceRecord.getRisk() != null) {
                for (riskCount = 0; riskCount <= sourceRecord.getRisk().length - 1; riskCount++) {
                    Risk riskArea = sourceRecord.getRisk()[riskCount];
                    switch (riskArea) {
                        case COR: case SER: case TER: case ANT: case GOC: case FRA: case MON: case TAX: case SAN:
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_INTEGRITY_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_INTEGRITY_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SCORING_INTEGRITY_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            break;

                        case ENV: case ANI: case SAL: case HEA: case HUM: case MOD: case EMP: case GOV: case REG:
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_ESG_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_ESG_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SCORING_ESG_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            break;

                        case FIN: case FII:
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_FINANCIAL_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_FINANCIAL_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SCORING_FINANCIAL_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            break;

                        case IND: case PRO: case OPE: case BUS:
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_OAQ_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_OAQ_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SCORING_OAQ_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            break;

                        case SRC: case CTR: case COI:
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_ENGAGEMENT_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_ENGAGEMENT_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_SCORING_ENGAGEMENT_RISKS_RISK_AREA, sourceRecord.getRisk()[riskCount].getRisk())));
                            break;
                    }
                    Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, BTN_ADD_RISK_SUMMARY, sourceRecord.getRisk()[riskCount].getRisk())));
                    pass(sourceRecord.getRisk()[riskCount].getRisk() + " risk area was not visible. - verifyRiskAreaTagToIrrelevantSourceRecordNotVisible");
                }
            }
        } catch (AssertionError e) {
            fail(sourceRecord.getRisk()[riskCount].getRisk() + " risk area should not be visible. - verifyRiskAreaTagToIrrelevantSourceRecordNotVisible");
            throw e;
        }
    }

    /**
     * Use this function to add risk area from outliner
     * @param riskArea risk area to add
     */
    public void addRiskAreaFromOutliner(Risk riskArea) throws IllegalAccessException {
        if (isElementNotVisible(createDynamicLocator(XPATH, BTN_ADD_RISK_SUMMARY, riskArea.getRisk()))) {
            click(BTN_ADD);
            waitElementToBeVisible(MENU_RISK);
            click(MENU_RISK);
            type(TXT_OUTLINER_RISK, riskArea.getRisk());
            click(createDynamicLocator(XPATH, LST_OUTLINER_RISK_VALUE, riskArea.getRisk()));
        }
    }

    /**
     * Use this function to verify risk summary form
     */
    public void verifyRiskSummaryForm() throws IllegalAccessException {

        try {
            click(DD_TYPE);
            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH,LST_TYPE_VALUE, RiskInformationType.PUBLIC.getRiskSummaryType())));
            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH,LST_TYPE_VALUE, RiskInformationType.CLIENT_SPECIFIC.getRiskSummaryType())));
            Assert.assertTrue(isElementVisible(TXT_RISK_SUMMARY));
            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH,TXT_SOURCE,1)));
            Assert.assertTrue(isElementVisible(BTN_ADD_SOURCE));
            Assert.assertTrue(isElementVisible(BTN_SAVE));
            Assert.assertTrue(isElementVisible(BTN_CANCEL));
            pass("All elements in Risk Summary Form was verified. - verifyRiskSummaryForm");
        } catch (AssertionError e) {
            fail("All elements in Risk Summary Form should be verified. - verifyRiskSummaryForm");
            throw e;
        }
    }

    /**
     * Use this function to edit, populate and save risk summary to another risk summary
     * @param riskSummary details of risk summary to edit
     * @param riskSummaryB updated risk summary
     */
    public void editRiskSummaryAndSave(RiskSummary riskSummary, RiskSummary riskSummaryB) throws IllegalAccessException {
        this.openRiskSummaryForm(riskSummary);
        this.editRiskSummaryFormAndSave(riskSummaryB);
    }

    /**
     * Use this function to edit, populate and cancel the edits made
     * @param riskSummary details of risk summary to edit
     * @param riskSummaryB updated risk summary
     */
    public void editRiskSummaryAndCancel(RiskSummary riskSummary, RiskSummary riskSummaryB) throws IllegalAccessException {
        this.openRiskSummaryForm(riskSummary);
        this.editRiskSummaryForm(riskSummaryB);
        click(BTN_CANCEL);
    }

    /**
     * Use this function to open risk summary
     * @param riskSummary details of risk summary to edit or save
     */
    public void openRiskSummaryForm(RiskSummary riskSummary) throws IllegalAccessException {
        waitElementToBeInvisible(BTN_SAVE);
        waitElementToBeInvisible(IMG_LOADER);
        int index;
        switch (riskSummary.getRiskSummaryType()) {
            case PUBLIC:
                index = 1;
                for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_PUBLIC_VALUE, riskSummary.getRisk().getRisk()))) {
                    if (isActualValueEqualsValue(element.getText(), riskSummary.getSummary())) {
                        element.click();
                        click(BTN_EDIT_DATA);
                        if (!isElementNotVisible(BTN_CONTINUE)) {
                            click(BTN_CONTINUE);
                            waitElementToBeInvisible(BTN_CONTINUE);
                        }
                        break;
                    } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_PUBLIC_VALUE, riskSummary.getRisk().getRisk())).size()) {
                        fail(riskSummary.getSummary() + " should be available. - openRiskSummaryForm");
                        Assert.fail();
                    }
                    index ++;
                }
                break;
            case CLIENT_SPECIFIC:
                index = 1;
                for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_CLIENT_SPECIFIC_VALUE, riskSummary.getRisk().getRisk()))) {
                    if (isActualValueEqualsValue(element.getText(), riskSummary.getSummary())) {
                        element.click();
                        click(BTN_EDIT_DATA);
                        if (!isElementNotVisible(BTN_CONTINUE)) {
                            click(BTN_CONTINUE);
                            waitElementToBeInvisible(BTN_CONTINUE);
                        }
                        break;
                    } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_CLIENT_SPECIFIC_VALUE, riskSummary.getRisk().getRisk())).size()) {
                        fail(riskSummary.getSummary() + " should be available. - openRiskSummaryForm");
                        Assert.fail();
                    }
                    index ++;
                }
                break;
        }
    }

    /**
     * Use this function to verify risk summary read view section
     * @param riskSummary details of risk summary to verify
     */
    public void verifyRiskSummaryReadView(RiskSummary... riskSummary) throws IllegalAccessException {
        int riskSummaryCount = 0;

        try {
            waitElementToBeInvisible(IMG_LOADER);
            for (riskSummaryCount = 0; riskSummaryCount <= riskSummary.length - 1; riskSummaryCount++) {
                switch (riskSummary[riskSummaryCount].getRiskSummaryType()) {
                    case PUBLIC:
                        Assert.assertTrue(isValueDisplayedForDuplicateElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_PUBLIC_VALUE, riskSummary[riskSummaryCount].getRisk().getRisk()), riskSummary[riskSummaryCount].getSummary()));
                        break;
                    case CLIENT_SPECIFIC:
                        Assert.assertTrue(isValueDisplayedForDuplicateElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_CLIENT_SPECIFIC_VALUE, riskSummary[riskSummaryCount].getRisk().getRisk()), riskSummary[riskSummaryCount].getSummary()));
                        break;
                }

                if (riskSummary[riskSummaryCount].getSourceRecord() != null) {
                    for (int sourceRecordCount = 0; sourceRecordCount<= riskSummary[riskSummaryCount].getSourceRecord().length-1; sourceRecordCount++) {
                        Assert.assertTrue(isActualValueContainsValue(getText(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_SOURCES_VALUE, riskSummary[riskSummaryCount].getSummary())), riskSummary[riskSummaryCount].getSourceRecord()[sourceRecordCount].getRecordID()));
                    }
                } else {
                    Assert.assertTrue(isActualValueContainsValue(getText(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_SOURCES_VALUE, riskSummary[riskSummaryCount].getSummary())), DOUBLE_DASH));
                }
                pass("Risk Summary" + riskSummary[riskSummaryCount].getSummary() + " was added/updated. - verifyRiskSummaryReadView");
            }
        } catch (AssertionError e) {
            fail("Risk Summary" + riskSummary[riskSummaryCount].getSummary() + " should be added/updated. - verifyRiskSummaryReadView");
            throw e;
        }
    }

    /**
     * Use this function to edit risk score and save
     * @param risk risk to save score
     */
    public void editRiskScoreAndSave(Risk... risk) throws IllegalAccessException, InterruptedException {
        for (int riskCount = 0; riskCount <= risk.length - 1; riskCount++) {
            switch (risk[riskCount]) {
                case COR: case SER: case TER: case ANT: case GOC: case FRA: case MON: case TAX: case SAN:
                    this.editData(LBL_SCORING_INTEGRITY_RISKS);
                    break;

                case ENV: case ANI: case SAL: case HEA: case HUM: case MOD: case EMP: case GOV: case REG:
                    this.editData(LBL_SCORING_ESG_RISKS);
                    break;

                case FIN: case FII:
                    this.editData(LBL_SCORING_FINANCIAL_RISKS);
                    break;

                case IND: case PRO: case OPE: case BUS:
                    this.editData(LBL_SCORING_OAQ_RISKS);
                    break;

                case SRC: case CTR: case COI:
                    this.editData(LBL_SCORING_ENGAGEMENT_RISKS);
                    break;
            }
            waitElementToBeVisible(BTN_SAVE);
            type(createDynamicLocator(XPATH, TXT_RISK_AREA, risk[riskCount].getRisk()), Integer.toString(risk[riskCount].getRiskScore()));
            int index = 0;
            while (getValue(createDynamicLocator(XPATH, TXT_RISK_AREA, risk[riskCount].getRisk())).isEmpty()) {
                sleepForSeconds(3);
                if (index == 10) {
                    Assert.fail();
                }
                index++;
            }
            click(BTN_SAVE);
        }
    }

    /**
     * Use this function to verify risk score in scoring section
     * @param risk risk to verify score
     */
    public void verifyRiskScoreReadView(Risk...risk) throws IllegalAccessException {

        try {
            waitElementToBeInvisible(BTN_SAVE);
            waitElementToBeInvisible(IMG_LOADER);
            for (int riskCount = 0; riskCount<=risk.length-1; riskCount++) {
                switch (risk[riskCount]) {
                    case COR: case SER: case TER: case ANT: case GOC: case FRA: case MON: case TAX: case SAN:
                        Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SCORING_INTEGRITY_RISKS_RISK_AREA_SCORE, risk[riskCount].getRisk())), Integer.toString(risk[riskCount].getRiskScore())));
                        break;

                    case ENV: case ANI: case SAL: case HEA: case HUM: case MOD: case EMP: case GOV: case REG:
                        Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SCORING_ESG_RISKS_RISK_AREA_SCORE, risk[riskCount].getRisk())), Integer.toString(risk[riskCount].getRiskScore())));
                        break;

                    case FIN: case FII:
                        Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SCORING_FINANCIAL_RISKS_RISK_AREA_SCORE, risk[riskCount].getRisk())), Integer.toString(risk[riskCount].getRiskScore())));
                        break;

                    case IND: case PRO: case OPE: case BUS:
                        Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SCORING_OAQ_RISKS_RISK_AREA_SCORE, risk[riskCount].getRisk())), Integer.toString(risk[riskCount].getRiskScore())));
                        break;

                    case SRC: case CTR: case COI:
                        Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_SCORING_ENGAGEMENT_RISKS_RISK_AREA_SCORE, risk[riskCount].getRisk())), Integer.toString(risk[riskCount].getRiskScore())));
                        break;
                }
                pass("Risk Score of " + risk[riskCount].getRisk() + " was verified. - verifyRiskScoreReadView");
            }
        } catch (AssertionError e) {
            fail("Risk Scores should be sync with Risk Category Scoring Form. - verifyRiskScoreReadView");
            throw e;
        }
    }

    /**
     * Use this function to verify Risk Area added
     * @param riskArea risk added
     */
    public void verifyRiskAreaAdded(Risk riskArea) throws IllegalAccessException {

        try {
            switch (riskArea) {
                case COR: case SER: case TER: case ANT: case GOC: case FRA: case MON: case TAX: case SAN:
                    Assert.assertTrue(isElementVisible(LBL_INTEGRITY_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_INTEGRITY_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LNK_RISKS_OUTLINER_INTEGRITY_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_INTEGRITY_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LBL_SCORING_INTEGRITY_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_SCORING_INTEGRITY_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LNK_SCORING_OUTLINER_INTEGRITY_RISKS));
                    break;

                case ENV: case ANI: case SAL: case HEA: case HUM: case MOD: case EMP: case GOV: case REG:
                    Assert.assertTrue(isElementVisible(LBL_ESG_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_ESG_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LNK_RISKS_OUTLINER_ESG_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_ESG_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LBL_SCORING_ESG_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_SCORING_ESG_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LNK_SCORING_OUTLINER_ESG_RISKS));
                    break;

                case FIN: case FII:
                    Assert.assertTrue(isElementVisible(LBL_FINANCIAL_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_FINANCIAL_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LNK_RISKS_OUTLINER_FINANCIAL_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_FINANCIAL_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LBL_SCORING_FINANCIAL_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_SCORING_FINANCIAL_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LNK_SCORING_OUTLINER_FINANCIAL_RISKS));
                    break;

                case IND: case PRO: case OPE: case BUS:
                    Assert.assertTrue(isElementVisible(LBL_OAQ_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_OAQ_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LNK_RISKS_OUTLINER_OAQ_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_OAQ_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LBL_SCORING_OAQ_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_SCORING_OAQ_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LNK_SCORING_OUTLINER_OAQ_RISKS));
                    break;

                case SRC: case CTR: case COI:
                    Assert.assertTrue(isElementVisible(LBL_ENGAGEMENT_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_ENGAGEMENT_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LNK_RISKS_OUTLINER_ENGAGEMENT_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LNK_RISKS_OUTLINER_ENGAGEMENT_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LBL_SCORING_ENGAGEMENT_RISKS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_SCORING_ENGAGEMENT_RISKS_RISK_AREA, riskArea.getRisk())));
                    Assert.assertTrue(isElementVisible(LNK_SCORING_OUTLINER_ENGAGEMENT_RISKS));
                    break;
            }
            Assert.assertTrue(isElementVisible(LNK_RISK_CATEGORIES));
            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, BTN_ADD_RISK_SUMMARY, riskArea.getRisk())));
            pass(riskArea.getRisk() + " risk area was added. - verifyRiskAreaAdded");
        } catch (AssertionError e) {
            fail(riskArea.getRisk() + " risk area should be added. - verifyRiskAreaAdded");
            throw e;
        }
    }

    /**
     * Use this function to verify Risks Scoring Form
     * @param riskCategory risk category form to verify
     */
    public void verifyRiskScoringForm(RiskCategory riskCategory) throws IllegalAccessException {

        try {
            switch (riskCategory) {
                case INTEGRITY_RISKS:
                    Assert.assertTrue(isElementVisible(TXT_INTEGRITY_RISKS));
                    for (int integrityRiskCount = 0; integrityRiskCount<=INTEGRITY_RISKS.length-1; integrityRiskCount++) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, TXT_RISK_AREA, INTEGRITY_RISKS[integrityRiskCount].getRisk())));
                    }
                    break;

                case ESG_RISKS:
                    Assert.assertTrue(isElementVisible(TXT_ESG_RISKS));
                    for (int integrityRiskCount = 0; integrityRiskCount<=ESG_RISKS.length-1; integrityRiskCount++) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, TXT_RISK_AREA, ESG_RISKS[integrityRiskCount].getRisk())));
                    }
                    break;

                case OAQ_RISKS:
                    Assert.assertTrue(isElementVisible(TXT_OAQ_RISKS));
                    for (int integrityRiskCount = 0; integrityRiskCount<=OAQ_RISKS.length-1; integrityRiskCount++) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, TXT_RISK_AREA, OAQ_RISKS[integrityRiskCount].getRisk())));
                    }
                    break;

                case FINANCIAL_RISKS:
                    Assert.assertTrue(isElementVisible(TXT_FINANCIAL_RISKS));
                    for (int integrityRiskCount = 0; integrityRiskCount<=FINANCIAL_RISKS.length-1; integrityRiskCount++) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, TXT_RISK_AREA, FINANCIAL_RISKS[integrityRiskCount].getRisk())));
                    }
                    break;

                case ENGAGEMENT_RISKS:
                    Assert.assertTrue(isElementVisible(TXT_ENGAGEMENT_RISKS));
                    for (int integrityRiskCount = 0; integrityRiskCount<=ENGAGEMENT_RISKS.length-1; integrityRiskCount++) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, TXT_RISK_AREA, ENGAGEMENT_RISKS[integrityRiskCount].getRisk())));
                    }
                    break;
            }
            Assert.assertTrue(isElementVisible(BTN_SAVE));
            Assert.assertTrue(isElementVisible(BTN_CANCEL));
            pass("All elements in " + riskCategory.getRiskCategory() + " Scoring Form was verified. - verifyRiskScoringForm");
        } catch (AssertionError e) {
            fail("All elements in " + riskCategory.getRiskCategory() + " Scoring Form should be verified. - verifyRiskScoringForm");
            throw e;
        }
    }

    /**
     * Use this function to edit risk summary form
     * @param riskSummary details of risk summary
     */
    public void editRiskSummaryForm(RiskSummary riskSummary) throws IllegalAccessException {
        if (riskSummary.getRiskSummaryType() != null) {
            click(DD_TYPE);
            click(createDynamicLocator(XPATH, LST_TYPE_VALUE, riskSummary.getRiskSummaryType().getRiskSummaryType()));
        }

        if (!riskSummary.getSummary().isEmpty()) {
            type(TXT_RISK_SUMMARY, riskSummary.getSummary());
        }

        if (riskSummary.getSourceRecord() != null) {
            this.referenceSourceRecord(riskSummary.getSourceRecord());
        }
    }

    /**
     * Use this function to populate risk summary form and save
     * @param riskSummary details of risk summary
     */
    public void editRiskSummaryFormAndSave(RiskSummary riskSummary) throws IllegalAccessException {
        this.editRiskSummaryForm(riskSummary);
        click(BTN_SAVE);
    }

    /**
     * Use this function to add risk summary and save
     * @param riskSummary details of risk summary
     */
    public void addRiskSummaryAndSave(RiskSummary riskSummary) throws IllegalAccessException {
        waitElementToBeInvisible(BTN_SAVE);
        waitElementToBeInvisible(IMG_LOADER);
        click(createDynamicLocator(XPATH, BTN_ADD_RISK_SUMMARY, riskSummary.getRisk().getRisk()));
        this.editRiskSummaryFormAndSave(riskSummary);
        riskSummary.getRisk().setRiskSummaryCount(riskSummary.getRisk().getRiskSummaryCount() + 1);
    }

    /**
     * Use this function to verify error regarding Risk Summary is required was displayed
     */
    public void verifyErrorRiskSummaryRequiredIsDisplayed() {
        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_RISK_SUMMARY), ERR_REQUIRED_FIELD));
            pass("Error regarding Risk Summary is required was displayed. - verifyErrorRiskSummaryRequiredIsDisplayed");
        } catch (AssertionError e) {
            fail("Error regarding Risk Summary is required should be displayed. - verifyErrorRiskSummaryRequiredIsDisplayed");
            throw e;
        }
    }

    /**
     * Use this function to add, modify and save source record
     * @param sourceRecord source record to add and verify
     */
    public void addSourceRecordAndVerify(SourceRecord sourceRecord) throws IllegalAccessException, ParseException {
        this.addSourceRecord(sourceRecord);
        this.modifyAndSaveSourceRecordForm(sourceRecord);
        this.verifyRiskAreaTagToRelevantSourceRecordVisible(sourceRecord);
        this.verifySourceRecordReadView(sourceRecord);
    }

    /**
     * Use this function to verify Add Risk Insight Button Disabled
     */
    public void verifyAddRiskInsightButtonDisabled() throws IllegalAccessException {
        try {
            Assert.assertTrue(isElementDisabled(createDynamicLocator(Bys.XPATH, BTN_ADD_RISK_INSIGHT, RANDOM_RISK00.getRisk())));
            pass("Add Risk Insight Button was disabled. - verifyAddRiskInsightButtonDisabled");
        } catch (AssertionError e) {
            fail("Add Risk Insight Button should be disabled. - verifyAddRiskInsightButtonDisabled");
            throw e;
        }
    }

    /**
     * Use this function to verify tooltip message regarding Risk Insight is displayed
     */
    public void verifyTooltipMessageRegardingRiskInsightIsDisplayed() {
        String tooltipRiskInsight = "Risk Insight must be filled up first.";

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(TT_FURTHER_STEPS), tooltipRiskInsight));
            pass("Tooltip message regarding Risk Insight was displayed. - verifyTooltipMessageRegardingRiskInsightIsDisplayed");
        } catch (AssertionError e) {
            fail("Tooltip message regarding Risk Insight should be displayed. - verifyTooltipMessageRegardingRiskInsightIsDisplayed");
            throw e;
        }
    }

    /**
     * Use this function to edit risk insight form
     * @param riskInsight details of risk insight
     */
    public void editRiskInsightForm(RiskInsight riskInsight) throws IllegalAccessException, InterruptedException {
        if (riskInsight.getRiskInsightType() != null) {
            click(DD_TYPE);
            click(createDynamicLocator(XPATH, LST_TYPE_VALUE, riskInsight.getRiskInsightType().getRiskSummaryType()));
        }

        if (!riskInsight.getRiskInsight().isEmpty()) {
            type(TXT_RISK_INSIGHT, riskInsight.getRiskInsight());
            sleepForSeconds(1);
        }

        if (riskInsight.getFurtherSteps() == ToggleOption.ON) {
            toggleOn(SWTCH_FURTHER_STEPS);
            if (riskInsight.getRecommendation() != null && !riskInsight.getRecommendation().isEmpty()) {
                type(TXT_RECOMMENDATION, riskInsight.getRecommendation());
            }
        } else {
            toggleOff(SWTCH_FURTHER_STEPS);
        }
    }

    /**
     * Use this function to verify risk insight form
     */
    public void verifyRiskInsightForm() throws IllegalAccessException {
        try {
            Assert.assertTrue(isElementVisible(DD_TYPE));
            click(DD_TYPE);
            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH,LST_TYPE_VALUE, RiskInformationType.PUBLIC.getRiskSummaryType())));
            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH,LST_TYPE_VALUE, RiskInformationType.CLIENT_SPECIFIC.getRiskSummaryType())));
            click(DD_TYPE);

            if (getText(TXT_RISK_INSIGHT).isEmpty()) {
                Assert.assertTrue(isActualValueEqualsValue("true", getAttributeValue(SWTCH_FURTHER_STEPS, "aria-disabled")));
            } else {
                Assert.assertTrue(isActualValueEqualsValue("false", getAttributeValue(SWTCH_FURTHER_STEPS, "aria-disabled")));
            }

            if (getAttributeValue(SWTCH_FURTHER_STEPS, CLASS).contains(CHECKED)) {
                Assert.assertTrue(isElementVisible(TXT_RECOMMENDATION));
            } else {
                Assert.assertTrue(isElementNotVisible(TXT_RECOMMENDATION));
            }
            Assert.assertTrue(isElementVisible(BTN_SAVE));
            Assert.assertTrue(isElementVisible(BTN_CANCEL));
            pass("All elements in Risk Insight Form was verified. - verifyRiskInisghtForm");
        } catch (AssertionError e) {
            fail("All elements in Risk Insight Form should be verified. - verifyRiskInsightForm");
            throw e;
        }
    }

    /**
     * Use this function to add edit and save risk insight
     * @param riskInsight details of risk insight
     */
    public void addRiskInsightAndSave(RiskInsight riskInsight) throws IllegalAccessException, InterruptedException {
        waitElementToBeInvisible(BTN_SAVE);
        waitElementToBeInvisible(IMG_LOADER);
        click(createDynamicLocator(XPATH, BTN_ADD_RISK_INSIGHT, riskInsight.getRisk().getRisk()));
        this.editRiskInsightFormAndSave(riskInsight);
    }

    /**
     * Use this function to verify risk insight read view section
     * @param riskInsight details of risk insight to verify
     */
    public void verifyRiskInsightReadView(RiskInsight... riskInsight) throws IllegalAccessException {
        int riskInsightCount = 0;

        try {
            for (riskInsightCount=0; riskInsightCount<=riskInsight.length-1; riskInsightCount++) {
                waitElementToBeInvisible(IMG_LOADER);
                if (riskInsight[riskInsightCount].getRiskInsightType().equals(RiskInformationType.PUBLIC)) {
                    Assert.assertTrue(isValueDisplayedForDuplicateElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_PUBLIC_VALUE, riskInsight[riskInsightCount].getRisk().getRisk()), riskInsight[riskInsightCount].getRiskInsight()));
                } else if (riskInsight[riskInsightCount].getRiskInsightType().equals(RiskInformationType.CLIENT_SPECIFIC)) {
                    Assert.assertTrue(isValueDisplayedForDuplicateElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_CLIENT_SPECIFIC_VALUE, riskInsight[riskInsightCount].getRisk().getRisk()), riskInsight[riskInsightCount].getRiskInsight()));
                }

                if (riskInsight[riskInsightCount].getRecommendation() != null) {
                    Assert.assertTrue(isValueDisplayedForDuplicateElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_RECOMMENDATION_VALUE, riskInsight[riskInsightCount].getRisk().getRisk()), riskInsight[riskInsightCount].getRecommendation()));
                }
                pass("Risk Insight " + riskInsight[riskInsightCount].getRiskInsight() + " was added/updated. - verifyRiskInsightReadView");
            }
        } catch (AssertionError e) {
            fail("Risk Insight " + riskInsight[riskInsightCount].getRiskInsight() + " should be added/updated. - verifyRiskInsightReadView");
            throw e;
        }
    }

    /**
     * Use this function to open risk insight
     * @param riskInsight details of risk insight to edit or save
     */
    public void openRiskInsightForm(RiskInsight riskInsight) throws IllegalAccessException {
        waitElementToBeInvisible(BTN_SAVE);
        waitElementToBeInvisible(IMG_LOADER);
        int index;
        switch (riskInsight.getRiskInsightType()){
            case PUBLIC:
                index = 1;
                for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_PUBLIC_VALUE, riskInsight.getRisk().getRisk()))) {
                    if (isActualValueEqualsValue(element.getText(), riskInsight.getRiskInsight())) {
                        element.click();
                        click(BTN_EDIT_DATA);
                        if (!isElementNotVisible(BTN_CONTINUE)) {
                            click(BTN_CONTINUE);
                            waitElementToBeInvisible(BTN_CONTINUE);
                        }
                        break;
                    } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_PUBLIC_VALUE, riskInsight.getRisk().getRisk())).size()) {
                        fail(riskInsight.getRiskInsight() + " should be available. - openRiskInsightForm");
                        Assert.fail();
                    }
                    index ++;
                }
                break;
            case CLIENT_SPECIFIC:
                index = 1;
                for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_CLIENT_SPECIFIC_VALUE, riskInsight.getRisk().getRisk()))) {
                    if (isActualValueEqualsValue(element.getText(), riskInsight.getRiskInsight())) {
                        element.click();
                        click(BTN_EDIT_DATA);
                        if (!isElementNotVisible(BTN_CONTINUE)) {
                            click(BTN_CONTINUE);
                            waitElementToBeInvisible(BTN_CONTINUE);
                        }
                        break;
                    } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_CLIENT_SPECIFIC_VALUE, riskInsight.getRisk().getRisk())).size()) {
                        fail(riskInsight.getRiskInsight() + " should be available. - openRiskInsightForm");
                        Assert.fail();
                    }
                    index++;
                }
                break;
        }
    }

    /**
     * Use this function to edit risk insight form and save
     * @param riskInsight details of risk insight
     */
    public void editRiskInsightFormAndSave(RiskInsight riskInsight) throws IllegalAccessException, InterruptedException {
        this.editRiskInsightForm(riskInsight);
        click(BTN_SAVE);
    }

    /**
     * Use this function to open, edit and save specific risk insight
     * @param riskInsight details of risk insight to edit
     * @param riskInsightB updated risk insight
     */
    public void editRiskInsightAndSave(RiskInsight riskInsight, RiskInsight riskInsightB) throws IllegalAccessException, InterruptedException {
        this.openRiskInsightForm(riskInsight);
        this.editRiskInsightFormAndSave(riskInsightB);
    }

    /**
     * Use this function to open, edit and cancel edit for specific risk insight
     * @param riskInsight details of risk insight to edit
     * @param riskInsightB updated risk insight
     */
    public void editRiskInsightAndCancel(RiskInsight riskInsight, RiskInsight riskInsightB) throws IllegalAccessException, InterruptedException {
        this.openRiskInsightForm(riskInsight);
        this.editRiskInsightForm(riskInsightB);
        click(BTN_CANCEL);
        waitElementToBeInvisible(BTN_CANCEL);
    }

    /**
     * Use this function to go to remove risk summary
     * @param riskSummary details of risk summary
     */
    public void goToRemoveRiskSummary(RiskSummary riskSummary) throws IllegalAccessException {
        waitElementToBeInvisible(BTN_SAVE);
        waitElementToBeInvisible(IMG_LOADER);
        int index;
        switch (riskSummary.getRiskSummaryType()) {
            case PUBLIC:
                index = 1;
                for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_PUBLIC_VALUE, riskSummary.getRisk().getRisk()))) {
                    if (isActualValueEqualsValue(element.getText(), riskSummary.getSummary())) {
                        int counter = 0;
                        do {
                            element.click();
                            if (counter == 10) {
                                Assert.assertTrue(isElementVisible(BTN_REMOVE_DATA));
                            }
                            counter++;
                        } while (isElementNotVisible(BTN_REMOVE_DATA));
                        click(BTN_REMOVE_DATA);
                        waitElementToBeInvisible(BTN_REMOVE_DATA);
                        break;
                    } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_PUBLIC_VALUE, riskSummary.getRisk().getRisk())).size()) {
                        fail(riskSummary.getSummary() + " should be available. - goToRemoveRiskSummary");
                        Assert.fail();
                    }
                    index ++;
                }
                break;
            case CLIENT_SPECIFIC:
                index = 1;
                for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_CLIENT_SPECIFIC_VALUE, riskSummary.getRisk().getRisk()))) {
                    if (isActualValueEqualsValue(element.getText(), riskSummary.getSummary())) {
                        int counter = 0;
                        do {
                            element.click();
                            if (counter == 10) {
                                Assert.assertTrue(isElementVisible(BTN_REMOVE_DATA));
                            }
                            counter++;
                        } while (isElementNotVisible(BTN_REMOVE_DATA));
                        click(BTN_REMOVE_DATA);
                        waitElementToBeInvisible(BTN_REMOVE_DATA);
                        break;
                    } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_CLIENT_SPECIFIC_VALUE, riskSummary.getRisk().getRisk())).size()) {
                        fail(riskSummary.getSummary() + " should be available. - goToRemoveRiskSummary");
                        Assert.fail();
                    }
                    index ++;
                }
                break;
        }
    }

    /**
     * Use this function to verify message in remove risk anlysis modal
     * @param riskSummary risk summary to be removed
     */
    public void verifyRemoveRiskSummaryMessage(RiskSummary riskSummary) {
        String titleRemoveRiskSummary = "Remove Risk Summary";
        String msgRemoveRiskSummary = "Are you sure you want to remove this Risk Summary?";
        String msgRemoveRiskSummaryAndRiskInsight = "Removing the Risk Summary will remove Risk Insights for " + riskSummary.getRisk().getRisk();

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_REMOVE_RISK_SUMMARY), titleRemoveRiskSummary));
            Assert.assertTrue(isActualValueContainsValue(getText(MSG_CASE_EDITOR_MODAL), msgRemoveRiskSummary));
            if (riskSummary.getRisk().getRiskSummaryCount() == 1) {
                Assert.assertTrue(isActualValueContainsValue(getText(MSG_CASE_EDITOR_MODAL), msgRemoveRiskSummaryAndRiskInsight));
            }
            Assert.assertTrue(isElementVisible(BTN_REMOVE));
            Assert.assertTrue(isElementVisible(BTN_CANCEL));
            info("Message in Remove Risk Summary Modal was verified. - verifyRemoveRiskSummaryMessage");
        } catch (AssertionError e) {
            fail("Message in Remove Risk Summary Modal should be verified. - verifyRemoveRiskSummaryMessage");
            throw e;
        }
    }

    /**
     * Use this function to remove risk summary
     * @param riskSummary details of risk summary
     */
    public void removeRiskSummary(RiskSummary riskSummary) throws IllegalAccessException {
        this.goToRemoveRiskSummary(riskSummary);
        this.verifyRemoveRiskSummaryMessage(riskSummary);
        click(BTN_REMOVE);
        waitElementToBeInvisible(BTN_REMOVE);
        waitElementToBeInvisible(IMG_LOADER);
    }

    /**
     * Use this function to remove risk summary and cancel
     * @param riskSummary details of risk summary
     */
    public void removeRiskSummaryAndCancel(RiskSummary riskSummary) throws IllegalAccessException {
        this.goToRemoveRiskSummary(riskSummary);
        this.verifyRemoveRiskSummaryMessage(riskSummary);
        click(BTN_CANCEL);
        waitElementToBeInvisible(BTN_CANCEL);
        waitElementToBeInvisible(IMG_LOADER);
    }

    /**
     * Use this function to verify risk summary removed in read view section
     * @param riskSummary details of risk summary to verify
     */
    public void verifyRiskSummaryRemoved(RiskSummary... riskSummary) throws IllegalAccessException {

        try {
            for (int riskSummaryCount = 0; riskSummaryCount <= riskSummary.length - 1; riskSummaryCount++) {
                waitElementToBeInvisible(IMG_LOADER);
                int index;
                switch (riskSummary[riskSummaryCount].getRiskSummaryType()) {
                    case PUBLIC:
                        index = 1;
                        for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_PUBLIC_VALUE, riskSummary[riskSummaryCount].getRisk().getRisk()))) {
                            if (isActualValueEqualsValue(element.getText(), riskSummary[riskSummaryCount].getSummary())) {
                                Assert.fail();
                            } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_PUBLIC_VALUE, riskSummary[riskSummaryCount].getRisk().getRisk())).size()) {
                                break;
                            }
                            index ++;
                        }
                        break;
                    case CLIENT_SPECIFIC:
                        index = 1;
                        for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_CLIENT_SPECIFIC_VALUE, riskSummary[riskSummaryCount].getRisk().getRisk()))) {
                            if (isActualValueEqualsValue(element.getText(), riskSummary[riskSummaryCount].getSummary())) {
                                Assert.fail();
                            } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_SUMMARY_CLIENT_SPECIFIC_VALUE, riskSummary[riskSummaryCount].getRisk().getRisk())).size()) {
                                break;
                            }
                            index ++;
                        }
                        break;
                }
                pass("Risk Summary was removed. - verifyRiskSummaryRemoved");
            }
        } catch (AssertionError e) {
            fail("Risk Summaryshould be removed. - verifyRiskSummaryRemoved");
            throw e;
        }
    }

    /**
     * Use this function to verify risk insight removed in read view section
     * @param riskInsight details of risk insight to verify
     */
    public void verifyRiskInsightRemoved(RiskInsight... riskInsight) throws IllegalAccessException {

        try {
            for (int riskInsightCount=0; riskInsightCount<=riskInsight.length-1; riskInsightCount++) {
                waitElementToBeInvisible(IMG_LOADER);
                int index;
                switch (riskInsight[riskInsightCount].getRiskInsightType()) {
                    case PUBLIC:
                        index = 1;
                        for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_PUBLIC_VALUE, riskInsight[riskInsightCount].getRisk().getRisk()))) {
                            if (isActualValueEqualsValue(element.getText(), riskInsight[riskInsightCount].getRiskInsight())) {
                                Assert.fail();
                            } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_PUBLIC_VALUE, riskInsight[riskInsightCount].getRisk().getRisk())).size()) {
                                break;
                            }
                            index++;
                        }
                        break;
                    case CLIENT_SPECIFIC:
                        index = 1;
                        for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_CLIENT_SPECIFIC_VALUE, riskInsight[riskInsightCount].getRisk().getRisk()))) {
                            if (isActualValueEqualsValue(element.getText(), riskInsight[riskInsightCount].getRiskInsight())) {
                                Assert.fail();
                            } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_CLIENT_SPECIFIC_VALUE, riskInsight[riskInsightCount].getRisk().getRisk())).size()) {
                                break;
                            }
                            index++;
                        }
                        break;
                }
                pass("Risk Insight was removed. - verifyRiskInsightRemoved");
            }
        } catch (AssertionError e) {
            fail("Risk Insight should be removed. - verifyRiskInsightRemoved");
            throw e;
        }
    }

    /**
     * Use this function to go to remove risk insight
     * @param riskInsight details of risk insight to remove
     */
    public void goToRemoveRiskInsight(RiskInsight riskInsight) throws IllegalAccessException {
        waitElementToBeInvisible(BTN_SAVE);
        waitElementToBeInvisible(IMG_LOADER);
        int index;
        switch (riskInsight.getRiskInsightType()) {
            case PUBLIC:
                index = 1;
                for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_PUBLIC_VALUE, riskInsight.getRisk().getRisk()))) {
                    if (isActualValueEqualsValue(element.getText(), riskInsight.getRiskInsight())) {
                        int counter = 0;
                        do {
                            element.click();
                            if (counter == 10) {
                                Assert.assertTrue(isElementVisible(BTN_REMOVE_DATA));
                            }
                            counter++;
                        } while (isElementNotVisible(BTN_REMOVE_DATA));
                        click(BTN_REMOVE_DATA);
                        waitElementToBeInvisible(BTN_REMOVE_DATA);
                        break;
                    } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_PUBLIC_VALUE, riskInsight.getRisk().getRisk())).size()) {
                        fail(riskInsight.getRiskInsight() + " should be available. - goToRemoveRiskInsight");
                        Assert.fail();
                    }
                    index ++;
                }
                break;
            case CLIENT_SPECIFIC:
                index = 1;
                for (WebElement element : findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_CLIENT_SPECIFIC_VALUE, riskInsight.getRisk().getRisk()))) {
                    if (isActualValueEqualsValue(element.getText(), riskInsight.getRiskInsight())) {
                        int counter = 0;
                        do {
                            element.click();
                            if (counter == 10) {
                                Assert.assertTrue(isElementVisible(BTN_REMOVE_DATA));
                            }
                            counter++;
                        } while (isElementNotVisible(BTN_REMOVE_DATA));
                        click(BTN_REMOVE_DATA);
                        waitElementToBeInvisible(BTN_REMOVE_DATA);
                        break;
                    } else if (index == findElements(createDynamicLocator(XPATH, LBL_RISK_INSIGHT_CLIENT_SPECIFIC_VALUE, riskInsight.getRisk().getRisk())).size()) {
                        fail(riskInsight.getRiskInsight() + " should be available. - goToRemoveRiskInsight");
                        Assert.fail();
                    }
                    index++;
                }
                break;
        }
    }

    /**
     * Use this function to verify remove risk insight modal
     */
    public void verifyRemoveRiskInsightModal() {
        String titleRemoveRiskInsight = "Remove Risk Insight";
        String msgRemoveRiskInsight = "Are you sure you want to remove this Risk Insight?";

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_REMOVE_RISK_INSIGHT), titleRemoveRiskInsight));
            Assert.assertTrue(isActualValueContainsValue(getText(MSG_CASE_EDITOR_MODAL), msgRemoveRiskInsight));
            Assert.assertTrue(isElementVisible(BTN_REMOVE));
            Assert.assertTrue(isElementVisible(BTN_CANCEL));
            info("Remove Risk Insight Modal was verified. - verifyRemoveRiskInsightModal");
        } catch (AssertionError e) {
            fail("Remove Risk Insight Modal should be verified. - verifyRemoveRiskInsightModal");
            throw e;
        }
    }

    /**
     * Use this function to remove risk insight
     * @param riskInsight details of risk insight to remove
     */
    public void removeRiskInsight(RiskInsight riskInsight) throws IllegalAccessException {
        this.goToRemoveRiskInsight(riskInsight);
        this.verifyRemoveRiskInsightModal();
        click(BTN_REMOVE);
        waitElementToBeInvisible(BTN_REMOVE);
        waitElementToBeInvisible(IMG_LOADER);
    }

    /**
     * Use this function to remove risk insight and cancel
     * @param riskInsight details of risk insight to edit
     */
    public void removeRiskInsightAndCancel(RiskInsight riskInsight) throws IllegalAccessException {
        this.goToRemoveRiskInsight(riskInsight);
        this.verifyRemoveRiskInsightModal();
        click(BTN_CANCEL);
        waitElementToBeInvisible(BTN_CANCEL);
        waitElementToBeInvisible(IMG_LOADER);
    }

    /**
     * Use this function to verify user cannot edit case member
     */
    public void verifyUserCannotEditCaseMember() {

        try {
            Assert.assertTrue(isElementNotVisible(TXT_CASE_OWNER));
            Assert.assertTrue(isElementNotVisible(TXT_WRITER_NAME));
            Assert.assertTrue(isElementNotVisible(TXT_REVIEWER_NAME));
            info("User was not able to edit case members. - verifyUserCannotEditCaseMember");
        } catch (AssertionError e) {
            fail("User should not be able to edit case members. - verifyUserCannotEditCaseMember");
            throw e;
        }
    }

    /**
     * Use this function to set specific from date in profile data form
     * @param day   any day from 1-31
     * @param month any month Jan - Dec
     * @param year  any year ex 1989
     */
    public void setSpecificFromDateInProfileDataForm(int day, String month, int year) throws IllegalAccessException {
        toggleOff(CHK_FROM_PRESENT);
        if (day > 0) {
            type(TXT_FROM_DAY, Integer.toString(day));
            click(createDynamicLocator(XPATH, TXT_DAY_VALUE, day));
        }
        if (!month.isEmpty()) {
            type(TXT_FROM_MONTH, month);
            click(createDynamicLocator(XPATH, TXT_MONTH_VALUE, month));
        }
        if (year > 0) {
            type(TXT_FROM_YEAR, Integer.toString(year));
            click(createDynamicLocator(XPATH, TXT_YEAR_VALUE, year));
        }
    }

    /**
     * Use this function to set specific To date in profile data form
     * @param day   any day from 1-31
     * @param month any month Jan - Dec
     * @param year  any year ex 1989
     */
    public void setSpecificToDateInProfileDataForm(int day, String month, int year) throws IllegalAccessException {
        toggleOff(CHK_TO_PRESENT);
        if (day > 0) {
            type(TXT_TO_DAY, Integer.toString(day));
            click(createDynamicLocator(XPATH, TXT_DAY_VALUE, day));
        }
        if (!month.isEmpty()) {
            type(TXT_TO_MONTH, month);
            click(createDynamicLocator(XPATH, TXT_MONTH_VALUE, month));
        }
        if (year > 0) {
            type(TXT_TO_YEAR, Integer.toString(year));
            click(createDynamicLocator(XPATH, TXT_YEAR_VALUE, year));
        }
    }

    /**
     * Use this function to edit data entry form
     * @param dataEntry details of data entry to edit
     */
    public void editDataEntryForm(DataEntry dataEntry) throws IllegalAccessException {
        if (dataEntry.getProfileData() == ProfileData.PART_OF_GROUP_OF_COMPANIES) {
            if (dataEntry.getPartOfGroupOfCompanies()) {
                click(RDO_PART_OF_GROUP_OF_COMPANIES_YES);
                if (dataEntry.getNameOfGroup() != null) {
                    type(TXT_NAME_OF_GROUP, dataEntry.getNameOfGroup());
                }
            } else {
                click(RDO_PART_OF_GROUP_OF_COMPANIES_NO);
            }
        }
        if (dataEntry.getWebsiteDescription() != null) {
            type(TXT_WEBSITE_DESCRIPTION, dataEntry.getWebsiteDescription());
        }
        if (dataEntry.getUrl() != null) {
            type(TXT_URL, dataEntry.getUrl());
        }
        if (dataEntry.getComment() != null) {
            type(TXT_PROFILE_DATA_COMMENT, dataEntry.getComment());
        }
        if (dataEntry.getDetails() != null) {
            type(TXT_DETAILS, dataEntry.getDetails());
        }
        if (dataEntry.getKeyIndustry() != null) {
            type(TXT_INDUSTRY, dataEntry.getKeyIndustry().getKeyIndustry());
            click(createDynamicLocator(XPATH, LST_INDUSTRY_VALUE, dataEntry.getKeyIndustry().getKeyIndustry()));
        }
        if (dataEntry.getName() != null) {
            type(TXT_NAME, dataEntry.getName());
        }
        if (dataEntry.getFromPresentToggle() != null) {
            if (dataEntry.getFromPresentToggle() == ToggleOption.ON) {
                toggleOn(CHK_FROM_PRESENT);
            } else {
                toggleOff(CHK_FROM_PRESENT);
                if (dataEntry.getFromDay() > 0 || dataEntry.getFromMonth() != null || dataEntry.getFromYear() > 0) {
                    this.setSpecificFromDateInProfileDataForm(dataEntry.getFromDay(), dataEntry.getFromMonth(), dataEntry.getFromYear());
                }
            }
        }
        if (dataEntry.getToPresentToggle() != null) {
            if (dataEntry.getToPresentToggle() == ToggleOption.ON) {
                toggleOn(CHK_TO_PRESENT);
            } else {
                toggleOff(CHK_TO_PRESENT);
                if (dataEntry.getToDay() > 0 || dataEntry.getToMonth() != null || dataEntry.getToYear() > 0) {
                    this.setSpecificToDateInProfileDataForm(dataEntry.getToDay(), dataEntry.getToMonth(), dataEntry.getToYear());
                }
            }
        }
        if (dataEntry.getSourceRecord() != null) {
            this.referenceSourceRecord(dataEntry.getSourceRecord());
        }
    }

    /**
     * Use this function to edit data entry form and save
     * @param dataEntry details of data entry to save
     */
    public void editDataEntryFormAndSave(DataEntry dataEntry) throws IllegalAccessException {
        this.editDataEntryForm(dataEntry);
        click(BTN_SAVE);
        waitElementToBeInvisible(BTN_SAVE);
        dataEntry.getProfileData().setProfileDataEntryCount(dataEntry.getProfileData().getProfileDataEntryCount() + 1);
    }

    /**
     * Use this function to edit data entry form and cancel
     * @param dataEntry details of data entry to cancel
     */
    public void editDataEntryFormAndCancel(DataEntry dataEntry) throws IllegalAccessException {
        this.editDataEntryForm(dataEntry);
        click(BTN_CANCEL);
        waitElementToBeInvisible(BTN_CANCEL);
    }

    /**
     * Use this function to go to remove data entry modal
     * @param value data entry value to be removed
     */
    public void goToRemoveDataEntryModal(String value) throws IllegalAccessException {
        waitElementToBeInvisible(BTN_SAVE);
        waitElementToBeInvisible(IMG_LOADER);
        int index = 0;
        do {
            hover(createDynamicLocator(XPATH, LBL_DATA_VALUE, value));
            click(LBL_PROFILE_FIRST_NAME);
            hover(createDynamicLocator(XPATH, LBL_DATA_VALUE, value));
            if (index == 10) {
                Assert.assertTrue(isElementVisible(BTN_REMOVE_DATA), "Remove Button should be visible.");
            }
            index++;
        } while (!isElementVisible(BTN_REMOVE_DATA));
        click(BTN_REMOVE_DATA);
        waitElementToBeInvisible(BTN_REMOVE_DATA);
    }

    /**
     * Use this function to verify remove data entry modal
     * @param profileData profile data of data entry to be removed
     */
    public void verifyRemoveDataEntryModal(ProfileData profileData) {
        String titleRemoveDataEntry = "Remove " + profileData.getProfileData();
        String msgRemoveDataEntry = "Are you sure you want to remove this entry?";

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_REMOVE_DATA_ENTRY), titleRemoveDataEntry));
            Assert.assertTrue(isActualValueEqualsValue(getText(MSG_CASE_EDITOR_MODAL), msgRemoveDataEntry));
            pass("Remove " + profileData.getProfileData() + " modal was verified. - verifyRemoveDataEntryModal");
        } catch (AssertionError e) {
            fail("Remove " + profileData.getProfileData() + " should be verified. - verifyRemoveDataEntryModal");
            throw e;
        }
    }

    /**
     * Use this function to remove specific data entry of a profile data
     * @param value data entry value to be removed
     */
    public void removeSpecificDataEntryOfProfileData(ProfileData profileData, String value) throws IllegalAccessException {
        this.goToRemoveDataEntryModal(value);
        this.verifyRemoveDataEntryModal(profileData);
        click(BTN_REMOVE);
        waitElementToBeInvisible(BTN_REMOVE);
        if (profileData.getProfileDataEntryCount() > 0){
            profileData.setProfileDataEntryCount(profileData.getProfileDataEntryCount() - 1);
        }
    }

    /**
     * Use this function to cancel removal of data entry
     * @param value data entry value to be removed
     */
    public void removeSpecificDataEntryOfProfileDataAndCancel(ProfileData profileData, String value) throws IllegalAccessException {
        this.goToRemoveDataEntryModal(value);
        this.verifyRemoveDataEntryModal(profileData);
        click(BTN_CANCEL);
        waitElementToBeInvisible(BTN_CANCEL);
    }

    /**
     * Use this function to verify data entry in profile data read view
     * @param dataEntry details of data entry to verify
     */
    public void verifyDataEntryInProfileDataReadView(DataEntry dataEntry) throws IllegalAccessException, ParseException {

        try {
            switch (dataEntry.getProfileData()) {
                case COMPANY_BACKGROUND: case COMPLIANCE_AND_ETHICS: case INDIVIDUAL_BACKGROUND:
                    Assert.assertTrue(isElementVisible(SECTION_GENERAL_INFORMATION));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_GENERAL_INFORMATION_PROFILE_DATA, dataEntry.getProfileData().getProfileData())));
                    if (dataEntry.getDetails() != null) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_DETAILS_VALUE, dataEntry.getDetails())));
                    } else {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_DETAILS_VALUE, DOUBLE_DASH)));
                    }
                    this.verifySourceRecordsReferencedToDataEntry(dataEntry, dataEntry.getDetails());
                    pass(dataEntry.getDetails() + " details was added to " + dataEntry.getProfileData().getProfileData() + " - verifyDataEntryInProfileDataReadView");
                    break;
                case PART_OF_GROUP_OF_COMPANIES:
                    Assert.assertTrue(isElementVisible(SECTION_GENERAL_INFORMATION));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_GENERAL_INFORMATION_PROFILE_DATA, dataEntry.getProfileData().getProfileData())));
                    if (dataEntry.getPartOfGroupOfCompanies()) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_PART_OF_GROUP_OF_COMPANIES_VALUE, YesNo.Y.getYesNo())));
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_NAME_OF_GROUP_VALUE, dataEntry.getNameOfGroup())));
                    } else {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_PART_OF_GROUP_OF_COMPANIES_VALUE, YesNo.N.getYesNo())));
                    }
                    if (dataEntry.getComment() != null) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_COMMENTS_VALUE, dataEntry.getComment())));
                    }
                    this.verifySourceRecordsReferencedToDataEntry(dataEntry, dataEntry.getNameOfGroup());
                    pass(dataEntry.getNameOfGroup() + " name of group was added to " + dataEntry.getProfileData().getProfileData() + " - verifyDataEntryInProfileDataReadView");
                    break;
                case WEBSITE:
                    Assert.assertTrue(isElementVisible(SECTION_GENERAL_INFORMATION));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_GENERAL_INFORMATION_PROFILE_DATA, dataEntry.getProfileData().getProfileData())));
                    if (dataEntry.getWebsiteDescription() != null) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_WEBSITE_DESCRIPTION_VALUE, dataEntry.getWebsiteDescription())));
                    } else {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_WEBSITE_DESCRIPTION_VALUE, DOUBLE_DASH)));
                    }
                    if (dataEntry.getUrl() != null) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_URL_VALUE, dataEntry.getUrl())));
                    } else {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_URL_VALUE, DOUBLE_DASH)));
                    }
                    this.verifySourceRecordsReferencedToDataEntry(dataEntry, dataEntry.getWebsiteDescription());
                    pass(dataEntry.getWebsiteDescription() + " website was added to " + dataEntry.getProfileData().getProfileData() + " - verifyDataEntryInProfileDataReadView");
                    break;
                case KEY_INDUSTRY:
                    Assert.assertTrue(isElementVisible(SECTION_GENERAL_INFORMATION));
                    Assert.assertTrue(isElementVisible(SECTION_KEY_INDUSTRY));
                    this.verifySourceRecordsReferencedToDataEntry(dataEntry, dataEntry.getKeyIndustry().getKeyIndustry());
                    pass(dataEntry.getKeyIndustry().getKeyIndustry() + " industry was added to " + dataEntry.getProfileData().getProfileData() + " - verifyDataEntryInProfileDataReadView");
                    break;
                case FULL_NAME: case REGISTERED_NAME: case SHORT_NAME: case NAME_IN_LOCAL_LANGUAGE: case TRADING_AS_NAME: case PREVIOUS_NAME:
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
                    Assert.assertTrue(isElementVisible(SECTION_NAMES));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_NAMES_PROFILE_DATA, dataEntry.getProfileData().getProfileData())));
                    if (dataEntry.getName() != null) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_NAMES_NAME_VALUE, dataEntry.getName())));
                    } else {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_NAMES_NAME_VALUE, DOUBLE_DASH)));
                    }
                    if (dataEntry.getFromPresentToggle() == ToggleOption.ON) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_DATA_ENTRY_FROM_VALUE, PRESENT)));
                    } else if (dataEntry.getFromDay() == 0 && dataEntry.getFromMonth() == null && dataEntry.getFromYear() == 0) {
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_DATA_ENTRY_FROM_VALUE, DOUBLE_DASH)));
                    } else {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_DATA_ENTRY_FROM_VALUE, dataEntry.getFromDate())));
                    }
                    if (dataEntry.getToPresentToggle() == ToggleOption.ON) {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_DATA_ENTRY_TO_VALUE, PRESENT)));
                    } else if (dataEntry.getToDay() == 0 && dataEntry.getToMonth() == null && dataEntry.getToYear() == 0) {
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_DATA_ENTRY_TO_VALUE, DOUBLE_DASH)));
                    } else {
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_DATA_ENTRY_TO_VALUE, dataEntry.getToDate())));
                    }
                    this.verifySourceRecordsReferencedToDataEntry(dataEntry, dataEntry.getName());
                    pass(dataEntry.getName() + " name was added to " + dataEntry.getProfileData().getProfileData() + " - verifyDataEntryInProfileDataReadView");
                    break;
            }
        } catch (AssertionError e) {
            fail("Data Entry should be added to " + dataEntry.getProfileData().getProfileData() + " - verifyDataEntryInProfileDataReadView");
            throw e;
        }
    }

    /**
     * Use this function to verify data entry in profile data read view
     * @param dataEntry details of data entry to verify
     */
    public void verifyDataEntryInProfileDataReadView(DataEntry... dataEntry) throws ParseException, IllegalAccessException {
        for (int dataEntryCount = 0; dataEntryCount <= dataEntry.length - 1; dataEntryCount++) {
            this.verifyDataEntryInProfileDataReadView(dataEntry[dataEntryCount]);
        }
    }

    /**
     * Use this function to verify source records for data entry
     * @param dataEntry information of data entry where source record is tag
     * @param dataEntryInformation specific information where source record is tag
     */
    public void verifySourceRecordsReferencedToDataEntry(DataEntry dataEntry, String dataEntryInformation) throws IllegalAccessException {
        int sourceRecordCount = 0;
        try {
            if (dataEntry.getSourceRecord() != null) {
                for (sourceRecordCount = 0; sourceRecordCount <= dataEntry.getSourceRecord().length - 1; sourceRecordCount++) {
                    Assert.assertTrue(isActualValueContainsValue(getText(createDynamicLocator(XPATH, LBL_DATA_ENTRY_SOURCE_VALUE, dataEntryInformation)), dataEntry.getSourceRecord()[sourceRecordCount].getRecordID()));
                    info(dataEntry.getSourceRecord()[sourceRecordCount].getRecordID() + " source record was referenced to data entry. - verifySourceRecordsReferencedToDataEntry");
                }
            } else {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(XPATH, LBL_DATA_ENTRY_SOURCE_VALUE, dataEntryInformation)), DOUBLE_DASH));
                info("No source record was referenced to data entry. - verifySourceRecordsReferencedToDataEntry");
            }
        } catch (AssertionError e) {
            fail(dataEntry.getSourceRecord()[sourceRecordCount].getRecordID() + " source record should be referenced to data entry. - verifySourceRecordsReferencedToDataEntry");
            throw e;
        }
    }

    /**
     * Use this function to verify data entry removed
     * @param dataEntry details of data entry to verify removed
     */
    public void verifyDataEntryRemoved(DataEntry dataEntry) throws IllegalAccessException {

        try {
            switch (dataEntry.getProfileData()) {
                case COMPANY_BACKGROUND: case COMPLIANCE_AND_ETHICS: case INDIVIDUAL_BACKGROUND:
                    if (dataEntry.getProfileData().getProfileDataEntryCount() > 0) {
                        Assert.assertTrue(isElementVisible(SECTION_GENERAL_INFORMATION));
                        Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, LBL_GENERAL_INFORMATION_PROFILE_DATA, dataEntry.getProfileData().getProfileData())));
                    } else {
                        Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_GENERAL_INFORMATION_PROFILE_DATA, dataEntry.getProfileData().getProfileData())));
                    }
                    Assert.assertTrue(isElementNotVisible(createDynamicLocator(XPATH, LBL_DETAILS_VALUE, dataEntry.getDetails())));
                    pass(dataEntry.getDetails() + " details was removed from " + dataEntry.getProfileData().getProfileData() + " - verifyDataEntryRemoved");
                    break;
                case KEY_INDUSTRY:
                    if (dataEntry.getProfileData().getProfileDataEntryCount() > 0) {
                        Assert.assertTrue(isElementVisible(SECTION_GENERAL_INFORMATION));
                        Assert.assertTrue(isElementVisible(SECTION_KEY_INDUSTRY));
                    } else {
                        Assert.assertTrue(isElementNotVisible(SECTION_KEY_INDUSTRY));
                    }
                    pass(dataEntry.getKeyIndustry().getKeyIndustry() + " industry was removed from " + dataEntry.getProfileData().getProfileData() + " - verifyDataEntryRemoved");
                    break;
            }
        } catch (AssertionError e) {
            fail("Data Entry should be removed from " + dataEntry.getProfileData().getProfileData() + " - verifyDataEntryRemoved");
            throw e;
        }
    }

    /**
     * Use this function to Profile Data Full Name Form
     */
    public void verifyProfileDataEntryForm(ProfileData profileData) throws IllegalAccessException {

        try {
            switch (profileData) {
                case COMPANY_BACKGROUND: case COMPLIANCE_AND_ETHICS: case INDIVIDUAL_BACKGROUND:
                    Assert.assertTrue(isElementVisible(TXT_DETAILS));
                    break;
                case PART_OF_GROUP_OF_COMPANIES:
                    Assert.assertTrue(isElementVisible(RDO_PART_OF_GROUP_OF_COMPANIES_YES));
                    Assert.assertTrue(isElementVisible(RDO_PART_OF_GROUP_OF_COMPANIES_NO));
                    click(RDO_PART_OF_GROUP_OF_COMPANIES_NO);
                    Assert.assertTrue(isElementDisabled(TXT_NAME_OF_GROUP));
                    Assert.assertTrue(isElementVisible(TXT_PROFILE_DATA_COMMENT));
                    break;
                case WEBSITE:
                    Assert.assertTrue(isElementVisible(TXT_WEBSITE_DESCRIPTION));
                    Assert.assertTrue(isElementVisible(TXT_URL));
                    break;
                case EMAIL:
                    Assert.assertTrue(isElementVisible(TXT_EMAIL_ADDRESS));
                    break;
                case FULL_NAME: case REGISTERED_NAME: case SHORT_NAME: case NAME_IN_LOCAL_LANGUAGE: case TRADING_AS_NAME: case PREVIOUS_NAME:
                    Assert.assertTrue(isElementVisible(TXT_NAME));
                    Assert.assertTrue(isElementVisible(TXT_FROM_DAY));
                    Assert.assertTrue(isElementVisible(TXT_FROM_MONTH));
                    Assert.assertTrue(isElementVisible(TXT_FROM_YEAR));
                    Assert.assertTrue(isElementVisible(CHK_FROM_PRESENT));
                    Assert.assertTrue(isElementVisible(TXT_TO_YEAR));
                    Assert.assertTrue(isElementVisible(TXT_TO_MONTH));
                    Assert.assertTrue(isElementVisible(TXT_TO_YEAR));
                    Assert.assertTrue(isElementVisible(CHK_TO_PRESENT));
                    break;
            }
            Assert.assertTrue(isElementVisible(createDynamicLocator(XPATH, TXT_SOURCE, 1)));
            Assert.assertTrue(isElementVisible(BTN_ADD_SOURCE));
            Assert.assertTrue(isElementVisible(BTN_SAVE));
            Assert.assertTrue(isElementVisible(BTN_CANCEL));
            pass("All elements for Profile Data " + profileData.getProfileData() + " form were verified. - verifyProfileDataEntryForm");
        } catch (AssertionError e) {
            fail("All elements for Profile Data " + profileData.getProfileData() + " form should be verified. - verifyProfileDataEntryForm");
            throw e;
        }
    }
}