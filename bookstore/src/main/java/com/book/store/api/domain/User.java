package com.book.store.api.domain;

public class User {

    private int id;
    private String phone;
    private String pwd;
    private String name;
    private int create_time;

    public User() {

    }

    public User(int id,String phone,String pwd,String name, int create_time) {
        this.id = id;
        this.phone = phone;
        this.pwd = pwd;
        this.name = name;
        this.create_time = create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCreate_time () {
        return create_time;
    }

    public void setCreate_time(int phone) {
        this.create_time = create_time;
    }

}
