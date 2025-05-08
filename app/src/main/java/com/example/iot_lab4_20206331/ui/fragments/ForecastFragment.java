package com.example.iot_lab4_20206331.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.ForecastResponse;
import com.example.iot_lab4_20206331.data.model.Forecastday;
import com.example.iot_lab4_20206331.data.repository.WeatherRepository;
import com.example.iot_lab4_20206331.data.sensor.AccelerometerManager;
import com.example.iot_lab4_20206331.ui.adapter.ForecastAdapter;

import java.util.ArrayList;
import java.util.List;

public class ForecastFragment extends Fragment {

    private RecyclerView recyclerView;
    private ForecastAdapter forecastAdapter;
    private List<Forecastday> forecastList;

    private WeatherRepository weatherRepository;

    private EditText idLocationInput;
    private EditText daysInput;
    private Button searchButton;
    private LinearLayout emptyContainer;

    private AccelerometerManager accelerometerManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewForecast);
        idLocationInput = rootView.findViewById(R.id.id_location_input);
        daysInput = rootView.findViewById(R.id.days_input);
        searchButton = rootView.findViewById(R.id.forecast_search_button);
        emptyContainer = rootView.findViewById(R.id.empty_container);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        forecastList = new ArrayList<>();
        forecastAdapter = new ForecastAdapter(forecastList);
        recyclerView.setAdapter(forecastAdapter);

        weatherRepository = new WeatherRepository();
        accelerometerManager = new AccelerometerManager(requireContext(), this::showConfirmationDialog);

        // ⚠️ Si se recibe un ID desde LocationFragment, llenarlo automáticamente
        Bundle args = getArguments();
        if (args != null && args.containsKey("location_id")) {
            String locationId = args.getString("location_id");
            idLocationInput.setText(locationId);
            daysInput.setText("14"); // default
            fetchForecastData(locationId, 14); // auto-consulta
        }

        searchButton.setOnClickListener(v -> {
            String idText = idLocationInput.getText().toString().trim();
            String daysText = daysInput.getText().toString().trim();

            if (idText.isEmpty() || daysText.isEmpty()) {
                Toast.makeText(getContext(), "Por favor completa ambos campos", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int locationId = Integer.parseInt(idText);
                int days = Integer.parseInt(daysText);
                fetchForecastData(String.valueOf(locationId), days);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "IDs y días deben ser números válidos", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        accelerometerManager.register();
    }

    @Override
    public void onPause() {
        super.onPause();
        accelerometerManager.unregister();
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("¿Eliminar pronósticos?")
                .setMessage("¿Deseas eliminar los últimos pronósticos mostrados?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    forecastList.clear();
                    forecastAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Pronósticos eliminados", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void fetchForecastData(String locationId, int days) {
        String apiKey = "ec24b1c6dd8a4d528c1205500250305";
        String query = "id:" + locationId;

        weatherRepository.getForecast(apiKey, query, days, new WeatherRepository.OnWeatherDataReceived() {
            @Override
            public void onSuccess(Object result) {
                ForecastResponse forecastResponse = (ForecastResponse) result;
                forecastList.clear();

                if (forecastResponse != null &&
                        forecastResponse.getForecast() != null &&
                        forecastResponse.getForecast().getForecastday() != null &&
                        !forecastResponse.getForecast().getForecastday().isEmpty()) {

                    forecastList.addAll(forecastResponse.getForecast().getForecastday());
                    forecastAdapter.notifyDataSetChanged();

                    recyclerView.setVisibility(View.VISIBLE);
                    emptyContainer.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    emptyContainer.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                recyclerView.setVisibility(View.GONE);
                emptyContainer.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Error al obtener pronóstico: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
