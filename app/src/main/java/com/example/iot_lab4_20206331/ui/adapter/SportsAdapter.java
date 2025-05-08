package com.example.iot_lab4_20206331.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.Sport;
import com.example.iot_lab4_20206331.data.model.SportsResponse;

import java.util.List;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {

    private List<SportsResponse.Football> events;

    public SportsAdapter(List<SportsResponse.Football> events) {
        this.events = events;
    }

    public void setEvents(List<SportsResponse.Football> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stadium, match, tournament, start, country;

        public ViewHolder(View view) {
            super(view);
            stadium = view.findViewById(R.id.tv_stadium);
            match = view.findViewById(R.id.tv_match);
            tournament = view.findViewById(R.id.tv_tournament);
            start = view.findViewById(R.id.tv_start);
            country = view.findViewById(R.id.tv_country);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sports, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SportsResponse.Football event = events.get(position);
        holder.stadium.setText("Estadio: " + event.getStadium());
        holder.match.setText("Partido: " + event.getMatch());
        holder.tournament.setText("Torneo: " + event.getTournament());
        holder.start.setText("Hora: " + event.getStart());
        holder.country.setText("País: " + event.getCountry());
    }

    @Override
    public int getItemCount() {
        return events != null ? events.size() : 0;
    }
}
