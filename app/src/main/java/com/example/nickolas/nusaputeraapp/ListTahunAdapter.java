package com.example.nickolas.nusaputeraapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nickolas on 17-May-17.
 */

class ListTahunAdapter extends RecyclerView.Adapter<ListTahunAdapter.ViewHolder> {
    private List<TahunAjaran> listItems;
    private Context context;

    public ListTahunAdapter(List<TahunAjaran> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.list_tahun_ajaran, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final TahunAjaran listItem = listItems.get(position);

        holder.textViewTahun.setText(listItem.getTahun());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context.getApplicationContext(), SelectSemester.class);
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                detail.putExtra("tahun", listItem.getTahun());
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTahun;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTahun = (TextView)itemView.findViewById(R.id.tv_tahun);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
