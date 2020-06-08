package com.csc472.pawfriendz.service;

import com.csc472.pawfriendz.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserServiceAPI {
    @POST("/register")
    Call<User> registerUser(@Body User user);

    @GET("/users")
    Call<List<User>> getAllUsers();
}
