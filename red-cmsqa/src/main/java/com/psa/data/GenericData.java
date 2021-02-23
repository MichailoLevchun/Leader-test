package com.psa.data;

import com.psa.data.SubjectProfile.KeyIndustry;
import com.psa.library.TestInitReference;

import java.time.LocalDate;
import java.time.Month;


public class GenericData extends TestInitReference {

    // ~ POC
    public static final String PSA_POC = "PSA - Mykhailo Levchun";

    public static final int timeout = 60;
    public static final String CLASS = "class";
    public static final String CHECKED = "checked";
    public static final String PRESENT = "Present";
    public static final String DOUBLE_DASH = "--";
    public static final int[] RATING_SCORE = {1,2,3,4,5,6,7,8,9,10};
    public static final LocalDate START_DATE = LocalDate.of(1900, Month.JANUARY, 1);
    public static final LocalDate END_DATE = LocalDate.of(2100, Month.DECEMBER, 1);

    // Common Error Messages
    public static final String ERR_INDIVIDUAL_NAME = "Provide at least one name";
    public static final String ERR_REQUIRED_FIELD = "This is a required field";
    public static final String ERR_INVALID_LINK = "Invalid link";
    public static final String ERR_INVALID_RISK_SCORE = "Score must be from 1 to 10";

    public static final String PATTERN_PAGINATION = "\\d+-\\d+ of \\d+";
    public static final String PATTERN_PROFILE_FOUND = "\\d+ profile match.* found";
    public static final String INVALID_KEYWORD = "invalid keyword";
    public static final String INVALID_LINK = "invalid_link";
    public static final String INVALID_PROFILE = "invalid_profile";
    public static final String INVALID_RISK = "invalid_risk";
    public static final String VALID_LINK01 = "www.cnn.com";
    public static final String VALID_LINK02 = "www.google.com";
    public static final String[] VALID_LINKS = {VALID_LINK01, VALID_LINK02};
    public static final String[] INVALID_LINKS = {INVALID_LINK, INVALID_PROFILE};
    public static final String CLIENT00_NAME = "00  -  DnD";

    public static final String countryOfRegistration = "Country of Registration";
    public static final String country = "Country";

    private static final Country[] COUNTRY_RANDOM = {Country.getRandom()};
    private static final Country[] COUNTRY_BE = {Country.BE};
    private static final Country[] COUNTRY_CA_UA = {Country.CA, Country.UA};

    private static final String randomName = randomString(5);

    public static final ProfileDetails INDIVIDUAL_PROFILE_00 = new ProfileDetails(ProfileType.INDIVIDUAL, "FN00" + randomName, "MN00" + randomName, "LN00" + randomName, COUNTRY_RANDOM, "FN00" + randomName + " " + "MN00" + randomName + " " + "LN00" + randomName);
    public static final ProfileDetails INDIVIDUAL_PROFILE_01 = new ProfileDetails(ProfileType.INDIVIDUAL, "FN01" + randomName, "MN01" + randomName, "", COUNTRY_RANDOM, "FN01" + randomName + " " + "MN01" + randomName);
    public static final ProfileDetails INDIVIDUAL_PROFILE_02 = new ProfileDetails(ProfileType.INDIVIDUAL, "FN02" + randomName, "", "LN02" + randomName, COUNTRY_RANDOM, "FN02" + randomName + " " + "LN02" + randomName);
    public static final ProfileDetails INDIVIDUAL_PROFILE_03 = new ProfileDetails(ProfileType.INDIVIDUAL, "", "MN03" + randomName, "LN03" + randomName, COUNTRY_RANDOM, "MN03" + randomName + " " + "LN03" + randomName);
    public static final ProfileDetails INDIVIDUAL_PROFILE_04 = new ProfileDetails(ProfileType.INDIVIDUAL, "FN04" + randomName, "", "", COUNTRY_RANDOM, "FN04" + randomName);
    public static final ProfileDetails INDIVIDUAL_PROFILE_05 = new ProfileDetails(ProfileType.INDIVIDUAL, "", "MN05" + randomName, "", COUNTRY_RANDOM, "MN05" + randomName);
    public static final ProfileDetails INDIVIDUAL_PROFILE_06 = new ProfileDetails(ProfileType.INDIVIDUAL, "", "", "LN06" + randomName, COUNTRY_RANDOM, "LN06" + randomName);
    public static final ProfileDetails INDIVIDUAL_PROFILE_07 = new ProfileDetails(ProfileType.INDIVIDUAL, "FN07" + randomName, "MN07" + randomName, "LN07" + randomName, COUNTRY_RANDOM, "FN07" + randomName + " " + "MN07" + randomName + " " + "LN07" + randomName);

