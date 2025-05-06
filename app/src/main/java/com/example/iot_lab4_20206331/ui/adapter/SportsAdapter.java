package com.example.iot_lab4_20206331.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20206331.R;
import com.example.iot_lab4_20206331.data.model.SportEvent;

import java.util.List;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder> {

    private List<SportEvent> sportEventList;

    @Override
    public SportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport, parent, false);
        return new SportsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SportsViewHolder holder, int position) {
        SportEvent sportEvent = sportEventList.get(position);
        holder.sportEventName.setText(sportEvent.getName());
    }

    @Override
    public int getItemCount() {
        return sportEventList != null ? sportEventList.size() : 0;
    }

    public void setSportEventList(List<SportEvent> sportEventList) {
        this.sportEventList = sportEventList;
        notifyDataSetChanged();
    }

    public static class SportsViewHolder extends RecyclerView.ViewHolder {
        TextView sportEventName;

        public SportsViewHolder(View itemView) {
            super(itemView);
            sportEventName = itemView.findViewById(R.id.sport_event_name);
        }
    }
}
