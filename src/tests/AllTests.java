package tests;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Linear;
import modal.algorithm.AStar;
import modal.algorithm.Dijkstra;
import modal.algorithm.MCST;
import modal.generic.MyLinkedList;
import modal.menu.Menus;
import modal.menu.Printer;
import modal.sorting.*;
import modal.tree.AVLTree;
import modal.utils.ReadFile;
import modal.utils.RegularExpression;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AllTests {

    @Test
    void filterStation() throws IOException {
        RegularExpression regex = new RegularExpression();

        String validID = "123";
        String validCode = "ABC";
        String validUIC = "456789";
        String validLatitude = "12.345";
        String validLongitude = "-98.765";

        assertTrue(regex.filterStation(validID, validCode, validUIC, validLatitude, validLongitude));

    }

    @Test
    void filterStationBW() throws IOException {
        RegularExpression regex = new RegularExpression();

        String invalidID = "A1";
        String validCode = "ABC";
        String invalidUIC = "45A789";
        String validLatitude = "12.345";
        String validLongitude = "-98.765";

        assertFalse(regex.filterStation(invalidID, validCode, invalidUIC, validLatitude, validLongitude));
    }

    @Test
    void filterTrack() throws IOException {
        RegularExpression regex = new RegularExpression();

        String validCodeFrom = "ABC";
        String validCodeTo = "EED";
        String validDistance = "123";

        assertTrue(regex.filterTrack(validCodeFrom, validCodeTo, validDistance));
    }

    @Test
    void filterTrackBW() throws IOException {
        RegularExpression regex = new RegularExpression();

        String invalidCodeFrom = "A1";
        String validCodeTo = "EED";
        String invalidDistance = "123A";

        assertFalse(regex.filterTrack(invalidCodeFrom, validCodeTo, invalidDistance));
    }

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
        assertEquals("Total distance: 151km", lastLine);


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


    @Test
    void mainMenu() {

        Printer printer = new Printer();

        // Simulate user input "42\n"
        String userInput = "2\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.mainMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(2, result);

    }

    @Test
    void mainMenuBW() {

        Printer printer = new Printer();
        String userInput = "a\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Assert that the code throws a NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> {
            printer.inputIntScanner(0, 3);
        });

        // Reset the standard input to the original value
        System.setIn(System.in);


    }

    @Test
    void routeMenu() {
        Printer printer = new Printer();

        String userInput = "3\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.routeMenu("A", "B");

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(3, result);

    }

    @Test
    void dijkstraMenu() {
        Printer printer = new Printer();


        String userInput = "2\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.dijkstraMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(2, result);
    }

    @Test
    void stationMenu() {
        Printer printer = new Printer();

        String userInput = "1\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.stationMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(1, result);
    }

    @Test
    void dataMenu() {
        Printer printer = new Printer();

        String userInput = "3\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.dataMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(3, result);
    }

    @Test
    void sortingMenu() {
        Printer printer = new Printer();

        String userInput = "4\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.sortingMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(4, result);
    }

    @Test
    void foundStationMenu() {
        Printer printer = new Printer();


        String userInput = "2\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        Station station = new Station(1, "a", 1, "D", "E", "F", "G", "H", "I", 5,6);
        // Call the inputIntScanner method
        int result = printer.foundStationMenu(station);

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(2, result);
    }


    @Test
    void stationOptionsSorting() {
        Printer printer = new Printer();

        String userInput = "8\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.stationOptionsSorting();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(8, result);
    }

    @Test
    void findStationID() {
        Printer printer = new Printer();

        String userInput = "899\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.findStationID();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(899, result);
    }

    @Test
    void findStationName() {
        Printer printer = new Printer();

        String userInput = "Amsterdam\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        String result = printer.findStationName();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals("Amsterdam", result);

    }

    @Test
    void findStationCode() {
        Printer printer = new Printer();

        String userInput = "AMS\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        String result = printer.findStationCode();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals("AMS", result);
    }

    @Test
    void findStationOn() {
        Printer printer = new Printer();

        String userInput = "1\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.findStationOn();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(1, result);
    }

    @Test
    void findStationAVL() {
        Printer printer = new Printer();


        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.findStationAVL();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(0, result);
    }

    @Test
    void searchOptions() {
        Printer printer = new Printer();

        String userInput = "2\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.searchOptions();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(2, result);
    }

    @Test
    void inputIntScanner() {
        Printer printer = new Printer();

        String userInput = "50\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.inputIntScanner(0, 557);

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(50, result);
    }

    @Test
    void inputStringScanner() {
        Printer printer = new Printer();

        String userInput = "Random\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        String result = printer.inputStringScanner();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals("Random", result);
    }

    @Test
    void whichSort() {
        Printer printer = new Printer();

        // Simulate user input "42\n"
        String userInput = "1\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Call the inputIntScanner method
        int result = printer.whichSort();

        // Reset the standard input to the original value
        System.setIn(System.in);

        System.out.println(result);
        // Assert the result
        assertEquals(1, result);
    }

    @Test
    void run() throws Exception {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);

        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Run the menus


        Menus menu = new Menus();
        menu.run(stations, tracks, avlTree);

        // Reset the standard input to the original value
        System.setIn(System.in);


    }

    @Test
    void menuStationMenu() throws Exception {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);

        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Run the menus



        Menus menu = new Menus();
        menu.stationMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);
    }

    @Test
    void menurouteMenu() {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);

        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Run the menus

        Linear search = new Linear();
        Station beginStation = search.searchStationID(stations, 43);
        Station endStation = search.searchStationID(stations, 171);


        Menus menu = new Menus();
        menu.routeMenu(beginStation, endStation);

        // Reset the standard input to the original value
        System.setIn(System.in);
    }

    @Test
    void menufoundStationMenu() {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);

        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Run the menus

        Linear search = new Linear();
        Station beginStation = search.searchStationID(stations, 43);



        Menus menu = new Menus();
        menu.foundStationMenu(beginStation);

        // Reset the standard input to the original value
        System.setIn(System.in);
    }

    @Test
    void menudataMenu() {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);

        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Run the menus



        Menus menu = new Menus();
        menu.dataMenu();

        // Reset the standard input to the original value
        System.setIn(System.in);

    }

    @Test
    void menusearchStation() throws Exception {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);

        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Run the menus



        Menus menu = new Menus();
        menu.searchStation();

        // Reset the standard input to the original value
        System.setIn(System.in);
    }

    @Test
    void menufindLinearStation() {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);

        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Run the menus



        Menus menu = new Menus();
        menu.findLinearStation();

        // Reset the standard input to the original value
        System.setIn(System.in);
    }

    @Test
    void menufindBinaryStation() {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);

        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());

        // Redirect the standard input to use the inputStream
        System.setIn(inputStream);

        // Run the menus



        Menus menu = new Menus();
        menu.findBinaryStation();

        // Reset the standard input to the original value
        System.setIn(System.in);
    }

    @Test
    void getStationComparator() {
        SortingComparator sortingComparator = new SortingComparator();
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));

        // Test each comparator
        for (int i = 1; i <= 11; i++) {
            Comparator<Station> comparator = SortingComparator.getStationComparator(i);

            // Sort the stations using the comparator
            stations.sort(comparator);

            // Assert the correct order
            assertEquals(1, stations.get(0).getId());
            assertEquals(2, stations.get(1).getId());
        }
    }

    @Test
    void getStationComparatorBW() {
        Comparator<Station> comparator = SortingComparator.getStationComparator(12);

        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));

        // Sort the stations using the comparator, and expect an exception
        assertThrows(ClassCastException.class, () -> stations.sort(comparator));
    }


    @Test
    void stationSorting() {

        Sort sort = new Sort();

        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));
        // Add more stations for additional testing

        // Test each sorting type and option
        for (int sortingType = 1; sortingType <= 4; sortingType++) {
            for (int sortingOption = 1; sortingOption <= 11; sortingOption++) {
                // Copy the original list for comparison
                ArrayList<Station> originalStations = new ArrayList<>(stations);

                // Sort the list using the Sort class
                ArrayList<Station> sortedStations = sort.stationSorting(stations, sortingOption, sortingType);

                // Assert that the list is sorted
                assertTrue(sort.isSorted(sortedStations, sortingOption));

                // Assert that the original list is unchanged
                assertEquals(originalStations, stations);
            }
        }

    }


    @Test
    void stationSortingBW() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Sort sort = new Sort();

        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));

        // Test with an invalid sorting type (5)
        sort.stationSorting(stations, 1, 5);

        // Reset System.out to the original PrintStream
        System.setOut(System.out);

        // Get the captured output
        String output = outputStream.toString();

        // Assert that the output contains the expected message
        assertTrue(output.contains("Invalid sorting type. The list remains unsorted."));
    }

    @Test
    void sortingTimer() {

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create an instance of Sort
        Sort sort = new Sort();

        // Perform the sorting operation (you might need an actual list to sort)
        long startTime = System.currentTimeMillis();
        // Simulate a sorting operation



        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));

        int sortingOption = 1;
        int sortingType = 1;
        ArrayList<Station> sortedStations = sort.stationSorting(stations, sortingOption, sortingType);

        // ...

        // Call the sortingTimer method
        sort.sortingTimer(startTime);

        // Reset System.out to the original PrintStream
        System.setOut(System.out);

        // Get the captured output
        String output = outputStream.toString();

        // Assert that the output contains "Time taken"
        assertTrue(output.contains("Time taken"));

    }

    @Test
    void stationSelectionSort() {
        Sort sort = new Sort();
        SelectionSort selectionSort = new SelectionSort();

        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));

        // Sort the list using the SelectionSort class
        ArrayList<Station> sortedStations = selectionSort.stationSelectionSort(stations, 1);

        // Assert that the list is sorted
        assertTrue(sort.isSorted(sortedStations, 1));
    }

    @Test
    void stationSelectionSortBW(){
        SelectionSort selectionSort = new SelectionSort();

        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));

        // Attempt to sort the list with an invalid sorting option (12)
        assertThrows(NullPointerException.class, () -> selectionSort.stationSelectionSort(stations, 12));

    }

    @Test
    void stationQuickSort() {
        Sort sort = new Sort();
        QuickSort quickSort = new QuickSort();

        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));

        // Sort the list using the QuickSort class
        ArrayList<Station> sortedStations = quickSort.stationQuickSort(stations, 0, stations.size() - 1, 1);

        // Assert that the list is sorted
        assertTrue(sort.isSorted(sortedStations, 1));

    }

    @Test
    void stationQuickSortBW() {
        QuickSort quickSort = new QuickSort();

        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));

        // Attempt to sort the list with an invalid sorting option (12)
        assertThrows(NullPointerException.class, () -> quickSort.stationQuickSort(stations, 0, stations.size() - 1, 12));
    }

    @Test
    void stationMergeSort() {
        Sort sort = new Sort();
        MergeSort mergeSort = new MergeSort();

        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));

        // Sort the list using the MergeSort class
        ArrayList<Station> sortedStations = mergeSort.stationMergeSort(stations, stations.size(), 1);

        // Assert that the list is sorted
        assertTrue(sort.isSorted(sortedStations, 1));
    }

    @Test
    void stationMergeSortBW() {
        MergeSort mergeSort = new MergeSort();

        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));

        // Attempt to sort the list with an invalid sorting option (12)
        assertThrows(NullPointerException.class, () -> mergeSort.stationMergeSort(stations, stations.size(), 12));
    }

    @Test
    void mergeSortStationsByDistance() {
        Sort sort = new Sort();
        MergeSort mergeSort = new MergeSort();

        // Create a list of stations with weights for testing
        ArrayList<Map.Entry<String, Integer>> stationsWithWeights = new ArrayList<>();
        stationsWithWeights.add(Map.entry("ST1", 10));
        stationsWithWeights.add(Map.entry("ST2", 5));
        stationsWithWeights.add(Map.entry("ST3", 8));

        // Sort the list using the mergeSortStationsByDistance method
        ArrayList<Map.Entry<String, Integer>> sortedStations = new ArrayList<>(mergeSort.mergeSortStationsByDistance(stationsWithWeights));

        // Assert that the list is sorted
        assertTrue(sort.isSortedByDistance(sortedStations));
    }

    @Test
    void stationInsertionSort() {
        Sort sort = new Sort();
        InsertionSort insertionSort = new InsertionSort();
        SortingComparator sortingComparator = new SortingComparator();

        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));

        // Sort the list using the InsertionSort class
        ArrayList<Station> sortedStations = insertionSort.stationInsertionSort(stations, 1);

        // Assert that the list is sorted
        assertTrue(sort.isSorted(sortedStations, 1));
    }

    @Test
    void stationInsertionSortBW() {

        InsertionSort insertionSort = new InsertionSort();

        // Create a list of stations for testing
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station(2, "ST2", 456, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 3.0, 4.0));
        stations.add(new Station(1, "ST1", 123, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));

        // Attempt to sort the list with an invalid sorting option (12)
        assertThrows(NullPointerException.class, () -> insertionSort.stationInsertionSort(stations, 12));


    }

    @Test
    void sortStationDistance() {
        // Create a mutable list of stations with weights for testing
        List<Map.Entry<String, Integer>> stationsWithWeights = new ArrayList<>();
        stationsWithWeights.add(Map.entry("Station1", 3));
        stationsWithWeights.add(Map.entry("Station2", 1));
        stationsWithWeights.add(Map.entry("Station3", 5));
        stationsWithWeights.add(Map.entry("Station4", 2));

        // Create an instance of InsertionSort
        InsertionSort insertionSort = new InsertionSort();

        // Sort the list using the sortStationDistance method
        List<Map.Entry<String, Integer>> sortedStations = insertionSort.sortStationDistance(stationsWithWeights);

        // Check that the list is sorted in ascending order by distance
        assertEquals(1, sortedStations.get(0).getValue());
        assertEquals("Station2", sortedStations.get(0).getKey());

        assertEquals(2, sortedStations.get(1).getValue());
        assertEquals("Station4", sortedStations.get(1).getKey());

        assertEquals(3, sortedStations.get(2).getValue());
        assertEquals("Station1", sortedStations.get(2).getKey());

        assertEquals(5, sortedStations.get(3).getValue());
        assertEquals("Station3", sortedStations.get(3).getKey());
    }

}


class ReadFileTest {

    @Test
    void readStationFile() {
    }

    @Test
    void readTracksFile() {
    }

    @Test
    void addTracksToStations() {
    }
}




