package dk.osl.intelligentbil;

import java.util.List;

import dk.osl.intelligentbil.testretro.User;

/**
 * Created by Oliver on 04-03-2018.
 */

public interface IDataCommunication {

    public String getTripName();
    public void setTripName(String x);
    public void startListening();
    public void stopListening();
    public boolean isBluetoothOn();
    public boolean isConnected();
    public List getEffectList();
    public void setEffectList(List eft );
    public List getSpeedList();
    public void setSpeedList(List spd );
    public User getUser();
    public void setUser(User u);
    public int getDuration();
    public void setDuration(int duration);
    public void setDistance(int distance);
    public int getDistance();

}

