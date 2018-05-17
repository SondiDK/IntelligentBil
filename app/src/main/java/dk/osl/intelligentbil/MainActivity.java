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

    private final static  String TAG =  "MainActivity";
   private List<Integer> speedList,effectList;
    private int duration;
    private int distance;
    private String tripName;
   private BTSerial bluetoothSerial;
    private  User us;
   private TextView userEt;
 private  Button testbtn, send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init bluetooth forbindelses objekt
        bluetoothSerial = new BTSerial(this, this);

        userEt = findViewById(R.id.headline);
        userEt.setText("Velkommen");

        //få data fra anden aktivitet
        Bundle extras = getIntent().getExtras();
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("usr");
        User user = gson.fromJson(strObj, User.class);
        us = user;

        //todo decide if this is approach
        setTitle(getTitle()+": "+extras.getString("name"));
        setupFragment();

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

    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
    SetupFragment fragment = new SetupFragment();

    // Replace the contents of the container with the new fragment
    ft.replace(R.id.placeholder, fragment);
    // adder ikke til backstack fordi eller sgår den bare tilbage til tom view
    // ft.addToBackStack(null);

    ft.commit();
    }
    @Override
    public void setTripName(String x) {
        this.tripName = x;

    }
    @Override
    public String getTripName(){
        return tripName;
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
        //stub for test
        bluetoothSerial.write("data", true);
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


        //Update the fragment
        DriveFragment frag =(DriveFragment)getFragmentManager().findFragmentByTag("driveFrag");
        boolean fragmentExist= frag!=null;

        if(fragmentExist){
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
        if(view == send){
            Log.d(TAG, "onClick: CALLED");
        }
    }

    public void updateBluetoothState() {
        //Få Bluetooth state
        final int state;
        if (bluetoothSerial != null)
            state = bluetoothSerial.getState();
        else
            state = BluetoothSerial.STATE_DISCONNECTED;

        // vis bluetooth state
        String status;
        switch (state) {
            case BluetoothSerial.STATE_CONNECTING:
                status ="Connecting...";
                break;
            case BluetoothSerial.STATE_CONNECTED:
                status ="Connected to" + bluetoothSerial.getConnectedDeviceName();
                break;
            default:
                status = "Disconnected";
                break;
        }

        TextView view = findViewById(R.id.connectstatus);
        view.setText(status);
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
