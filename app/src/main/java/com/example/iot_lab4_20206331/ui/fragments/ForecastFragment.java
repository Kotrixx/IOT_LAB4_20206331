package com.example.iot_lab4_20206331.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.ForecastResponse;
import com.example.iot_lab4_20206331.data.model.Forecastday;
import com.example.iot_lab4_20206331.ui.adapter.ForecastAdapter;
import com.example.iot_lab4_20206331.data.repository.WeatherRepository;

import java.util.ArrayList;
import java.util.List;

public class ForecastFragment extends Fragment {

    private RecyclerView recyclerView;
    private ForecastAdapter forecastAdapter;
    private List<Forecastday> forecastList;
    private WeatherRepository weatherRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);

        // Configura el RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewForecast);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializa la lista de pronósticos
        forecastList = new ArrayList<>();

        // Configura el Adapter
        forecastAdapter = new ForecastAdapter(forecastList);
        recyclerView.setAdapter(forecastAdapter);

        // Inicializa el WeatherRepository
        weatherRepository = new WeatherRepository();

        // Llama a la API para obtener los pronósticos
        fetchForecastData("Buenos Aires", 7);  // Ejemplo de ubicación y días

        return rootView;
    }

    // Método para obtener los datos de pronóstico desde la API usando el repositorio
    private void fetchForecastData(String location, int days) {
        String apiKey = "ec24b1c6dd8a4d528c1205500250305";  // Tu clave de API

        weatherRepository.getForecast(apiKey, days, new WeatherRepository.OnWeatherDataReceived() {
            @Override
            public void onSuccess(Object result) {
                ForecastResponse forecastResponse = (ForecastResponse) result;
                if (forecastResponse != null && forecastResponse.getForecast().getForecastday().size() > 0) {
                    forecastList.clear();
                    forecastList.addAll(forecastResponse.getForecast().getForecastday());
                    forecastAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "No se encontraron datos de pronóstico", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "Error al obtener pronóstico: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
