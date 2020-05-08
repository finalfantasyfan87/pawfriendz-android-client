package com.csc472.pawfriendz.model;

import android.text.Editable;
import android.widget.CheckBox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("firstName")
    @Expose
   private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("phoneNumber")
    @Expose
    private  String cellPhone;

    @SerializedName("email")
    @Expose
    private String userEmail;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("favoriteDog")
    @Expose
    private String favDog;
//
//    @SerializedName("ownDog")
//    @Expose
//    private  Boolean ownDog;

    public User(String firstName, String lastName, String username,String cellPhone, String userEmail, String password, String favDog) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.cellPhone = cellPhone;
        this.userEmail = userEmail;
        this.password = password;
        this.favDog = favDog;
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

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
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

        public String getFavDog() {
        return favDog;
    }

    public void setFavDog(String favDog) {
        this.favDog = favDog;
    }

//    public Boolean getOwnDog() {
//        return ownDog;
//    }
//
//    public void setOwnDog(Boolean ownDog) {
//        this.ownDog = ownDog;
//    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", favDog='" + favDog + '\'' +
                '}';
    }
}
