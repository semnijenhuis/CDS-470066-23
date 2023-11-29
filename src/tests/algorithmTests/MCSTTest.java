package tests.algorithmTests;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Linear;
import modal.algorithm.MCST;
import modal.utils.ReadFile;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MCSTTest {

    @Test
    void mcstPrimFindStations() {
        MCST mcst = new MCST();

        // Create stations and tracks for the test
        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);

        // Choose start and end stations for the test
        Linear search = new Linear();
        Station beginStation = search.searchStationID(stations, 43);
        Station endStation = search.searchStationID(stations, 171);

        // Redirect standard output to capture printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the method with your input
        mcst.mcstPrimFindStations(stations, tracks, beginStation, endStation, 1);

        // Reset standard output
        System.setOut(System.out);

        // Extract the printed content and split by newline
        String[] lines = outputStream.toString().trim().split("\n");

        // Get the last line
        String lastLine = lines[lines.length - 1].trim();

        // Assert the last line
        assertEquals("Emmerich                  | Weight: 125  | Stations visited: 25", lastLine);

    }

    @Test
    void mcstPrimFindStationsBW() {
        MCST mcst = new MCST();

        // Create stations and tracks for the test
        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);

        // Choose start and end stations for the test
        Linear search = new Linear();
        Station beginStation = search.searchStationID(stations, 43);
        Station endStation = search.searchStationID(stations, 393);

        // Redirect standard output to capture printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the method with your input
        mcst.mcstPrimFindStations(stations, tracks, beginStation, endStation, 1);

        // Reset standard output
        System.setOut(System.out);

        // Extract the printed content and split by newline
        String[] lines = outputStream.toString().trim().split("\n");

        // Get the last line
        String lastLine = lines[lines.length - 1].trim();

        // Assert the last line because its outside the square range
        assertEquals("\"Amsterdam Centraal\"      | Weight: 0    | Stations visited: 0", lastLine);

    }


}
