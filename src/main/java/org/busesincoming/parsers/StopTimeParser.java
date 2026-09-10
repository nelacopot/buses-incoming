package org.busesincoming.parsers;

import org.busesincoming.Arrival;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StopTimeParser {
    public List<Arrival> findArrivalsForStop(Path f, int stopID, LocalTime start, LocalTime end) throws IOException {
        List<Arrival> arrivals = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(f)) {
            br.readLine(); //glava CSV
            String l;

            while ((l = br.readLine()) != null) {
                String[] parts = l.split(",", 5);
                String tripID = parts[0];
                int foundStopID = Integer.parseInt(parts[3]);

                if (foundStopID == stopID){
                    LocalTime arrival = LocalTime.parse(parts[1]); //time parse samo za tiste, ki imajo iskan stopID

                    if (!arrival.isBefore(start) && !arrival.isAfter(end)){ //od start do end
                        arrivals.add(new Arrival(tripID, arrival));
                    }
                }


            }
        }

        return arrivals;
    }
}
