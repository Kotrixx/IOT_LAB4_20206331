package com.example.iot_lab4_20206331.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.LocationResponse;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<LocationResponse> locationList;
    private final OnLocationSelectListener listener;

    public interface OnLocationSelectListener {
        void onLocationSelected(LocationResponse location);
    }

    public LocationAdapter(List<LocationResponse> locationList, OnLocationSelectListener listener) {
        this.locationList = locationList;
        this.listener = listener;
    }

    public void setLocationList(List<LocationResponse> newList) {
        this.locationList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        LocationResponse location = locationList.get(position);
        holder.nameText.setText(location.getName());
        holder.regionText.setText(location.getRegion());
        holder.idText.setText(String.valueOf(location.getIdLocation()));

        holder.selectButton.setOnClickListener(v -> listener.onLocationSelected(location));
    }

    @Override
    public int getItemCount() {
        return locationList != null ? locationList.size() : 0;
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, regionText, idText;
        Button selectButton;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.location_name);
            regionText = itemView.findViewById(R.id.location_region);
            idText = itemView.findViewById(R.id.location_id);
            selectButton = itemView.findViewById(R.id.btn_select);
        }
    }
}
