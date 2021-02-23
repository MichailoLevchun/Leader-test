package com.psa.data;

public enum YesNo {

    Y ("Yes"),
    N ("No");

    private String yesNo;

    YesNo(String yesNo) {
        this.yesNo = yesNo;
    }

    public String getYesNo() {
        return yesNo;
    }
}
