package com.journaldev.loginwithfbexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.login.LoginManager;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(MainActivity3.this,MainActivity.class);
        startActivity(intent);


    }
}