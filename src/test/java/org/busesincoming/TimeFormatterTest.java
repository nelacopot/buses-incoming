package org.busesincoming;


import org.junit.jupiter.api.Test;

import java.time.LocalTime;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeFormatterTest {
    @Test
    void absoluteTimeTester() {
        TimeFormatter timeFormatter = new TimeFormatter();

        Arrival arrival = new Arrival("NORMAL_03_101_Return_22:10", LocalTime.of(22, 10));
        assertEquals("22:10", timeFormatter.format(arrival, LocalTime.of(21, 0), "absolute"));
    }

    @Test
    void relativeTimeTester() {
        TimeFormatter timeFormatter = new TimeFormatter();

        Arrival arrival = new Arrival("NORMAL_03_101_Return_22:10", LocalTime.of(22, 10));
        assertEquals("70 min", timeFormatter.format(arrival, LocalTime.of(21, 0), "relative"));
        assertEquals("120 min", timeFormatter.format(arrival, LocalTime.of(20, 10), "relative"));
    }
}
