package com.csc472.pawfriendz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.csc472.pawfriendz.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfileActivity extends AppCompatActivity {

    TextView userBio;
    private static final String TAG = "PAWFRIENDZ";
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        TextView userName = findViewById(R.id.username2);
        TextView userBio = findViewById(R.id.userBio);
        ImageView profilePic = findViewById(R.id.profilePic);
        Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        Gson gson = new Gson();
        System.out.println("Response from previous page " + response);
        try {
            final JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
            User userFromModel = gson.fromJson(String.valueOf(jsonObject), User.class);
            System.out.println("User EMAIL " + userFromModel.getEmail());
           userName.setText(userFromModel.getUsername());
          //  profilePic.setImageURI(userFromModel.getProfilePic());
            String bio = "Hi I am "  +userFromModel.getFirstName() + ", " +userFromModel.getLastName() + ". My dog's name is " +userFromModel.getFavoriteDog();
            userBio.setText(bio);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }




    }
}