    public static final ProfileDetails COMPANY_PROFILE_00 = new ProfileDetails(ProfileType.COMPANY, "C00" + randomName, COUNTRY_RANDOM, ("C00" + randomName).substring(0,6));
    public static final ProfileDetails COMPANY_PROFILE_01 = new ProfileDetails(ProfileType.COMPANY, "C01" + randomString(5), COUNTRY_RANDOM);
    public static final ProfileDetails COMPANY_PROFILE_02 = new ProfileDetails(ProfileType.COMPANY, "C02" + randomString(5), COUNTRY_RANDOM);
    public static final ProfileDetails COMPANY_PROFILE_03 = new ProfileDetails(ProfileType.COMPANY, "C03" + randomString(5), COUNTRY_RANDOM, getRandom(RATING_SCORE), randomString(50), randomString(50));
    public static final ProfileDetails COMPANY_PROFILE_04 = new ProfileDetails(ProfileType.COMPANY, "C04" + randomString(5), COUNTRY_RANDOM, getRandom(RATING_SCORE), randomString(50), randomString(50), KeyIndustry.BANKS);
    public static final ProfileDetails COMPANY_PROFILE_NO_COUNTRY_00 = new ProfileDetails(ProfileType.COMPANY, randomString(10));
    public static final ProfileDetails COMPANY_PROFILE_08 = new ProfileDetails(ProfileType.COMPANY, "C08" + randomName, COUNTRY_RANDOM, ("C08" + randomName).substring(0,6));
    public static final ProfileDetails COMPANY_PROFILE_09 = new ProfileDetails(ProfileType.COMPANY, "C09" + randomString(5), COUNTRY_RANDOM);
    public static final ProfileDetails COMPANY_PROFILE_100 = new ProfileDetails(ProfileType.COMPANY, "C100" + randomString(5), COUNTRY_RANDOM);

    public static final UserAccount[] WRITERS = {UserData.WRITER00_ACCOUNT, UserData.WRITER01_ACCOUNT, UserData.WRITER02_ACCOUNT, UserData.WRITER03_ACCOUNT};
    public static final UserAccount[] REVIEWERS = {UserData.REVIEWER00_ACCOUNT, UserData.REVIEWER01_ACCOUNT};

    public static final CaseDetails CASE_DETAILS_00 = new CaseDetails(Scope.getRandom());
    public static final CaseDetails CASE_DETAILS_01 = new CaseDetails(Scope.L4);
    public static final CaseDetails CASE_DETAILS_02 = new CaseDetails(Scope.L3);
    public static final CaseDetails CASE_DETAILS_03 = new CaseDetails(Scope.L2);
    public static final CaseDetails CASE_DETAILS_04 = new CaseDetails(Scope.L1);
    public static final CaseDetails CASE_DETAILS_05 = new CaseDetails(Scope.L4, randomString(50), CLIENT00_NAME, WRITERS, REVIEWERS);
    public static final CaseDetails CASE_DETAILS_06 = new CaseDetails(Scope.getRandom(), randomString(50), CLIENT00_NAME, WRITERS, REVIEWERS);
    public static final CaseDetails CASE_DETAILS_07 = new CaseDetails(Scope.L1, randomString(50), CLIENT00_NAME, WRITERS, REVIEWERS);
    public static final CaseDetails CASE_DETAILS_08 = new CaseDetails(Scope.L4, randomString(50), CLIENT00_NAME, WRITERS, REVIEWERS);
    public static final CaseDetails CASE_DETAILS_09 = new CaseDetails(Scope.L4);
    public static final CaseDetails CASE_DETAILS_10 = new CaseDetails(Scope.getRandom());
    public static final CaseDetails CASE_DETAILS_11 = new CaseDetails(Scope.L1);
    public static final CaseDetails CASE_DETAILS_12 = new CaseDetails(Scope.L1, randomString(50), CLIENT00_NAME, WRITERS, REVIEWERS);
    public static final CaseDetails CASE_DETAILS_13 = new CaseDetails(Scope.getRandom(), WRITERS, REVIEWERS);
    public static final CaseDetails CASE_DETAILS_100 = new CaseDetails(Scope.L4);

