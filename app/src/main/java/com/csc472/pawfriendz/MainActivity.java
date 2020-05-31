package com.csc472.pawfriendz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.csc472.pawfriendz.model.User;
import com.csc472.pawfriendz.service.UserServiceAPI;
import com.csc472.pawfriendz.utils.APIUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import androidx.appcompat.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String TAG = "PAWFRIENDZ";
    private UserServiceAPI userService;
    User myUser;
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
        final Button buttonHome = (Button) findViewById(R.id.buttonHome);
        final Button buttonLogout = (Button) findViewById(R.id.buttonLogout);
        final Button profilePicUpload = (Button) findViewById(R.id.profilePicture);
        final Button editProfile = (Button) findViewById(R.id.editProfile);
        final ImageView profilePic = (ImageView) findViewById(R.id.profilePic);
        final TextView userBio = (TextView) findViewById(R.id.userBio);
        final ScrollView previousPost = (ScrollView) findViewById(R.id.previousPost);
        //lets discuss if we really need this field before the demo?
        final CheckBox ownDog = (CheckBox) findViewById(R.id.checkBoxdog);
        userService = APIUtils.getUserServiceAPI();
        //editProfile.setOnClickListener(new View.OnClickListener()
        //buttonHome.setOnClickListener(new View.OnClickListener()
        //buttonLogout.setOnClickListener(new View.OnClickListener()
        //==========setting onclick listener for profile upload====
        profilePicUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryView = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryView, RESULT_LOAD_IMAGE);
            }

            //===== this activity allows the image to be selected from the gallery and uploaded=====
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                MainActivity.super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
                    Uri selectedImage = data.getData();
                    profilePic.setImageURI(selectedImage);
                }

            }
        });


        //====Setting On Click Listener For Create Profile Button ===
        regProfile.setOnClickListener(v -> {
//will add this value to the constructor
           // ownDog.isChecked();
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

           // User someUser = new User(firstName1, lastName1, userEmail1, password1, userName, cellPhone1, faveDog, null);
           //this bypasses entering data in the text fields for quick application testing
            User someUser = new User("Melissa", "Mitchell", "zacharycox@yahoo.com", "TF5!7Ea@$1", "rogerskatherine", "653-261-4365", "Mints", null);
            userService.registerUser(someUser).enqueue(new Callback<User>() {
                @Override
                public void onResponse(retrofit2.Call<User> call, Response<User> response) {
                    Log.i(TAG, String.format("post submitted to API.  %s \n", response));
                    openUserProfile(response.body());
                }

                @Override
                public void onFailure(retrofit2.Call<User> call, Throwable t) {
                    Log.e(TAG, String.format("Unable to submit post to API.%s", t.getMessage()));
                }

            });

        });
    }

    //===== Function calls the user profile page ====
    public void openUserProfile(User response) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("response", String.valueOf(response));
        startActivity(intent);



    }

}
