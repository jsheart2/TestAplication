package com.testaplication;

public class UserRequest {
    private String fullname;
    private String email;
    private String password;
    private String confrimpassword;
    private String radio;
    private String dob = "1994-04-01";

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getConfrimpassword() {
        return confrimpassword;
    }

    public void setConfrimpassword(String confrimpassword) {
        this.confrimpassword = confrimpassword;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}