    private static final ProfileDetails EXISTING_COMPANY00_PROFILE = new ProfileDetails(ProfileType.COMPANY, "DO NOT DELETE KEYWORDS", COUNTRY_BE);
    private static final ProfileDetails EXISTING_INDIVIDUAL00_PROFILE = new ProfileDetails(ProfileType.INDIVIDUAL, "DO NOT DELETE INDIVIDUAL", COUNTRY_CA_UA);

    public static final String[] EXISTING_PROFILE_KEYWORDS = {"DO NOT DELETE", EXISTING_COMPANY00_PROFILE.getFullName(), //Common Keyword, Company Full Name
            EXISTING_INDIVIDUAL00_PROFILE.getFullName(), "DO NOT DELETE LOCAL LANGUAGE NAME", //Individual Full Name, Local Language Name
             "DO NOT DELETE TRADING", "DO NOT DELETE REGISTERED NAME", "DO NOT DELETE PREVIOUS NAME", //Trading as Name, Registered Name, Previous Name
            EXISTING_INDIVIDUAL00_PROFILE.getCountry()[0].getCountry(), "DnD", "5fbe6cad6d2bc75adf6c2bef"} ; //Country of Registration, Short Name, profileId

    private static final Risk[] RISK_CAT7_00 = {Risk.COR, Risk.TRA, Risk.ENV, Risk.INT, Risk.FIN, Risk.IND, Risk.SRC};
    private static final Risk[] RISK_CAT7_01 = {Risk.SER, Risk.SOU, Risk.ANI, Risk.PER, Risk.FII, Risk.PRO, Risk.CTR};
    private static final Risk[] RISK_CAT5_00 = {Risk.TER, Risk.SAL, Risk.DAT, Risk.OPE, Risk.COI};
    private static final Risk[] RISK_CAT3_00 = {Risk.ANT, Risk.HEA, Risk.BUS};
    private static final Risk[] RISK_CAT2_00 = {Risk.GOC, Risk.HUM};
    private static final Risk[] RISK_CAT2_01 = {Risk.FRA, Risk.MOD};
    private static final Risk[] RISK_CAT2_02 = {Risk.MON, Risk.EMP};
    private static final Risk[] RISK_CAT2_03 = {Risk.TAX, Risk.GOV};
    private static final Risk[] RISK_CAT2_04 = {Risk.SAN, Risk.REG};

    public static final ProfileData[] PROFILE_DATA_COMPANY_NAMES = {ProfileData.REGISTERED_NAME, ProfileData.FULL_NAME, ProfileData.SHORT_NAME, ProfileData.NAME_IN_LOCAL_LANGUAGE,
            ProfileData.TRADING_AS_NAME, ProfileData.PREVIOUS_NAME};
    public static final ProfileData RANDOM_PROFILE_DATA_COMPANY_NAMES = (ProfileData) getRandom(PROFILE_DATA_COMPANY_NAMES);

    private static final Risk[] RISK_RANDOM_00 = {Risk.getRandom()};

    private static final String[] INVOLVING_PROFILE_C00 = {COMPANY_PROFILE_00.getFullName()};
    private static final String[] INVOLVING_PROFILE_C01 = {COMPANY_PROFILE_01.getFullName()};
    private static final String[] INVOLVING_PROFILE_C02 = {COMPANY_PROFILE_02.getFullName()};
    private static final String[] INVOLVING_PROFILE_C03 = {COMPANY_PROFILE_03.getFullName()};
    private static final String[] INVOLVING_PROFILE_C100 = {COMPANY_PROFILE_100.getFullName()};
    private static final String[] INVOLVING_PROFILE_I00 = {INDIVIDUAL_PROFILE_00.getFullName()};
    private static final String[] INVOLVING_PROFILE_I01 = {INDIVIDUAL_PROFILE_01.getFullName()};
    private static final String[] INVOLVING_PROFILE_I02 = {INDIVIDUAL_PROFILE_02.getFullName()};
    private static final String[] INVOLVING_PROFILE_I03 = {INDIVIDUAL_PROFILE_03.getFullName()};
    private static final String[] INVOLVING_PROFILE_I04 = {INDIVIDUAL_PROFILE_04.getFullName()};
    private static final String[] INVOLVING_PROFILE_I05 = {INDIVIDUAL_PROFILE_05.getFullName()};
    private static final String[] INVOLVING_PROFILE_I06 = {INDIVIDUAL_PROFILE_06.getFullName()};
    private static final String[] INVOLVING_PROFILE_X00 = {INDIVIDUAL_PROFILE_04.getFullName(), INDIVIDUAL_PROFILE_06.getFullName(), COMPANY_PROFILE_01.getFullName()};

