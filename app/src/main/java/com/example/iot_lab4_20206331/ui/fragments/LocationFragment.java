package com.example.iot_lab4_20206331.ui.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.Location;
import com.example.iot_lab4_20206331.ui.adapter.LocationAdapter;
import com.example.iot_lab4_20206331.data.api.WeatherApiService;
import com.example.iot_lab4_20206331.data.api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationFragment extends Fragment {

    private RecyclerView recyclerView;
    private LocationAdapter locationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        // Setup RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        locationAdapter = new LocationAdapter();
        recyclerView.setAdapter(locationAdapter);

        // Fetch locations from API
        fetchLocations("miraflores lima");

        return rootView;
    }

    private void fetchLocations(String query) {
        WeatherApiService apiService = RetrofitClient.getClient().create(WeatherApiService.class);
        Call<List<Location>> call = apiService.getLocations("ec24b1c6dd8a4d528c1205500250305", query);

        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.body() != null) {
                    locationAdapter.setLocationList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                // Handle error
            }
        });
    }
}
