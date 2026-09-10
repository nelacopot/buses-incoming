package org.busesincoming;

import org.busesincoming.parsers.RouteParser;
import org.busesincoming.parsers.StopTimeParser;
import org.busesincoming.parsers.TripParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusesIncomingTest {
    @Test
    void findingIncomingBuses() throws Exception {
        StopTimeParser stp = new StopTimeParser();
        TripParser tp = new TripParser();
        RouteParser rp = new RouteParser();

        BusesIncoming busesIncoming = new BusesIncoming(stp, tp, rp);
        Path stf = Path.of("src/test/resources/gtfs/test_stop_times.txt");
        Path tf = Path.of("src/test/resources/gtfs/test_trips.txt");
        Path rf = Path.of("src/test/resources/gtfs/test_routes.txt");

        Map<Route, List<Arrival>> result = busesIncoming.findIncomingBuses(stf, tf, rf, 2, 2, LocalTime.of(21, 0));
        assertEquals(1, result.size());

        Route route = result.keySet().iterator().next();
        assertEquals("101", route.getId());
        assertEquals("101", route.getName());

        List<Arrival> arrivals = result.get(route);

        assertEquals(2, arrivals.size());
        assertEquals(LocalTime.of(22, 10), arrivals.getFirst().getArrivalTime()); //ali je sortiranje ok
        assertEquals(LocalTime.of(22, 20), arrivals.get(1).getArrivalTime());
        assertEquals("NORMAL_03_101_Return_22:10", arrivals.getFirst().getTripID());
    }

    @Test
    void findingBusesWithinTwoHours() throws Exception {
        StopTimeParser stp = new StopTimeParser();
        TripParser tp = new TripParser();
        RouteParser rp = new RouteParser();

        BusesIncoming busesIncoming = new BusesIncoming(stp, tp, rp);

        Path stf = Path.of("src/test/resources/gtfs/test_stop_times.txt");
        Path tf = Path.of("src/test/resources/gtfs/test_trips.txt");
        Path rf = Path.of("src/test/resources/gtfs/test_routes.txt");

        Map<Route, List<Arrival>> result = busesIncoming.findIncomingBuses(stf, tf, rf, 2, 10, LocalTime.of(21, 0));
        Route route = result.keySet().iterator().next();
        List<Arrival> arrivals = result.get(route);

        assertEquals(4, arrivals.size());
        assertEquals(LocalTime.of(22, 10), arrivals.get(0).getArrivalTime()); //ali je sortiranje ok
        assertEquals(LocalTime.of(22, 20), arrivals.get(1).getArrivalTime());
        assertEquals(LocalTime.of(22, 30), arrivals.get(2).getArrivalTime());
        assertEquals(LocalTime.of(23, 0), arrivals.get(3).getArrivalTime());
    }

    @Test
    void numBusLimitChecker() throws Exception {
        StopTimeParser stp = new StopTimeParser();
        TripParser tp = new TripParser();
        RouteParser rp = new RouteParser();

        BusesIncoming busesIncoming = new BusesIncoming(stp, tp, rp);

        Path stf = Path.of("src/test/resources/gtfs/test_stop_times.txt");
        Path tf = Path.of("src/test/resources/gtfs/test_trips.txt");
        Path rf = Path.of("src/test/resources/gtfs/test_routes.txt");
        //preverjanje tudi za edge case čez polnoč
        Map<Route, List<Arrival>> result = busesIncoming.findIncomingBuses(stf, tf, rf, 2, 10, LocalTime.of(22, 31));
        assertEquals(1, result.size());

        Route route = result.keySet().iterator().next();
        List<Arrival> arrivals = result.get(route);

        assertEquals(2, arrivals.size());
        assertEquals(LocalTime.of(23, 0), arrivals.getFirst().getArrivalTime());
        assertEquals(LocalTime.of(23, 1), arrivals.get(1).getArrivalTime());


    }
}
