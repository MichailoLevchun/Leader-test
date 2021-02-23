package com.psa.data.SubjectProfile;

public enum KeyIndustry {

    AUTOMOBILE ("Automobiles & Components"),
    FOOD_STAPLES ("Food & Staples Retailing"),
    PHARMACEUTICALS ("Pharmaceuticals, Biotechnology & Life Sciences"),
    TECHNOLOGY ("Technology Hardware & Equipment"),
    FOOD_BEVERAGE ("Food, Beverage & Tobacco"),
    TELCO ("Telecommunication Services"),
    RETAILING ("Retailing"),
    HOUSEHOLD ("Household & Personal Products"),
    MATERIALS ("Materials"),
    HEALTH_CARE ("Health Care Equipment & Services"),
    SOFTWARE ("Software & Services"),
    TRANSPORTATION ("Transportation"),
    UTILITIES ("Utilities"),
    INSURANCE ("Insurance"),
    FINANCIALS ("Diversified Financials"),
    COMMERCIAL ("Commercial & Professional Services"),
    ENERGY ("Energy"),
    CONSUMER_DURABLES ("Consumer Durables & Apparel"),
    GOODS ("Capital Goods"),
    CONSUMER_SERVICES ("Consumer Services"),
    MEDIA ("Media & Entertainment"),
    SEMICON ("Semiconductors & Semiconductor Equipment"),
    REAL_ESTATE ("Real Estate"),
    BANKS ("Banks");

    private String keyIndustry;

    KeyIndustry(String keyIndustry) {
        this.keyIndustry = keyIndustry;
    }

    public String getKeyIndustry() {
        return keyIndustry;
    }

    public void setKeyIndustry(String keyIndustry) {
        this.keyIndustry = keyIndustry;
    }

    public static KeyIndustry getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
