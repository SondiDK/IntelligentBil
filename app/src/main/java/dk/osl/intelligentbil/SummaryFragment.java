package dk.osl.intelligentbil;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SummaryFragment extends Fragment implements View.OnClickListener {
    TextView textview;
    IDataCommunication mCallback;
    Button bckButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_summary, null);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {



        textview = getActivity().findViewById(R.id.headline);
        textview.setText(mCallback.getMyVariableX());
        bckButton =  view.findViewById(R.id.sumButton);

        bckButton.setOnClickListener(this);

    }

    // @Override
    public  void onClick(View view) {
            if(view==bckButton) {
                mCallback.stopListening();
            }



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
