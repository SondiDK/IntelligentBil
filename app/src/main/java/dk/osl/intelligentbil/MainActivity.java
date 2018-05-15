package dk.osl.intelligentbil;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.macroyau.blue2serial.BluetoothDeviceListDialog;
import com.macroyau.blue2serial.BluetoothSerial;
import com.macroyau.blue2serial.BluetoothSerialListener;

import java.util.List;

import dk.osl.intelligentbil.testretro.User;

public class MainActivity extends AppCompatActivity implements IDataCommunication,
        BluetoothSerialListener, BluetoothDeviceListDialog.OnDeviceSelectedListener,
        View.OnClickListener {

    List<Integer> speedList,effectList;
    private int duration;
    private int distance;
    private String x;
    BTSerial bluetoothSerial;
    User us;
    TextView userEt;
    Button testbtn, send;
    private final static  String TAG =  "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEt = findViewById(R.id.headline);


        Bundle extras = getIntent().getExtras();
        Gson gson = new Gson();
        userEt.setText("Velkommen");
        String strObj = getIntent().getStringExtra("usr");
        User user = gson.fromJson(strObj, User.class);
        us = user;
        Log.d(TAG, "onCreate: " +us.getUserID());
        //todo decide if this is approach
        setTitle(getTitle()+": "+extras.getString("name"));
        setupFragment();

        bluetoothSerial = new BTSerial(this, this);

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
    public void setTripName(String x) {
        this.x = x;

    }
    @Override
    public String getTripName(){
        return x;
    }

    @Override
    public List getSpeedList() {
        return speedList;
    }

    @Override
    public void setSpeedList(List spd) {
        this.speedList = spd;
    }

    @Override
    public User getUser() {
        return us;
    }

    @Override
    public void setUser(User u) {
        us = u;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
    this.duration = duration;
    }

    @Override
    public void setDistance(int distance) {
this.distance = distance;
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public List getEffectList() {
        return effectList;
    }

    @Override
    public void setEffectList(List eft) {
        this.effectList = eft;

    }

    @Override
    public void startListening() {
        bluetoothSerial.write("stub", true);
    }

    @Override
    public void stopListening(){
        bluetoothSerial.write("stop", true);
        bluetoothSerial.stop();
    }

    @Override
    public boolean isBluetoothOn(){
    return bluetoothSerial.isBluetoothEnabled();
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
       // int messageLength = message.length();
      //  Log.d(TAG, "onBluetoothSerialRead: Data full message: " + message + " Length: " + messageLength);
        DriveFragment frag =(DriveFragment)getFragmentManager().findFragmentByTag("driveFrag");
        boolean test= frag!=null;

        if(test){
           // System.out.println("hej");
            frag.updateView(message);
        }

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
    public void onBluetoothSerialWrite(String message) {

    }

}
