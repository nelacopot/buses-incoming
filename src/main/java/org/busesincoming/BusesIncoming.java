package org.busesincoming;

import org.busesincoming.parsers.RouteParser;
import org.busesincoming.parsers.StopTimeParser;
import org.busesincoming.parsers.TripParser;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusesIncoming {
    private final StopTimeParser stp;
    private final TripParser tp;
    private final RouteParser rp;

    public BusesIncoming(StopTimeParser stp, TripParser tp, RouteParser rp) {
        this.stp = stp;
        this.tp = tp;
        this.rp = rp;
    }

    public Map<Route, List<Arrival>> findIncomingBuses(Path stopTimesFile, Path tripsFile, Path routesFile, int stopID, int numBuses, LocalTime now) throws IOException {
        Map<String, Trip> trips = tp.makeMapOfTrips(tripsFile);
        Map<String, Route> routes = rp.makeMapOfRoutes(routesFile);
        LocalTime end = now.plusHours(2);

        List<Arrival> arrivals = stp.findArrivalsForStop(stopTimesFile, stopID, now, end);
        Map<Route, List<Arrival>> res = new HashMap<>();

        for(Arrival arrival : arrivals){
            Trip trip = trips.get(arrival.getTripID());
            if(trip == null){
                continue;
            }

            Route route = routes.get(trip.getRouteID());
            if(route == null){
                continue;
            }

            List<Arrival> list = res.computeIfAbsent(route, k -> new ArrayList<>());
            list.add(arrival);
        }

        for (List<Arrival> list : res.values()) {
            list.sort((a, b) -> a.getArrivalTime().compareTo(b.getArrivalTime()));
            if (list.size() > numBuses) {
                list.subList(numBuses, list.size()).clear();
            }
        }

        return res;
    }
}



