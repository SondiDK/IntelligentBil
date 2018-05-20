package dk.osl.intelligentbil.Model;

import android.util.Log;

/**
 * Created by oliver on 4/4/18.
 */

public class DataInterpreter {

    private final static String TAG = "DataInterpreter";


    //ff betyder start byte
    private final static String START_ID = "ff";
    private final static String EFFECT_ID = "49";
    private final static String SPEED_ID = "50";
    private final static String DIST_ID = "51";
    private String[] splittedArray;

    //næste 2 bit betyder kode
    //sidste er value

    public TYPE[] divideMessage(String message){

        //sikrer at vi fåt et array med min længde 6, ellers kan vi ikke læse det.
        if(message.length()>=6) {
            //splitter efter ff, fordi vi nogle gange får lange strings, hvor der er flere beskeder I
            splittedArray = message.split(START_ID);
            int length = splittedArray.length;

            //Array, der holder styr på typerne
             TYPE[] types = new TYPE[length - 1];

             //kigger ikke på første[0] del, fordi splits ligger efter
            for (int i = 1; i < splittedArray.length; i++) {
                String currentMessage = splittedArray[i];
                //sender en besked som fx 49xxxx
                types[i - 1] = handleMessage(currentMessage);
            }
            return  types;
        }
      else return null;
    }

    //start på hvordan det kan handles
    public TYPE handleMessage(String message){
        Log.d(TAG, "handleMessage " + message);


          if(message.length()>1) {
              //afkoder typen
              switch (message.substring(0, 2)) {
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

    public int convertFromHex(String hex){
        return Integer.parseInt(hex,16);
    }

    public enum TYPE {
        SPEED,
        EFFECT,
        DISTANCE,
        UNKNOWN
    }


    public String[] getSplittedArray() {
        return splittedArray;
    }

    public void setSplittedArray(String[] splittedArray) {
        this.splittedArray = splittedArray;
    }


}
