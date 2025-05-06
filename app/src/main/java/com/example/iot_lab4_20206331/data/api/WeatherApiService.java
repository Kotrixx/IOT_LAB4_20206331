package com.example.iot_lab4_20206331.data.api;

import com.example.iot_lab4_20206331.data.model.Location;
import com.example.iot_lab4_20206331.data.model.Forecast;
import com.example.iot_lab4_20206331.data.model.SportEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    // Método para obtener locaciones
    @GET("v1/search.json")
    Call<List<Location>> getLocations(@Query("key") String apiKey, @Query("q") String location);

    // Método para obtener pronósticos
    @GET("v1/forecast.json")
    Call<Forecast> getForecast(@Query("key") String apiKey, @Query("q") String locationId, @Query("days") int days);

    // Método para obtener eventos deportivos
    @GET("v1/sports.json")
    Call<List<SportEvent>> getSportsEvents(@Query("key") String apiKey, @Query("q") String location);
}
