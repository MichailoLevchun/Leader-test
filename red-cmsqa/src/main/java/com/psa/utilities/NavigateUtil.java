package com.psa.utilities;

import com.psa.library.TestInitReference;
import com.psa.data.Module;

import org.testng.Assert;

public class NavigateUtil extends TestInitReference {

    /**
     * Use this function to verify where user is navigated
     * @param module any module
     */
    public void verifyUserNavigatedToModule(Module module) {

        try {
            switch (module) {
                case LOGIN:
                    Assert.assertTrue(isElementVisible(BTN_LOGIN));
                    break;
                case MY_CASES:
                    Assert.assertTrue(isElementVisible(LBL_MY_CASES));
                    break;
                case CASE_EDITOR:
                    Assert.assertTrue(isElementVisible(LBL_SUBJECT_PROFILE));
                    break;
            }
            pass("User was navigated to " + module.getModule() + " Module. - verifyUserNavigatedToModule");
        } catch (AssertionError e) {
            fail("User should be navigated to " + module.getModule() + " Module. - verifyUserNavigatedToModule");
            throw e;
        }
    }

    /**
     * Use this function to logout user
     */
    public void logoutUser() {
        click(IMG_AVATAR);
        click(BTN_LOGOUT);
    }

    /**
     * Use this function to navigate to different modules of the Application
     * @param module main modules
     */
    public void navigateToApplicationModule(Module module) {

        try {
            switch (module) {
                case CREATE_NEW_CASE:
                    waitElementToBeVisible(BTN_CREATE_NEW_CASE);
                    waitElementToBeInvisible(IMG_SKELETON_LOADER);
                    click(BTN_CREATE_NEW_CASE);
                    waitElementToBeVisible(TXT_SUBJECT_PROFILE_NAME);
                    break;

                case CREATE_NEW_PROFILE_COMPANY:
                    click(RDO_PROFILE_TYPE_COMPANY);
                    click(BTN_CREATE_NEW_PROFILE);
                    waitElementToBeInvisible(BTN_CREATE_NEW_PROFILE);
                    break;

                case CREATE_NEW_PROFILE_INDIVIDUAL:
                    click(RDO_PROFILE_TYPE_INDIVIDUAL);
                    click(BTN_CREATE_NEW_PROFILE);
                    waitElementToBeInvisible(BTN_CREATE_NEW_PROFILE);
                    break;

                case ADD_NEW_ASSOCIATE_PROFILE_COMPANY:
                    click(BTN_ADD);
                    click(MENU_ASSOCIATE_PROFILE);
                    click(RDO_PROFILE_TYPE_COMPANY);
                    click(BTN_CREATE_NEW_PROFILE);
                    waitElementToBeInvisible(BTN_CREATE_NEW_PROFILE);
                    break;

                case ADD_NEW_ASSOCIATE_PROFILE_INDIVIDUAL:
                    click(BTN_ADD);
                    click(MENU_ASSOCIATE_PROFILE);
                    click(RDO_PROFILE_TYPE_INDIVIDUAL);
                    click(BTN_CREATE_NEW_PROFILE);
                    waitElementToBeInvisible(BTN_CREATE_NEW_PROFILE);
                    break;

                case MY_CASES:
                    waitElementToBeVisible(ICO_MY_CASES);
                    waitElementToBeInvisible(IMG_SKELETON_LOADER);
                    click(ICO_MY_CASES);
                    waitElementToBeVisible(LBL_MY_CASES);
                    waitElementToBeInvisible(IMG_SKELETON_LOADER);
                    break;

                case ALL_CASES:
                    waitElementToBeVisible(ICO_ALL_CASES);
                    waitElementToBeInvisible(IMG_SKELETON_LOADER);
                    click(ICO_ALL_CASES);
                    waitElementToBeVisible(LBL_ALL_CASES);
                    waitElementToBeInvisible(IMG_SKELETON_LOADER);
                    break;

                case CASE_SETTINGS:
                    click(BTN_CASE_SETTINGS);
                    waitElementToBeVisible(LBL_PROFILE_ID_VALUE);
                    break;
            }
            info("User navigated to " + module.getModule() + " Module. - navigateToApplicationModule");
        } catch (AssertionError e) {
            fail("User should be navigated to " + module.getModule() + " Module. - navigateToApplicationModule");
            throw e;
        }
    }
}
