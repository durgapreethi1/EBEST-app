package com.eserv.client;

/**
 * Created by Lenovo on 16-02-2018.
 */

public class User {
    int _id;
    String _name;
    String _email;
    String _password;
    long _mobile;

    public User() {
    }

    public User(int id, String name, String email,String password, long mobile) {
        this._id = id;
        this._name = name;
        this._email = email;
        this._password = password;
        this._mobile = mobile;
    }
    public User(int id, String name, String email, long mobile) {
        this._id = id;
        this._name = name;
        this._email = email;
        this._password = "";
        this._mobile = mobile;
    }

    public User(String name, long mobile) {
        this._name = name;
        this._email = "";
        this._password = "";
        this._mobile = _mobile;
    }

    public User(String name, String email) {
        this._name = name;
        this._email = email;
        this._mobile = 0;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }
    public String getEmail() {
        return this._email;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    public String getPassword() {
        return this._password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public long getMobile() {
        return this._mobile;
    }

    public void setMobile(long mobile) {
        this._mobile = mobile;
    }
}