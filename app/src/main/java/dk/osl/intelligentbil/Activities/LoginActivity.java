package dk.osl.intelligentbil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import dk.osl.intelligentbil.R;
import dk.osl.intelligentbil.Retrofit.GetDataService;
import dk.osl.intelligentbil.Retrofit.RetrofitClientInstance;
import dk.osl.intelligentbil.Model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginAkt";
    private EditText usernameEt, passwordEt;
    private Button loginBtn;
    private User us;

    // Få API klient klar.
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
    //Metode til at tjekke, at der ikke er empty tekt
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


        Call<User> call = service.login(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body()!=null){
                   Log.d(TAG, "onResponse: Token " +  response.body().getToken());
                    Log.d(TAG, "onResponse UserID: " + us.getUserID());

                   // gem userdata
                    us.setUserID(response.body().getUserID());
                    us.setToken(response.body().getToken());

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    Gson gson = new Gson();
                    //Sender username og user objekt med
                    //todo fix så username bliver sat i user objektet
                    i.putExtra("name",username);
                    i.putExtra("usr", gson.toJson(us));
                    startActivity(i);

                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }



    }
