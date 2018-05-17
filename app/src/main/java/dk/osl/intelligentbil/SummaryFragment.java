package dk.osl.intelligentbil;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dk.osl.intelligentbil.testretro.GetDataService;
import dk.osl.intelligentbil.testretro.RetrofitClientInstance;
import dk.osl.intelligentbil.testretro.Trip;
import dk.osl.intelligentbil.testretro.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SummaryFragment extends Fragment implements View.OnClickListener {
    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    TextView textview, averageSpeed, averagePower, totalDistance, duration, uploadStatus;
    IDataCommunication mCallback;
    Button bckButton;
    List<Integer> speedList, effectList;
    Trip drive;
    User currentUser;
   DecimalFormat df = new DecimalFormat("#0.00");
   boolean testData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_summary, null);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        textview = getActivity().findViewById(R.id.headline);
        textview .setText(mCallback.getTripName());

        averageSpeed = view.findViewById(R.id.avgspd);
        averagePower = view.findViewById(R.id.avgpow);
        duration = view.findViewById(R.id.duration);
        totalDistance = view.findViewById(R.id.totaldist);
        uploadStatus = view.findViewById(R.id.status);


        getLists();
        Double avgspd = calculateListAverage(speedList);
        Double avgpow = calculateListAverage(effectList);

        int durationInMinutes = mCallback.getDuration();
        String tripName = mCallback.getTripName();
        currentUser = mCallback.getUser();

        averageSpeed.setText(df.format(avgspd) + " km/h");
        averagePower.setText(df.format(avgpow)+" w");
        totalDistance.setText(mCallback.getDistance() + " km/h");
        duration.setText(Integer.toString(mCallback.getDuration()) + " min");

        drive = new Trip(getDate(),avgpow,mCallback.getDistance() , tripName,durationInMinutes);
        uploadDrive();
        bckButton = view.findViewById(R.id.sumButton);
        bckButton.setOnClickListener(this);

    }

    public void uploadDrive(){
        if(!testData) {
            Call<Trip> call = service.saveTrip(drive.getDate(), drive.getPowerAverage(), drive.getDistance(),
                    drive.getName(), currentUser.getUserID(), drive.getDuration(),
                    "Bearer " + currentUser.getToken());
        }
            Call<Trip> call = service.saveTrip("Today", 450.5, 20, "jagt",
                    "e34", 5, "Bearer " + currentUser.getToken());

        call.enqueue(new Callback<Trip>() {
            @Override
            public void onResponse(Call<Trip> call, Response<Trip> response) {
                Log.d(" ", "onResponse: status" + response.raw());
                Log.d("", "onResponse: body " + response.body());
                uploadStatus.setText(response.code());
            }

            @Override
            public void onFailure(Call<Trip> call, Throwable t) {

            }
        });


    }
    @Override
    public void onClick(View view) {

        if (view == bckButton) {

            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            SetupFragment fragment = new SetupFragment();
            ft.replace(R.id.placeholder, fragment);
            // adder ikke til backstack fordi eller sgår den bare tilbage til tom view
            // ft.addToBackStack(null);
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

    public String getDate(){
        java.text.DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        return reportDate;
    }


}



