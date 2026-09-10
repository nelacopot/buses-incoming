package org.busesincoming;

public class Stop {

    private final int stopId;
    private final String stopName;

    public Stop(int id, String name) {
        this.stopId = id;
        this.stopName = name;
    }

    public int getStopId(){
        return stopId;
    }

    public String getStopName(){
        return stopName;
    }


}
