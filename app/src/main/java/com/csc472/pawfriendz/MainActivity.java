package com.csc472.pawfriendz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.csc472.pawfriendz.model.User;
import com.csc472.pawfriendz.service.UserServiceAPI;
import com.csc472.pawfriendz.utils.APIUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String TAG = "PAWFRIENDZ";
    private UserServiceAPI userService;
    private static final int IMAGE = 100;
    Bitmap bitmap;

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
                Intent galleryView = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryView, RESULT_LOAD_IMAGE);


            }

            //===== this activity allows the image to be selected from the gallery and uploaded=====
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                MainActivity.super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == IMAGE && resultCode == RESULT_OK && data != null) {
                    Uri path = data.getData();

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                        profilePic.setImageBitmap(bitmap);
                    } catch (IOException e) {
                 Log.e(TAG,e.getMessage());
                    }
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
            String image = convertToString();
            User someUser = new User(firstName1, lastName1, userEmail1, password1, userName, cellPhone1, faveDog, image);
            //this bypasses entering data in the text fields for quick application testing
            //User someUser = new User("Timothy", "Sutton", "dominiquebecker@yahoo.com", "#0+HWUjkJs", "jamiekirby", "339-760-1250", "Mints", null);
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

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE);
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }
}
