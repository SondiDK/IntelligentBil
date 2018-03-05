package dk.osl.intelligentbil;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Oliver on 26-02-2018.
 */

public class SetupFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "Setupfrag";
    Button startButton;
    EditText driveName;

    IDataCommunication mCallback;

    //Todo Denne fragment viser basic setup. Den skal starte ny fragment der viser live data.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_setup, null);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        startButton = view.findViewById(R.id.startButton);
        driveName = view.findViewById(R.id.turNavn);



        startButton.setOnClickListener(this);


    }


    // @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: ");
        if (view == startButton) {
            Log.d(TAG, "onClick: inside button");
            // Begin the transaction
            // Begin the transaction
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();


// Replace the contents of the container with the new fragment
            ft.replace(R.id.placeholder,new DriveFragment());
            //

             ft.addToBackStack(null);

            //dette sætter variablen i mainack, dvs turens navn. bagefter kan jeg hente denne værdi i de andre fragmenter
            mCallback.setMyVariableX(driveName.getText().toString());
// Complete the changes added above
            ft.commit();
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



}
