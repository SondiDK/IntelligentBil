package dk.osl.intelligentbil;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.macroyau.blue2serial.BluetoothSerial;
import com.macroyau.blue2serial.BluetoothSerialListener;

import org.w3c.dom.Text;

public class MainActivity extends FragmentActivity implements IDataCommunication, BluetoothSerialListener {
    private String x;
    boolean b;
    private int y;
    BluetoothSerial bt;

    TextView userEt;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEt = (TextView)findViewById(R.id.headline);
        bt = new BluetoothSerial(this, this);


        Bundle extras = getIntent().getExtras();

        userEt.setText("Velkommen, "  + extras.getString("name"));

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
    public boolean isConnected() {

        return bt.checkBluetooth();
    }

    @Override
    public String getMyVariableX(){
        return x;
    }


    @Override
    public void onBluetoothNotSupported() {

    }

    @Override
    public void onBluetoothDisabled() {

    }

    @Override
    public void onBluetoothDeviceDisconnected() {

    }

    @Override
    public void onConnectingBluetoothDevice() {

    }

    @Override
    public void onBluetoothDeviceConnected(String name, String address) {

    }

    @Override
    public void onBluetoothSerialRead(String message) {

    }

    @Override
    public void onBluetoothSerialWrite(String message) {

    }
}
