package com.example.iot_lab4_20206331.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.LocationResponse;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<LocationResponse> locationList;

    // Constructor que recibe la lista de locaciones
    public LocationAdapter(List<LocationResponse> locationList) {
        this.locationList = locationList;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        LocationResponse location = locationList.get(position);
        holder.locationName.setText(location.getName());
        holder.locationRegion.setText(location.getRegion()); // Usa los atributos correctos de tu modelo
    }

    @Override
    public int getItemCount() {
        return locationList.size(); // Devuelve el tamaño de la lista
    }

    // Método para actualizar los datos en el Adapter
    public void setLocationList(List<LocationResponse> locationList) {
        this.locationList = locationList;
        notifyDataSetChanged(); // Notifica al RecyclerView que los datos han cambiado
    }

    // ViewHolder para los items del RecyclerView
    public static class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;
        TextView locationRegion;

        public LocationViewHolder(View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.location_name);
            locationRegion = itemView.findViewById(R.id.location_region);
        }
    }
}
