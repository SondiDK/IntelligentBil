package dk.osl.intelligentbil.testretro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Oliver on 11-05-2018.
 */

public interface GetDataService {


    @GET("/posts")
    Call<List<DataObject>> getAllPosts();

}
