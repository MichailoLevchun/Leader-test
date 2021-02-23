package com.psa.data;

public enum  Region {

    AMERICAS ("Americas"),
    CHINA ("China"),
    EUROPE ("Europe"),
    MEA ("MEA"),
    SEAPAC ("SEAPAC");

    private String region;

    Region(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
