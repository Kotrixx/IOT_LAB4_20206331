package com.example.iot_lab4_20206331.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

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

    private LocationAdapter locationAdapter;
    private List<LocationResponse> locationList;
    private WeatherRepository weatherRepository;
    private EditText locationEditText;
    private Button searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewLocation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        locationList = new ArrayList<>();
        locationAdapter = new LocationAdapter(locationList);
        recyclerView.setAdapter(locationAdapter);

        // 👇 Forzar actualización por si acaso
        recyclerView.getAdapter().notifyDataSetChanged();

        weatherRepository = new WeatherRepository();
        locationEditText = rootView.findViewById(R.id.location_edit_text);
        searchButton = rootView.findViewById(R.id.search_button);

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

        weatherRepository.getLocationData(apiKey, locationQuery, new WeatherRepository.OnWeatherDataReceived() {
            @Override
            public void onSuccess(Object result) {
                List<LocationResponse> locations = (List<LocationResponse>) result;

                // 👇 Log para verificar cuántas locaciones llegan
                Log.d("LocationFragment", "Cantidad de locaciones recibidas: " + locations.size());

                if (locations != null && !locations.isEmpty()) {
                    locationList.clear();
                    locationList.addAll(locations);
                    locationAdapter.setLocationList(locationList);  // 👈 Esto actualiza la vista
                } else {
                    Toast.makeText(getContext(), "No se encontraron locaciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "Error en la consulta: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
