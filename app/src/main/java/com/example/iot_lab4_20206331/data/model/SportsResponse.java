package com.example.iot_lab4_20206331.data.model;

import java.util.List;

public class SportsResponse {
    private List<Football> football;

    public List<Football> getFootball() {
        return football;
    }

    public static class Football {
        private String stadium;
        private String tournament;
        private String start;
        private String country;
        private String match;

        // Getters
        public String getStadium() { return stadium; }
        public String getTournament() { return tournament; }
        public String getStart() { return start; }
        public String getCountry() { return country; }
        public String getMatch() { return match; }
    }
}
