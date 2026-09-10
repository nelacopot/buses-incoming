package org.busesincoming;

import java.time.LocalTime;
import java.time.Duration;
import java.util.Objects;

public class TimeFormatter {
    public String format(Arrival arrival, LocalTime now, String format) {
        if (Objects.equals(format, "absolute"))
            return arrival.getArrivalTime().toString();
        else if (Objects.equals(format, "relative")){
            long minutes = Duration.between(now, arrival.getArrivalTime()).toMinutes();
            return minutes + " min";
        }
        return "";
    }
}
