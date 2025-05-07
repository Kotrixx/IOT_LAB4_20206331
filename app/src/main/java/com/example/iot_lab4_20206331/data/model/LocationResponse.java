package com.example.iot_lab4_20206331.data.model;

import com.google.gson.annotations.SerializedName;

public class LocationResponse {

    @SerializedName("name")
    private String name; // Nombre de la locación

    @SerializedName("region")
    private String region; // Región o provincia

    @SerializedName("country")
    private String country; // País

    @SerializedName("lat")
    private double latitude; // Latitud

    @SerializedName("lon")
    private double longitude; // Longitud

    @SerializedName("id")
    private String idLocation;
    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
