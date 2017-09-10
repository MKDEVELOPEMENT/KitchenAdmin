package com.example.muaaz.kitchenadmin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {

    LinearLayout mOrdersLL;
    LinearLayout mMenuLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mOrdersLL = (LinearLayout) findViewById(R.id.home_view_orders);
        mMenuLL = (LinearLayout) findViewById(R.id.home_view_menu);

        mMenuLL.setOnClickListener(goToActivity(HomeActivity.this, MenuActivity.class));
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
