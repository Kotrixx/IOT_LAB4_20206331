package com.example.iot_lab4_20206331.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.SportsResponse;
import com.example.iot_lab4_20206331.data.repository.WeatherRepository;
import com.example.iot_lab4_20206331.ui.adapter.SportsAdapter;

import java.util.ArrayList;

public class SportsFragment extends Fragment {

    private EditText inputLocation;
    private Button searchButton;
    private RecyclerView recyclerView;
    private SportsAdapter sportsAdapter;
    private WeatherRepository weatherRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports, container, false);

        inputLocation = view.findViewById(R.id.input_location);
        searchButton = view.findViewById(R.id.btn_search);
        recyclerView = view.findViewById(R.id.recyclerViewSports);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        sportsAdapter = new SportsAdapter(new ArrayList<>());
        recyclerView.setAdapter(sportsAdapter);

        weatherRepository = new WeatherRepository();

        searchButton.setOnClickListener(v -> {
            String location = inputLocation.getText().toString().trim();
            if (!location.isEmpty()) {
                fetchSportsData(location);
            } else {
                Toast.makeText(getContext(), "Ingresa una localidad", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void fetchSportsData(String location) {
        String apiKey = "ec24b1c6dd8a4d528c1205500250305";
        weatherRepository.getSports(apiKey, location, new WeatherRepository.OnWeatherDataReceived() {
            @Override
            public void onSuccess(Object result) {
                SportsResponse response = (SportsResponse) result;
                if (response.getFootball() != null && !response.getFootball().isEmpty()) {
                    sportsAdapter.setEvents(response.getFootball());
                } else {
                    Toast.makeText(getContext(), "No se encontraron partidos de fútbol", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
