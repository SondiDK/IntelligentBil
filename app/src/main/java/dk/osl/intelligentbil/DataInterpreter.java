package dk.osl.intelligentbil;

import android.util.Log;

/**
 * Created by oliver on 4/4/18.
 */

public class DataInterpreter {

    private final static String TAG = "DataMan";


    private final static int ARRAY_LENGTH = 6;


    //ff betyder start byte
    private final static String START_ID = "FF";

    private final static String SPEED_ID = "XX";
    private final static String EFFECT_ID = "YY";
    private final static String DIST_ID = "ZZ";


    //næste 2 bit betyder kode
    //sidste er value




    //start på hvordan det kan handles
    public TYPE handleShit(String message){

        //modtager array på længde 6 (
        if (message.length() >= ARRAY_LENGTH) return TYPE.UNKNOWN;


        if (message.startsWith(START_ID)){

            switch (message.substring(2,4)){

                case SPEED_ID:
                    Log.d(TAG, "MessageType: SPEED");
                    return TYPE.SPEED;

                case EFFECT_ID:
                    Log.d(TAG, "MessageType: SPEED");
                    return TYPE.EFFECT;

                case DIST_ID:
                     Log.d(TAG, "MessageType: SPEED");
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
