package dk.osl.intelligentbil;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.List;


public class SummaryFragment extends Fragment implements View.OnClickListener {
    TextView textview, averageSpeed, averagePower, totalDistance;
    IDataCommunication mCallback;
    Button bckButton;
    List<Integer> speedList, effectList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_summary, null);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Date d = new Date();
        d.getTime();
        CharSequence s  = DateFormat.format("EEEE, MMMM d, yyyy", d.getTime());


        textview = getActivity().findViewById(R.id.headline);
        textview.setText(mCallback.getMyVariableX());

        averageSpeed = view.findViewById(R.id.avgspd);
        averagePower = view.findViewById(R.id.avgpow);
        totalDistance = view.findViewById(R.id.totaldist);

        getLists();
        Double avgspd = calculateListAverage(speedList);
        Double avgpow = calculateListAverage(effectList);

        averageSpeed.setText(Double.toString(avgspd));
        averagePower.setText(Double.toString(avgpow));


        Drive drive = new Drive(mCallback.getMyVariableX(),avgpow, avgspd,40.5);

        bckButton = view.findViewById(R.id.sumButton);
        bckButton.setOnClickListener(this);

    }

     @Override
    public void onClick(View view) {

        if (view == bckButton) {
            // Begin the transaction
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            SetupFragment fragment = new SetupFragment();

            // Replace the contents of the container with the new fragment
            ft.replace(R.id.placeholder, fragment);

            // adder ikke til backstack fordi eller sgår den bare tilbage til tom view
            // ft.addToBackStack(null);

            // Complete the changes added above
            ft.commit();
            //fetsd
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


    //få liste fra interfacet
    public void getLists() {
        effectList = mCallback.getEffectList();
        speedList = mCallback.getSpeedList();

    }

    // udregner listens gennemsnitslige værdi
    public double calculateListAverage(List<Integer> list) {
        double result = 0;

        for (int a : list) {
            result += a;
        }
        result = result / list.size();
        return result;
    }

}



