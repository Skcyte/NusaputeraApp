package com.example.nickolas.nusaputeraapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Edwin Listyo on 5/30/2017.
 */

public class PesanAdapter extends RecyclerView.Adapter<PesanAdapter.ViewHolder> {
private List<Pesan> listItems;
private Context context;

public PesanAdapter(List<Pesan> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_pesan, parent, false);
        return new ViewHolder(v);
        }

@Override
public void onBindViewHolder(final ViewHolder holder, final int position) {
final Pesan listItem = listItems.get(position);
        holder.textViewJudul.setText(listItem.getJudul());
        holder.textViewPesan.setText(listItem.getPesan());
        holder.textViewCreated.setText(listItem.getTgl());
        if(listItem.getStatus().equals("1")){
            holder.linearLayout.setSelected(true);
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(listItems.get(position)!=null) {
                Intent pesanDetail = new Intent(context.getApplicationContext(), pesanDetail.class);
                pesanDetail.putExtra("no", listItem.getNo());
                pesanDetail.putExtra("judul", listItem.getJudul());
                pesanDetail.putExtra("pesan", listItem.getPesan());
                holder.linearLayout.setSelected(true);
                context.startActivity(pesanDetail);
            }
        }
    });
}

@Override
public int getItemCount() {
        return listItems.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewPesan, textViewJudul, textViewCreated;
    public LinearLayout linearLayout;

    public ViewHolder(View itemView) {
        super(itemView);

        textViewJudul = (TextView)itemView.findViewById(R.id.tv_judul);
        textViewPesan = (TextView)itemView.findViewById(R.id.tv_pesan);
        textViewCreated = (TextView)itemView.findViewById(R.id.tv_created);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.linearlayout);
    }
}
}