    public static final SourceRecord CD01A_DETAILS = new SourceRecord(SourceType.CLIENT_DOCUMENTATION, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C01,
            RISK_CAT5_00, randomString(50), randomString(50));
    public static final SourceRecord CD01B_DETAILS = new SourceRecord(SourceType.CLIENT_DOCUMENTATION, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C00,
            RISK_CAT7_00, randomString(50), randomString(50));
    public static final SourceRecord CD01C_DETAILS = new SourceRecord(SourceType.CLIENT_DOCUMENTATION, INVOLVING_PROFILE_C01);

    public static final SourceRecord GC01A_DETAILS = new SourceRecord(SourceType.GLOBAL_COMPLIANCE_CHECKS, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C00,
            RISK_CAT3_00, randomString(50), VALID_LINK01, VALID_LINKS);
    public static final SourceRecord GC01B_DETAILS = new SourceRecord(SourceType.GLOBAL_COMPLIANCE_CHECKS, ToggleOption.OFF,ToggleOption.OFF,null,
            null, "", INVALID_LINK, INVALID_LINKS);
    public static final SourceRecord GC01C_DETAILS = new SourceRecord(SourceType.GLOBAL_COMPLIANCE_CHECKS, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_X00,
            RISK_CAT2_04, randomString(50), "", VALID_LINKS);
    public static final SourceRecord GC01D_DETAILS = new SourceRecord(SourceType.GLOBAL_COMPLIANCE_CHECKS, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I00,
            RISK_CAT2_00, randomString(50), VALID_LINK01, VALID_LINKS);
    public static final SourceRecord GC01E_DETAILS = new SourceRecord(SourceType.GLOBAL_COMPLIANCE_CHECKS, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C01,
            RISK_CAT2_01, randomString(50), VALID_LINK01, VALID_LINKS);
    public static final SourceRecord GC01F_DETAILS = new SourceRecord(SourceType.GLOBAL_COMPLIANCE_CHECKS, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C03,
            RISK_CAT2_02, randomString(50), VALID_LINK01, VALID_LINKS);

    public static final SourceRecord IN01A_DETAILS = new SourceRecord(SourceType.INTERVIEWS, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I05,
            RISK_CAT2_01, randomString(50), randomString(10), randomString(5), VALID_LINK01);
    public static final SourceRecord IN01B_DETAILS = new SourceRecord(SourceType.INTERVIEWS, ToggleOption.OFF,ToggleOption.OFF, null,
            null, "", "", "", INVALID_LINK);
    public static final SourceRecord IN01C_DETAILS = new SourceRecord(SourceType.INTERVIEWS, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I06,
            RISK_CAT2_02, randomString(50), randomString(10), randomString(5), VALID_LINK01);
    public static final SourceRecord IN01D_DETAILS = new SourceRecord(SourceType.INTERVIEWS, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C01,
            RISK_CAT2_03, randomString(50), randomString(10), randomString(5), VALID_LINK01);
    public static final SourceRecord IN01E_DETAILS = new SourceRecord(SourceType.INTERVIEWS, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I03,
            RISK_CAT7_00, randomString(50), randomString(10), randomString(5), VALID_LINK01);

    public static final SourceRecord LI01A_DETAILS = new SourceRecord(SourceType.LITIGATION, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C03,
            RISK_CAT3_00, randomString(50), VALID_LINK01);
    public static final SourceRecord LI01B_DETAILS = new SourceRecord(SourceType.LITIGATION, INVOLVING_PROFILE_I04);
    public static final SourceRecord LI01C_DETAILS = new SourceRecord(SourceType.LITIGATION, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I03,
            RISK_RANDOM_00, randomString(50), VALID_LINK01);

