package org.busesincoming;

import org.busesincoming.parsers.RouteParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RouteParserTest {
    @Test
    void idSearchForRoute() throws Exception {
        RouteParser parser = new RouteParser();
        Path f = Path.of("src/test/resources/gtfs/test_routes.txt");

        Map<String, Route> routes = parser.makeMapOfRoutes(f);
        assertEquals(5, routes.size());

        Route route = routes.get("102");
        assertEquals("102", route.getId());
        assertEquals("102", route.getName());
    }

    @Test
    void nonexistentRoute() throws Exception {
        RouteParser parser = new RouteParser();
        Path f = Path.of("src/test/resources/gtfs/test_routes.txt");
        Map<String, Route> routes = parser.makeMapOfRoutes(f);
        assertNull(routes.get("111"));
    }
}
