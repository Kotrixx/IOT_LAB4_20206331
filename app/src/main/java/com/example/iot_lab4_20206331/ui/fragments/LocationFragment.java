package com.example.iot_lab4_20206331.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.LocationResponse;
import com.example.iot_lab4_20206331.ui.adapter.LocationAdapter;
import com.example.iot_lab4_20206331.data.repository.WeatherRepository;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment {

    private EditText locationEditText;
    private Button searchButton;
    private TextView emptyView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ViewFlipper resultsContainer;

    private LocationAdapter locationAdapter;
    private List<LocationResponse> locationList;
    private WeatherRepository weatherRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        // Referencias de vistas
        locationEditText = rootView.findViewById(R.id.input_location);
        searchButton = rootView.findViewById(R.id.btn_search);
        emptyView = rootView.findViewById(R.id.empty_view);
        recyclerView = rootView.findViewById(R.id.recyclerViewLocation);
        progressBar = rootView.findViewById(R.id.progress_bar);
        resultsContainer = rootView.findViewById(R.id.results_container);

        // Configuración de RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        locationList = new ArrayList<>();
        locationAdapter = new LocationAdapter(locationList);
        recyclerView.setAdapter(locationAdapter);

        // Inicialización del repositorio
        weatherRepository = new WeatherRepository();

        // Listener del botón de búsqueda
        searchButton.setOnClickListener(v -> {
            String locationQuery = locationEditText.getText().toString().trim();

            if (!locationQuery.isEmpty()) {
                fetchLocationData(locationQuery);
            } else {
                Toast.makeText(getContext(), "Por favor ingrese una locación", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private void fetchLocationData(String locationQuery) {
        String apiKey = "ec24b1c6dd8a4d528c1205500250305";

        // Mostrar el estado de carga
        resultsContainer.setDisplayedChild(0);
        progressBar.setVisibility(View.VISIBLE);

        weatherRepository.getLocationData(apiKey, locationQuery, new WeatherRepository.OnWeatherDataReceived() {
            @Override
            public void onSuccess(Object result) {
                progressBar.setVisibility(View.GONE);

                List<LocationResponse> locations = (List<LocationResponse>) result;
                locationList.clear();

                if (locations != null && !locations.isEmpty()) {
                    locationList.addAll(locations);
                    locationAdapter.setLocationList(locationList);
                    resultsContainer.setDisplayedChild(1); // Mostrar resultados
                } else {
                    resultsContainer.setDisplayedChild(2); // Mostrar vista vacía
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error en la consulta: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                resultsContainer.setDisplayedChild(2); // Mostrar vista vacía
            }
        });
    }
}
