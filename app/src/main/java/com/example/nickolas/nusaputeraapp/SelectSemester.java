package com.example.nickolas.nusaputeraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectSemester extends AppCompatActivity {
    Button sem1, sem2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_semester);

        sem1 = (Button)findViewById(R.id.semester1);
        sem2 = (Button)findViewById(R.id.semester2);

        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
