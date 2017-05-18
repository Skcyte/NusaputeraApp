package com.example.nickolas.nusaputeraapp;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    SessionManager session;
    String noinduk, status;
    int stat;
    JSONObject response;
    TextView nama, nis, alamat, jkelamin, ttl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        noinduk = user.get(SessionManager.KEY_NMRINDUK);
        status = user.get(SessionManager.KEY_STATUS);
        stat = Integer.parseInt(status);

        System.out.println(noinduk+" "+status);
        nama = (TextView)findViewById(R.id.nama);
        nis = (TextView)findViewById(R.id.no_induk);
        alamat = (TextView)findViewById(R.id.alamat);
        jkelamin = (TextView)findViewById(R.id.jkelamin);
        ttl = (TextView)findViewById(R.id.ttl);

        new ProfileAsync().execute(noinduk, status);
    }

    class ProfileAsync extends AsyncTask<String, String, String>{
        ProgressDialog loadingDialog;
        HttpURLConnection conn;
        URL url = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog = ProgressDialog.show(ProfileActivity.this, "Please wait", "Loading...");
            loadingDialog.setCancelable(false);
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                if (Integer.parseInt(params[1])==1 || Integer.parseInt(params[1])==3)
                    url = new URL("http://wkshop142017.esy.es/android/profile_siswa.php");
                else
                    url = new URL("http://wkshop142017.esy.es/android/profile_guru.php");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("noinduk", params[0]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return(result.toString());

                }else{
                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result){
            loadingDialog.dismiss();
            String nm = "";
            String res = "";
            try {
                response = new JSONObject(result);
                res = response.getString("result");
                JSONArray data = response.getJSONArray("data");
                for (int i = 0; i<data.length(); i++){
                    JSONObject indeks = data.getJSONObject(i);
                    if (stat==2)
                        nis.setText(indeks.getString("NIK"));
                    else
                        nis.setText(indeks.getString("nis"));
                    nama.setText(indeks.getString("nama"));
                    alamat.setText(indeks.getString("alamat"));
                    jkelamin.setText(indeks.getString("jKelamin"));
                    ttl.setText(indeks.getString("kotaAsal")+", "+indeks.getString("tglLahir"));
                }
                System.out.println(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (res.equalsIgnoreCase("exception"))
                Toast.makeText(ProfileActivity.this, "Exception", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
