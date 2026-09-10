package org.busesincoming;

import org.busesincoming.parsers.StopTimeParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StopTimeParserTest {
    @Test
    void stopIDSearchForArrivals() throws Exception {
        StopTimeParser parser = new StopTimeParser();
        Path f = Path.of("src/test/resources/gtfs/test_stop_times.txt");
        List<Arrival> arrivals = parser.findArrivalsForStop(f, 10, LocalTime.of(7, 15), LocalTime.of(9, 40));
        assertEquals(2, arrivals.size());

        assertEquals("NORMAL_03_103_Go_07:20", arrivals.getFirst().getTripID());
        assertEquals(LocalTime.of(7, 20), arrivals.getFirst().getArrivalTime());

        assertEquals("NORMAL_03_105_Return_09:10", arrivals.get(1).getTripID());
        assertEquals(LocalTime.of(9, 5, 16), arrivals.get(1).getArrivalTime());
    }
}
