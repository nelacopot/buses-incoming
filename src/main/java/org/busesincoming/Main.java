package org.busesincoming;

import org.busesincoming.parsers.RouteParser;
import org.busesincoming.parsers.StopParser;
import org.busesincoming.parsers.StopTimeParser;
import org.busesincoming.parsers.TripParser;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            System.out.println("Rules: busTrips <station_id> <num_buses_per_line> <relative|absolute>");
            return;
        }

        String command = args[0]; //"busTrips"

        int stopID;
        int numBuses;
        try {
            stopID = Integer.parseInt(args[1]);
            numBuses = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("station_id and num_buses_per_line should be INTEGERS.");
            return;
        }

        String format = args[3];
        if (!format.equals("relative") && !format.equals("absolute")) {
            System.out.println("Format can be RELATIVE or ABSOLUTE.");
            return;
        }

        if (!command.equals("busTrips")) {
            System.out.println("Unknown command: " + command);
            return;
        }

        StopTimeParser stp = new StopTimeParser();
        TripParser tp = new TripParser();
        RouteParser rp = new RouteParser();
        StopParser sp = new StopParser();
        Path sf = Path.of("src/main/resources/gtfs/stops.txt");
        Path stf = Path.of("src/main/resources/gtfs/stop_times.txt");
        Path tf = Path.of("src/main/resources/gtfs/trips.txt");
        Path rf = Path.of("src/main/resources/gtfs/routes.txt");
        TimeFormatter timeFormatter = new TimeFormatter();
        OutputFormatter outputFormatter = new OutputFormatter(timeFormatter);

        Stop stop;
        try {
            stop = sp.searchForStop(sf, stopID);
        } catch (Exception e) {
            System.out.println("Could not read the file: stops.txt.");
            return;
        }
        if (stop == null) {
            System.out.println("Stop not found.");
            return;
        }

        BusesIncoming busesIncoming = new BusesIncoming(stp, tp, rp);
        LocalTime now = LocalTime.now();
        Map<Route, List<Arrival>> res = busesIncoming.findIncomingBuses(stf, tf, rf, stopID, numBuses, now);

        System.out.println("Stop: " + stop.getStopName());
        outputFormatter.print(res, now, format);

    }
}