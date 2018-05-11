package dk.osl.intelligentbil.testretro;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Oliver on 11-05-2018.
 */

public class DataObject {
    @SerializedName("userId")
    int userID;
    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("body")
    String body;

    public DataObject(int userID, int id, String title, String body) {
        this.userID = userID;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
