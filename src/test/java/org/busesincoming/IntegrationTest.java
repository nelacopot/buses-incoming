package org.busesincoming;

import org.busesincoming.parsers.RouteParser;
import org.busesincoming.parsers.StopTimeParser;
import org.busesincoming.parsers.TripParser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    @Test
    void numBusLimitChecker() throws Exception {
        PrintStream originalOut = System.out;

        StopTimeParser stp = new StopTimeParser();
        TripParser tp = new TripParser();
        RouteParser rp = new RouteParser();
        BusesIncoming busesIncoming = new BusesIncoming(stp, tp, rp);
        Path stf = Path.of("src/test/resources/gtfs/test_stop_times.txt");
        Path tf = Path.of("src/test/resources/gtfs/test_trips.txt");
        Path rf = Path.of("src/test/resources/gtfs/test_routes.txt");

        Map<Route, List<Arrival>> result = busesIncoming.findIncomingBuses(stf, tf, rf, 2, 2, LocalTime.of(21, 0));
        TimeFormatter tfm = new TimeFormatter();
        OutputFormatter of = new OutputFormatter(tfm);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        of.print(result, LocalTime.of(21, 0), "absolute");
        assertEquals("Route 101" + System.lineSeparator() + "22:10" + System.lineSeparator() + "22:20" + System.lineSeparator(), output.toString());

        System.setOut(originalOut);
    }
}
