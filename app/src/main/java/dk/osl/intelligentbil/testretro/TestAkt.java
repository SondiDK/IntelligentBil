package dk.osl.intelligentbil.testretro;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dk.osl.intelligentbil.R;
import dk.osl.intelligentbil.testretro.DataObject;
import dk.osl.intelligentbil.testretro.GetDataService;
import dk.osl.intelligentbil.testretro.RetrofitClientInstance;
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


        Call<List<Drive>> call = service.getAllDrives("5af998d052acbf06be3876cb");
        call.enqueue(new Callback<List<Drive>>() {
            @Override
            public void onResponse(Call<List<Drive>> call, Response<List<Drive>> response) {
                printUsers(response.body());
            }

            @Override
            public void onFailure(Call<List<Drive>> call, Throwable t) {
                Log.e(TAG, "onFailure: " );
            }
        });
    }

    private void printUsers(List<Drive> body) {

        for (Drive d: body) {

            Log.d(TAG, "printUsers: " + d.toString());


        }

    }

public void date(){
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date today = Calendar.getInstance().getTime();
    String reportDate = df.format(today);
    System.out.println("Report Date: " + reportDate);
}
}



