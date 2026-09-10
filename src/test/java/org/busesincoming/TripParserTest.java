package org.busesincoming;

import org.busesincoming.parsers.TripParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TripParserTest {
    @Test
    void idSearchForTrips() throws Exception {
        TripParser parser = new TripParser();
        Path f = Path.of("src/test/resources/gtfs/test_trips.txt");

        Map<String, Trip> trips = parser.makeMapOfTrips(f);
        assertEquals(9, trips.size());

        Trip trip = trips.get("NORMAL_03_103_Return_17:00");
        assertEquals("NORMAL_03_103_Return_17:00", trip.getId());
        assertEquals("103", trip.getRouteID());
    }

    @Test
    void nonexistentTrip() throws Exception {
        TripParser parser = new TripParser();
        Path f = Path.of("src/test/resources/gtfs/test_trips.txt");
        Map<String, Trip> trips = parser.makeMapOfTrips(f);
        assertNull(trips.get("NORMAL_03_104_Return_17:00"));
    }

}
