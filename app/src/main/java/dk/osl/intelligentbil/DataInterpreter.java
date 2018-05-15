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
    String[] newArray;
    //næste 2 bit betyder kode
    //sidste er value

    public TYPE[] divideShit(String message){

        if(message.length()>=6) {
            newArray = message.split(START_ID);
        int length = newArray.length;

        TYPE[] types = new TYPE[length - 1];

            for (int i = 1; i < newArray.length; i++) {
                String currentMessage = newArray[i];
                types[i - 1] = handleShit(currentMessage);
            }
            return  types;
        }
      else return null;
    }

    //start på hvordan det kan handles
    public TYPE handleShit(String message){
        Log.d(TAG, "handleShit " + message);
      // System.out.println("size" + message.length());

          if(message.length()>1) {
              switch (message.substring(0, 2)) {
                  case EFFECT_ID:
                      Log.d(TAG, "MessageType: EFFECT");
                      return TYPE.EFFECT;
                  case SPEED_ID:
                      Log.d(TAG, "MessageType: SPEED");
                      return TYPE.SPEED;
                  case DIST_ID:
                      //System.out.println("effect");
                      Log.d(TAG, "MessageType: Dist");
                      return TYPE.DISTANCE;
              }

          }
        return TYPE.UNKNOWN;
    }

    public int convertFromHex(String hex){
        return Integer.parseInt(hex,16);
    }

    public enum TYPE {
        SPEED,
        EFFECT,
        DISTANCE,
        UNKNOWN
    }


}
