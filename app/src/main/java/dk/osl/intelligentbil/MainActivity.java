package dk.osl.intelligentbil;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.macroyau.blue2serial.BluetoothSerial;
import com.macroyau.blue2serial.BluetoothSerialListener;

import org.w3c.dom.Text;

import java.util.Set;

public class MainActivity extends FragmentActivity implements IDataCommunication, BluetoothSerialListener {
    private String x;
    boolean b;
    private int y;
    BluetoothSerial bt;
    private final static int REQUEST_ENABLE_BT = 1;
    TextView userEt;
    BluetoothAdapter mBluetoothAdapter;
    String TAG =  "MAIN AKT";



    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEt = (TextView)findViewById(R.id.headline);
        bt = new BluetoothSerial(this, this);

       // IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        //registerReceiver(BTTest.mReceiver, filter);

        Bundle extras = getIntent().getExtras();

        userEt.setText("Velkommen, "  + extras.getString("name"));

        setupFragment();
        promptForBT();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.d(TAG, deviceName);
            }
        }else Log.d(TAG, "onCreate: mno paired");


    }

    public void promptForBT(){

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

    }
public void setupFragment(){


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
        return false;
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
    /*
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView btStatus = findViewById(R.id.btstatus);
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                Log.d("BT ", "CHANGED: ");
                final int bluetoothState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR);
                switch (bluetoothState) {
                    case BluetoothAdapter.STATE_ON:
                         Log.d("BT ", "ON: ");

                      btStatus.setText("On");
                        break;
                        case BluetoothAdapter.STATE_OFF:
                            btStatus.setText("Off");
                            break;
                }
            }
        }
    };
    */
}
