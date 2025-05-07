package com.example.iot_lab4_20206331.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.Sport;

import java.util.List;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder> {

    private List<Sport> sportsList;

    public SportsAdapter(List<Sport> sportsList) {
        this.sportsList = sportsList;
    }

    @Override
    public SportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sport, parent, false);
        return new SportsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SportsViewHolder holder, int position) {
        Sport sport = sportsList.get(position);
        holder.sportName.setText(sport.getName());
    }

    @Override
    public int getItemCount() {
        return sportsList.size();
    }

    public static class SportsViewHolder extends RecyclerView.ViewHolder {
        public TextView sportName;

        public SportsViewHolder(View view) {
            super(view);
            sportName = view.findViewById(R.id.sportName);
        }
    }
}