    public static final SourceRecord ME01A_DETAILS = new SourceRecord(SourceType.MEDIA, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I02,
            RISK_CAT2_00, randomString(50), VALID_LINK01);
    public static final SourceRecord ME01B_DETAILS = new SourceRecord(SourceType.MEDIA, ToggleOption.OFF,ToggleOption.OFF, null,
            null, "", INVALID_LINK);

    public static final SourceRecord OD01A_DETAILS = new SourceRecord(SourceType.OFFICIAL_DOCUMENTATION, ToggleOption.OFF,ToggleOption.OFF, null,
            null, "", INVALID_LINK);
    public static final SourceRecord OD01B_DETAILS = new SourceRecord(SourceType.OFFICIAL_DOCUMENTATION, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I02,
            RISK_CAT2_01, randomString(50), VALID_LINK01);
    public static final SourceRecord OD01C_DETAILS = new SourceRecord(SourceType.OFFICIAL_DOCUMENTATION, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I03,
            null, randomString(50), VALID_LINK01);

    public static final SourceRecord RE01A_DETAILS = new SourceRecord(SourceType.REPUTATION, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I03,
            RISK_CAT5_00, randomString(50), randomString(10), randomString(10), randomString(10), ReputationSourceType.getRandom(), randomString(50));
    public static final SourceRecord RE01B_DETAILS = new SourceRecord(SourceType.REPUTATION, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I04,
            RISK_CAT2_02, randomString(50), randomString(10), randomString(10), randomString(10), ReputationSourceType.getRandom(), randomString(50));
    public static final SourceRecord RE01C_DETAILS = new SourceRecord(SourceType.REPUTATION, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C01,
            RISK_CAT3_00, randomString(50), randomString(10), randomString(10), randomString(10), ReputationSourceType.getRandom(), randomString(50));

    public static final SourceRecord SV01A_DETAILS = new SourceRecord(SourceType.SITE_VISIT, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I02,
            RISK_CAT3_00, randomString(50), randomString(20), ToggleOption.ON, randomString(50), new String[] {randomString(10),randomString(10)});
    public static final SourceRecord SV01B_DETAILS = new SourceRecord(SourceType.SITE_VISIT,ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C00,
            RISK_CAT2_03, randomString(50), randomString(20), ToggleOption.ON, randomString(50), new String[] {randomString(10),randomString(10)});
    public static final SourceRecord SV01C_DETAILS = new SourceRecord(SourceType.SITE_VISIT, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C01,
            RISK_CAT2_04, randomString(50), randomString(20), ToggleOption.ON, randomString(50), new String[] {randomString(10),randomString(10)});

    public static final SourceRecord WS01A_DETAILS = new SourceRecord(SourceType.WEBSITE, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C01,
            RISK_CAT2_00, randomString(50), VALID_LINK01);
    public static final SourceRecord WS01B_DETAILS = new SourceRecord(SourceType.WEBSITE, INVOLVING_PROFILE_C01);
    public static final SourceRecord WS01C_DETAILS = new SourceRecord(SourceType.WEBSITE, ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_I03,
            RISK_RANDOM_00, randomString(50), VALID_LINK01);

    public static final SourceRecord RANDOM_SOURCE_RECORD_DETAILS_00 = new SourceRecord(SourceType.getRandom(), ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C00,
            RISK_RANDOM_00, randomString(50));
    public static final SourceRecord RANDOM_SOURCE_RECORD_DETAILS_100A = new SourceRecord(SourceType.getRandom(), ToggleOption.ON,ToggleOption.OFF, INVOLVING_PROFILE_C100,
            new Risk[]{Risk.getRandom()}, randomString(50));
    public static final SourceRecord RANDOM_SOURCE_RECORD_DETAILS_100B = new SourceRecord(SourceType.getRandom(), ToggleOption.OFF,ToggleOption.ON, INVOLVING_PROFILE_C100,
            new Risk[]{Risk.getRandom()}, randomString(50));

