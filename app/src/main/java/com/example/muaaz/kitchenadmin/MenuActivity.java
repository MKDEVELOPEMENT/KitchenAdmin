package com.example.muaaz.kitchenadmin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    LinearLayout mSweetLL;
    LinearLayout mSavouryLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mSweetLL = (LinearLayout) findViewById(R.id.mid_ll);
        mSavouryLL = (LinearLayout) findViewById(R.id.bot_ll);

        mSweetLL.setOnClickListener(goToActivity(MenuActivity.this, SweetMenuActivity.class));
    }

    public View.OnClickListener goToActivity(Context thisAct, Class goToActivity){
        final Intent intent = new Intent(thisAct, goToActivity);
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        };
    }
}
