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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameEt, passwordEt;
    private Button loginBtn;
    private static final String TAG = "LoginAkt";

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
    protected void onResume() {

        super.onResume();

        new JSONAsyncTask().execute();

    }
    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: login clicked");

        if(view == loginBtn){
           if(validateInput()){
               login();
           }

        }

    }

    private boolean validateInput() {
        Log.d(TAG, "validateInput: called");
        boolean isValid = true;

        String username = usernameEt.getText().toString();
        String password = usernameEt.getText().toString();

        //validate for empty input
        if(username.isEmpty() || password.isEmpty()){
            Log.d(TAG, "validateInput: empty");
            Toast.makeText(getBaseContext(),"Enter both fields", Toast.LENGTH_SHORT).show();
        }



        return isValid;

    }


    public void login(){

        String username = usernameEt.getText().toString();
        String password = usernameEt.getText().toString();

        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("name",username);
        startActivity(i);


    }


    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(String... urls) {

                String URLadresse = "N/a";
                int userid = 0;

                // get all trips from user
//          {
//            "date": "0"
//            "lenght": "0"
//            "effect": "0"
//            "name": "work"
//            "userid": "0"
//            "message": "trip created"
//        }
                try {
                    URL url = new URL("https://api.dc01.gamelockerapp.com/shards/eu/players?filter[playerNames]=iPatrick");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestProperty("Authorization", "Bearer " +
                            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjNzBkY2Y2MC0yYWMw" +
                            "LTAxMzYtZjc5Ni0wYTU4NjQ2MTIzMDUiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNTI" +
                            "0NjY1NjIwLCJwdWIiOiJzZW1jIiwidGl0bGUiOiJ2YWluZ2xvcnkiLCJhcHAiOiJnbG9yeXN0Y" +
                            "XRkZGQiLCJzY29wZSI6ImNvbW11bml0eSIsImxpbWl0IjoxMH0.F2bUydBWf7DtbqL-KEbSY" +
                            "36kkcndNr7z9fJzLPfwSjk");

                    if (conn.getResponseCode() != 200) {

                        throw new RuntimeException("Failed"
                                + conn.getResponseCode());
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));

                    String output;
                    while ((output = br.readLine()) != null) {
                        System.out.println("output" + output);
                    }

                    conn.disconnect();

                } catch (MalformedURLException e) {

                    e.printStackTrace();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();

                }


            return true;
        }

        protected void onPostExecute(Boolean result) {

        }


    }
    }
