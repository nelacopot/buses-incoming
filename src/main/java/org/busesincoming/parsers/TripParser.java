package org.busesincoming.parsers;

import org.busesincoming.Trip;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class TripParser {

    public Map<String, Trip> makeMapOfTrips(Path f) throws IOException {
        Map<String, Trip> trips = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(f)) {
            br.readLine();
            String l;
            while ((l = br.readLine()) != null) {
                String[] parts = l.split(",", 4);
                String routeID = parts[0];
                String tripID = parts[2];

                Trip trip = new Trip(tripID, routeID);
                trips.put(tripID, trip);
            }
            return trips;
        }
    }
}
