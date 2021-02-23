package com.psa.utilities;

import com.psa.data.CaseDetails;
import com.psa.data.CaseStatus;
import com.psa.data.ProfileDetails;
import com.psa.data.ProfileType;
import com.psa.data.Scope;
import com.psa.data.SourceType;
import com.psa.library.TestInitReference;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static com.psa.data.GenericData.*;
import static com.psa.data.UserData.TEST_AUTOMATION_ACCOUNT;
import static com.psa.pages.HomePage.USER_INFO_DROPDOWN;
import static com.psa.pages.HomePage.USER_INFO_EMAIL_FIELD;


public class CommonUtil extends TestInitReference {

    /**
     * Use this function to verify total cases is displayed
     */
    public void verifyPaginationShowsTotalCases() {

        try {
            if (isElementNotVisible(LBL_NO_CASES_AVAILABLE)) {
                String[] splitPaginationLabel = getText(LBL_PAGINATION).split(" of ");
                String totalCaseCount = splitPaginationLabel[splitPaginationLabel.length-1];
                Assert.assertTrue(Pattern.compile(PATTERN_PAGINATION).matcher(getText(LBL_PAGINATION)).matches());
                pass("Total cases: " + totalCaseCount + " was displayed. - verifyPaginationShowsTotalCases");
            } else {
                pass("No cases available. - verifyPaginationShowsTotalCases");
            }
        } catch (AssertionError e) {
            fail("Total cases count should be displayed. - verifyPaginationShowsTotalCases");
            throw e;
        }
    }

    /**
     * Use this function to go to pagination first page
     */
    public void paginationGoToFirstPage() {
        String[] splitPaginationLabelFirst;

        if (isElementNotVisible(LBL_NO_CASES_AVAILABLE)) {
            splitPaginationLabelFirst = getText(LBL_PAGINATION).split("-");

            while (!splitPaginationLabelFirst[0].equals("1")) {
                click(BTN_PAGINATION_BACK);
                waitElementToBeInvisible(IMG_SKELETON_LOADER);
                splitPaginationLabelFirst = getText(LBL_PAGINATION).split("-");
            }
        }
    }

    /**
     * Use this function to verify Pagination back button disabled when on page 1
     */
    public void verifyPaginationBackButtonDisabledWhenOnFirstPage() {

        try {
            Assert.assertTrue(isActualValueEqualsValue(getAttributeValue(BTN_PAGINATION_BACK, "aria-disabled"), "true"));
            pass("Pagination back button was disabled in page 1 - verifyPaginationBackButtonDisabledWhenOnFirstPage");
        } catch (AssertionError e) {
            fail("Pagination back button should be disabled in page 1 - verifyPaginationBackButtonDisabledWhenOnFirstPage");
            throw e;
        }
    }

    /**
     * Use this function to go to pagination last page
     */
    public void paginationGoToLastPage() {
        String[] splitPaginationLabelLast;
        String[] splitPaginationToLastPage;

        if (isElementNotVisible(LBL_NO_CASES_AVAILABLE)) {
            splitPaginationLabelLast = getText(LBL_PAGINATION).split("-");
            splitPaginationToLastPage = splitPaginationLabelLast[1].split(" ");

            while (!splitPaginationToLastPage[0].equals(splitPaginationToLastPage[2])) {
                click(BTN_PAGINATION_NEXT);
                waitElementToBeInvisible(IMG_SKELETON_LOADER);
                splitPaginationLabelLast = getText(LBL_PAGINATION).split("-");
                splitPaginationToLastPage = splitPaginationLabelLast[1].split(" ");
            }
        }
    }

    /**
     * Use this function to verify Pagination next button disabled when on last page
     */
    public void verifyPaginationNextButtonDisabledWhenOnLastPage() {

        try {
            Assert.assertTrue(isActualValueEqualsValue(getAttributeValue(BTN_PAGINATION_NEXT, "aria-disabled"), "true"));
            pass("Pagination back button was disabled on last page - verifyPaginationBackButtonDisabled");
        } catch (AssertionError e) {
            fail("Pagination back button should be disabled on last page - verifyPaginationBackButtonDisabled");
            throw e;
        }
    }

