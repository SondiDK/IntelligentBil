package dk.osl.intelligentbil;

import android.util.Log;

/**
 * Created by oliver on 4/4/18.
 */

public class DataInterpreter {

    private final static String TAG = "DataMan";


    private final static int ARRAY_LENGTH = 6;


    //ff betyder start byte
    private final static String START_ID = "ff";

    private final static String EFFECT_ID = "49";
    private final static String SPEED_ID = "50";

    private final static String DIST_ID = "51";


    //næste 2 bit betyder kode
    //sidste er value




    //start på hvordan det kan handles
    public TYPE handleShit(String message){
        Log.d(TAG, "handleShit " + message);
        //modtager array på længde 6 (
        //if (message.length() >= ARRAY_LENGTH) return TYPE.UNKNOWN;


        if (message.contains(START_ID)&& message.length()>=6){
            Log.d(TAG, "STARTS WITth" + START_ID);

            switch (message.substring(2,4)){
                case EFFECT_ID:
                    Log.d(TAG, "MessageType: EFFECT");
                    return TYPE.EFFECT;
                case SPEED_ID:
                    Log.d(TAG, "MessageType: SPEED");
                    return TYPE.SPEED;

                case DIST_ID:
                     Log.d(TAG, "MessageType: Dist");
                    return TYPE.DISTANCE;
            }
        }

        return TYPE.UNKNOWN;
    }

    public enum TYPE {
        SPEED,
        EFFECT,
        DISTANCE,
        UNKNOWN
    }


}
