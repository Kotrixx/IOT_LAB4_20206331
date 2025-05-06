package com.example.iot_lab4_20206331.data.model;

import java.util.List;

public class Forecast {

    private List<Day> forecast;

    // Getters y Setters
    public List<Day> getForecast() {
        return forecast;
    }

    public void setForecast(List<Day> forecast) {
        this.forecast = forecast;
    }

    public static class Day {
        private String date;
        private double maxTemp;
        private double minTemp;
        private String condition;

        // Getters y Setters
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(double maxTemp) {
            this.maxTemp = maxTemp;
        }

        public double getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(double minTemp) {
            this.minTemp = minTemp;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }
    }
}
