package com.psa.utilities;

import com.psa.data.CaseDetails;
import com.psa.data.Country;
import com.psa.data.ProfileDetails;
import com.psa.data.ProfileType;
import com.psa.data.Scope;
import com.psa.data.UserAccount;
import com.psa.library.TestInitReference;
import com.psa.pages.ObjectReference;
import com.psa.data.Module;

import org.testng.Assert;

import static com.psa.data.GenericData.*;


public class NewCaseUtil extends TestInitReference {

    /**
     * Use this function to verify all elements in Create New Case Form for New Company Profile
     * @param userAccount account logged in with region and full name
     */
    public void verifyCreateNewCaseFormForNewCompanyProfile(UserAccount userAccount) throws IllegalAccessException {

        try {
            Assert.assertTrue(isElementVisible(LBL_PROFILE_TYPE));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_TYPE_VALUE), ProfileType.COMPANY.getProfileType()));
            Assert.assertTrue(isElementEnabled(TXT_FULL_NAME));
            Assert.assertTrue(isElementEnabled(TXT_SHORT_NAME));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_COUNTRY), countryOfRegistration));
            click(TXT_COUNTRY);
            for (Country country : Country.values()) {
                Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_COUNTRY_VALUE, country.getCountry())));
            }
            click(LBL_CREATE_NEW_CASE);
            click(DD_SCOPE);
            for (Scope scope : Scope.values()) {
                Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, scope)));
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
            pass("All elements were verified in the Create New Case Form. - verifyCreateNewCaseFormForNewCompanyProfile");
        } catch (AssertionError e) {
            fail("All elements should be verified in the Create New Case Form. - verifyCreateNewCaseFormForNewCompanyProfile");
            throw e;
        }
    }

    /**
     * Use this function to verify all elements in Create New Case Form for New Individual Profile
     * @param userAccount account logged in with region and full name
     */
    public void verifyCreateNewCaseFormForNewIndividualProfile(UserAccount userAccount) throws IllegalAccessException {

        try {
            Assert.assertTrue(isElementVisible(LBL_PROFILE_TYPE));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_PROFILE_TYPE_VALUE), ProfileType.INDIVIDUAL.getProfileType()));
            Assert.assertTrue(isElementEnabled(TXT_FIRST_NAME));
            Assert.assertTrue(isElementEnabled(TXT_MIDDLE_NAME));
            Assert.assertTrue(isElementEnabled(TXT_LAST_NAME));
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_COUNTRY), country));
            click(TXT_COUNTRY);
            for (Country country : Country.values()) {
                Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_COUNTRY_VALUE, country.getCountry())));
            }
            click(LBL_CREATE_NEW_CASE);
            click(DD_SCOPE);
            Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, Scope.L4)));
            Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, Scope.L1)));
            Assert.assertTrue(isElementNotVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, Scope.L3)));
            Assert.assertTrue(isElementNotVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LST_SCOPE_VALUE, Scope.L2)));
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
            pass("All elements were verified in the Create New Case Form. - verifyCreateNewCaseFormForNewIndividualProfile");
        } catch (AssertionError e) {
            fail("All elements should be verified in the Create New Case Form. - verifyCreateNewCaseFormForNewIndividualProfile");
            throw e;
        }
    }

    /**
     * Use this function to select existing client name
     * @param clientName existing value
     */
    public void selectClientName(String clientName) throws IllegalAccessException {
        if (!clientName.isEmpty()) {
            click(TXT_CLIENT_NAME);
            click(createDynamicLocator(ObjectReference.Bys.XPATH, LST_CLIENT_NAME_VALUE, clientName));
        }
    }

    /**
     * Use this function to populate required fields for Creating New Case for New Individual Profile
     * @param profileDetails details of new profile
     * @param country object collection for country of the profile
     * @param caseID any value
     * @param clientName any value
     * @param clientCode any value but should be unique
     */
    public void populateRequiredFieldsCreateNewCaseFormForNewProfile(ProfileDetails profileDetails, Country[] country, Scope scope, String caseID, String clientName, String clientCode) throws IllegalAccessException {
        if (profileDetails != null) {
            if (profileDetails.getProfileType() == ProfileType.COMPANY) {
                if (!profileDetails.getFullName().isEmpty()) {
                    type(TXT_FULL_NAME, profileDetails.getFullName());
                }
            } else if (profileDetails.getProfileType() == ProfileType.INDIVIDUAL) {
                if (!profileDetails.getFirstName().isEmpty()) {
                    type(TXT_FIRST_NAME, profileDetails.getFirstName());
                }

                if (!profileDetails.getMiddleName().isEmpty()) {
                    type(TXT_MIDDLE_NAME, profileDetails.getMiddleName());
                }

                if (!profileDetails.getLastName().isEmpty()) {
                    type(TXT_LAST_NAME, profileDetails.getLastName());
                }
            }
        }

        if (country != null) {
            click(TXT_COUNTRY);
            click(createDynamicLocator(ObjectReference.Bys.XPATH, LST_COUNTRY_VALUE, country[0].getCountry()));
        }

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
     * Use this function to verify error messages of required fields is displayed
     */
    public void verifyErrorRegardingRequiredFieldsIsDisplayed() {

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_FULL_NAME), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_COUNTRY), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_SCOPE), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_CASE_ID), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_CLIENT_NAME), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_CLIENT_CODE), ERR_REQUIRED_FIELD));
            pass("Error regarding required fields was displayed. - verifyErrorRegardingRequiredFieldsIsDisplayed");
        } catch (AssertionError e) {
            fail("Error regarding required fields should be displayed. - verifyErrorRegardingRequiredFieldsIsDisplayed");
            throw e;
        }
    }

    /**
     * Use this function to verify create case button is disabled and error requirements for specific fields is displayed
     */
    public void verifyErrorRequirementsIsDisplayed() {
        String errCaseIDalreadyExist = "Case ID already exists";
        String errClientCodeAlreadyExist = "Client code already exists";

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_FIRST_NAME), ERR_INDIVIDUAL_NAME));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_MIDDLE_NAME), ERR_INDIVIDUAL_NAME));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_LAST_NAME), ERR_INDIVIDUAL_NAME));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_COUNTRY), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_SCOPE), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_CASE_ID), errCaseIDalreadyExist));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_CLIENT_NAME), ERR_REQUIRED_FIELD));
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_CLIENT_CODE), errClientCodeAlreadyExist));
            pass("Error messages was displayed. - verifyErrorRequirementsIsDisplayed");
        } catch (AssertionError e) {
            fail("Error messages should be displayed. - verifyErrorRequirementsIsDisplayed");
            throw e;
        }
    }

    /**
     * Use this function to assign writer name (can be multiple)
     * @param userAccount writer accounts with full name and region
     */
    public void assignWriterName(UserAccount[] userAccount) throws IllegalAccessException {
        if (userAccount != null) {
            for (int userAccountCount = 0; userAccountCount<=userAccount.length-1; userAccountCount++) {
                type(TXT_WRITER_NAME, userAccount[userAccountCount].getFullName());
                click(createDynamicLocator(ObjectReference.Bys.XPATH, LST_WRITER_NAME_VALUE, userAccount[userAccountCount].getFullName()));
            }
        }
    }

    /**
     * Use this function to assign reviewer name (can be multiple)
     * @param userAccount reviewer accounts with full name and region
     */
    public void assignReviewerName(UserAccount[] userAccount) throws IllegalAccessException {
        if (userAccount != null) {
            for (int userAccountCount = 0; userAccountCount<=userAccount.length-1; userAccountCount++) {
                type(TXT_REVIEWER_NAME, userAccount[userAccountCount].getFullName());
                click(createDynamicLocator(ObjectReference.Bys.XPATH, LST_REVIEWER_NAME_VALUE, userAccount[userAccountCount].getFullName()));
            }
        }
    }

    /**
     * Use this function to verify region of account added to region values
     * @param userAccount accounts with full name and region
     */
    public void verifyRegionOfAccountAddedToRegionValues(UserAccount[] userAccount) {
        for (int userAccountCount = 0; userAccountCount<=userAccount.length-1; userAccountCount++) {
            try {
                Assert.assertTrue(isActualValueContainsValue(getText(LBL_REGION_VALUE), userAccount[userAccountCount].getRegion().getRegion()));
                pass(userAccount[userAccountCount].getFullName() + "'s Region: " + userAccount[userAccountCount].getRegion() + " was added to the Region values. - verifyRegionOfAccountAddedToRegionValues");
            } catch (AssertionError e) {
                fail(userAccount[userAccountCount].getFullName() + "'s Region: " + userAccount[userAccountCount].getRegion() + " should be added to the Region values. - verifyRegionOfAccountAddedToRegionValues");
                throw e;
            }
        }
    }

    /**
     * Use this function to create new case for new profile
     * @param profileDetails details of new profile
     * @param caseDetails details of new case
     */
    public void createNewCaseNewProfile(ProfileDetails profileDetails, CaseDetails caseDetails) throws IllegalAccessException {
        CaseDetails.setCaseDetails(caseDetails);
        navigateUtil.navigateToApplicationModule(Module.CREATE_NEW_CASE);
        if (profileDetails.getProfileType()==ProfileType.COMPANY) {
            navigateUtil.navigateToApplicationModule(Module.CREATE_NEW_PROFILE_COMPANY);
        } else  if (profileDetails.getProfileType()==ProfileType.INDIVIDUAL) {
            navigateUtil.navigateToApplicationModule(Module.CREATE_NEW_PROFILE_INDIVIDUAL);
        }
        this.populateRequiredFieldsCreateNewCaseFormForNewProfile(profileDetails, caseDetails);
        this.populateNonRequiredFieldsCreateNewCaseFormForNewProfile(profileDetails, caseDetails);
        caseDetails.setCaseRegion(getText(LBL_REGION_VALUE));
        caseDetails.setCaseOwner(getText(LBL_CASE_OWNER_VALUE));
        waitElementToBeClickable(BTN_CREATE_CASE);
        click(BTN_CREATE_CASE);
        navigateUtil.navigateToApplicationModule(Module.CASE_SETTINGS);
        profileDetails.setProfileID(getText(LBL_PROFILE_ID_VALUE));
        click(BTN_CLOSE);
        commonUtil.verifyNewCaseCreated(profileDetails, caseDetails);
    }

    /**
     * Use this function to populate required fields for Creating New Case for New Profile
     * @param profileDetails details of new profile
     * @param caseDetails details of new case
     */
    public void populateRequiredFieldsCreateNewCaseFormForNewProfile(ProfileDetails profileDetails, CaseDetails caseDetails) throws IllegalAccessException {
        if (profileDetails.getProfileType() == ProfileType.COMPANY) {
            if (!profileDetails.getFullName().isEmpty()) {
                type(TXT_FULL_NAME, profileDetails.getFullName());
            }
        } else if (profileDetails.getProfileType() == ProfileType.INDIVIDUAL) {
            if (!profileDetails.getFirstName().isEmpty()) {
                type(TXT_FIRST_NAME, profileDetails.getFirstName());
            }

            if (!profileDetails.getMiddleName().isEmpty()) {
                type(TXT_MIDDLE_NAME, profileDetails.getMiddleName());
            }

            if (!profileDetails.getLastName().isEmpty()) {
                type(TXT_LAST_NAME, profileDetails.getLastName());
            }
        }

        if (profileDetails.getCountry() != null) {
            click(TXT_COUNTRY);
            click(createDynamicLocator(ObjectReference.Bys.XPATH, LST_COUNTRY_VALUE, profileDetails.getCountry()[0].getCountry()));
        }

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
     * Use this function to populate non required fields for Creating New Case
     * @param profileDetails details of new profile
     * @param caseDetails details of new case
     */
    public void populateNonRequiredFieldsCreateNewCaseFormForNewProfile(ProfileDetails profileDetails, CaseDetails caseDetails) throws IllegalAccessException {
        if (profileDetails.getProfileType() == ProfileType.COMPANY) {
            if (profileDetails.getShortName() != null) {
                type(TXT_SHORT_NAME, profileDetails.getShortName());
            }
        }

        if (caseDetails.getAdditionalScope() != null) {
            type(TXT_ADDITIONAL_SCOPE_CONDITIONS, caseDetails.getAdditionalScope());
        }

        if (caseDetails.getSpecificClientName() != null) {
            this.selectClientName(caseDetails.getSpecificClientName());
            String[] splitSpecificClientName = caseDetails.getSpecificClientName().split(" {2}- {2}");
            caseDetails.setClientCode(splitSpecificClientName[0]);
        }

        if (caseDetails.getWriter() != null) {
            this.assignWriterName(caseDetails.getWriter());
            this.verifyRegionOfAccountAddedToRegionValues(caseDetails.getWriter());
        }

        if (caseDetails.getReviewer() != null) {
            this.assignReviewerName(caseDetails.getReviewer());
            this.verifyRegionOfAccountAddedToRegionValues(caseDetails.getReviewer());
        }
    }

    /**
     * Use this function to populate non required fields for Creating New Case
     * @param caseDetails details of new case
     */
    public void populateNonRequiredFieldsCreateNewCaseFormForExistingProfile(CaseDetails caseDetails) throws IllegalAccessException {
        if (caseDetails.getAdditionalScope() != null) {
            type(TXT_ADDITIONAL_SCOPE_CONDITIONS, caseDetails.getAdditionalScope());
        }

        if (caseDetails.getSpecificClientName() != null) {
            this.selectClientName(caseDetails.getSpecificClientName());
            String[] splitSpecificClientName = caseDetails.getSpecificClientName().split(" {2}- {2}");
            caseDetails.setClientCode(splitSpecificClientName[0]);
        }

        if (caseDetails.getWriter() != null) {
            this.assignWriterName(caseDetails.getWriter());
            this.verifyRegionOfAccountAddedToRegionValues(caseDetails.getWriter());
        }

        if (caseDetails.getReviewer() != null) {
            this.assignReviewerName(caseDetails.getReviewer());
            this.verifyRegionOfAccountAddedToRegionValues(caseDetails.getReviewer());
        }
    }
}
