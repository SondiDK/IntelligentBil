package dk.osl.intelligentbil;

public class Drive {

    private String Name;
    private Double SpeedAverage;
    private Double PowerAverage;
    private Double Distance;

    public Drive(){

    }


    public Drive(String name, Double speedAverage, Double powerAverage, Double distance) {
        Name = name;
        SpeedAverage = speedAverage;
        PowerAverage = powerAverage;
        Distance = distance;
    }

    public String getName() {
        return Name;
    }


    public void setDistance(Double distance) {
        Distance = distance;
    }

    public Double getSpeedAverage() {
        return SpeedAverage;
    }

    public void setSpeedAverage(Double speedAverage) {
        SpeedAverage = speedAverage;
    }

    public Double getPowerAverage() {
        return PowerAverage;
    }

    public void setPowerAverage(Double powerAverage) {
        PowerAverage = powerAverage;
    }
    public void setName(String name) {
        Name = name;
    }

    public Double getDistance() {
        return Distance;
    }

}


