package com.psa.data;

public enum Scope {

    L4 ("Premium - L4"),
    L3 ("Advanced - L3"),
    L2 ("Core - L2"),
    L1 ("Basic - L1");

    private String scope;

    Scope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public static Scope getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
