package dk.osl.intelligentbil.testretro;

import com.google.gson.annotations.SerializedName;

public class Drive {


    @SerializedName("name")
    private String name;

    @SerializedName("")
    private Double speedAverage;

    @SerializedName("effect")
    private Double powerAverage;

    @SerializedName("length")
    private Double distance;

    @SerializedName("time_length")
    private int duration;

    @SerializedName("date")
    private String date;


    public Drive(String name, Double speedAverage, Double powerAverage, Double distance, int duration, String date) {
        this.name = name;
        this.speedAverage = speedAverage;
        this.powerAverage = powerAverage;
        this.distance = distance;
        this.duration = duration;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSpeedAverage() {
        return speedAverage;
    }

    public void setSpeedAverage(Double speedAverage) {
        this.speedAverage = speedAverage;
    }

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
        return "Drive{" +
                "name='" + name + '\'' +
                ", speedAverage=" + speedAverage +
                ", powerAverage=" + powerAverage +
                ", distance=" + distance +
                ", duration=" + duration +
                ", date='" + date + '\'' +
                '}';
    }
}







