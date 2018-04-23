package dk.osl.intelligentbil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

}
