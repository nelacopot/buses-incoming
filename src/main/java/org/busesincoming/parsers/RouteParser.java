package org.busesincoming.parsers;

import org.busesincoming.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class RouteParser {
    public Map<String, Route> makeMapOfRoutes(Path f) throws IOException {
        Map<String, Route> routes = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(f)) {
            br.readLine();
            String l;
            while ((l = br.readLine()) != null) {
                String[] parts = l.split(",", 4); //ne rabim delitve na več kot 4 dele ... potrebujem 1. in 3.

                String routeID = parts[0];
                String name = parts[2];

                Route route = new Route(routeID, name);
                routes.put(routeID, route);
            }
        }
        return routes;
    }
}
