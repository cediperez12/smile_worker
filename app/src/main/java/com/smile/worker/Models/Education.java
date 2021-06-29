package com.smile.worker.Models;

public class Education {
    private String schoolLevel, schoolName, year;

    public Education(String schoolLevel, String schoolName, String year) {
        this.schoolLevel = schoolLevel;
        this.schoolName = schoolName;
        this.year = year;
    }

    public Education() {
    }

    public String getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
