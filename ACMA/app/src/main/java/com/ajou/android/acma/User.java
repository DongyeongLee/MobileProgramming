package com.ajou.android.acma;

/**
 * Created by LDY on 2017-05-12.
 */

class User {

    public String Major;
    public String Gender;
    public String Grade;
    public String Name;

    public User(String Name, String Major, String Gender, String Grade){
        this.Name = Name;
        this.Major = Major;
        this.Gender = Gender;
        this.Grade = Grade;
    }

  /*  public String getMajor(){
        return Major;
    }

    public String getGender(){
        return Gender;
    }

    public String getGrade(){
        return Grade;
    }

    public String getName(){
        return Name;
    }*/
}
