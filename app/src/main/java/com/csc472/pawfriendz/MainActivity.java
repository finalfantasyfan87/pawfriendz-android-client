package com.csc472.pawfriendz;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.csc472.pawfriendz.model.User;
import com.csc472.pawfriendz.service.UserServiceAPI;
import com.csc472.pawfriendz.utils.APIUtils;
import com.google.gson.Gson;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import androidx.appcompat.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "tagMe";
    private UserServiceAPI userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-----Declaring Input Element and Finding in Our Layout View----
        final EditText username = (EditText) findViewById(R.id.editTextusername);
        final EditText firstName = (EditText) findViewById(R.id.editTextFirstName);
        final EditText lastName = (EditText) findViewById(R.id.editTextLastName);
        final EditText userEmail = (EditText) findViewById(R.id.editTextemail);
        final EditText cellPhone = (EditText) findViewById(R.id.editText5cellnumber);
        final EditText password = (EditText) findViewById(R.id.editText6password);
        final EditText favDog = (EditText) findViewById(R.id.editText7FavDog);
        final Button regProfile = (Button) findViewById(R.id.buttonSubmit);
        //lets discuss if we really need this field before the demo?
        final CheckBox ownDog = (CheckBox) findViewById(R.id.checkBoxdog);
        userService = APIUtils.getUserServiceAPI();

        //====Setting On Click Listener For Create Profile Button ===
        regProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                //Here we get the values from the form field, convert to string and send that data to the Post request
                String firstName1 = firstName.getText().toString();
                String lastName1 = lastName.getText().toString();
                String userName = username.getText().toString();
                String cellPhone1 = cellPhone.getText().toString();
                String userEmail1 = userEmail.getText().toString();
                String password1 = password.getText().toString();
                String faveDog = favDog.getText().toString();

                User pikachu = new User(firstName1, lastName1, userName, cellPhone1, userEmail1, password1, faveDog);
                Log.i(TAG, "post submitted to API." + pikachu.toString());
                userService.registerUser(pikachu).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.i(TAG, String.format("post submitted to API.  %s \n", response.body()));

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e(TAG, "Unable to submit post to API." + t.getMessage());
                    }

                });

                //
//                if (username.getText().toString().isEmpty()) {
//                    username.setError("Please Enter A Username");
//                } else if (password.getText().toString().isEmpty()) {
//                    password.setError("Please Enter A Password");
//                } else if (userEmail.getText().toString().isEmpty()) {
//                    userEmail.setError("Please Enter Email Address");
//                }
                //        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                //====Adding Route for Profile ===
                //  else {
                //         Toast.makeText(MainActivity.this, username.getText().toString(), Toast.LENGTH_SHORT).show();
                //@GetMapping
                //@ResponseBody
                //public String loginMsg(){
                //return "Welcome!"}
            }

        });
    }

}
