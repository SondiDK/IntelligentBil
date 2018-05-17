package dk.osl.intelligentbil.testretro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Oliver on 11-05-2018.
 */

public interface GetDataService {


    @POST("/users/login")
    @FormUrlEncoded
    Call<User> login(@Field("name") String username,
                     @Field("password") String password);


    @GET("/trip/{userId}")
    Call<List<Trip>> getAllDrives(@Path("userId") String userId);

    @POST("/trip/createtrip")
    @FormUrlEncoded
    Call<Trip>saveTrip(@Field("date") String date,
                       @Field("effect") Double powerAverage,
                       @Field("length") int distance,
                       @Field("name") String name,
                       @Field("userid") String userid,
                       @Field("time_length") int duration,
                       @Header("Authorization") String authHeader);
    /*
    @POST("/trip/createtrip")
      Call<Trip>saveTrip(@Body Trip d,
                        @Part("userid") String userid,
                        @Header("Authorization") String authHeader);
       */


}
