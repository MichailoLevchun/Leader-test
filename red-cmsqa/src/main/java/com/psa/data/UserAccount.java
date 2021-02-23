package com.psa.data;

public class UserAccount {

    private String email;
    private String password;
    private String fullName;
    private String firstName;
    private String middleName;
    private String lastName;
    private Region region;

    public UserAccount(String email, String password, String fullName, String firstName, Region region) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.firstName = firstName;
        this.region = region;
    }

    public UserAccount(String fullName, Region region) {
        this.fullName = fullName;
        this.region = region;
    }

    public UserAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}