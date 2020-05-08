package com.csc472.pawfriendz.service;

import com.csc472.pawfriendz.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceAPI {
    @POST("/register")
    Call<User> registerUser(@Body User user);
}
