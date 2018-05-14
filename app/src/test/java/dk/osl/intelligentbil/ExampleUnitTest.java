package dk.osl.intelligentbil;

import android.util.Log;

import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        DataInterpreter d = new DataInterpreter();
        String test = "00ff51";


        DataInterpreter.TYPE[] a = d.divideShit(test);

        String[] splittedArray = d.newArray;
        System.out.println("split arrau" + splittedArray.length);

        for (int i = 1; i < splittedArray.length; i++) {
            switch (a[i-1]) {
                case UNKNOWN:

                    break;
                case SPEED:
                    String s = splittedArray[i].substring(2,splittedArray[i].length());
                    System.out.println("sp" + s);
                    break;
                case EFFECT:
                    String ef = splittedArray[i].substring(2,splittedArray[i].length());
                    System.out.println("ef" + ef);
                    break;
                case DISTANCE:
                    String di = splittedArray[i].substring(2,splittedArray[i].length());
                    System.out.println("Dist" + di);
                    break;
            }
            assertEquals(4, 2 + 2);
        }
    }

    @Test
    public void formatTest(){
        DecimalFormat df = new DecimalFormat("#0.00");


        Double avgspd = 20345.5667676;

      String test =  df.format(avgspd);
        System.out.println(test);
    }

}