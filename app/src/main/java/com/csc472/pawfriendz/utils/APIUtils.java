package com.csc472.pawfriendz.utils;

import com.csc472.pawfriendz.retroclient.RetrofitClient;
import com.csc472.pawfriendz.service.UserServiceAPI;

public class APIUtils {
    private APIUtils() {
    }

    //retrofit 2 doesn't support secure ssl port by default, so that means more configuration classes
//To run get implement the spring boot app locally, you must use the IPV4 of your computer...localhost doesn't
    //work. Of course when we deploy the app, we will use that base URL.
    public static final String BASE_URL = "http://10.0.0.112:8080/";

    public static UserServiceAPI getUserServiceAPI() {

        return RetrofitClient.getClient(BASE_URL).create(UserServiceAPI.class);
    }

    public static UserServiceAPI getAllUsers(){
        return RetrofitClient.getClient(BASE_URL).create(UserServiceAPI.class);
    }
}
