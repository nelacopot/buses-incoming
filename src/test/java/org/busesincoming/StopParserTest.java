package org.busesincoming;

import org.busesincoming.parsers.StopParser;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;


class StopParserTest {
    @Test
    void idSearchForStop() throws Exception {
        StopParser parser = new StopParser();
        Path f = Path.of("src/test/resources/gtfs/test_stops.txt");
        Stop stop = parser.searchForStop(f, 15);
        assertNotNull(stop);
        assertEquals("Glavna avtobusna postaja", stop.getStopName());
        assertEquals(15, stop.getStopId());
    }

    @Test
    void nonexistentStop() throws Exception {
        StopParser parser = new StopParser();
        Path f = Path.of("src/test/resources/gtfs/test_stops.txt");
        Stop stop = parser.searchForStop(f, 1);
        assertNull(stop);
    }
}
