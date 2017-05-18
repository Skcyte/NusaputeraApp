package com.example.nickolas.nusaputeraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NilaiDetailActivity extends AppCompatActivity {
    Bundle extra;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_detail);
        extra = getIntent().getExtras();
        if (extra==null)
            title = null;
        else
            title = extra.getString("mapel");
        setTitle(title);
    }
}
