package dk.osl.intelligentbil;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;

import dk.osl.intelligentbil.testretro.Trip;
import dk.osl.intelligentbil.testretro.GetDataService;
import dk.osl.intelligentbil.testretro.RetrofitClientInstance;
import dk.osl.intelligentbil.testretro.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameEt, passwordEt;
    private Button loginBtn;
    private static final String TAG = "LoginAkt";

    User us;
    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        us = new User();
        usernameEt = (EditText) findViewById(R.id.usrnametxt);
        passwordEt = (EditText) findViewById(R.id.pwdtxt);

        loginBtn = (Button) findViewById(R.id.loginbtn);
        loginBtn.setOnClickListener(this);

        usernameEt.setText("Klaus");
        passwordEt.setText("flagstang");
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: login clicked");

        if(view == loginBtn){
           if(validateInput()){
               loginRequest();
           }

        }

    }

    private boolean validateInput() {
        Log.d(TAG, "validateInput: called");
        boolean isValid = true;

        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        //validate for empty input
        if(username.isEmpty() || password.isEmpty()){
            Log.d(TAG, "validateInput: empty");
            Toast.makeText(getBaseContext(),"Enter both fields", Toast.LENGTH_SHORT).show();
        }



        return isValid;

    }



    public void loginRequest(){
        //get from input fields
       final  String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        Call<User> call = service.savePost(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body()!=null){
                   Log.d(TAG, "onResponse: Token " +  response.body().getToken());
                    Log.d(TAG, "onResponse UserID: " +us.getUserID());
                    us.setUserID(response.body().getUserID());
                    us.setToken(response.body().getToken());


                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    i.putExtra("name",username);
                    Gson gson = new Gson();

                    i.putExtra("usr", gson.toJson(us));
                    startActivity(i);

                   // testCreateTrip();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }



    }
