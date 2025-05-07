package com.example.iot_lab4_20206331.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.Forecastday;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private List<Forecastday> forecastList;

    public ForecastAdapter(List<Forecastday> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        Forecastday forecast = forecastList.get(position);
        holder.date.setText(forecast.getDate());
        holder.maxTemp.setText(String.valueOf(forecast.getDay().getMaxtempC()) + "°C");
        holder.minTemp.setText(String.valueOf(forecast.getDay().getMintempC()) + "°C");
        holder.condition.setText(forecast.getDay().getCondition().getText());

        // Aquí puedes cargar la imagen del ícono usando una librería como Glide o Picasso
        String iconUrl = "https:" + forecast.getDay().getCondition().getIcon();
        // Glide.with(holder.itemView.getContext()).load(iconUrl).into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView date, maxTemp, minTemp, condition;
        ImageView weatherIcon;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            maxTemp = itemView.findViewById(R.id.maxTemp);
            minTemp = itemView.findViewById(R.id.minTemp);
            condition = itemView.findViewById(R.id.condition);
            weatherIcon = itemView.findViewById(R.id.weatherIcon);
        }
    }
}
