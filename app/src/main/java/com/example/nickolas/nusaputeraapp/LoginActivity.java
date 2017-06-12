package com.example.nickolas.nusaputeraapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    EditText user, pass;
    Button login;
    SessionManager session;
    JSONObject response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());

        user = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = user.getText().toString();
                final String password = pass.getText().toString();
                System.out.println(username+password);
                if (username.isEmpty() || password.isEmpty())
                    Toast.makeText(LoginActivity.this, "Username and Password must be filled.", Toast.LENGTH_SHORT).show();
                else
                    new LoginAsync().execute(username,password);
            }
        });
    }

    class LoginAsync extends AsyncTask<String, String, String> {

        private Dialog loadingDialog;
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog = ProgressDialog.show(LoginActivity.this, "Please wait", "Loading...");
            loadingDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL("http://wkshop142017.esy.es/android/login_android.php");
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
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
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
            String res = "";
            String stat = "";
            String id = "";
            String ref = "";
            loadingDialog.dismiss();
            try {
                response = new JSONObject(result);
                res = response.getString("result");
                System.out.println(res);
                JSONArray data = response.getJSONArray("data");
                for (int i = 0; i<data.length(); i++){
                    JSONObject indeks = data.getJSONObject(i);
                    id = indeks.getString("id");
                    stat = indeks.getString("status");
                    ref = indeks.getString("nmrinduk");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(res.equalsIgnoreCase("success")){
                session.createUserLoginSession(id, stat, ref);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else if(res.equalsIgnoreCase("failure")){
                Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
            }
            else if(res.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                Toast.makeText(getApplicationContext(), "There's a problem while connecting", Toast.LENGTH_LONG).show();
            }
            else if(res.equalsIgnoreCase("failed")){
                Toast.makeText(getApplicationContext(), "Data is not set", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

}

