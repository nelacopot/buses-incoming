package org.busesincoming;

public class Route {
    private final String id;
    private final String name;

    public Route(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
