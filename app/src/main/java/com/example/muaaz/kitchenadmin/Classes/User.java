package com.example.muaaz.kitchenadmin.Classes;

/**
 * Created by muaaz on 2017/09/07.
 */

public class User {
    public String email;
    public String name;
    public String surname;
    public long phoneNo;
    public String password;

    public User(){}

    public User(String email, String name, String surname,long phoneNo, String password){
        //User this = ......
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNo = phoneNo;
        this.password = password;
    }

}
