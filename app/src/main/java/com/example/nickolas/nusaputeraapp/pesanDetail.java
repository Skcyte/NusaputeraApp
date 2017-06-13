package com.example.nickolas.nusaputeraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class pesanDetail extends AppCompatActivity {
    Bundle extra;
    String jdul, psan;
    TextView judul, pesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_detail);

        judul = (TextView)findViewById(R.id.textViewJudul);
        pesan = (TextView)findViewById(R.id.textViewPesan);

        extra = getIntent().getExtras();
        if (extra==null) {
            jdul = null;
            pesan = null;
        }
        else{
            jdul = extra.getString("judul");
            psan = extra.getString("pesan");
        }

        judul.setText(jdul);
        pesan.setText(psan);
    }
}
