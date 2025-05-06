package com.example.iot_lab4_20206331.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.Forecast;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private List<Forecast> forecastList;

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        Forecast forecast = forecastList.get(position);
        holder.forecastDetails.setText(forecast.getDetails());
    }

    @Override
    public int getItemCount() {
        return forecastList != null ? forecastList.size() : 0;
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
        notifyDataSetChanged();
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView forecastDetails;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            forecastDetails = itemView.findViewById(R.id.forecast_details);
        }
    }
}
