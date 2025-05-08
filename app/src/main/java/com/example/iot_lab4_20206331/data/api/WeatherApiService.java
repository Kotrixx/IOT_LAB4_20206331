package com.example.iot_lab4_20206331.data.api;

import com.example.iot_lab4_20206331.data.model.ForecastResponse;
import com.example.iot_lab4_20206331.data.model.LocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface WeatherApiService {

    // Endpoint 1: Búsqueda de locaciones
    @GET("search.json")
    Call<List<LocationResponse>> searchLocation(
            @Query("key") String apiKey,
            @Query("q") String locationQuery
    );

    @GET("sports.json")
    Call<com.example.iot_lab4_20206331.data.model.SportsResponse> getSports(
            @Query("key") String apiKey,
            @Query("q") String location
    );


    // Endpoint 3: Obtener pronóstico
    @GET("forecast.json")
    Call<ForecastResponse> getForecast(
            @Query("key") String apiKey,
            @Query("q") String query,       // Aquí se espera "id:{locationId}"
            @Query("days") int days         // Número de días de pronóstico
    );
}
