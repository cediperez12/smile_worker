package com.smile.worker.Models;

public class PersonalInformation {
    public String first_name, last_name;
    public long birthdate;
    public String sex;

    public PersonalInformation(String first_name, String last_name, long birthdate, String sex) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.sex = sex;
    }

    public PersonalInformation() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(long birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
