package com.example.muaaz.kitchenadmin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.muaaz.kitchenadmin.Classes.User;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    EditText mEmailET;
    EditText mPasswordET;
    Button mLoginButton;

    TextView mNoAccountTv;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        final SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);

        /*String abColor = sharedPreferences.getString("action_bar_color", String.valueOf(R.color.colorPrimary));
        String statColor = sharedPreferences.getString("status_bar_color", String.valueOf(R.color.colorPrimaryDark));

        ActionBar ab = getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor(abColor)));

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(statColor)); */

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        mEmailET = (EditText) findViewById(R.id.login_email_et);
        mPasswordET = (EditText) findViewById(R.id.login_password_et);
        mLoginButton = (Button) findViewById(R.id.login_login_button);
        mNoAccountTv = (TextView) findViewById(R.id.login_no_account_tv);

        Intent getEmail = getIntent();
        String userEmail = getEmail.getStringExtra("userEmail");
        mEmailET.setText(userEmail);

        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putInt("2", 2);
        //editor.commit();
        //String sharedTest = String.valueOf(sharedPref.getInt("2", 3));-
        //mEmailET.setText(sharedTest);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String thisEmail = mEmailET.getText().toString();
                final String thisPassword = mPasswordET.getText().toString();

                final String mail = thisEmail.replaceAll("[.]", "%");

                DatabaseReference ref = database.getReference("AdminUsers/" + mail);

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User attemptedLoginUser;
                        String attempteduserActualPassword;
                        try {
                            attemptedLoginUser = dataSnapshot.getValue(User.class);
                            attempteduserActualPassword = attemptedLoginUser.password;
                            if(thisPassword.equals(attempteduserActualPassword)){
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("LOGGED", "IN");
                                editor.putString("USER_EMAIL", attemptedLoginUser.email);
                                editor.putString("USER_NAME", attemptedLoginUser.name);
                                editor.putString("USER_SURNAME", attemptedLoginUser.surname);
                                editor.putLong("USER_PHONE", attemptedLoginUser.phoneNo);
                                editor.apply();
                            }else{
                                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                                alertDialog.setTitle("Error");
                                alertDialog.setMessage("Incorrect Password");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Dismiss",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }
                        }catch (NullPointerException e){
                            Log.w("login", e);
                            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                            alertDialog.setTitle("Error");
                            alertDialog.setMessage("No account exists with that email");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Dismiss",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
