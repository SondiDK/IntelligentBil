package dk.osl.intelligentbil;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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
    boolean result;
    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        final String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        Call<User> call = service.savePost(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: "+ response.body());
                if(response.body()!=null){
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    i.putExtra("name",username);
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
