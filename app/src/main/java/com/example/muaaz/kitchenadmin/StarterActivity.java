package com.example.muaaz.kitchenadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StarterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        String logged = prefs.getString("LOGGED", "OUT");
        if (logged.equals("IN")){
            Intent goToHome = new Intent(StarterActivity.this, HomeActivity.class);
            startActivity(goToHome);
        }else{
            Intent goToLogin = new Intent(StarterActivity.this, LoginActivity.class);
            startActivity(goToLogin);
        }

    }
}
