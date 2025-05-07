package com.example.iot_lab4_20206331.data.model;

import com.google.gson.annotations.SerializedName;

public class Forecastday {
    @SerializedName("date")
    private String date;

    @SerializedName("day")
    private Day day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
