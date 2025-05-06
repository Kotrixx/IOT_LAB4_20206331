package com.example.iot_lab4_20206331.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.weatherapi.com/") // Base URL de la API
                    .addConverterFactory(GsonConverterFactory.create()) // Convierte la respuesta JSON en objetos Java
                    .build();
        }
        return retrofit;
    }
}
