package com.example.nickolas.nusaputeraapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    SessionManager session;
    Button profile, nilai, pengumuman, logout, absen;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Main Menu");
        session = new SessionManager(getApplicationContext());
        if(session.checkLogin())
            finish();
        HashMap<String, String> user = session.getUserDetails();
        status = user.get(SessionManager.KEY_STATUS);
        if(status==null)
            status="1";
        checkStatus(Integer.parseInt(status));

        logout = (Button)findViewById(R.id.btn_logout);
        nilai = (Button)findViewById(R.id.btn_nilai);
        profile = (Button)findViewById(R.id.btn_profile);
        pengumuman = (Button)findViewById(R.id.btn_pengumuman);
        absen = (Button)findViewById(R.id.btn_absensi);

        //setContentView(R.layout.activity_main);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prof = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(prof);
            }
        });

        if(Integer.parseInt(status)==2){

        }
        else {
            nilai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent anilai = new Intent(MainActivity.this, ListNilaiActivity.class);
                    startActivity(anilai);
                }
            });
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Confirm Logout");
                alertDialog.setMessage("Are you sure you want to logout?");
                alertDialog.setIcon(R.drawable.exit);

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        session.logoutUser();
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDialog.show();
            }
        });
    }

    public void checkStatus(int status){
        switch (status){
            case 1:
                setContentView(R.layout.activity_main);
                break;
            case 2:
                setContentView(R.layout.activity_main_teacher);
                break;
            case 3:
                setContentView(R.layout.activity_main_parent);
                break;
        }
    }
}
