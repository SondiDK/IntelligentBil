package dk.osl.intelligentbil;

import java.util.List;

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




    ////////Mainakt implenterer disse. kalder på setList i DriveAkt for at give dem værdien og så og getList i summAkt.



    public List getEffectList();
    public void setEffectList(List eft );


    public List getSpeedList();
    public void setSpeedList(List spd );


    public int getDuration();
    public void setDuration(int duration);

}

