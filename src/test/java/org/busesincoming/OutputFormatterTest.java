package org.busesincoming;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputFormatterTest {
    @Test
    void outputTest() {
        PrintStream originalOut = System.out;

        TimeFormatter tf = new TimeFormatter();
        OutputFormatter of = new OutputFormatter(tf);
        Route route = new Route("102", "102");
        Arrival arrival = new Arrival("trip_From_Center", LocalTime.of(22, 10));
        Map<Route, List<Arrival>> example = new HashMap<>();
        example.put(route, List.of(arrival));

        ByteArrayOutputStream output = new ByteArrayOutputStream(); //za ta del (kako preveriti izpis) sem si pomagala z umetno inteligenco
        System.setOut(new PrintStream(output));

        of.print(example, LocalTime.of(22, 0), "absolute");
        assertEquals("Route 102" + System.lineSeparator() + "22:10" + System.lineSeparator(), output.toString());
        output.reset();
        of.print(example, LocalTime.of(22, 0), "relative");
        assertEquals("Route 102" + System.lineSeparator() + "10 min" + System.lineSeparator(), output.toString());

        System.setOut(originalOut);

    }
}
