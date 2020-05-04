package com.csc472.pawfriendz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import androidx.appcompat.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-----Declaring Input Element and Finding in Our Layout View----
        final EditText username = (EditText)findViewById(R.id.editTextusername);
        final EditText userEmail = (EditText)findViewById(R.id.editTextemail);
        final EditText cellPhone = (EditText)findViewById(R.id.editText5cellnumber);
        final EditText password = (EditText)findViewById(R.id.editText6password);
        final EditText favDog = (EditText)findViewById(R.id.editText7FavDog);
        final Button regProfile = (Button)findViewById(R.id.buttonSubmit);
        final CheckBox ownDog = (CheckBox)findViewById(R.id.checkBoxdog);

        //====Setting On Click Listener For Create Profile Button ===
        regProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().toString().isEmpty()) {
                    username.setError("Please Enter A Username");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Please Enter A Password");
                } else if (userEmail.getText().toString().isEmpty()) {
                    userEmail.setError("Please Enter Email Address");

                }
                //====Adding Route for Profile ===
                else {
                    Toast.makeText(MainActivity.this, username.getText().toString(), Toast.LENGTH_SHORT).show();
                    //@GetMapping
                    //@ResponseBody
                    //public String loginMsg(){
                    //return "Welcome!"}
                }
            }
        });
    }
}
