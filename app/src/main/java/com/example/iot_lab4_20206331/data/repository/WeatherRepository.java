package com.example.iot_lab4_20206331.data.repository;

import com.example.iot_lab4_20206331.data.api.RetrofitClient;
import com.example.iot_lab4_20206331.data.api.WeatherApiService;
import com.example.iot_lab4_20206331.data.model.ForecastResponse;
import com.example.iot_lab4_20206331.data.model.LocationResponse;
import com.example.iot_lab4_20206331.data.model.WeatherResponse;

import java.util.List;

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


    // Método para obtener el clima actual
    public void getCurrentWeather(String apiKey, String locationQuery, OnWeatherDataReceived callback) {
        weatherApiService.getCurrentWeather(apiKey, locationQuery).enqueue(new retrofit2.Callback<WeatherResponse>() {
            @Override
            public void onResponse(retrofit2.Call<WeatherResponse> call, retrofit2.Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception("Error al obtener el clima actual"));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<WeatherResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    // Método para obtener el pronóstico
    public void getForecast(String apiKey, int days, OnWeatherDataReceived callback) {
        weatherApiService.getForecast(apiKey, days).enqueue(new retrofit2.Callback<ForecastResponse>() {
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
