package com.example.nickolas.nusaputeraapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectSemester extends AppCompatActivity {
    Button sem1, sem2;
    String tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_semester);

        sem1 = (Button)findViewById(R.id.semester1);
        sem2 = (Button)findViewById(R.id.semester2);

        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tahun = ListMapelActivity.vartahun+"0";
                Intent go = new Intent(SelectSemester.this, RaportActivity.class);
                go.putExtra("tahun", tahun);
                startActivity(go);
            }
        });
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tahun = ListMapelActivity.vartahun+"1";
                Intent go = new Intent(SelectSemester.this, RaportActivity.class);
                go.putExtra("tahun", tahun);
                startActivity(go);
            }
        });
    }
}
