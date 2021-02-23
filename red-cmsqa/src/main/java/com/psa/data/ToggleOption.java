package com.psa.data;

public enum ToggleOption {

    ON ("ON"),
    OFF ("OFF");

    private String toggleOption;

    ToggleOption(String toggleOption) {
        this.toggleOption = toggleOption;
    }

    public String getToggleOption() {
        return toggleOption;
    }

    public void setToggleOption(String toggleOption) {
        this.toggleOption = toggleOption;
    }
}
