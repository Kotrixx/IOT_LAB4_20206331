package com.example.iot_lab4_20206331.data.api;

import com.example.iot_lab4_20206331.data.model.ForecastResponse;
import com.example.iot_lab4_20206331.data.model.LocationResponse;
import com.example.iot_lab4_20206331.data.model.WeatherResponse;

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

    // Endpoint 2: Obtener clima actual
    @GET("current.json")
    Call<WeatherResponse> getCurrentWeather(
            @Query("key") String apiKey,
            @Query("q") String locationQuery
    );

    // Endpoint 3: Obtener pronóstico
    @GET("forecast.json")
    Call<ForecastResponse> getForecast(
            @Query("key") String apiKey,      // Clave de la API
            // Ubicación para el pronóstico
            @Query("days") int days          // Número de días del pronóstico
    );
}
