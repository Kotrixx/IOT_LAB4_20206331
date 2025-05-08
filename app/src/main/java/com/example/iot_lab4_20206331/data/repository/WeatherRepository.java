package com.example.iot_lab4_20206331.data.repository;

import com.example.iot_lab4_20206331.data.api.RetrofitClient;
import com.example.iot_lab4_20206331.data.api.WeatherApiService;
import com.example.iot_lab4_20206331.data.model.ForecastResponse;
import com.example.iot_lab4_20206331.data.model.SportsResponse;
import com.example.iot_lab4_20206331.data.model.LocationResponse;

import java.util.List;
import retrofit2.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class WeatherRepository {

    // Interfaz para manejar las respuestas de la API
    public interface OnWeatherDataReceived {
        void onSuccess(Object result);  // Puede ser un ForecastResponse, WeatherResponse o LocationResponse
        void onFailure(Throwable t);
    }

    private WeatherApiService weatherApiService;

    public WeatherRepository() {
        weatherApiService = RetrofitClient.getRetrofitInstance().create(WeatherApiService.class);
    }

    // Método para obtener las locaciones
    public void getLocationData(String apiKey, String locationQuery, OnWeatherDataReceived callback) {
        weatherApiService.searchLocation(apiKey, locationQuery).enqueue(new retrofit2.Callback<List<LocationResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<List<LocationResponse>> call, retrofit2.Response<List<LocationResponse>> response) {
                if (response.isSuccessful()) {
                    List<LocationResponse> locations = response.body();

                    if (locations != null && !locations.isEmpty()) {
                        // Si la lista no está vacía, pasa los datos al callback.onSuccess
                        callback.onSuccess(locations);
                    } else {
                        // Si la lista está vacía, muestra un mensaje adecuado
                        callback.onFailure(new Exception("No se encontraron locaciones para la búsqueda: " + locationQuery));
                    }
                } else {
                    // Si la respuesta no es exitosa, muestra un mensaje con el código de error
                    callback.onFailure(new Exception("Error al obtener locaciones: Código " + response.code()));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<LocationResponse>> call, Throwable t) {
                // Si la llamada falla (por ejemplo, no hay conexión a Internet), muestra un mensaje de error
                callback.onFailure(t);
            }
        });
    }


    public void getSports(String apiKey, String location, OnWeatherDataReceived callback) {
        weatherApiService.getSports(apiKey, location).enqueue(new Callback<SportsResponse>() {
            @Override
            public void onResponse(Call<SportsResponse> call, Response<SportsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Error al obtener eventos deportivos"));
                }
            }

            @Override
            public void onFailure(Call<SportsResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }


    public void getForecast(String apiKey, String query, int days, OnWeatherDataReceived callback) {
        weatherApiService.getForecast(apiKey, query, days).enqueue(new retrofit2.Callback<ForecastResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ForecastResponse> call, retrofit2.Response<ForecastResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Error al obtener el pronóstico"));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ForecastResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
