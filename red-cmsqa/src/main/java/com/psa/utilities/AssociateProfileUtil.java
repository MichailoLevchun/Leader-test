package com.psa.utilities;

import com.google.inject.internal.util.$Nullable;
import com.psa.library.TestInitReference;
import com.psa.pages.ObjectReference;
import com.psa.data.Module;
import com.psa.data.ProfileDetails;
import com.psa.data.ProfileType;
import com.psa.data.SourceType;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.psa.data.GenericData.*;


public class AssociateProfileUtil extends TestInitReference {

    /**
     * Use this function to verify create new profile form
     */
    public void verifyCreateNewProfileForm() {
        String titleCreateNewProfile = "Create New Profile";

        try {
            Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CREATE_NEW_PROFILE), titleCreateNewProfile));
            if (getText(LBL_TYPE_VALUE).equals(ProfileType.COMPANY.getProfileType())) {
                Assert.assertTrue(isElementEnabled(TXT_FULL_NAME));
                Assert.assertTrue(isElementEnabled(TXT_SHORT_NAME));
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CREATE_NEW_PROFILE_COUNTRY), countryOfRegistration));
            } else if (getText(LBL_TYPE_VALUE).equals(ProfileType.INDIVIDUAL.getProfileType())) {
                Assert.assertTrue(isElementEnabled(TXT_FIRST_NAME));
                Assert.assertTrue(isElementEnabled(TXT_MIDDLE_NAME));
                Assert.assertTrue(isElementEnabled(TXT_LAST_NAME));
                Assert.assertTrue(isActualValueEqualsValue(getText(LBL_CREATE_NEW_PROFILE_COUNTRY), country));
            }
            Assert.assertTrue(isElementEnabled(TXT_COUNTRY));
            Assert.assertTrue(isElementEnabled(BTN_CLOSE));
            Assert.assertTrue(isElementEnabled(BTN_BACK));
            Assert.assertTrue(isElementEnabled(BTN_CREATE_PROFILE));
            pass("All elements were verified in Create New Profile Form. - verifyCreateNewProfileForm");
        } catch (AssertionError e) {
            fail("All elements should be verified in Create New Profile Form. - verifyCreateNewProfileForm");
            throw e;
        }
    }

    /**
     * Use this function to populate create new profile form
     * @param profileDetails details of associate profile
     */
    public void populateAndSubmitCreateNewProfileForm(@$Nullable ProfileDetails profileDetails) throws IllegalAccessException {
        if (profileDetails != null) {
            if (profileDetails.getProfileType() == ProfileType.COMPANY) {
                if (!profileDetails.getFullName().isEmpty()) {
                    type(TXT_FULL_NAME, profileDetails.getFullName());
                }
                if (profileDetails.getShortName() != null) {
                    type(TXT_SHORT_NAME, profileDetails.getShortName());
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
        }
        click(BTN_CREATE_PROFILE);
    }

    /**
     * Use this function to verify required fields in create new profile form
     */
    public void verifyErrorRequiredFieldsInCreateNewProfileForm() {

        try {
            if (getText(LBL_TYPE_VALUE).equals(ProfileType.COMPANY.getProfileType())) {
                Assert.assertTrue(isActualValueEqualsValue(getText(ERR_FULL_NAME), ERR_REQUIRED_FIELD));
            } else if (getText(LBL_TYPE_VALUE).equals(ProfileType.INDIVIDUAL.getProfileType())) {
                Assert.assertTrue(isActualValueEqualsValue(getText(ERR_FIRST_NAME), ERR_INDIVIDUAL_NAME));
                Assert.assertTrue(isActualValueEqualsValue(getText(ERR_MIDDLE_NAME), ERR_INDIVIDUAL_NAME));
                Assert.assertTrue(isActualValueEqualsValue(getText(ERR_LAST_NAME), ERR_INDIVIDUAL_NAME));
            }
            Assert.assertTrue(isActualValueEqualsValue(getText(ERR_COUNTRY), ERR_REQUIRED_FIELD));
            pass("Required fields errors are displayed. - verifyErrorRequiredFieldsInCreateNewProfileForm");
        } catch (AssertionError e) {
            fail("Required fields errors should be displayed. - verifyErrorRequiredFieldsInCreateNewProfileForm");
            throw e;
        }
    }

    /**
     * Use this function to verify new associate profile added
     * @param profileDetails details of associate profile
     */
    private void verifyNewAssociateProfileAdded(ProfileDetails profileDetails) throws IllegalAccessException {
        String titleAssociateProfile = "Associate Profile: " + profileDetails.getFullName();

        try {
            int index = 1;
            for (WebElement element : findElements(OH_ASSOCIATE_PROFILE)) {
                if (isActualValueEqualsValue(element.getText(), titleAssociateProfile)) {
                    break;
                }
                index ++;
            }
            Assert.assertTrue(isElementVisible(OH_ASSOCIATE_PROFILE), titleAssociateProfile);
            Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LNK_LOCATION, index)));
            Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_ASSOCIATE_PROFILE, titleAssociateProfile)));
            Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, BTN_ASSOCIATE_PROFILE_SOURCE_TYPES, titleAssociateProfile)));
            if (profileDetails.getProfileType() == ProfileType.COMPANY) {
                Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LNK_NAMES, index)));
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_ASSOCIATE_PROFILE_TYPE_VALUE, titleAssociateProfile)), ProfileType.COMPANY.getProfileType()));
                if (profileDetails.getShortName() != null && !profileDetails.getShortName().isEmpty()) {
                    Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_ASSOCIATE_PROFILE_SHORT_NAME_VALUE, titleAssociateProfile)), profileDetails.getShortName()));
                }
            } else if (profileDetails.getProfileType() == ProfileType.INDIVIDUAL) {
                Assert.assertTrue(isElementVisible(createDynamicLocator(ObjectReference.Bys.XPATH, LNK_PERSONAL_DETAILS, index)));
                Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_ASSOCIATE_PROFILE_TYPE_VALUE, titleAssociateProfile)), ProfileType.INDIVIDUAL.getProfileType()));
            }
            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_ASSOCIATE_PROFILE_FULL_NAME_VALUE, titleAssociateProfile)), profileDetails.getFullName()));
            Assert.assertTrue(isActualValueEqualsValue(getText(createDynamicLocator(ObjectReference.Bys.XPATH, LBL_ASSOCIATE_PROFILE_COUNTRY_VALUE, titleAssociateProfile)), profileDetails.getCountry()[0].getCountry()));
            pass("Associate Profile: " + profileDetails.getFullName() + " was added. - verifyNewAssociateProfileAdded");
        } catch (AssertionError e) {
            fail("Associate Profile: " + profileDetails.getFullName() + " should be added. - verifyNewAssociateProfileAdded");
            throw e;
        }
    }

    /**
     * Use this function to add new associate profile
     * @param profileDetails details of new profile to add
     */
    public void addNewAssociateProfile(ProfileDetails profileDetails) throws IllegalAccessException {
        if (profileDetails.getProfileType() == ProfileType.COMPANY) {
            navigateUtil.navigateToApplicationModule(Module.ADD_NEW_ASSOCIATE_PROFILE_COMPANY);
        } else if (profileDetails.getProfileType() == ProfileType.INDIVIDUAL) {
            navigateUtil.navigateToApplicationModule(Module.ADD_NEW_ASSOCIATE_PROFILE_INDIVIDUAL);
        }
        this.populateAndSubmitCreateNewProfileForm(profileDetails);
        waitElementToBeInvisible(BTN_CREATE_PROFILE);
        waitElementToBeInvisible(IMG_LOADER);
        this.verifyNewAssociateProfileAdded(profileDetails);
    }

    /**
     * Use this function to add source type in specific profile
     * @param profileDetails profile where to add sourceType
     * @param sourceType sourceType/s to add
     */
    public void addSourceType(ProfileDetails profileDetails, SourceType... sourceType) throws IllegalAccessException {
        click(createDynamicLocator(ObjectReference.Bys.XPATH, BTN_ASSOCIATE_PROFILE_SOURCE_TYPES, profileDetails.getFullName()));
        waitElementToBeVisible(BTN_SAVE);
        for (int sourceTypeCount = 0; sourceTypeCount<=sourceType.length-1; sourceTypeCount++) {
            toggleOn(createDynamicLocator(ObjectReference.Bys.XPATH, CHK_SOURCE_TYPE, sourceType[sourceTypeCount].getSourceType()));
        }
        click(BTN_SAVE);
        waitElementToBeInvisible(BTN_SAVE);
    }
}
