package tests.genericTests;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Linear;
import modal.generic.MyGraph;
import modal.utils.ReadFile;
import modal.utils.Rectangle;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MyGraphTest {

    @Test
    void testGraphAddNodeName() {
        Map<String, Station> stationMap = new HashMap<>();
        MyGraph myGraph = new MyGraph(5, stationMap);

        myGraph.addNodeName("ABC", 0);
        myGraph.addNodeName("DEF", 1);

        assertEquals(0, myGraph.nameToStation.get("ABC"));
        assertEquals(1, myGraph.nameToStation.get("DEF"));
    }
    @Test
    void testGraphAddNodeNameBW() {
        Map<String, Station> stationMap = new HashMap<>();
        MyGraph myGraph = new MyGraph(5, stationMap);

        myGraph.addNodeName("ABC", 0);

        // Attempt to add a duplicate key
        try {
            myGraph.addNodeName("ABC", 1);
            fail("Expected IllegalArgumentException, but no exception was thrown.");
        } catch (IllegalArgumentException e) {
            // Verify that the map is unchanged
            assertEquals(0, myGraph.nameToStation.get("ABC"));
            // Verify that the size of the map is still 1
            assertEquals(1, myGraph.nameToStation.size());
        }
    }

    @Test
    void testGraphAddConnections() {
        Map<String, Station> stationMap = new HashMap<>();
        MyGraph myGraph = new MyGraph(5, stationMap);

        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);
        Station station2 = new Station(2, "DEF", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012);

        // Add stations to stationMap
        stationMap.put("ABC", station1);
        stationMap.put("DEF", station2);

        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(station1.getCode(), station2.getCode(), 50, 50, 0));

        myGraph.addConnections(tracks);

        assertEquals(0, myGraph.nameToStation.get("ABC"));
        assertEquals(1, myGraph.nameToStation.get("DEF"));

        assertEquals(1, myGraph.adjacencyList.get(0).size());
        assertEquals(0, myGraph.adjacencyList.get(1).size());
    }

    @Test
    void testGraphAddConnectionsStationBW() {
        Map<String, Station> stationMap = new HashMap<>();
        MyGraph myGraph = new MyGraph(5, stationMap);

        // Test with null tracks
        ArrayList<Track> nullTracks = null;
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> myGraph.addConnections(nullTracks));
        assertEquals("The array of tracks is null", nullPointerException.getMessage());

        // Test with empty tracks
        ArrayList<Track> emptyTracks = new ArrayList<>();
        NullPointerException emptyTracksException = assertThrows(NullPointerException.class, () -> myGraph.addConnections(emptyTracks));
        assertEquals("The array of tracks is null", emptyTracksException.getMessage());

        // Test with valid tracks
        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(station1.getCode(), "aaa", 50, 50, 0));

        // No exception should be thrown here
        myGraph.addConnections(tracks);

    }

    @Test
    void testGraphPrimAllStations() {

        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        HashMap<String, Station> stationMap = new HashMap<>();

        for (Station station : stations) {
            stationMap.put(station.getCode(), station);

        }

        Linear search = new Linear();
        Station startStation = search.searchStationID(stations, 43);
        Station endStation = search.searchStationID(stations, 171);
        MyGraph myGraph = new MyGraph(tracks.size(),stationMap);
        myGraph.addConnections(tracks);
        Rectangle boundingRectangle = new Rectangle(startStation, endStation);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the method with your input
        myGraph.primAllStations(startStation.getCode().toLowerCase(),boundingRectangle,1);

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
    void testGraphGraphViz() {
        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        HashMap<String, Station> stationMap = new HashMap<>();

        for (Station station : stations) {
            stationMap.put(station.getCode(), station);
        }

        Linear search = new Linear();
        Station startStation = search.searchStationID(stations, 43);
        Station endStation = search.searchStationID(stations, 171);
        MyGraph myGraph = new MyGraph(tracks.size(), stationMap);
        myGraph.addConnections(tracks);

        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Run the method with your input
        myGraph.graphViz();

        // Reset standard output
        System.setOut(originalOut);

        // Extract the console output
        String consoleOutput = outputStream.toString();

        // Print the entire console output for debugging
        System.out.println("Console Output:\n" + consoleOutput);

        // Check if the last line contains the expected substring
        assertTrue(consoleOutput.contains("leuven --> luik with a distance of 55km"),
                "Expected substring 'leuven --> luik with a distance of 55km' not found in the last line");
    }


}
