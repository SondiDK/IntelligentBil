package dk.osl.intelligentbil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Oliver on 04-03-2018.
 */

public class DriveFragment extends Fragment {
 TextView textview;
    IDataCommunication mCallback;

   //Todo Denne fragment viser basic setup. Den skal starte ny fragment der viser live data.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_drive, null);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {



textview = getActivity().findViewById(R.id.headline);
textview.setText(mCallback.getMyVariableX());
    }

    // @Override
    public void onClick(View view) {
        // if(view==startbtn){
        // Begin the transaction
        // FragmentTransaction ft = getFragmentManager().beginTransaction();

// Replace the contents of the container with the new fragment
        //ft.replace(R.id.your_placeholder, new PlayerFragment());
        //ft.addToBackStack(null);
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        //ft.commit();
        //}
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