    public static final Risk[] ALL_RISKS = {Risk.COR, Risk.SER, Risk.TER, Risk.ANT, Risk.GOC, Risk.FRA, Risk.MON, Risk.TAX, Risk.SAN,
            Risk.TRA, Risk.SOU, Risk.ENV, Risk.ANI, Risk.SAL, Risk.HEA, Risk.HUM, Risk.MOD, Risk.EMP, Risk.GOV, Risk.REG,
            Risk.INT, Risk.PER, Risk.DAT, Risk.FIN, Risk.FII, Risk.IND, Risk.PRO, Risk.OPE, Risk.BUS, Risk.SRC, Risk.CTR, Risk.COI};
    public static final Risk[] INTEGRITY_RISKS = {Risk.COR, Risk.SER, Risk.TER, Risk.ANT, Risk.GOC, Risk.FRA, Risk.MON, Risk.TAX, Risk.SAN};
    public static final Risk[] IDENTITY_RISKS = {Risk.TRA, Risk.SOU};
    public static final Risk[] ESG_RISKS = {Risk.ENV, Risk.ANI, Risk.SAL, Risk.HEA, Risk.HUM, Risk.MOD, Risk.EMP, Risk.GOV, Risk.REG};
    public static final Risk[] DATA_AND_CYBER_RISKS = {Risk.INT, Risk.PER, Risk.DAT};
    public static final Risk[] FINANCIAL_RISKS = {Risk.FIN, Risk.FII};
    public static final Risk[] OAQ_RISKS = {Risk.IND, Risk.PRO, Risk.OPE, Risk.BUS};
    public static final Risk[] ENGAGEMENT_RISKS = {Risk.SRC, Risk.CTR, Risk.COI};

    public static final Risk RANDOM_RISK00 = Risk.getRandom();

    public static final RiskSummary RISK_SUMMARY_00A = new RiskSummary(RANDOM_RISK00, RiskInformationType.PUBLIC, randomString(25));
    public static final RiskSummary RISK_SUMMARY_00B = new RiskSummary(RANDOM_RISK00, RiskInformationType.PUBLIC, randomString(50));
    public static final RiskSummary RISK_SUMMARY_00C = new RiskSummary(RANDOM_RISK00, RiskInformationType.CLIENT_SPECIFIC, randomString(25));
    public static final RiskSummary RISK_SUMMARY_00D = new RiskSummary(RANDOM_RISK00, RiskInformationType.CLIENT_SPECIFIC, randomString(50));
    public static final RiskSummary RISK_SUMMARY_03A = new RiskSummary(RANDOM_RISK00, RiskInformationType.CLIENT_SPECIFIC, randomString(50));
    public static final RiskSummary RISK_SUMMARY_03B = new RiskSummary(RANDOM_RISK00, RiskInformationType.PUBLIC, randomString(50));
    public static final RiskSummary RISK_SUMMARY_04 = new RiskSummary(RISK_RANDOM_00[0], RiskInformationType.CLIENT_SPECIFIC, randomString(50), LI01C_DETAILS, WS01C_DETAILS);

    public static final RiskInsight RISK_INSIGHT_00 = new RiskInsight(RANDOM_RISK00, RiskInformationType.CLIENT_SPECIFIC, randomString(50), ToggleOption.ON, randomString(50));
    public static final RiskInsight RISK_INSIGHT_01 = new RiskInsight(RANDOM_RISK00, RiskInformationType.CLIENT_SPECIFIC, randomString(50), ToggleOption.ON);
    public static final RiskInsight RISK_INSIGHT_02A = new RiskInsight(RANDOM_RISK00, RiskInformationType.CLIENT_SPECIFIC, randomString(50), ToggleOption.ON, randomString(50));
    public static final RiskInsight RISK_INSIGHT_02B = new RiskInsight(RANDOM_RISK00, RiskInformationType.CLIENT_SPECIFIC, randomString(50));
    public static final RiskInsight RISK_INSIGHT_02C = new RiskInsight(RANDOM_RISK00, RiskInformationType.PUBLIC, randomString(50), ToggleOption.ON, randomString(50));
    public static final RiskInsight RISK_INSIGHT_02D = new RiskInsight(RANDOM_RISK00, RiskInformationType.PUBLIC, randomString(50));
    public static final RiskInsight RISK_INSIGHT_03A = new RiskInsight(RANDOM_RISK00, RiskInformationType.CLIENT_SPECIFIC, randomString(50), ToggleOption.ON, randomString(50));
    public static final RiskInsight RISK_INSIGHT_03B = new RiskInsight(RANDOM_RISK00, RiskInformationType.CLIENT_SPECIFIC, randomString(50), ToggleOption.OFF);

