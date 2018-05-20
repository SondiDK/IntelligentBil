package dk.osl.intelligentbil.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Oliver on 14-05-2018.
 */

public class User {

    @SerializedName("name")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("userId")
    private String userID;
    @SerializedName("token")
    private String token;

    public User(){};

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
