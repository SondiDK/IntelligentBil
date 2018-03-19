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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.macroyau.blue2serial.BluetoothDeviceListDialog;
import com.macroyau.blue2serial.BluetoothSerial;
import com.macroyau.blue2serial.BluetoothSerialListener;

import org.w3c.dom.Text;

import java.util.Set;

public class MainActivity extends FragmentActivity implements IDataCommunication, BluetoothSerialListener, BluetoothDeviceListDialog.OnDeviceSelectedListener, View.OnClickListener {
    private String x;
    boolean b;
    private int y;
    BluetoothSerial bluetoothSerial;
    private final static int REQUEST_ENABLE_BT = 1;
    TextView userEt, btreceived, conkt;
    Button testbtn, send, stop;


    String TAG =  "HOVED";



    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEt = (TextView)findViewById(R.id.headline);
        testbtn = findViewById(R.id.testbt);
        send = findViewById(R.id.testbtsend);
        btreceived = findViewById(R.id.btrece);
        conkt = findViewById(R.id.connected);
        stop = findViewById(R.id.testbtstop);
       // IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        //registerReceiver(BTTest.mReceiver, filter);

        Bundle extras = getIntent().getExtras();

        userEt.setText("Velkommen, "  + extras.getString("name"));

        setupFragment();
        testbtn.setOnClickListener(this);
        send.setOnClickListener(this);
        stop.setOnClickListener(this);
        bluetoothSerial = new BluetoothSerial(this, this);

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();

        // Check Bluetooth availability on the device and set up the Bluetooth adapter
        bluetoothSerial.setup();

    }


    private void showDeviceListDialog() {
        // Display dialog for selecting a remote Bluetooth device
        BluetoothDeviceListDialog dialog = new BluetoothDeviceListDialog(this);
        dialog.setOnDeviceSelectedListener(this);
        dialog.setTitle("Snuggi");
        dialog.setDevices(bluetoothSerial.getPairedDevices());
        dialog.showAddress(true);
        dialog.show();
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
        updateBluetoothState();

    }

    @Override
    public void onBluetoothDeviceConnected(String name, String address) {
        updateBluetoothState();
    }

    @Override
    public void onBluetoothSerialRead(String message) {
        Log.d(TAG, "onBluetoothSerialRead: CALLED");
        StringBuilder sb = new StringBuilder();
        for(byte b:  message.getBytes())
            sb.append(String.format("%02x", b));

        btreceived.setText(sb);

    }

    @Override
    public void onBluetoothSerialWrite(String message) {

    }

    @Override
    public void onBluetoothDeviceSelected(BluetoothDevice device) {
        Log.d(TAG, "onBluetoothDeviceSelected: ");
        bluetoothSerial.connect(device);

    }

    @Override
    public void onClick(View view) {

        if(view == testbtn){
            showDeviceListDialog();
        }
        if(view==send){
            Log.d(TAG, "onClick: CALLED");
            bluetoothSerial.write("data", true);

        }if(view==stop){
            Log.d(TAG, "onClick: CALLED");
            bluetoothSerial.write("stop", true);

        }
    }
    private void updateBluetoothState() {
        // Get the current Bluetooth state
        final int state;
        if (bluetoothSerial != null)
            state = bluetoothSerial.getState();
        else
            state = BluetoothSerial.STATE_DISCONNECTED;

        // Display the current state on the app bar as the subtitle
        String subtitle;
        switch (state) {
            case BluetoothSerial.STATE_CONNECTING:
                subtitle ="status_connecting";
                break;
            case BluetoothSerial.STATE_CONNECTED:
                subtitle ="status_connected" + bluetoothSerial.getConnectedDeviceName();
                break;
            default:
                subtitle = "status_disconnected";
                break;
        }

       conkt.setText(subtitle);
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