    // Hex Colors
    public static final String STRONG_BLUE = "#1494cb";

    public static final SourceType[] ALL_SOURCE_TYPE = {SourceType.GLOBAL_COMPLIANCE_CHECKS, SourceType.MEDIA,
            SourceType.WEBSITE, SourceType.CLIENT_DOCUMENTATION, SourceType.OFFICIAL_DOCUMENTATION,
            SourceType.LITIGATION, SourceType.REPUTATION, SourceType.INTERVIEWS, SourceType.SITE_VISIT};

    // Data Entries
    public static final DataEntry DATA_ENTRY_COMPANY_BACKGROUND_00 = new DataEntry(ProfileData.COMPANY_BACKGROUND, randomString(10));
    public static final DataEntry DATA_ENTRY_COMPANY_BACKGROUND_01 = new DataEntry(ProfileData.COMPANY_BACKGROUND, randomString(20));
    public static final DataEntry DATA_ENTRY_COMPANY_BACKGROUND_02A = new DataEntry(ProfileData.COMPANY_BACKGROUND, randomString(10), RANDOM_SOURCE_RECORD_DETAILS_100A);
    public static final DataEntry DATA_ENTRY_COMPANY_BACKGROUND_02B = new DataEntry(ProfileData.COMPANY_BACKGROUND, randomString(10), RANDOM_SOURCE_RECORD_DETAILS_100A, RANDOM_SOURCE_RECORD_DETAILS_100B);
    public static final DataEntry DATA_ENTRY_COMPLIANCE_AND_ETHICS_00 = new DataEntry(ProfileData.COMPLIANCE_AND_ETHICS, randomString(10));
    public static final DataEntry DATA_ENTRY_NAMES_00A = new DataEntry(RANDOM_PROFILE_DATA_COMPANY_NAMES, randomString(10), ToggleOption.OFF, ToggleOption.ON);
    public static final DataEntry DATA_ENTRY_NAMES_00B = new DataEntry(RANDOM_PROFILE_DATA_COMPANY_NAMES, randomString(20), ToggleOption.ON, ToggleOption.OFF);
    public static final DataEntry DATA_ENTRY_NAMES_01A = new DataEntry(RANDOM_PROFILE_DATA_COMPANY_NAMES, randomString(10), ToggleOption.ON, ToggleOption.ON, RANDOM_SOURCE_RECORD_DETAILS_100A);
    public static final DataEntry DATA_ENTRY_NAMES_01B = new DataEntry(RANDOM_PROFILE_DATA_COMPANY_NAMES, randomString(10), ToggleOption.OFF, ToggleOption.OFF, RANDOM_SOURCE_RECORD_DETAILS_100A, RANDOM_SOURCE_RECORD_DETAILS_100B);
    public static final DataEntry DATA_ENTRY_INDIVIDUAL_BACKGROUND_00 = new DataEntry(ProfileData.INDIVIDUAL_BACKGROUND, randomString(10));
    public static final DataEntry DATA_ENTRY_PART_OF_GROUP_OF_COMPANY_00A = new DataEntry(ProfileData.PART_OF_GROUP_OF_COMPANIES, true, randomString(10), randomString(20));
    public static final DataEntry DATA_ENTRY_PART_OF_GROUP_OF_COMPANY_00B = new DataEntry(ProfileData.PART_OF_GROUP_OF_COMPANIES, false, randomString(20), randomString(10));
    public static final DataEntry DATA_ENTRY_PART_OF_GROUP_OF_COMPANY_01A = new DataEntry(ProfileData.PART_OF_GROUP_OF_COMPANIES, true, randomString(10), randomString(20), RANDOM_SOURCE_RECORD_DETAILS_100A);
    public static final DataEntry DATA_ENTRY_PART_OF_GROUP_OF_COMPANY_01B = new DataEntry(ProfileData.PART_OF_GROUP_OF_COMPANIES, true, randomString(10), randomString(20), RANDOM_SOURCE_RECORD_DETAILS_100A, RANDOM_SOURCE_RECORD_DETAILS_100B);
    public static final DataEntry DATA_ENTRY_WEBSITE_00A = new DataEntry(ProfileData.WEBSITE, randomString(20), VALID_LINK01);
    public static final DataEntry DATA_ENTRY_WEBSITE_00B = new DataEntry(ProfileData.WEBSITE, randomString(20), VALID_LINK02);
    public static final DataEntry DATA_ENTRY_WEBSITE_01A = new DataEntry(ProfileData.WEBSITE, randomString(20), VALID_LINK01, RANDOM_SOURCE_RECORD_DETAILS_100A);
    public static final DataEntry DATA_ENTRY_WEBSITE_01B = new DataEntry(ProfileData.WEBSITE, randomString(20), VALID_LINK02, RANDOM_SOURCE_RECORD_DETAILS_100A, RANDOM_SOURCE_RECORD_DETAILS_100B);
    public static final DataEntry DATA_ENTRY_WEBSITE_02 = new DataEntry(ProfileData.WEBSITE, randomString(20), INVALID_LINK);

