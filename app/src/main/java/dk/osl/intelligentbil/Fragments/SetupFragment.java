package dk.osl.intelligentbil.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dk.osl.intelligentbil.IDataCommunication;
import dk.osl.intelligentbil.Activities.MainActivity;
import dk.osl.intelligentbil.R;

/**
 * Created by Oliver on 26-02-2018.
 */

public class SetupFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "Setupfragment";
    private Button startButton,connectButton;
    private EditText driveName;
    private IDataCommunication mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
             return inflater.inflate(R.layout.fragment_setup, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        startButton = view.findViewById(R.id.startButton);
        connectButton = view.findViewById(R.id.cntbtn);
        driveName = view.findViewById(R.id.turNavn);

        //gi underline til connect button
        connectButton.setPaintFlags(connectButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //klikkers
        connectButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: ");
        if (view == startButton ) {
            if(mCallback.isConnected()){
                startDriveFragment();
            }
            else Toast.makeText(getContext(),"Connect to device", Toast.LENGTH_SHORT).show();
        }
        if(view == connectButton){
                //vis dialog over devices
            ((MainActivity)getActivity()).showDeviceListDialog();
        }

    }

public void startDriveFragment(){

    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

    ft.replace(R.id.placeholder,new DriveFragment(),"driveFrag");
    ft.addToBackStack(null);

    //dette sætter variablen i mainack, dvs turens navn. bagefter kan jeg hente denne værdi i de andre fragmenter
    mCallback.setTripName(driveName.getText().toString());
    ft.commit();
}
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (IDataCommunication) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataCommunication");
        }
    }







}
