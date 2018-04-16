package dk.osl.intelligentbil;

import android.bluetooth.BluetoothDevice;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.macroyau.blue2serial.BluetoothDeviceListDialog;
import com.macroyau.blue2serial.BluetoothSerial;
import com.macroyau.blue2serial.BluetoothSerialListener;

public class MainActivity extends AppCompatActivity implements IDataCommunication,
        BluetoothSerialListener, BluetoothDeviceListDialog.OnDeviceSelectedListener,
        View.OnClickListener {

    private String x;
    boolean b;
    private int y;
    BTSerial bluetoothSerial;
    DataInterpreter dt;
    private final static int REQUEST_ENABLE_BT = 1;
    TextView userEt, btreceived, conkt;
    Button testbtn, send;
    private final static  String TAG =  "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEt = findViewById(R.id.headline);

        Bundle extras = getIntent().getExtras();
        userEt.setText("Velkommen, "  + extras.getString("name"));

        //todo decide if this is approach
        setTitle(getTitle()+extras.getString("name"));
        setupFragment();

        bluetoothSerial = new BTSerial(this, this);
        dt = new DataInterpreter();

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();

        // Check Bluetooth availability on the device and set up the Bluetooth adapter
        bluetoothSerial.setup();

    }


    public void showDeviceListDialog() {
        
        if(bluetoothSerial.isBluetoothEnabled()) {
            // Display dialog for selecting a remote Bluetooth device
            BluetoothDeviceListDialog dialog = new BluetoothDeviceListDialog(this);
            dialog.setOnDeviceSelectedListener(this);
            dialog.setTitle("Select Micro controller");
            dialog.setDevices(bluetoothSerial.getPairedDevices());
            dialog.showAddress(true);
            dialog.show();
        }
        else Log.d(TAG, "showDeviceListDialog: CONNECT BT");
    }
    public void setupFragment(){

    // Begin the transaction
    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
    SetupFragment fragment = new SetupFragment();

    // Replace the contents of the container with the new fragment
    ft.replace(R.id.placeholder, fragment);
    // adder ikke til backstack fordi eller sgår den bare tilbage til tom view
    // ft.addToBackStack(null);

    // Complete the changes added above
    ft.commit();
    }
    @Override
    public void setMyVariableX(String x) {
        this.x = x;

    }

    @Override
    public void startListening() {
        bluetoothSerial.write("data", true);
    }

    @Override
    public void stopListening(){bluetoothSerial.write("stop", true);}
    @Override
    public boolean isBluetoothOn(){
    return bluetoothSerial.isBluetoothEnabled();
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

        Log.d(TAG, "onBluetoothSerialRead: Data full message: " + message);

        TextView speedView = findViewById(R.id.speedView);
        TextView effectView = findViewById(R.id.effectview);
        TextView distview =  findViewById(R.id.distanceview);

        if(getSupportFragmentManager().findFragmentByTag("driveFrag")!=null )

            switch (dt.handleShit(message)){
                case UNKNOWN:
                    Log.e(TAG, "onBluetoothSerialRead:SHit array" );
                    break;
                case SPEED:
                    String speed= message.substring(4,6);
                    Log.d(TAG, "onBluetoothSerialRead: SPEED:" + speed);
                    speedView.setText(speed);
                    break;
                case EFFECT:
                    String efkt= message.substring(4,6);
                    Log.d(TAG, "onBluetoothSerialRead: effect:" + efkt);
                    effectView.setText(efkt);
                    break;
                case DISTANCE:
                    String dist= message.substring(4,6);
                    Log.d(TAG, "onBluetoothSerialRead: dist:" + dist);
                    distview.setText(dist);
                    break;

            }




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


        }
    }
    public void updateBluetoothState() {
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
                subtitle ="Connecting...";
                break;
            case BluetoothSerial.STATE_CONNECTED:
                subtitle ="Connected to" + bluetoothSerial.getConnectedDeviceName();
                break;
            default:
                subtitle = "Disconnected";
                break;
        }

        TextView view = findViewById(R.id.connectstatus);
        view.setText(subtitle);
    }
    @Override
public boolean isConnected(){
       return bluetoothSerial.isConnected();
}

}
