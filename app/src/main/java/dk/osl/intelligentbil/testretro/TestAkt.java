package dk.osl.intelligentbil.testretro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dk.osl.intelligentbil.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestAkt extends AppCompatActivity {
    private static final String TAG ="TestAkt" ;
    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_akt);
    //loginTest();
        getPosts();


    }


    void printUser(User u) {
        Log.d(TAG, "printUser: " + u.getUserID());

    }

    public void loginTest(){

        Call<User> call = service.savePost("Klaus", "flagstang");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                printUser(response.body());

            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void getPosts(){


        Call<List<Trip>> call = service.getAllDrives("5af998d052acbf06be3876cb");
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                printUsers(response.body());
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Log.e(TAG, "onFailure: " );
            }
        });
    }

    private void printUsers(List<Trip> body) {

        for (Trip d: body) {

            Log.d(TAG, "printUsers: " + d.toString());


        }

    }




}



