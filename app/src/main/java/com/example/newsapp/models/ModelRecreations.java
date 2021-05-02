package com.example.newsapp.models;

public class ModelRecreations {
    private String urlToImage;
    private String name;
    private String address;
    private String desc;
    private String latitude; //lintang
    private String longitude; //bujur

    public void setUrlToImage (String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDesc(String Desc) {
        this.desc = Desc;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getAddress() {
        return address;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
