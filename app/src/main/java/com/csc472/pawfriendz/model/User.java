package com.csc472.pawfriendz.model;

import android.text.Editable;
import android.widget.CheckBox;

import okhttp3.MultipartBody;
import retrofit2.Response;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String favoriteDog;
    private MultipartBody.Part profilePic;

    public User(String firstName, String lastName, String email, String password, String username, String phoneNumber, String favoriteDog,MultipartBody.Part profilePic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.favoriteDog = favoriteDog;
        this.profilePic = profilePic;
    }

    public User(Response<User> response) {
        this.firstName = response.body().firstName;
        this.lastName = response.body().lastName;
        this.email = response.body().email;
        this.password =response.body().password;
        this.username = response.body().username;
        this.phoneNumber = response.body().phoneNumber;
        this.favoriteDog = response.body().favoriteDog;
        this.profilePic = response.body().profilePic;

    }

    private String userId;

    public User() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFavoriteDog() {
        return favoriteDog;
    }

    public void setFavoriteDog(String favoriteDog) {
        this.favoriteDog = favoriteDog;
    }

    public MultipartBody.Part getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(MultipartBody.Part profilePic) {
        this.profilePic = profilePic;
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", favoriteDog='" + favoriteDog + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