    /**
     * Use this function to verify if new case was created
     * @param profileDetails details of profile
     * @param caseDetails case details of new case
     * coverage added RFG-45, 875, 876
     */
    public void verifyNewCaseCreated(ProfileDetails profileDetails, CaseDetails caseDetails) throws IllegalAccessException {
        String titleSubjectProfile = "Subject Profile: " + profileDetails.getFullName();

        try {
            caseDetails.setCreated(commonUtil.getDateCreated());
            caseDetails.setLastUpdated(commonUtil.getDateCreated());
            caseDetails.setLastUpdatedBy(caseDetails.getCaseOwner());
            caseDetails.setCaseStatus(CaseStatus.DRAFT);
            caseDetails.setAssignedTo(caseDetails.getCaseOwner());
            waitElementToBeInvisible(BTN_CREATE_CASE);
            Assert.assertTrue(isElementVisible(IMG_AVATAR));
            Assert.assertTrue(isElementVisible(BTN_ASSIGN_FOR_REVISION));
            Assert.assertTrue(isElementVisible(BTN_ASSIGN_FOR_REVIEW));
            Assert.assertTrue(isElementVisible(BTN_PUBLISH));
            Assert.assertTrue(isElementVisible(SECTION_SUBJECT_PROFILE));
            Assert.assertTrue(isElementVisible(SECTION_RISKS));
            Assert.assertTrue(isElementVisible(SECTION_SCORING));
            Assert.assertTrue(isElementVisible(SECTION_SOURCES));
            Assert.assertTrue(isElementVisible(OUTLINER_SUBJECT_PROFILE));
            Assert.assertTrue(isElementVisible(OUTLINER_RISKS));
            Assert.assertTrue(isElementVisible(OUTLINER_SCORING));
            Assert.assertTrue(isElementVisible(OUTLINER_SOURCES));
            Assert.assertTrue(isActualValueEqualsValue(getText(OH_SUBJECT_PROFILE), titleSubjectProfile));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SUBJECT_PROFILE), titleSubjectProfile));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CASE_ID_VALUE), caseDetails.getCaseId()));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SCOPE_VALUE), caseDetails.getScope().getScope()));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_ASSIGNED_TO_VALUE), caseDetails.getCaseOwner()));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_STATUS_VALUE).toLowerCase(),caseDetails.getCaseStatus().getCaseStatus().toLowerCase()));
            Assert.assertTrue(isElementEnabled(BTN_CASE_SETTINGS));
            Assert.assertTrue(isElementVisible(LNK_SOURCES_GLOBAL_COMPLIANCE_CHECKS));
            Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.GLOBAL_COMPLIANCE_CHECKS.getSourceType())));
            Assert.assertTrue(isElementVisible(LNK_SOURCES_MEDIA));
            Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.MEDIA.getSourceType())));
            Assert.assertTrue(isElementVisible(LNK_SOURCES_CLIENT_DOCUMENTATION));
            Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.CLIENT_DOCUMENTATION.getSourceType())));
            this.verifySourceTypeSelected(new SourceType[] {SourceType.GLOBAL_COMPLIANCE_CHECKS, SourceType.MEDIA, SourceType.CLIENT_DOCUMENTATION});
            if (profileDetails.getProfileType() == ProfileType.COMPANY) {
                Assert.assertTrue(isElementVisible(LNK_SOURCES_WEBSITE));
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.WEBSITE.getSourceType())));
                this.verifySourceTypeSelected(new SourceType[] {SourceType.WEBSITE});
            }
            if (caseDetails.getScope() == Scope.L4) {
                Assert.assertTrue(isElementVisible(LNK_SOURCES_OFFICIAL_DOCUMENTATION));
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.OFFICIAL_DOCUMENTATION.getSourceType())));
                Assert.assertTrue(isElementVisible(LNK_SOURCES_LITIGATION));
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.LITIGATION.getSourceType())));
                Assert.assertTrue(isElementVisible(LNK_SOURCES_REPUTATION));
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.REPUTATION.getSourceType())));
                this.verifySourceTypeSelected(new SourceType[] {SourceType.OFFICIAL_DOCUMENTATION, SourceType.LITIGATION, SourceType.REPUTATION});
                if (profileDetails.getProfileType() == ProfileType.INDIVIDUAL) {
                    Assert.assertTrue(isElementVisible(LNK_SOURCES_INTERVIEWS));
                    Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.INTERVIEWS.getSourceType())));
                    this.verifySourceTypeSelected(new SourceType[] {SourceType.INTERVIEWS});
                }
            } else if (caseDetails.getScope() == Scope.L3) {
                Assert.assertTrue(isElementVisible(LNK_SOURCES_OFFICIAL_DOCUMENTATION));
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.OFFICIAL_DOCUMENTATION.getSourceType())));
                Assert.assertTrue(isElementVisible(LNK_SOURCES_LITIGATION));
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.LITIGATION.getSourceType())));
                this.verifySourceTypeSelected(new SourceType[] {SourceType.OFFICIAL_DOCUMENTATION, SourceType.LITIGATION});
            } else if (caseDetails.getScope() == Scope.L2) {
                Assert.assertTrue(isElementVisible(LNK_SOURCES_OFFICIAL_DOCUMENTATION));
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LBL_CASE_EDITOR_HEADER, SourceType.OFFICIAL_DOCUMENTATION.getSourceType())));
                this.verifySourceTypeSelected(new SourceType[] {SourceType.OFFICIAL_DOCUMENTATION});
            }
            Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LNK_SUBJECT_PROFILE_VALUE, "Location")));
            if (profileDetails.getProfileType() == ProfileType.COMPANY) {
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LNK_SUBJECT_PROFILE_VALUE, "Names")));
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SUBJECT_PROFILE_PROFILE_TYPE_VALUE), ProfileType.COMPANY.getProfileType()));
            } else {
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, LNK_SUBJECT_PROFILE_VALUE, "Personal Details")));
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SUBJECT_PROFILE_PROFILE_TYPE_VALUE), ProfileType.INDIVIDUAL.getProfileType()));
            }
            Assert.assertTrue(isElementVisible(LBL_OVER_ALL_RISK_RATING));
            pass("New Case for Profile: " + profileDetails.getFullName() + " was created with correct entries. - verifyNewCaseCreated");
        } catch (AssertionError e) {
            fail("New Case for Profile: " + profileDetails.getFullName() + " should be created and should have correct entries. - verifyNewCaseCreated");
            throw e;
        }
    }

    /**
     * Use this function to verify if source type is checked
     * @param sourceType sourceType/s to verify
     */
    private void verifySourceTypeSelected(SourceType[] sourceType) throws IllegalAccessException {
        int sourceTypeCount = 0;
        try {
            click(BTN_SOURCE_TYPES);
            waitElementToBeVisible(BTN_SAVE);
            for (sourceTypeCount = 0; sourceTypeCount<=sourceType.length-1; sourceTypeCount++) {
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, CHK_SOURCE_CHECKED, sourceType[sourceTypeCount].getSourceType())));
                pass("Source Type: " + sourceType[sourceTypeCount].getSourceType() + " was selected. - verifySourceTypeSelected");
            }
            click(BTN_CLOSE);
            waitElementToBeInvisible(BTN_SAVE);
        } catch (AssertionError e) {
            fail("Source Type: " + sourceType[sourceTypeCount].getSourceType() + " should be selected. - verifySourceTypeSelected");
            throw e;
        }
    }

    /**
     * Use this function to verify correct case id was selected
     * @param caseID selected
     */
    private void verifyCaseIDSelected(String caseID) {

        try {
            waitElementToBeInvisible(BTN_CREATE_CASE);
            waitElementToBeInvisible(IMG_SKELETON_LOADER);
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CASE_ID_VALUE), caseID));
            pass("Case: " + caseID + " was selected. - verifyCaseIDSelected");
        } catch (AssertionError e) {
            fail("Case: " + caseID + " should be selected. - verifyCaseIDSelected");
            throw e;
        }
    }

    /**
     * Use this function to verify if new case was created
     * @return date created
     */
    private String getDateCreated() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy");
        return formatter.format(date);
    }

    /**
     * Use this function show preview of case details of specific case id
     * @param caseID case details to preview
     */
    public void showCasePreviewOfSpecificCaseID(String caseID) throws IllegalAccessException {
        waitElementToBeInvisible(IMG_SKELETON_LOADER);
        int index = 1;
        for (WebElement element : findElements(TBL_CASE_ID_COLUMN)) {
            if (element.getText().equals(caseID)) {
                hover(createDynamicLocator(Bys.XPATH, TBL_ICO_CASE_DETAILS_VIEW, index));
                click(createDynamicLocator(Bys.XPATH, TBL_ICO_CASE_DETAILS_VIEW, index));
                break;
            } else if (index == findElements(TBL_CASE_ID_COLUMN).size()) {
                info("Case: " + caseID + " is not available to preview. - showCasePreviewOfSpecificCaseID");
            }
            index ++;
        }
    }

    /**
     * Use this function show preview of case details of specific case id
     * @param index row of case id from 1-20
     */
    private void showCasePreviewOfSpecificCaseID(int index) throws IllegalAccessException {
        waitElementToBeInvisible(IMG_SKELETON_LOADER);
        hover(createDynamicLocator(Bys.XPATH, TBL_ICO_CASE_DETAILS_VIEW, index));
        click(createDynamicLocator(Bys.XPATH, TBL_ICO_CASE_DETAILS_VIEW, index));
    }

    /**
     * Use this function to open case
     * @param caseID case details to open
     */
    public void openCase(String caseID) throws IllegalAccessException {
        waitElementToBeInvisible(IMG_SKELETON_LOADER);
        int index = 1;
        for (WebElement element : findElements(TBL_CASE_ID_COLUMN)) {
            if (element.getText().equals(caseID)) {
                click(createDynamicLocator(Bys.XPATH, TBL_CASE_ID_SPECIFIC_CELL, index));
                break;
            } else if (index == findElements(TBL_CASE_ID_COLUMN).size()) {
                Assert.fail(caseID + " should be available in case list. - openCase");
            }
            index ++;
        }
        this.verifyCaseIDSelected(caseID);
    }

    /**
     * Use this function to open any case
     * @param index any case 1-20
     */
    public void openCase(int index) throws IllegalAccessException {
        info("User about to click Case ID: " + getText(createDynamicLocator(Bys.XPATH, TBL_CASE_ID_SPECIFIC_CELL, index)));
        click(createDynamicLocator(Bys.XPATH, TBL_CASE_ID_SPECIFIC_CELL, index));
        waitElementToBeVisible(LBL_SUBJECT_PROFILE);
    }


    /**
     * Use this function to verify details in case preview
     * @param profileDetails details used to create case
     * @param caseDetails case details to verify
     */
    public void verifyCasePreviewDetails(ProfileDetails profileDetails, CaseDetails caseDetails) throws IllegalAccessException {

        try {
            Assert.assertTrue(isElementVisible(LBL_CASE_ID));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CASE_ID_VALUE), caseDetails.getCaseId()));
            Assert.assertTrue(isElementVisible(LBL_CREATED));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CREATED_VALUE), caseDetails.getCreated()));
            Assert.assertTrue(isElementVisible(LBL_LAST_UPDATED));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_LAST_UPDATED_VALUE), caseDetails.getLastUpdated()));
            Assert.assertTrue(isElementVisible(LBL_DAYS_IN_PROGRESS));
            Assert.assertNotNull(getText(LBL_DAYS_IN_PROGRESS_VALUE));
            Assert.assertTrue(isElementVisible(LBL_SUBJECT));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SUBJECT_VALUE), profileDetails.getFullName()));
            Assert.assertTrue(isElementVisible(LBL_PROFILE_ID));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_ID_VALUE), profileDetails.getProfileID()));
            Assert.assertTrue(isElementVisible(LBL_STATUS));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_STATUS_VALUE).toLowerCase(), caseDetails.getCaseStatus().getCaseStatus().toLowerCase()));
            Assert.assertTrue(isElementVisible(LBL_CLIENT_NAME));
            if (caseDetails.getSpecificClientName() != null) {
                String[] splitClientName = caseDetails.getSpecificClientName().split( (" {2}- {2}"));
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CLIENT_NAME_VALUE), splitClientName[1]));
            } else {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CLIENT_NAME_VALUE), caseDetails.getClientName()));
            }
            Assert.assertTrue(isElementVisible(LBL_SCOPE));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SCOPE_VALUE), caseDetails.getScope().getScope()));
            Assert.assertTrue(isElementVisible(LBL_ADDITIONAL_SCOPE));
            if (caseDetails.getAdditionalScope() != null) {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_ADDITIONAL_SCOPE_VALUE), caseDetails.getAdditionalScope()));
            }
            Assert.assertTrue(isElementVisible(LBL_REGION));
            if (caseDetails.getCaseRegion().contains(", ")) {
                String[] splitRegion = caseDetails.getCaseRegion().split( (", "));
                for (int regionCount = 0; regionCount<=splitRegion.length-1; regionCount++) {
                    Assert.assertTrue(isActualValueContainsValue(getText(LBL_REGION_VALUE), splitRegion[regionCount]));
                }
            }
            Assert.assertTrue(isElementVisible(LBL_CASE_OWNER));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CASE_OWNER_VALUE), caseDetails.getCaseOwner()));
            Assert.assertTrue(isElementVisible(LBL_WRITER));
            if (caseDetails.getWriter() != null) {
                for (int writerCount = 0; writerCount<=caseDetails.getWriter().length-1; writerCount++) {
                    Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(Bys.XPATH, LBL_WRITER_VALUE, writerCount+1)), caseDetails.getWriter()[writerCount].getFullName()));
                }
            }
            Assert.assertTrue(isElementVisible(LBL_REVIEWER));
            if (caseDetails.getWriter() != null) {
                for (int reviewerCount = 0; reviewerCount<=caseDetails.getReviewer().length-1; reviewerCount++) {
                    Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(Bys.XPATH, LBL_REVIEWER_VALUE, reviewerCount+1)), caseDetails.getReviewer()[reviewerCount].getFullName()));
                }
            }
            Assert.assertTrue(isElementVisible(LBL_ATTACHMENTS));
            Assert.assertTrue(isElementVisible(LBL_LAST_ASSIGNED_BY));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_LAST_ASSIGNED_BY_VALUE), caseDetails.getLastUpdatedBy()));
            //to check assigned to
            if (caseDetails.getCaseStatus() != CaseStatus.PUBLISHED) {
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, ICO_EDIT, caseDetails.getAssignedTo())));
            }
            pass("All details in Case Preview was correct. - verifyCasePreviewDetails");
        } catch (AssertionError e) {
            fail("All details in Case Preview should be correct. - verifyCasePreviewDetails");
            throw e;
        }
    }

    /**
     * Use this function to verify all elements in Login Page
     * @param firstName logged in user's first name
     */
    public void verifyProfileFirstNameIsDisplayed(String firstName) {

        try {
            if (firstName.length()>9) {
                firstName = firstName.substring(0, 9) + "...";
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_FIRST_NAME), firstName));
                pass("Truncated Profile First Name: " + firstName + " was displayed. - verifyProfileFirstNameIsDisplayed");
            } else {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_FIRST_NAME), firstName));
                pass("Profile First Name: " + firstName + " was displayed. - verifyProfileFirstNameIsDisplayed");
            }
        } catch (AssertionError e) {
            fail("Profile First Name: " + firstName + " should be displayed. - verifyProfileFirstNameIsDisplayed");
            throw e;
        }
    }

    /**
     * Use this function to verify if Create New Case Button is enabled
     */
    public void verifyUserAllowedToCreateNewCase() {

        try {
            waitElementToBeVisible(BTN_CREATE_NEW_CASE);
            waitElementToBeInvisible(IMG_SKELETON_LOADER);
            Assert.assertTrue(isElementEnabled(BTN_CREATE_NEW_CASE));
            Assert.assertTrue(isActualValueEqualsValue(getBackgroundColor(BTN_CREATE_NEW_CASE), STRONG_BLUE));
            pass("Create New Case button was enabled. - verifyUserAllowedToCreateNewCase");
        } catch (AssertionError e) {
            fail("Create New Case button should be enabled. - verifyUserAllowedToCreateNewCase");
            throw e;
        }
    }

    /**
     * Use this function to verify if Create New Case Button is not visible
     */
    public void verifyUserNotAllowedToCreateNewCase() {

        try {
            Assert.assertTrue(isElementDisabled(BTN_CREATE_NEW_CASE));
            pass("Create New Case button was not visible. - verifyUserNotAllowedToCreateNewCase");
        } catch (AssertionError e) {
            fail("Create New Case button should not be visible. - verifyUserNotAllowedToCreateNewCase");
            throw e;
        }
    }

    /**
     * Use this function to verify All elements in All Cases Page
     */
    public void verifyAllCasesPage() throws IllegalAccessException, ParseException {

        try {
            Assert.assertTrue(isElementVisible(LBL_ALL_CASES));
            Assert.assertTrue(isElementVisible(ICO_MY_CASES));
            Assert.assertTrue(isElementVisible(ICO_ALL_CASES));
            Assert.assertTrue(isActualValueEqualsValue(getText(CH_CASE_ID), "Case ID"));
            Assert.assertTrue(isActualValueEqualsValue(getText(CH_SUBJECT), "Subject"));
            Assert.assertTrue(isActualValueEqualsValue(getText(CH_SCOPE), "Scope"));
            Assert.assertTrue(isActualValueEqualsValue(getText(CH_DIP), "DIP"));
            Assert.assertTrue(isActualValueEqualsValue(getText(CH_LAST_UPDATED), "Last Updated"));
            Assert.assertTrue(isActualValueEqualsValue(getText(CH_CLIENT), "Client"));
            Assert.assertTrue(isActualValueEqualsValue(getText(CH_CASE_OWNER), "Case Owner"));
            Assert.assertTrue(isActualValueEqualsValue(getText(CH_ASSIGNED_TO), "Assigned To"));
            Assert.assertTrue(isActualValueEqualsValue(getText(CH_REGION), "Region"));
            Assert.assertTrue(isActualValueEqualsValue(getText(CH_STATUS), "Status"));
            String[] splitPaginationLabelCaseCount = getText(LBL_PAGINATION).split("-");
            String[] splitPaginationLabelCaseCountPerPage = splitPaginationLabelCaseCount[1].split(" ");
            if (Integer.parseInt(splitPaginationLabelCaseCountPerPage[0]) != 0) {
                showCasePreviewOfSpecificCaseID(1);
                Assert.assertTrue(isElementVisible(createDynamicLocator(Bys.XPATH, TBL_ICO_CASE_DETAILS_VIEW, 1)));
                Assert.assertTrue(isActualValueEqualsValue(Integer.toString(countSameElements(TBL_CASE_ID_COLUMN)), splitPaginationLabelCaseCountPerPage[0]));
                SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
                Date latestUpdatedDate = dateFormat.parse(getText(TBL_LAST_UPDATED_COLUMN));
                for (WebElement element : findElements(TBL_LAST_UPDATED_COLUMN)) {
                    Date caseIDLastUpdateDate = dateFormat.parse(element.getText());
                    Assert.assertFalse(isActualValueEqualsValue(caseIDLastUpdateDate.toString(), addDays(latestUpdatedDate ,1).toString()));
                }
            }
            pass("All elements were verified in the All Cases Page. - verifyAllCasesPage");
        } catch (AssertionError e) {
            fail("All elements should be verified in the All Cases Page. - verifyAllCasesPage");
            throw e;
        }
    }

    /**
     * Use this function to verify aesthetics of newly updated case
     * @param caseID case to verify
     */
    public void verifyAestheticsNewlyUpdatedCase(String caseID) {

        try {
            for (WebElement element : findElements(TBL_CASE_ID_COLUMN)) {
                if (element.getText().equals(caseID)) {
                    Assert.assertEquals(element.getCssValue("font-weight"), "700");
                    pass(caseID + " was in bold aesthetics. - verifyAestheticsNewlyUpdatedCase");
                    break;
                }
            }
        } catch (AssertionError e) {
            fail(caseID + " should be in bold aesthetics. - verifyAestheticsNewlyUpdatedCase");
            throw e;
        }
    }

    /**
     * Use this function to verify aesthetics of newly updated case
     * @param caseID case to verify
     */
    public void verifyAestheticsNotUpdatedCase(String caseID) {

        try {
            for (WebElement element : findElements(TBL_CASE_ID_COLUMN)) {
                if (element.getText().equals(caseID)) {
                    Assert.assertEquals(element.getCssValue("font-weight"), "400");
                    pass(caseID + " was not bold aesthetics. - verifyAestheticsNotUpdatedCase");
                    break;
                }
            }
        } catch (AssertionError e) {
            fail(caseID + " should not be in bold aesthetics. - verifyAestheticsNotUpdatedCase");
            throw e;
        }
    }

    /**
     * Use this function to verify no case preview was shown
     */
    public void verifyCasePreviewNotAvailable() {

        try {
            Assert.assertTrue(isElementNotVisible(MODAL_CASE_PREVIEW));
            pass("Case not available to preview. - verifyCasePreviewNotAvailable");
        } catch (AssertionError e) {
            fail("Case should be available to preview. - verifyCasePreviewNotAvailable");
            throw e;
        }
    }

    /**
     * Use this function to search and open any case
     * @param caseDetails details of case to open
     */
    public void searchAndOpenCase(String caseDetails) throws IllegalAccessException {
        waitElementToBeInvisible(IMG_SKELETON_LOADER);
        waitElementToBeVisible(TXT_SEARCH);
        type(TXT_SEARCH, caseDetails);
        if (isElementVisible(IMG_SKELETON_LOADER)) {
            waitElementToBeInvisible(IMG_SKELETON_LOADER);
        }
        this.openCase(caseDetails);
    }


    public void verifyUserIsLogin() {
        waitElementToBeClickable(USER_INFO_DROPDOWN, 25000);
        click(USER_INFO_DROPDOWN);
        isActualValueEqualsValue(getText(USER_INFO_EMAIL_FIELD), TEST_AUTOMATION_ACCOUNT.getEmail());
    }
}