    public static final ProfileData[] PROFILE_DATA_COMPANY_COMPLETE = {ProfileData.COMPANY_BACKGROUND, ProfileData.COMPLIANCE_AND_ETHICS, ProfileData.PART_OF_GROUP_OF_COMPANIES,
            ProfileData.WEBSITE, ProfileData.EMAIL, ProfileData.LEGAL_FORM, ProfileData.OPERATIONAL_STATUS, ProfileData.PRIMARY_BUSINESS_ACTIVITY, ProfileData.KEY_INDUSTRY,
            ProfileData.REGISTERED_NAME, ProfileData.FULL_NAME, ProfileData.SHORT_NAME, ProfileData.NAME_IN_LOCAL_LANGUAGE, ProfileData.TRADING_AS_NAME, ProfileData.PREVIOUS_NAME,
            ProfileData.COUNTRY_OF_REGISTRATION, ProfileData.REGISTERED_LEGAL_ADDRESS, ProfileData.OPERATIONAL_ADDRESS, ProfileData.FORMER_ADDRESS, ProfileData.OPERATIONAL_PRESENCE,
            ProfileData.DATE_OF_ESTABLISHMENT, ProfileData.BUSINESS_LICENCE, ProfileData.TAX_LICENCE, ProfileData.MEDICAL_LICENCE, ProfileData.CERTIFICATION, ProfileData.INDUSTRY_AWARD,
            ProfileData.GOVERNMENT_AWARD, ProfileData.OTHER_LICENCE, ProfileData.LICENCE_GENERAL_COMMENT, ProfileData.OWNERSHIP, ProfileData.OWNERSHIP_GENERAL_COMMENT,
            ProfileData.EMPLOYEE_COUNT, ProfileData.SALESPERSON_COUNT, ProfileData.SUB_CONTRACTOR_COUNT, ProfileData.OPERATIONAL_DETAILS_GENERAL_COMMENT,
            ProfileData.COMMERCIAL_DEALINGS, ProfileData.FINANCIALS, ProfileData.COMMERCIAL_GENERAL_COMMENT, ProfileData.MANAGEMENT_GENERAL_COMMENT, ProfileData.COMPLIANCE_INTERVIEW};
    public static final ProfileData[] PROFILE_DATA_INDIVIDUAL_COMPLETE = {ProfileData.INDIVIDUAL_BACKGROUND, ProfileData.FULL_NAME, ProfileData.ALIAS, ProfileData.LOCAL_LANGUAGE_NAME,
            ProfileData.MAIDEN_NAME, ProfileData.NATIONALITY, ProfileData.IDENTIFICATION, ProfileData.DATE_OF_BIRTH_DEATH, ProfileData.GENDER, ProfileData.PHOTOGRAPH, ProfileData.CONTACT_NUMBER,
            ProfileData.EMAIL, ProfileData.COUNTRY, ProfileData.BUSINESS_ADDRESS, ProfileData.PERSONAL_ADDRESS, ProfileData. PROFESSIONAL_BACKGROUND, ProfileData.EDUCATIONAL_BACKGROUND,
            ProfileData.PROFESSIONAL_LICENCE, ProfileData.EDUCATIONAL_LICENCE, ProfileData.LICENCE_GENERAL_COMMENT, ProfileData.COMMERCIAL_DEALINGS, ProfileData.FINANCIALS,
            ProfileData.COMMERCIAL_GENERAL_COMMENT, ProfileData.ASSETS};
}