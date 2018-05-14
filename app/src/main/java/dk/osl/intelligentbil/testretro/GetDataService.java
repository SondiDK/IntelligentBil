package dk.osl.intelligentbil.testretro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Oliver on 11-05-2018.
 */

public interface GetDataService {


    @POST("/users/login")
    @FormUrlEncoded
    Call<User> savePost(@Field("name") String username,
                        @Field("password") String password);

    @GET("/posts")
    Call<List<DataObject>> getAllPosts();


    @GET("/trip/{userId}")
    Call<List<Drive>> getAllDrives(@Path("userId") String userId);
}
