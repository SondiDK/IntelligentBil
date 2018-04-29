package dk.osl.intelligentbil;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static dk.osl.intelligentbil.DataInterpreter.TYPE.UNKNOWN;


/**
 * Created by Oliver on 04-03-2018.
 */

public class DriveFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "DRIVEfrag";
    List<Integer> speedList,effectList;
    TextView textview, spdView, efview, dview;
    IDataCommunication mCallback;
    Button endButton;
    DataInterpreter dt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_drive, null);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        speedList =  new ArrayList<>();
        effectList =  new ArrayList<>();

        dt = new DataInterpreter();
        spdView = view.findViewById(R.id.speedView);
        efview = view.findViewById(R.id.effectview);
        dview = view.findViewById(R.id.distanceview);

    endButton = getActivity().findViewById(R.id.endButton);
    endButton.setOnClickListener(this);

    textview = getActivity().findViewById(R.id.headline);

    //sætter overskriften fra mainakt til at være overskrift på turen.
    textview.setText(mCallback.getMyVariableX());

    //sender "data" og ber om data
    mCallback.startListening();

    }

    public void updateView(String message){

        int messageLength = message.length();
        Log.d(TAG, "DRIVEFRAG: Data full message: " + message + " Length: " + messageLength);

       DataInterpreter.TYPE[] types = dt.divideShit(message);
        String[] splittedArray = dt.newArray;

        for (int i = 1; i <splittedArray.length ; i++) {
            switch (types[i-1]) {
                case UNKNOWN:
                    Log.e(TAG, "FROM DISTFRAG: Not knowny");
                    break;
                case SPEED:
                    String newSpeed = splittedArray[i].substring(2,splittedArray[i].length());
                    Log.d(TAG, "FROM DISTFRAG: SPEED:" + newSpeed);
                    speedList.add(Integer.parseInt(newSpeed));
                    spdView.setText(newSpeed);
                    break;
                case EFFECT:
                    String newefkt = splittedArray[i].substring(2,splittedArray[i].length());
                    Log.d(TAG, "FROM DISTFRAG: effect:" + newefkt);
                    effectList.add(Integer.parseInt(newefkt));
                    efview.setText(newefkt);
                    break;
                case DISTANCE:
                    String newdist = splittedArray[i].substring(2,splittedArray[i].length());
                    Log.d(TAG, "FROM DISTFRAG: dist:" + newdist);
                    dview.setText(newdist);
                    break;
            }
        }


        }


     @Override
    public void onClick(View view) {

        Log.d(TAG, "onClick: ");
         if(view==endButton){

             AlertDialog.Builder builder;
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                 builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
             } else {
                 builder = new AlertDialog.Builder(getContext());
             }
             builder.setTitle("End Drive")
                     .setMessage("Are you sure you want to end your drive?")
                     .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {
                             mCallback.stopListening();
                             updateLists();
                             startSumFrag();
                         }
                     })
                     .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {
                             // do nothing
                         }
                     })
                     .setIcon(android.R.drawable.ic_dialog_alert)
                     .show();


        }
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
    public void startSumFrag(){
        // Begin the transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        // Replace the contents of the container with the new fragment
        ft.replace(R.id.placeholder, new SummaryFragment());
        //ft.addToBackStack(null);

        // Complete the changes added above
        ft.commit();
    }

    //Sets the lists
    public void updateLists(){
        System.out.println("SPEED LIST LENGTH" +  speedList.size());
        System.out.println("EFECT LIST LENGTH" +  effectList.size());
        /*
           for(int a:speedList){
              System.out.println("LIST" +a);
                           }
                             */
        speedList.add(500);
        effectList.add(500);

        mCallback.setEffectList(effectList);
        mCallback.setSpeedList(speedList);
    }

}

