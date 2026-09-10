package org.busesincoming;

public class Trip {
    private final String id;
    private final String routeID;

    public Trip(String id, String routeID) {
        this.id = id;
        this.routeID = routeID;
    }

    public String getId() {
        return id;
    }

    public String getRouteID() {
        return routeID;
    }
}
