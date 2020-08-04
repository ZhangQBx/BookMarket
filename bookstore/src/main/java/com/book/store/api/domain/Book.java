package com.book.store.api.domain;

public class Book {

    private int id;
    private String category;
    private String title;
    private String tags;
    private String sub1;

    private String sub2;
    private String img;
    private String reading;
    private String online;

    private String byTime;
    private int price;
    private int discount;
    private boolean hot;

    private boolean isNew;
    private boolean racking;

    private int rack_start;
    private int rack_end;
    private int creat_time;

    public Book() {

    }

    public Book( int id ,String category, String title, String tags, String sub1,String sub2,
                 String img, String reading, String online, String byTime, int price, int discount,
                 boolean hot, boolean isNew, boolean racking, int rack_start, int rack_end, int creat_time) {

        this.id = id;
        this.category = category;
        this.title = title;
        this.tags = tags;
        this.sub1 = sub1;

        this.sub2 = sub2;
        this.img = img;
        this.reading = reading;
        this.online = online;

        this.byTime = byTime;
        this.price = price;
        this.discount = discount;
        this.hot = hot;

        this.isNew = isNew;
        this.racking = racking;

        this.rack_start = rack_start;
        this.rack_end = rack_end;
        this.creat_time = creat_time;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSub1() {
        return sub1;
    }
    public void setSub1(String sub1) {this.sub1 = sub1;}

    public String getSub2() {
        return sub2;
    }
    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }


    public String getImg() {
        return img;
    }
    public void setImg() { this.img = img; }

    public void setReading(String reading) {
        this.reading = reading;
    }
    public String getReading() {
        return reading;
    }

    public void setOnline(String online) {this.online = online;}
    public String getOnline() {
        return online;
    }

    public void setByTime(String byTime) {this.byTime = byTime;}
    public String getByTime() {
        return byTime;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setHot(boolean hot) {this.hot = hot;}
    public boolean isHot() {
        return hot;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }
    public boolean isNew() {
        return isNew;
    }

    public void setRacking(boolean racking) {
        this.racking = racking;
    }
    public boolean isRacking () {
        return racking;
    }

    public void setRack_start(int rack_start) {
        this.rack_start = rack_start;
    }
    public int getRack_start() {
        return rack_start;
    }


    public void setRack_end(int rack_end) {this.rack_end = rack_end;}
    public int getRack_end () {
        return rack_end;
    }

    public void setCreat_time(int creat_time) {
        this.creat_time = creat_time;
    }
    public int getCreat_time () {
        return creat_time;
    }

}
