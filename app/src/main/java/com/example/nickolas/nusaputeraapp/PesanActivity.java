package com.example.nickolas.nusaputeraapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PesanActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Pesan> pesan_list;
    private Bundle extra;
    private String url_data, noinduk;
    private int status;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String stat = user.get(SessionManager.KEY_STATUS);
        noinduk = user.get(SessionManager.KEY_NMRINDUK);
        status = Integer.parseInt(stat);

        if(status==1 || status==2)
            url_data = "http://wkshop142017.esy.es/android/showPesanSiswa.php";
        else if(status==3)
            url_data = "http://wkshop142017.esy.es/android/showPesanKaryawan.php";


        System.out.println(noinduk);
        recyclerView = (RecyclerView)findViewById(R.id.rec_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pesan_list = new ArrayList<>();
        loadDataPesan();
    }

    private void loadDataPesan() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray data = jsonObject.getJSONArray("data");
                            System.out.println(data);
                            for (int i = 0; i<data.length(); i++){
                                JSONObject x = data.getJSONObject(i);
                                Pesan item = new Pesan(
                                        x.getString("No"),
                                        x.getString("judul"),
                                        x.getString("isipesan"),
                                        x.getString("created_at"),
                                        x.getString("status")
                                );
                                pesan_list.add(item);
                            }

                            adapter = new PesanAdapter(pesan_list, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("noinduk", noinduk);

                return params;
            }};

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
}}

