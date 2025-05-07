package com.example.iot_lab4_20206331.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Condition {
    @SerializedName("text")
    private String text;

    @SerializedName("icon")
    private String icon;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static class SportsResponse {
        private List<Sport> sports;

        public List<Sport> getSports() {
            return sports;
        }

        public void setSports(List<Sport> sports) {
            this.sports = sports;
        }
    }
}
