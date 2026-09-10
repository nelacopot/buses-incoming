package org.busesincoming;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class OutputFormatter {
    private TimeFormatter timeFormatter;

    public OutputFormatter(TimeFormatter timeFormatter) {
        this.timeFormatter = timeFormatter;
    }

    public void print(Map<Route, List<Arrival>> output, LocalTime now, String format){
        for (Route route : output.keySet()) {
            System.out.println("Route " + route.getName());

            List<Arrival> arrivals = output.get(route);
            for (Arrival arrival : arrivals) {
                System.out.println(timeFormatter.format(arrival, now, format));
            }
        }
    }
}
