package com.book.store.api.domain;

public class Admin {

    private int id;
    private int phone;
    private String pwd;
    private String name;
    private int create_time;

    public Admin() {

    }

    public Admin(int id,int phone,String pwd,String name,int create_time) {
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

    public int getPhone () {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCreate_time () {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

}
