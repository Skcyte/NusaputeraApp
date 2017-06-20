package com.example.nickolas.nusaputeraapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nickolas on 15-Jun-17.
 */

class RaportAdapter extends RecyclerView.Adapter<RaportAdapter.ViewHolder> {
    private List<Raport> listItems;
    private Context context;

    public RaportAdapter(List<Raport> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_raport, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Raport listItem = listItems.get(position);

        holder.textViewKomponen.setText(listItem.getNamamapel());
        holder.textViewNilai.setText(listItem.getNilai());
        holder.textViewKetrampilan.setText(listItem.getKetrampilan());
        holder.textViewSikap.setText(listItem.getSikap());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewKomponen, textViewNilai, textViewKetrampilan, textViewSikap;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewKomponen = (TextView)itemView.findViewById(R.id.rkomponen);
            textViewNilai = (TextView)itemView.findViewById(R.id.rnilai);
            textViewKetrampilan = (TextView)itemView.findViewById(R.id.rketerampilan);
            textViewSikap = (TextView)itemView.findViewById(R.id.rsikap);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}

