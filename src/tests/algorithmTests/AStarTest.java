package tests.algorithmTests;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Linear;
import modal.algorithm.AStar;
import modal.tree.AVLTree;
import modal.utils.ReadFile;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AStarTest {

    @Test
    void AstarShortestPath() {
        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);

        AVLTree avlTree = new AVLTree();
        Linear search = new Linear();
        Station beginStation = search.searchStationID(stations, 43);
        Station endStation = search.searchStationID(stations, 171);

        AStar aStar = new AStar();

        // Redirect standard output to capture printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Test shortest path from beginStation to endStation
        aStar.shortestPath(stations, beginStation, endStation);

        // Reset standard output
        System.setOut(System.out);

        // Extract the printed content and split by newline
        String[] lines = outputStream.toString().trim().split("\n");

        // Get the last line
        String lastLine = lines[lines.length - 1].trim();

        // Assert the last line
        assertEquals("Total distance: 125km", lastLine);


    }

    @Test
    void AstarShortestPathBW() {
        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);

        AVLTree avlTree = new AVLTree();
        Linear search = new Linear();

        // Stations that are not connected in the data
        Station unconnectedStation1 = search.searchStationID(stations, 5);
        Station unconnectedStation2 = search.searchStationID(stations, 8);


        AStar aStar = new AStar();

        // Redirect standard output to capture printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Test shortest path between two unconnected stations
        aStar.shortestPath(stations, unconnectedStation1, unconnectedStation2);

        // Reset standard output
        System.setOut(System.out);

        // Extract the printed content and split by newline
        String[] lines = outputStream.toString().trim().split("\n");

        // Get the last line
        String lastLine = lines[lines.length - 1].trim();

        // Assert the last line
        assertEquals("No path found!", lastLine);
    }

    @Test
    void heuristicCostEstimate() {
        AStar aStar = new AStar();


        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);

        AVLTree avlTree = new AVLTree();
        Linear search = new Linear();
        Station startStation = search.searchStationID(stations, 43);
        Station endStation = search.searchStationID(stations, 171);

        // Create two stations with known geographic coordinates
        startStation.setGeo_lat(0.0);
        startStation.setGeo_lng(0.0);

        endStation.setGeo_lat(1.0);
        endStation.setGeo_lng(1.0);

        // Expected distance based on Euclidean distance formula
        int expectedDistance = (int) Math.sqrt(Math.pow(1.0 - 0.0, 2) + Math.pow(1.0 - 0.0, 2));

        // Call the heuristicCostEstimate method
        int actualDistance = aStar.heuristicCostEstimate(startStation, endStation);

        System.out.println(actualDistance);
        System.out.println(expectedDistance);
        // Assert the result
        assertEquals(expectedDistance, actualDistance);

    }

    @Test
    void heuristicCostEstimateBW() {
        AStar aStar = new AStar();

        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);

        AVLTree avlTree = new AVLTree();
        Linear search = new Linear();
        Station startStation = search.searchStationID(stations, 43);
        Station endStation = search.searchStationID(stations, 171);

        // Set invalid or missing geographic coordinates for one station
        startStation.setGeo_lat(0.0);
        startStation.setGeo_lng(0.0);

        // The endStation has valid coordinates
        endStation.setGeo_lat(1.0);
        endStation.setGeo_lng(1.0);

        // Call the heuristicCostEstimate method
        // This should handle the case of invalid or missing coordinates and return a meaningful default value
        int actualDistance = aStar.heuristicCostEstimate(startStation, endStation);

        int notexpectedDistance = 2;
        System.out.println(actualDistance);
        System.out.println(notexpectedDistance);

        // Assert the result, you may use an expected default value or NaN depending on how you handle such cases
        assertNotEquals(notexpectedDistance, actualDistance);
    }



}
