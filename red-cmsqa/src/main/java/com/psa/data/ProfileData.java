package com.psa.data;

public enum ProfileData {

    COMPANY_BACKGROUND ("Company Background"),
    COMPLIANCE_AND_ETHICS ("Compliance And Ethics"),
    PART_OF_GROUP_OF_COMPANIES ("Part of Group of Companies"),
    WEBSITE ("Website"),
    EMAIL ("Email"),
    LEGAL_FORM ("Legal Form"),
    OPERATIONAL_STATUS ("Operational Status"),
    PRIMARY_BUSINESS_ACTIVITY ("Primary Business Activity"),
    KEY_INDUSTRY ("Key Industry"),

    REGISTERED_NAME ("Registered Name"),
    FULL_NAME ("Full Name"),
    SHORT_NAME ("Short Name"),
    NAME_IN_LOCAL_LANGUAGE ("Name in Local Language"),
    TRADING_AS_NAME ("Trading as Name"),
    PREVIOUS_NAME ("Previous Name"),

    COUNTRY_OF_REGISTRATION ("Country of Registration"),
    REGISTERED_LEGAL_ADDRESS ("Registered Legal Address"),
    OPERATIONAL_ADDRESS ("Operational Address"),
    FORMER_ADDRESS ("Former Address"),
    OPERATIONAL_PRESENCE ("Operational Presence"),

    DATE_OF_ESTABLISHMENT ("Date of Establishment"),

    BUSINESS_LICENCE ("Business Licence"),
    TAX_LICENCE ("Tax Licence"),
    MEDICAL_LICENCE ("Medical Licence"),
    CERTIFICATION ("Certification"),
    INDUSTRY_AWARD ("Industry Award"),
    GOVERNMENT_AWARD ("Government Award"),
    OTHER_LICENCE ("Other Licence"),

    LICENCE_GENERAL_COMMENT ("General Comment"),

    OWNERSHIP ("Ownership"),
    OWNERSHIP_GENERAL_COMMENT ("General Comment"),

    EMPLOYEE_COUNT ("Employee Count"),
    SALESPERSON_COUNT ("Salesperson Count"),
    SUB_CONTRACTOR_COUNT ("Sub-contractor Count"),
    OPERATIONAL_DETAILS_GENERAL_COMMENT ("General Comment"),
    COMMERCIAL_DEALINGS ("Commercial Dealings"),
    FINANCIALS ("Financials"),
    COMMERCIAL_GENERAL_COMMENT ("General Comment"),

    MANAGEMENT_GENERAL_COMMENT ("General Comment"),

    COMPLIANCE_INTERVIEW ("Compliance Interview"),

    INDIVIDUAL_BACKGROUND ("Individual Background"),

    ALIAS ("Alias"),
    LOCAL_LANGUAGE_NAME ("Local Language Name"),
    MAIDEN_NAME ("Maiden Name"),
    NATIONALITY ("Nationality"),
    IDENTIFICATION ("Identification"),
    DATE_OF_BIRTH_DEATH ("Date of Birth and Death"),
    GENDER ("Gender"),
    PHOTOGRAPH ("Photograph"),

    CONTACT_NUMBER ("Contact Number"),

    COUNTRY ("Country"),
    BUSINESS_ADDRESS ("Business Address"),
    PERSONAL_ADDRESS ("Personal Address"),

    PROFESSIONAL_BACKGROUND ("Professional Background"),
    EDUCATIONAL_BACKGROUND ("Educational Background"),

    PROFESSIONAL_LICENCE ("Professional Licence"),
    EDUCATIONAL_LICENCE ("Educational Licence"),

    ASSETS ("Assets");

    private String profileData;
    private int profileDataEntryCount;

    ProfileData(String profileData) {
        this.profileData = profileData;
    }

    public String getProfileData() {
        return profileData;
    }

    public int getProfileDataEntryCount() {
        return profileDataEntryCount;
    }

    public void setProfileDataEntryCount(int profileDataEntryCount) {
        this.profileDataEntryCount = profileDataEntryCount;
    }
}
