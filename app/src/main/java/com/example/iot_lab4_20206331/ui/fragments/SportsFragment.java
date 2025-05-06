package com.example.iot_lab4_20206331.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.SportEvent;
import com.example.iot_lab4_20206331.ui.adapter.SportsAdapter;
import com.example.iot_lab4_20206331.data.api.WeatherApiService;
import com.example.iot_lab4_20206331.data.api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SportsAdapter sportsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sports, container, false);

        // Setup RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewSports);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sportsAdapter = new SportsAdapter();
        recyclerView.setAdapter(sportsAdapter);

        // Fetch sports events
        fetchSportsEvents("London"); // Example location

        return rootView;
    }

    private void fetchSportsEvents(String location) {
        WeatherApiService apiService = RetrofitClient.getClient().create(WeatherApiService.class);
        Call<List<SportEvent>> call = apiService.getSportsEvents("ec24b1c6dd8a4d528c1205500250305", location);

        call.enqueue(new Callback<List<SportEvent>>() {
            @Override
            public void onResponse(Call<List<SportEvent>> call, Response<List<SportEvent>> response) {
                if (response.body() != null) {
                    sportsAdapter.setSportsList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SportEvent>> call, Throwable t) {
                // Handle error
            }
        });
    }
}
