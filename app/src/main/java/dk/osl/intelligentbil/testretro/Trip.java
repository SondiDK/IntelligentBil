package dk.osl.intelligentbil.testretro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("effect")
    @Expose
    private Double powerAverage;


    @SerializedName("length")
    @Expose
    private Double distance;

    @SerializedName("name")
    @Expose
    private String name;

    /*
    @SerializedName("")
    @Expose
    private Double speedAverage;
*/

    @SerializedName("time_length")
    @Expose
    private int duration;


    public Trip(String date, Double powerAverage, Double distance, String name, int duration) {
        this.date = date;
        this.powerAverage = powerAverage;
        this.distance = distance;
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public Double getSpeedAverage() {
        return speedAverage;
    }

    public void setSpeedAverage(Double speedAverage) {
        this.speedAverage = speedAverage;
    }
*/
    public Double getPowerAverage() {
        return powerAverage;
    }

    public void setPowerAverage(Double powerAverage) {
        this.powerAverage = powerAverage;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "name='" + name + '\'' +
              //  ", speedAverage=" + speedAverage +
                ", powerAverage=" + powerAverage +
                ", distance=" + distance +
                ", duration=" + duration +
                ", date='" + date + '\'' +
                '}';
    }
}







