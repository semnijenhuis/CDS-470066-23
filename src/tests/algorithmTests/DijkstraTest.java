package tests.algorithmTests;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Linear;
import modal.algorithm.Dijkstra;
import modal.utils.ReadFile;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void dijkstraShortestPath() {

        Dijkstra dijkstra = new Dijkstra();

        // Read station and track data from files
        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);

        // Create linear search
        Linear search = new Linear();

        // Stations that are not connected in the data
        Station unconnectedStation1 = search.searchStationID(stations, 43); // Assuming 999 is not a valid station ID
        Station unconnectedStation2 = search.searchStationID(stations, 171); // Assuming 888 is not a valid station ID

        // Redirect standard output to capture printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run Dijkstra's algorithm for two unconnected stations
        dijkstra.shortestPath(stations, unconnectedStation1, unconnectedStation2);

        // Reset standard output
        System.setOut(System.out);

        // Extract the printed content and split by newline
        String[] lines = outputStream.toString().trim().split("\n");

        // Get the last line
        String lastLine = lines[lines.length - 1].trim();

        System.out.println(lastLine);
        // Assert the last line
        assertEquals("Total distance: 125km", lastLine);

    }

    @Test
    void dijkstraShortestPathBW() {

        Dijkstra dijkstra = new Dijkstra();

        // Read station and track data from files
        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);

        // Create linear search
        Linear search = new Linear();

        // Stations that are not connected in the data
        Station unconnectedStation1 = search.searchStationID(stations, 5); // Assuming 999 is not a valid station ID
        Station unconnectedStation2 = search.searchStationID(stations, 8); // Assuming 888 is not a valid station ID

        // Redirect standard output to capture printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run Dijkstra's algorithm for two unconnected stations
        dijkstra.shortestPath(stations, unconnectedStation1, unconnectedStation2);

        // Reset standard output
        System.setOut(System.out);

        // Extract the printed content and split by newline
        String[] lines = outputStream.toString().trim().split("\n");

        // Get the last line
        String lastLine = lines[lines.length - 1].trim();

        System.out.println(lastLine);
        // Assert the last line
        assertEquals("No path found!", lastLine);

    }

}
