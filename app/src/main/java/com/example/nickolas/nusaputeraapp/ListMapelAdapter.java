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
 * Created by Nickolas on 17-May-17.
 */

class ListMapelAdapter extends RecyclerView.Adapter<ListMapelAdapter.ViewHolder> {
    private List<Mapel> listItems;
    private Context context;

    public ListMapelAdapter(List<Mapel> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.list_mapel, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Mapel listItem = listItems.get(position);

        holder.textViewMapel.setText(listItem.getNamaMapel());
        holder.textViewKode.setText(listItem.getKdmapel());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context.getApplicationContext(), NilaiDetailActivity.class);
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                detail.putExtra("mapel", listItem.getNamaMapel());
                detail.putExtra("kdMapel", listItem.getKdmapel());
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMapel, textViewKode;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewMapel = (TextView)itemView.findViewById(R.id.nm_mapel);
            textViewKode = (TextView)itemView.findViewById(R.id.kode_mapel);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
