package dk.osl.intelligentbil;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends FragmentActivity implements IDataCommunication   {
    private String x;
    private int y;


    TextView userEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEt = (TextView)findViewById(R.id.headline);


        Bundle extras = getIntent().getExtras();

        userEt.setText("Velommen, " + extras.getString("name"));

        // Begin the transaction
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        SetupFragment fragment = new SetupFragment();

// Replace the contents of the container with the new fragment
        ft.replace(R.id.placeholder, fragment);
      //
        // adder ikke til backstack fordi eller sgår den bare tilbage til tom view
        // ft.addToBackStack(null);
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();

    }


    @Override
    public void setMyVariableX(String x) {
        this.x = x;

    }
    @Override
    public String getMyVariableX(){
        return x;
    }


}
