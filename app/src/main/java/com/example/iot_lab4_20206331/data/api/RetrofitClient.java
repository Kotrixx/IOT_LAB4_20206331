package com.example.iot_lab4_20206331.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.weatherapi.com/v1/";  // URL base de la API

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())  // Usamos Gson para convertir el JSON
                    .build();
        }
        return retrofit;
    }

    // Método para obtener la instancia del servicio API
    public static WeatherApiService getWeatherApiService() {
        return getRetrofitInstance().create(WeatherApiService.class);
    }
}
