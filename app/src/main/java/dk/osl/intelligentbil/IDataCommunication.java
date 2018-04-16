package dk.osl.intelligentbil;

/**
 * Created by Oliver on 04-03-2018.
 */

public interface IDataCommunication {
    public String getMyVariableX();
    public void setMyVariableX(String x);
    public void startListening();
    public void stopListening();
    public boolean isBluetoothOn();
    public boolean isConnected();



}

