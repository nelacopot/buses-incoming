package org.busesincoming.parsers;

import org.busesincoming.Stop;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StopParser {
    public Stop searchForStop(Path file, int id) throws IOException {

        try (BufferedReader br = Files.newBufferedReader(file)) {
            br.readLine(); //glava CSV
            String l;

            while ((l = br.readLine()) != null) {
                String[] parts = l.split(",", 4);
                int foundId = Integer.parseInt(parts[0]);
                String foundName = parts[2];

                if (foundId == id) {
                    return new Stop(foundId, foundName);
                }
            }
        }
        return null;
    }
}
