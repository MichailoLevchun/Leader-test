package com.psa.data;

import com.psa.library.TestInitReference;


public class UserData extends TestInitReference {

    public static final String PASS_INVALID = "INVALID";

    public static final UserAccount ADMIN_ACCOUNT = new UserAccount(rxml.data("admin00Email"), rxml.data("admin00Password"), "", "", Region.SEAPAC);
    public static final UserAccount TEST_AUTOMATION_ACCOUNT = new UserAccount(rxml.data("caseOwner00Email"), rxml.data("caseOwner00Password"), "Test Automation User", "Test Test", Region.SEAPAC);
    public static final UserAccount WRITER00_ACCOUNT = new UserAccount(rxml.data("writer00Email"), rxml.data("writer00Password"), "Allyn Phear", "", Region.SEAPAC);
    public static final UserAccount WRITER01_ACCOUNT = new UserAccount("Bobinette Napoli", Region.MEA);
    public static final UserAccount WRITER02_ACCOUNT = new UserAccount("Crichton Brennen", Region.EUROPE);
    public static final UserAccount WRITER03_ACCOUNT = new UserAccount("Jaquelin Kermath", Region.AMERICAS);
    public static final UserAccount REVIEWER00_ACCOUNT = new UserAccount(rxml.data("reviewer00Email"), rxml.data("reviewer00Password"), "Daron Winstanley", "", Region.SEAPAC);
    public static final UserAccount REVIEWER01_ACCOUNT = new UserAccount("Clim Karadzas", Region.CHINA);
    public static final UserAccount MANAGER00_ACCOUNT = new UserAccount(rxml.data("manager00Email"), rxml.data("manager00Password"));
    public static final UserAccount ADMIN00_ACCOUNT = new UserAccount(rxml.data("admin00Email"), rxml.data("admin00Password"));
    public static final UserAccount VIEWER00_ACCOUNT = new UserAccount(rxml.data("viewer00Email"), rxml.data("viewer00Password"));
    public static final UserAccount SEAPAC_MANAGER_ACCOUNT = new UserAccount(rxml.data("seapacManagerEmail"), rxml.data("seapacManagerPassword"), "", "", Region.SEAPAC);
    public static final UserAccount MEA_MANAGER_ACCOUNT = new UserAccount(rxml.data("meaManagerEmail"), rxml.data("meaManagerPassword"), "", "", Region.MEA);
}
