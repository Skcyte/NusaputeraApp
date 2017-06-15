package com.example.nickolas.nusaputeraapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
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

public class RaportActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Raport> raport_list;
    private SessionManager session;
    private String noinduk;
    public static String ajaran;
    private Bundle extra;
    private final String url_data = "http://wkshop142017.esy.es/android/requestRaport.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        noinduk = user.get(SessionManager.KEY_NMRINDUK);
        setTitle("Raport");

        extra = getIntent().getExtras();
        if (extra==null)
            ajaran = null;
        else
            ajaran = extra.getString("tahun");

        recyclerView = (RecyclerView)findViewById(R.id.rec_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        raport_list = new ArrayList<>();
        loadDataraport();
    }

    private void loadDataraport() {
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
                                Raport item = new Raport(
                                        x.getString("namamapel"),
                                        x.getString("kdmapel"),
                                        x.getString("raport")
                                );
                                raport_list.add(item);
                            }

                            adapter = new RaportAdapter(raport_list, getApplicationContext());
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
                params.put("ajaran", ajaran);

                return params;
            }};

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
