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
 * Created by Nickolas on 21-May-17.
 */

public class NilaiDetailAdapter extends RecyclerView.Adapter<NilaiDetailAdapter.ViewHolder> {
    private List<Nilai> listItems;
    private Context context;

    public NilaiDetailAdapter(List<Nilai> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public NilaiDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_nilai, parent, false);
        return new NilaiDetailAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NilaiDetailAdapter.ViewHolder holder, int position) {
        final Nilai listItem = listItems.get(position);

        holder.textViewKomponen.setText(listItem.getKomponen());
        holder.textViewNilai.setText(listItem.getNilai());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewKomponen, textViewNilai;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewKomponen = (TextView) itemView.findViewById(R.id.textView_Komponen);
            textViewNilai = (TextView)itemView.findViewById(R.id.textView_Nilai);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}