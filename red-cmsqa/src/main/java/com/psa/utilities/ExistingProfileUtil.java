package com.psa.utilities;

import com.psa.data.CaseDetails;
import com.psa.data.ProfileDetails;
import com.psa.data.ProfileType;
import com.psa.data.Scope;
import com.psa.data.UserAccount;
import com.psa.library.TestInitReference;
import com.psa.pages.ObjectReference;
import com.psa.data.Module;

import org.testng.Assert;
import java.util.regex.Pattern;

import static com.psa.data.GenericData.*;


public class ExistingProfileUtil extends TestInitReference {

    /**
     * Use this function to verify all elements in Create New Case Search Form
     */
    public void verifyCreateNewCaseSearchForm() {

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CREATE_NEW_CASE), Module.CREATE_NEW_CASE.getModule()));
            Assert.assertTrue(isElementEnabled(TXT_SUBJECT_PROFILE_NAME));
            Assert.assertTrue(isElementEnabled(BTN_CLEAR));
            Assert.assertTrue(isElementVisible(RDO_PROFILE_TYPE_COMPANY));
            Assert.assertTrue(isElementVisible(RDO_PROFILE_TYPE_INDIVIDUAL));
            Assert.assertTrue(isElementEnabled(BTN_CREATE_NEW_PROFILE));
            Assert.assertTrue(isElementDisabled(BTN_NEXT));
            pass("All elements were verified in the Create New Case Search Form. - verifyCreateNewCaseSearchForm");
        } catch (AssertionError e) {
            fail("All elements were verified in the Create New Case Search Form. - verifyCreateNewCaseSearchForm");
            throw e;
        }
    }

    /**
     * Use this function to verify published profile preview
     * @param profileDetails of existing profile including country and full name and keyword to search
     */
    public void verifyProfilePreview(ProfileDetails profileDetails) throws InterruptedException, IllegalAccessException {

        try {
            this.waitSearchToReturnResult();
            Assert.assertTrue(isElementNotVisible(LBL_WATERMARK));
            Assert.assertTrue(isElementVisible(SECTION_SUBJECT_PROFILE));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SUBJECT_PROFILE), profileDetails.getFullName()));
            for (int count = 0; count<=profileDetails.getCountry().length-1; count++) {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_SUBJECT_PROFILE_COUNTRY_VALUE, count+1)),profileDetails.getCountry()[count].getCountry().trim()));
            }
            Assert.assertTrue(isElementVisible(SECTION_RISKS));
            Assert.assertTrue(isElementVisible(SECTION_SCORING));
            Assert.assertTrue(isElementVisible(SECTION_SOURCES));
            Assert.assertTrue(isElementEnabled(BTN_NEXT));
            pass(profileDetails.getFullName() + " profile was previewed with correct details. - verifyProfilePreview");
        } catch (AssertionError e) {
            fail(profileDetails.getFullName() + " profile should be previewed with correct details. - verifyProfilePreview");
            throw e;
        }
    }

    /**
     * Use this function to verify not published profile preview
     * @param caseDetails case details of not published profile details
     */
    public void verifyNonPublishedProfilePreview(CaseDetails caseDetails) throws InterruptedException {

        try {
            this.waitSearchToReturnResult();
            Assert.assertTrue(Pattern.compile(PATTERN_PROFILE_FOUND).matcher(getText(LBL_PROFILE_FOUND_COUNT)).matches());
            Assert.assertTrue(isActualValueContainsValue(getText(LBL_WATERMARK), caseDetails.getCaseId()));
            Assert.assertTrue(isElementDisabled(BTN_NEXT));
            Assert.assertTrue(isElementEnabled(BTN_CREATE_NEW_PROFILE));
            pass(caseDetails.getCaseId() + " was not published. - verifyNonPublishedProfilePreview");
        } catch (AssertionError e) {
            fail(caseDetails.getCaseId() + " should not be published. - verifyNonPublishedProfilePreview");
            throw e;
        }
    }

    /**
     * Use this function to verify profile was found
     */
    public void verifyProfileFound() throws InterruptedException {

        try {
            this.waitSearchToReturnResult();
            Assert.assertTrue(Pattern.compile(PATTERN_PROFILE_FOUND).matcher(getText(LBL_PROFILE_FOUND_COUNT)).matches());
            String[] splitProfileFoundCountLabel = getText(LBL_PROFILE_FOUND_COUNT).split(" ");
            pass("Profile was found returning " + splitProfileFoundCountLabel[0] + " result. - verifyProfileFound");
        } catch (AssertionError e) {
            fail("Profile should be found. - verifyProfileFound");
            throw e;
        }
    }

    /**
     * Use this function to verify profile was not found
     */
    public void verifyProfileNotFound() throws InterruptedException {
        String msgProfileNotFound = "No results found";

        try {
            this.waitSearchToReturnResult();
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_FOUND_COUNT), msgProfileNotFound));
            pass("Profile was not found. - verifyProfileNotFound");
        } catch (AssertionError e) {
            fail("Profile should not be found. - verifyProfileNotFound");
            throw e;
        }
    }

    /**
     * Use this function to select specific profile
     * @param profileDetails of existing profile including country and full name and keyword to search
     */
    public void selectProfile(ProfileDetails profileDetails) throws IllegalAccessException {
        type(TXT_SUBJECT_PROFILE_NAME, profileDetails.getFullName());

        if (profileDetails.getProfileType() == ProfileType.COMPANY) {
            click(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_COMPANY_SUBJECT_PROFILE_RESULTS, profileDetails.getFullName()));
        } else {
            click(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_INDIVIDUAL_SUBJECT_PROFILE_RESULTS, profileDetails.getFullName()));
        }
    }

    /**
     * Use this function to wait search to return result
     */
    private void waitSearchToReturnResult() throws InterruptedException {

        sleepForSeconds(1);
        for (int count = 0; count<=10; count++) {
            if (!getText(LBL_PROFILE_FOUND_COUNT).isEmpty()) {
                info("Search done. - waitSearchToReturnResults");
                break;
            } else if (count==10){
                fail("Search should be done. - waitSearchToReturnResults");
                Assert.fail();
            } else {
                sleepForSeconds(2);
            }
        }
    }

    /**
     * Use this function to verify all elements in Create New Case Form for Existing Profile
     * @param userAccount account logged in with region and full name
     * @param profileDetails details of existing profile
     */
    public void verifyCreateNewCaseFormForExistingProfile(UserAccount userAccount, ProfileDetails profileDetails) throws IllegalAccessException {

        try {
            this.verifyExistingProfileDetailsInCreateNewCaseForm(profileDetails);
            if (profileDetails.getProfileType()==ProfileType.COMPANY) {
                click(DD_SCOPE);
                for (Scope scope : Scope.values()) {
                    Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, scope)));
                }
            } else  if (profileDetails.getProfileType()==ProfileType.INDIVIDUAL) {
                click(DD_SCOPE);
                Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, Scope.L4)));
                Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, Scope.L1)));
                Assert.assertTrue(isElementNotVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, Scope.L3)));
                Assert.assertTrue(isElementNotVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, Scope.L2 )));
            }
            Assert.assertTrue(isElementEnabled(TXT_ADDITIONAL_SCOPE_CONDITIONS));
            Assert.assertTrue(isElementEnabled(ICO_UPLOAD));
            Assert.assertTrue(isElementEnabled(TXT_CASE_ID));
            Assert.assertTrue(isElementEnabled(TXT_CLIENT_NAME));
            Assert.assertTrue(isElementEnabled(TXT_CLIENT_CODE));
            Assert.assertTrue(isElementVisible(LBL_REGION));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_REGION_VALUE), userAccount.getRegion().getRegion()));
            Assert.assertTrue(isElementVisible(LBL_CASE_OWNER));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CASE_OWNER_VALUE), userAccount.getFullName()));
            Assert.assertTrue(isElementEnabled(TXT_WRITER_NAME));
            Assert.assertTrue(isElementEnabled(TXT_REVIEWER_NAME));
            Assert.assertTrue(isElementEnabled(BTN_CREATE_CASE));
            pass("All elements were verified in the Create New Case Form. - verifyCreateNewCaseFormForExistingProfile");
        } catch (AssertionError e) {
            fail("All elements should be verified in the Create New Case Form. - verifyCreateNewCaseFormForExistingProfile");
            throw e;
        }
    }

    /**
     * Use this function to verify basic details of Existing Profile
     * @param profileDetails details of existing profile
     */
    public void verifyExistingProfileDetailsInCreateNewCaseForm(ProfileDetails profileDetails) {

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_FULL_NAME_VALUE), profileDetails.getFullName()));

            if (profileDetails.getProfileID() != null) {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_ID_VALUE), profileDetails.getProfileID()));
            }
            if (profileDetails.getProfileType()==ProfileType.COMPANY) {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_TYPE_VALUE), ProfileType.COMPANY.getProfileType()));
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_COUNTRY), countryOfRegistration));
            } else  if (profileDetails.getProfileType()==ProfileType.INDIVIDUAL) {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_TYPE_VALUE), ProfileType.INDIVIDUAL.getProfileType()));
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_COUNTRY), country));
            }
            for (int count = 0; count<=profileDetails.getCountry().length-1; count++) {
                Assert.assertTrue(isActualValueContainsValue(getText(LBL_COUNTRY_VALUE),profileDetails.getCountry()[count].getCountry().trim()));
            }
            pass(profileDetails.getFullName() + " profile was pulled together with correct details. - verifyExistingProfileDetailsInCreateNewCaseForm");
        } catch (AssertionError e) {
            fail(profileDetails.getFullName() + " profile should be pulled together with correct details - verifyExistingProfileDetailsInCreateNewCaseForm");
            throw e;
        }
    }

    /**
     * Use this function to populate required fields for Creating New Case for Existing Company Profile
     * @param scope any value from Scope data class
     * @param caseID any value
     * @param clientName any value
     * @param clientCode any value but should be unique
     */
    public void populateRequiredFieldsCreateNewCaseForExistingProfile(Scope scope, String caseID, String clientName, String clientCode) throws IllegalAccessException {
        if (scope != null) {
            click(DD_SCOPE);
            click(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, scope));
        }

        if (!clientName.isEmpty()) {
            type(TXT_CLIENT_NAME, clientName);
        }

        if (!caseID.isEmpty()) {
            type(TXT_CASE_ID, caseID);
        }

        if (!clientCode.isEmpty()) {
            type(TXT_CLIENT_CODE, clientCode);
        }
        click(BTN_CREATE_CASE);
    }

    /**
     * Use this function to verify create case button is disabled and error messages of required fields is displayed
     */
    public void verifyErrorRequiredFieldIsDisplayedCreateNewCaseFormForExistingProfile() {

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_SCOPE), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_CASE_ID), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_CLIENT_NAME), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_CLIENT_CODE), ERR_REQUIRED_FIELD));
            pass("Required field error message was displayed. - verifyErrorRequiredFieldIsDisplayedCreateNewCaseFormForExistingProfile");
        } catch (AssertionError e) {
            fail("Required field error message should be displayed. - verifyErrorRequiredFieldIsDisplayedCreateNewCaseFormForExistingProfile");
            throw e;
        }
    }

    /**
     * Use this function to create new case for new profile
     * @param profileDetails details of existing profile
     * @param caseDetails details of new case
     */
    public void createNewCaseExistingProfile(ProfileDetails profileDetails, CaseDetails caseDetails) throws IllegalAccessException, InterruptedException {
        CaseDetails.setCaseDetails(caseDetails);
        navigateUtil.navigateToApplicationModule(Module.CREATE_NEW_CASE);
        this.selectProfile(profileDetails);
        this.verifyProfilePreview(profileDetails);
        click(BTN_NEXT);
        this.verifyExistingProfileDetailsInCreateNewCaseForm(profileDetails);
        this.populateRequiredFieldsCreateNewCaseFormForExistingProfile(caseDetails);
        newCaseUtil.populateNonRequiredFieldsCreateNewCaseFormForExistingProfile(caseDetails);
        caseDetails.setCaseRegion(getText(LBL_REGION_VALUE));
        caseDetails.setCaseOwner(getText(LBL_CASE_OWNER_VALUE));
        waitElementToBeClickable(BTN_CREATE_CASE, 2);
        click(BTN_CREATE_CASE);
        commonUtil.verifyNewCaseCreated(profileDetails, caseDetails);
    }

    /**
     * Use this function to populate required fields for Creating New Case for New Profile
     * @param caseDetails details of new profile and new case
     */
    private void populateRequiredFieldsCreateNewCaseFormForExistingProfile(CaseDetails caseDetails) throws IllegalAccessException {
        if (caseDetails.getScope() != null) {
            click(DD_SCOPE);
            click(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, caseDetails.getScope()));
        }

        if (!caseDetails.getClientName().isEmpty()) {
            type(TXT_CLIENT_NAME, caseDetails.getClientName());
        }

        if (!caseDetails.getCaseId().isEmpty()) {
            type(TXT_CASE_ID, caseDetails.getCaseId());
        }

        if (!caseDetails.getClientCode().isEmpty()) {
            type(TXT_CLIENT_CODE, caseDetails.getClientCode());
        }
    }

    /**
     * Use this function to verify if basic details of existing profile was pulled
     * @param profileDetails details of profile to verify
     */
    public void verifyBasicDetailsOfExistingProfilePulled(ProfileDetails profileDetails) throws IllegalAccessException {

        try {
            Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_NAMES_NAME_VALUE, profileDetails.getFullName())));
            if (profileDetails.getProfileType() == ProfileType.COMPANY) {
                if (profileDetails.getShortName() != null) {
                    Assert.assertTrue(isActualValueEqualsValue(getText(LBL_SHORT_NAME_NAME_VALUE), profileDetails.getShortName()));
                }
            }
            for (int count = 0; count<=profileDetails.getCountry().length-1; count++) {
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_SUBJECT_PROFILE_COUNTRY_VALUE, count+1)),profileDetails.getCountry()[count].getCountry().trim()));
            }
            if (profileDetails.getKeyIndustry() != null) {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_INDUSTRY_VALUE), profileDetails.getKeyIndustry().getKeyIndustry()));
            }
            if (profileDetails.getOverallRiskRatingScore() > 0) {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_OVER_ALL_RISK_RATING_VALUE), Integer.toString(profileDetails.getOverallRiskRatingScore())));
            }
            pass("Basic Details of Profile: " + profileDetails.getFullName() + " was pulled - verifyBasicDetailsOfExistingProfilePulled");
        } catch (AssertionError e) {
            fail("Basic Details of Profile: " + profileDetails.getFullName() + " should be pulled - verifyBasicDetailsOfExistingProfilePulled");
            throw e;
        }
    }

    /**
     * Use this function to verify if basic details of existing profile was pulled
     * @param profileDetails details of profile to verify
     * @param caseDetails specific client name of case details
     */
    public void verifyExecutiveSummary(ProfileDetails profileDetails, CaseDetails caseDetails) {

        try {
            if (profileDetails.getPublicSummary() != null) {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PUBLIC_SUMMARY_VALUE), profileDetails.getPublicSummary()));
            }
            if (caseDetails.getSpecificClientName() != null) {
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CLIENT_SPECIFIC_SUMMARY_VALUE), profileDetails.getClientSummary()));
                pass("Executive Summary - Client Specific of Profile: " + profileDetails.getFullName() + " was pulled - verifyExecutiveSummary");
            } else {
                Assert.assertTrue(isElementNotVisible(LBL_CLIENT_SPECIFIC_SUMMARY_VALUE));
                pass("Executive Summary - Client Specific of Profile: " + profileDetails.getFullName() + " was not pulled - verifyExecutiveSummary");
            }

        } catch (AssertionError e) {
            fail("Executive Summary of Profile: " + profileDetails.getFullName() + " should be pulled - verifyExecutiveSummary");
            throw e;
        }
    }
}
