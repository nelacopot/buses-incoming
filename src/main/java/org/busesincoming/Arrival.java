package org.busesincoming;
import java.time.LocalTime;

public class Arrival {
    private final String tripID;
    private final LocalTime arrivalTime;

    public Arrival(String tripID, LocalTime arrivalTime){
        this.tripID = tripID;
        this.arrivalTime = arrivalTime;
    }

    public String getTripID(){
        return tripID;
    }

    public LocalTime getArrivalTime(){
        return arrivalTime;
    }
}
