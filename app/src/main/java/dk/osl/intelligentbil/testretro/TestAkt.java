package dk.osl.intelligentbil.testretro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import dk.osl.intelligentbil.R;
import dk.osl.intelligentbil.testretro.DataObject;
import dk.osl.intelligentbil.testretro.GetDataService;
import dk.osl.intelligentbil.testretro.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestAkt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_akt);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<DataObject>> call = service.getAllPosts();
        call.enqueue(new Callback<List<DataObject>>() {
            @Override
            public void onResponse(Call<List<DataObject>> call, Response<List<DataObject>> response) {
                System.out.println("RES" + response.body());
                printList(response.body());

            }



            @Override
            public void onFailure(Call<List<DataObject>> call, Throwable t) {
                Log.e("FAIL", "onFailure: ", t );
            }
        });
    }


    void printList(List<DataObject> list){
        for (DataObject d: list) {
            Log.d("List", "printList " +d.getTitle());
        }
    }
}
