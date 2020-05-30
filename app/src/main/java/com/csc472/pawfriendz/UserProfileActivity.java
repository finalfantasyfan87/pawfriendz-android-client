package com.csc472.pawfriendz;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        setContentView(R.layout.userprofile);
        System.out.println("Response from previous page " + response);
    }
}
