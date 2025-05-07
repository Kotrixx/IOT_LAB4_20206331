package com.example.iot_lab4_20206331.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
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
    private TextView emptyView;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewLocation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        locationList = new ArrayList<>();
        locationAdapter = new LocationAdapter(locationList);
        recyclerView.setAdapter(locationAdapter);

        // 👇 Forzar actualización por si acaso
        recyclerView.getAdapter().notifyDataSetChanged();

        weatherRepository = new WeatherRepository();
        locationEditText = rootView.findViewById(R.id.location_edit_text);
        searchButton = rootView.findViewById(R.id.search_button);
        emptyView = rootView.findViewById(R.id.empty_view); // Asegúrate de que esté en el layout

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

                locationList.clear();

                if (locations != null && !locations.isEmpty()) {
                    locationList.addAll(locations);
                    locationAdapter.setLocationList(locationList);

                    recyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                } else {
                    // No se encontraron datos
                    recyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "Error en la consulta: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                // También puedes mostrar el mensaje vacío si falla
                recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            }
        });
    }


}
