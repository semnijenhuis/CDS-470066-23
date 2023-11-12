package tests;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Binary;
import modal.Searching.Linear;
import modal.algorithm.AStar;
import modal.algorithm.Dijkstra;
import modal.algorithm.MCST;
import modal.generic.MyGraph;
import modal.generic.MyLinkedList;
import modal.generic.MyMinHeap;
import modal.menu.Menus;
import modal.menu.Printer;
import modal.sorting.*;
import modal.tree.AVLTree;
import modal.utils.ReadFile;
import modal.utils.Rectangle;
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


    @Test
    void avlTreeLevelPrinter() {
        AVLTree avlTree = new AVLTree();

        // Create stations
        Station station1 = new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0);
        Station station2 = new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0);
        Station station3 = new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0);

        // Add stations to the AVL Tree
        avlTree.addTree(avlTree, station1);
        avlTree.addTree(avlTree, station2);
        avlTree.addTree(avlTree, station3);

        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the treeLevelPrinter method for a specific level
        avlTree.treeLevelPrinter(avlTree.root, 2);

        // Reset System.out
        System.setOut(System.out);

        // Define your expected output based on the structure of your AVL tree
        String expectedOutput = "1 3";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void avlPrint() {

        AVLTree avlTree = new AVLTree();

        // Create stations
        Station station1 = new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0);
        Station station2 = new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0);
        Station station3 = new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0);

        // Add stations to the AVL Tree
        avlTree.addTree(avlTree, station1);
        avlTree.addTree(avlTree, station2);
        avlTree.addTree(avlTree, station3);

        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the print method
        avlTree.print();

        // Reset System.out
        System.setOut(System.out);

        // Define your expected output based on the structure of your AVL tree
        String expectedOutput = "order of the tree is : \n" +
                "2 \n" +
                "1 3";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void avlDeleteNode() {
        AVLTree avlTree = new AVLTree();

        // Create a station with a frequency
        Station station1 = new Station(557, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0);
        Station station2 = new Station(677, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0);
        Station station3 = new Station(353, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0);

        // Add the station to the AVL Tree
        avlTree.addTree(avlTree, station1);
        avlTree.addTree(avlTree, station2);
        avlTree.addTree(avlTree, station3);

        // Delete the node and update the root of the tree
        avlTree.deleteStation(station2);

        // Check that the found station is null
        System.out.println("Test 1");
        assertNull(avlTree.searchID(2));
        System.out.println("Test 2");
        assertNull(avlTree.searchName("Long2"));
        System.out.println("Test 3");
        assertNull(avlTree.searchCode("ST2"));
    }

    @Test
    void avlDeleteNodeBW() {
        AVLTree avlTree = new AVLTree();
        // Attempt to delete a node from an empty AVL Tree
        assertThrows(NullPointerException.class, () -> avlTree.deleteStation(null));
    }



    @Test
    void avlAddAndSearch() {
        AVLTree avlTree = new AVLTree();
        ReadFile readFile = new ReadFile();
        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        readFile.makeAVLTree(stations,avlTree);
        Linear search = new Linear();



        // Create a station with a frequency
        Station station = search.searchStationID(stations, 43);

        // Add the station to the AVL Tree
        avlTree.addTree(avlTree, station);

        // Search for the station in the AVL Tree
        Station foundStation1 = avlTree.searchID(station.getId());
        Station foundStation2 = avlTree.searchCode(station.getCode());
        Station foundStation3 = avlTree.searchName(station.getName_long());



        // Check that the found station is not null
        assertNotNull(foundStation1);

        // Check that the frequency of the found station matches the expected frequency
        assertEquals(station.getId(), foundStation1.getId());
        assertEquals(station.getCode(), foundStation2.getCode());
        assertEquals(station.getName_long(), foundStation3.getName_long());
    }

    @Test
    void avlAddAndSearchBW(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        AVLTree avlTree = new AVLTree();

        // Attempt to search for a station in an empty AVL Tree
        avlTree.searchID(1);
        avlTree.searchName("Long1");
        avlTree.searchCode("ST1");

        System.setOut(System.out);  // Reset System.out to the original PrintStream

        // Extract the printed content and split by newline
        String[] lines = outputStream.toString().trim().split("\n");

        // Get the last line
        String lastLine = lines[lines.length - 1].trim();

        // Assert the last line
        assertEquals("Station with code ST1 not found.", lastLine);

    }

    @Test
    void avlRightRotate(){
        AVLTree avlTree = new AVLTree();

        // Create stations
        Station station1 = new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0);
        Station station2 = new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0);
        Station station3 = new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0);

        // Add stations to the AVL Tree
        avlTree.addTree(avlTree, station1);
        avlTree.addTree(avlTree, station2);
        avlTree.addTree(avlTree, station3);

        // Rotate the tree to the right
        avlTree.root = avlTree.rightRotate(avlTree.root);

        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the print method to display the tree structure
        avlTree.print();

        // Reset System.out
        System.setOut(System.out);

        // Define your expected output based on the structure of your AVL tree after the right rotation
        String expectedOutput = "order of the tree is : \n" +
                "1 \n" +
                "2 \n" +
                "3";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

    }


    @Test
    void searchStationIDBin() {
        Binary binarySearch = new Binary();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        int existingStationID = 2;
        int nonExistingStationID = 5;

        // Test case for an existing station ID
        Station foundStation = binarySearch.searchStationIDBin(stationList, existingStationID);
        assertEquals(existingStationID, foundStation.getId());

        // Test case for a non-existing station ID
        assertNull(binarySearch.searchStationIDBin(stationList, nonExistingStationID));

    }

    @Test
    void searchStationCodeBin() {

        Binary binarySearch = new Binary();
        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationCode = "ST2";
        String nonExistingStationCode = "ST5";

        // Test case for an existing station code
        Station foundStation = binarySearch.searchStationCodeBin(stationList, existingStationCode);
        assertEquals(existingStationCode, foundStation.getCode());

        // Test case for a non-existing station code
        assertNull(binarySearch.searchStationCodeBin(stationList, nonExistingStationCode));
    }

    @Test
    void searchStationNameBin() {

        Binary binarySearch = new Binary();
        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationName = "Long2";
        String nonExistingStationName = "Long5";

        // Test case for an existing station name
        Station foundStation = binarySearch.searchStationNameBin(stationList, existingStationName);
        assertEquals(existingStationName, foundStation.getName_long());

        // Test case for a non-existing station name
        assertNull(binarySearch.searchStationNameBin(stationList, nonExistingStationName));
    }

    @Test
    void searchStationIDBadWeather() {
        Binary badWeatherSearch = new Binary();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        int existingStationID = 2;
        int nonExistingStationID = 5;

        // Test case for an existing station ID
        Station foundStation = badWeatherSearch.searchStationIDBin(stationList, existingStationID);
        assertEquals(existingStationID, foundStation.getId());

        // Test case for a non-existing station ID
        assertNull(badWeatherSearch.searchStationIDBin(stationList, nonExistingStationID));
    }

    @Test
    void searchStationCodeBadWeather() {
        Binary badWeatherSearch = new Binary();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationCode = "ST2";
        String nonExistingStationCode = "ST5";

        // Test case for an existing station code
        Station foundStation = badWeatherSearch.searchStationCodeBin(stationList, existingStationCode);
        assertEquals(existingStationCode, foundStation.getCode());

        // Test case for a non-existing station code
        assertNull(badWeatherSearch.searchStationCodeBin(stationList, nonExistingStationCode));
    }

    @Test
    void searchStationNameBadWeather() {
        Binary badWeatherSearch = new Binary();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationName = "Long2";
        String nonExistingStationName = "Long5";

        // Test case for an existing station name
        Station foundStation = badWeatherSearch.searchStationNameBin(stationList, existingStationName);
        assertEquals(existingStationName, foundStation.getName_long());

        // Test case for a non-existing station name
        assertNull(badWeatherSearch.searchStationNameBin(stationList, nonExistingStationName));
    }

    @Test
    void searchStationIDLinear() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        int existingStationID = 2;
        int nonExistingStationID = 5;

        // Test case for an existing station ID
        Station foundStation = linearSearch.searchStationID(stationList, existingStationID);
        assertEquals(existingStationID, foundStation.getId());

        // Test case for a non-existing station ID
        assertNull(linearSearch.searchStationID(stationList, nonExistingStationID));
    }

    @Test
    void searchStationCodeLinear() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationCode = "ST2";
        String nonExistingStationCode = "ST5";

        // Test case for an existing station code
        Station foundStation = linearSearch.searchStationCode(stationList, existingStationCode);
        assertEquals(existingStationCode, foundStation.getCode());

        // Test case for a non-existing station code
        assertNull(linearSearch.searchStationCode(stationList, nonExistingStationCode));
    }

    @Test
    void searchStationStringLinear() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationName = "Long2";
        String nonExistingStationName = "Long5";

        // Test case for an existing station name
        Station foundStation = linearSearch.searchStationString(stationList, existingStationName);
        assertEquals(existingStationName, foundStation.getName_long());

        // Test case for a non-existing station name
        assertNull(linearSearch.searchStationString(stationList, nonExistingStationName));
    }


    @Test
    void searchStationIDLinearBW() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        int existingStationID = 2;
        int nonExistingStationID = 5;

        // Test case for an existing station ID
        Station foundStation = linearSearch.searchStationID(stationList, existingStationID);
        assertEquals(existingStationID, foundStation.getId());

        // Test case for a non-existing station ID
        assertNull(linearSearch.searchStationID(stationList, nonExistingStationID));
    }

    @Test
    void searchStationCodeLinearBW() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationCode = "ST2";
        String nonExistingStationCode = "ST5";

        // Test case for an existing station code
        Station foundStation = linearSearch.searchStationCode(stationList, existingStationCode);
        assertEquals(existingStationCode, foundStation.getCode());

        // Test case for a non-existing station code
        assertNull(linearSearch.searchStationCode(stationList, nonExistingStationCode));
    }

    @Test
    void searchStationStringLinearBW() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationName = "Long2";
        String nonExistingStationName = "Long5";

        // Test case for an existing station name
        Station foundStation = linearSearch.searchStationString(stationList, existingStationName);
        assertEquals(existingStationName, foundStation.getName_long());

        // Test case for a non-existing station name
        assertNull(linearSearch.searchStationString(stationList, nonExistingStationName));
    }

    @Test
    void testStationGet(){

        Station station = new Station(1, "AA", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);

        assertEquals(1, station.getId());
        assertEquals("AA", station.getCode());
        assertEquals(123, station.getUic());
        assertEquals("ShortName", station.getName_short());
        assertEquals("MediumName", station.getName_medium());
        assertEquals("LongName", station.getName_long());
        assertEquals("slug", station.getSlug());
        assertEquals("Country", station.getCountry());
        assertEquals("Type", station.getType());
        assertEquals(12.345, station.getGeo_lat());
        assertEquals(67.890, station.getGeo_lng());


    }

    @Test
    void testAddArrivalTrack() {
        Station station = new Station(1, "AA", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);
        Station station2 = new Station(1, "BB", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);
        Track arrivalTrack = new Track(station.getCode(),station2.getCode(), 10,10,0);
        station.addArrivalTrack(arrivalTrack);

        assertEquals(1, station.arrivalTracks.size());
        assertEquals(arrivalTrack, station.arrivalTracks.get(0));
    }

    @Test
    void testAddDepartureTrack() {
        Station station = new Station(1, "AA", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);
        Station station2 = new Station(1, "BB", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);
        Track departureTrack = new Track(station.getCode(),station2.getCode(), 10,10,0);
        station.addDepartureTrack(departureTrack);

        assertEquals(1, station.departureTracks.size());
        assertEquals(departureTrack, station.departureTracks.get(0));
    }

    @Test
    void testAddConnectedStation() {
        Station station = new Station(1, "AA", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);
        Station connectedStation = new Station(2, "BB", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012);
        station.addConnectedStation(connectedStation);

        assertEquals(1, station.connectedStations.size());
        assertEquals(connectedStation, station.connectedStations.get(0));
    }

    @Test
    void testGetDistanceToNextNode() {
        Station station = new Station(1, "AA", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);
        Station station2 = new Station(1, "BB", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);
        Track track = new Track(station.getCode(),station2.getCode(), 22,22,0);
        station.addDepartureTrack(track);

        int distance = station.getDistanceToNextNode(station2);

        assertEquals(22, distance);
    }

    @Test
    void testStationPrintDepartureTracks(){
        Station station = new Station(1, "AA", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create departure tracks for testing
        Track departureTrack1 = new Track(station.getCode(), "DepartureTrackCode1", 20, 30, 2);
        Track departureTrack2 = new Track(station.getCode(), "DepartureTrackCode2", 30, 40, 3);
        station.addDepartureTrack(departureTrack1);
        station.addDepartureTrack(departureTrack2);

        // Call the method that prints departure tracks
        station.printDepartureTracks();

        // Reset System.out to its original value
        System.setOut(System.out);

        // Verify the output
        String expectedOutput = "Track {From: AA, to: DepartureTrackCode1, distance in km: 20}\n" +
                "\n" +
                "Track {From: AA, to: DepartureTrackCode2, distance in km: 30}\n"+
        "\n" ;
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void testStationToString(){
        Station station = new Station(1, "ABC", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);

        // Add some departure tracks for testing
        Track departureTrack1 = new Track(station.getCode(), "DepartureTrackCode1", 20, 30, 2);
        Track departureTrack2 = new Track(station.getCode(), "DepartureTrackCode2", 30, 40, 3);
        station.addDepartureTrack(departureTrack1);
        station.addDepartureTrack(departureTrack2);

        // Call the toString method
        String result = station.toString();

        // Verify the output
        String expectedOutput = "Station (ID:1, Code:ABC, UIC:123, Name:LongName, Slug:slug, Country:Country, Type:Type, Latitude:12.345, Longitude:67.89)\n" +
                "Departure: \n" +
                "[Track {From: ABC, to: DepartureTrackCode1, distance in km: 20}\n" +
                ", Track {From: ABC, to: DepartureTrackCode2, distance in km: 30}\n" +
                "]\n";

        assertEquals(expectedOutput, result);
    }


    @Test
    void testLinkedListAddStationNode() {
        MyLinkedList linkedList = new MyLinkedList();

        // Create a sample station
        Station station = new Station(1, "ABC", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);

        // Add the station to the linked list
        linkedList.addStationNode(station);

        // Verify that the linked list is not empty
        assertNotNull(linkedList.head);
        // Verify that the added node contains the correct station
        assertEquals(station, linkedList.head.currentStation);
    }

    @Test
    void testLinkedAddStationNodeBW(){
        MyLinkedList linkedList = new MyLinkedList();

        // Try to add a null station to the linked list
        try {
            linkedList.addStationNode(null);
            // If we reach here, the test has failed
            fail("Expected an IllegalArgumentException, but no exception was thrown.");
        } catch (IllegalArgumentException e) {
            // Verify that the linked list is still empty
            assertNull(linkedList.head);
        }
    }

    @Test
    void testLinkedAddStationNodeAsPath() {
        MyLinkedList linkedList = new MyLinkedList();

        // Create two sample stations
        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);
        Station station2 = new Station(2, "DEF", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012);

        // Add the stations to the linked list as a path
        linkedList.addStationNodeAsPath(station1).addStationNodeAsPath(station2);

        // Verify that the linked list is not empty
        assertNotNull(linkedList.head);
        // Verify that the first node contains the correct station
        assertEquals(station2, linkedList.head.currentStation);
        // Verify that the second node contains the correct station
        assertEquals(station1, linkedList.head.nextStation.currentStation);
    }

    @Test
    void testLinkedAddStationNodeAsPathBW() {

        MyLinkedList linkedList = new MyLinkedList();

        // Try to add a null station to the linked list
        try {
            linkedList.addStationNodeAsPath(null);
            // If we reach here, the test has failed
            fail("Expected an IllegalArgumentException, but no exception was thrown.");
        } catch (IllegalArgumentException e) {
            // Verify that the linked list is still empty
            assertNull(linkedList.head);
        }



    }



    @Test
    void testLinkedPrintList() {
        MyLinkedList linkedList = new MyLinkedList();

        // Create a sample station
        Station station = new Station(1, "ABC", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);

        // Add the station to the linked list
        linkedList.addStationNode(station);

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the printList method
        linkedList.printList();

        // Reset System.out to its original value
        System.setOut(System.out);

        // Verify the output
        String expectedOutput = "Station (ID:1, Code:ABC, UIC:123, Name:LongName, Slug:slug, Country:Country, Type:Type, Latitude:12.345, Longitude:67.89)\n" +
                "Departure: \n" +
                "[]\n" +
                "\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void testLinkedFindStationLinear() {
        MyLinkedList linkedList = new MyLinkedList();

        // Create two sample stations
        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);
        Station station2 = new Station(2, "DEF", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012);

        // Add the stations to the linked list
        linkedList.addStationNode(station1).addStationNode(station2);

        // Call the findStationLinear method
        Station foundStation = linkedList.findStationLinear(linkedList, "LongName2");

        // Verify that the correct station is found
        assertEquals(station2, foundStation);
    }

    @Test
    void testLinkedFindStationLinearBW() {
        MyLinkedList linkedList = new MyLinkedList();

        // Create two sample stations
        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);
        Station station2 = new Station(2, "DEF", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012);

        // Add the stations to the linked list
        linkedList.addStationNode(station1).addStationNode(station2);

        // Call the findStationLinear method with a station name that doesn't exist
        Station foundStation = linkedList.findStationLinear(linkedList, "NonExistentStation");

        // Verify that the found station is null
        assertNull(foundStation);

    }

    @Test
    void testLinkedPrintPath() {
        MyLinkedList linkedList = new MyLinkedList();

        // Create sample stations
        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);
        Station station2 = new Station(2, "DEF", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012);
        Station station3 = new Station(3, "GHI", 789, "ShortName3", "MediumName3", "LongName3", "slug3", "Country3", "Type3", 56.789, 12.345);

        // Add the stations to the linked list as a path
        linkedList.addStationNodeAsPath(station1).addStationNodeAsPath(station2).addStationNodeAsPath(station3);

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the printPath method
        linkedList.printPath(station1, station3);

        // Reset System.out to its original value
        System.setOut(System.out);

        // Verify the output
        String expectedOutput = "--- LongName1 to LongName3 ---\n" +
                "LongName3 --(401km)-> LongName2\n" +
                "LongName2 --(401km)-> LongName1\n" +
                "LongName1\n" +
                " --- End of path ---\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void testLinkedEdgeConstructor() {
        // Create sample stations
        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);
        Station station2 = new Station(2, "DEF", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012);

        // Create an Edge
        MyLinkedList.Node node1 = new MyLinkedList.Node(station1);
        MyLinkedList.Node node2 = new MyLinkedList.Node(station2);
        int weight = 100;
        MyLinkedList.Edge edge = new MyLinkedList.Edge(node1, node2, weight);


        // Verify that the Edge attributes are correctly set
        assertNotNull(edge);
        assertEquals(node1, edge.start);
        assertEquals(node2, edge.end);
        assertEquals(weight, edge.weight);
    }

    @Test
    void testLinkedEdgeConstructorBW() {
        // Create a sample station
        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);

        // Create a Node (but don't initialize node2, simulating a null node)
        MyLinkedList.Node node1 = new MyLinkedList.Node(station1);
        MyLinkedList.Node node2 = null;

        // Try to create an Edge with a null node
        try {
            int weight = 100;
            MyLinkedList.Edge edge = new MyLinkedList.Edge(node1, node2, weight);
            // If we reach here, the test has failed
            fail("Expected an IllegalArgumentException, but no exception was thrown.");
        } catch (IllegalArgumentException e) {
            // Verify that the exception is thrown as expected
            assertEquals("Data cannot be null", e.getMessage());
        }

    }

    @Test
    void testLinkedNodeToString() {
        // Create a sample station
        Station station = new Station(1, "ABC", 123, "ShortName", "MediumName", "LongName", "slug", "Country", "Type", 12.345, 67.890);

        // Create a Node
        MyLinkedList.Node node = new MyLinkedList.Node(station);

        // Call the toString method
        String result = node.toString();

        // Verify the output
        String expectedOutput = "Station (ID:1, Code:ABC, UIC:123, Name:LongName, Slug:slug, Country:Country, Type:Type, Latitude:12.345, Longitude:67.89)\n" +
                "Departure: \n" +
                "[]\n";
        assertEquals(expectedOutput, result);
    }

    @Test
    void minheapInsert() {
        MyMinHeap minHeap = new MyMinHeap(5, Comparator.comparing(Station::getId, Comparator.nullsLast(Integer::compare)));

        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);
        Station station2 = new Station(2, "DEF", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012);
        Station station3 = new Station(3, "GHI", 789, "ShortName3", "MediumName3", "LongName3", "slug3", "Country3", "Type3", 56.789, 90.123);

        minHeap.insert(station3);
        minHeap.insert(station1);
        minHeap.insert(station2);


        // Verify that peek returns the minimum element
        assertEquals(station1, minHeap.peek());

        // Verify that the heap is in the expected order
        assertEquals("[Station (ID:1, Code:ABC, UIC:123, Name:LongName1, Slug:slug1, Country:Country1, Type:Type1, Latitude:12.345, Longitude:67.89)\n" +
                "Departure: \n" +
                "[]\n" +
                ", Station (ID:3, Code:GHI, UIC:789, Name:LongName3, Slug:slug3, Country:Country3, Type:Type3, Latitude:56.789, Longitude:90.123)\n" +
                "Departure: \n" +
                "[]\n" +
                ", Station (ID:2, Code:DEF, UIC:456, Name:LongName2, Slug:slug2, Country:Country2, Type:Type2, Latitude:34.567, Longitude:89.012)\n" +
                "Departure: \n" +
                "[]\n" +
                "]", minHeap.toString());
    }

    @Test
    void minheapInsertBW() {
        MyMinHeap minHeap = new MyMinHeap(5, Comparator.comparing(Station::getId, Comparator.nullsLast(Integer::compare)));

        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);
        Station station2 = new Station(2, "DEF", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012);

        // Insert a valid station
        minHeap.insert(station1);

        // Try to insert a null station
        try {
            minHeap.insert(null);
            fail("Expected a NullPointerException, but no exception was thrown.");
        } catch (NullPointerException e) {
            // Verify that the heap is still in a valid state
            assertNotNull(minHeap.peek());
            assertEquals(station1, minHeap.peek());
        }

        // Insert another valid station
        minHeap.insert(station2);

        // Verify that the heap is in the expected order
        assertEquals("[Station (ID:1, Code:ABC, UIC:123, Name:LongName1, Slug:slug1, Country:Country1, Type:Type1, Latitude:12.345, Longitude:67.89)\n" +
                "Departure: \n" +
                "[]\n" +
                ", null, Station (ID:2, Code:DEF, UIC:456, Name:LongName2, Slug:slug2, Country:Country2, Type:Type2, Latitude:34.567, Longitude:89.012)\n" +
                "Departure: \n" +
                "[]\n" +
                "]", minHeap.toString());
    }


    @Test
    void minHeappop() {
        MyMinHeap minHeap = new MyMinHeap(5, Comparator.comparing(Station::getId, Comparator.nullsLast(Integer::compare)));

        Station station1 = new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890);
        Station station2 = new Station(2, "DEF", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012);
        Station station3 = new Station(3, "GHI", 789, "ShortName3", "MediumName3", "LongName3", "slug3", "Country3", "Type3", 56.789, 90.123);

        // Insert stations
        minHeap.insert(station3);
        minHeap.insert(station1);
        minHeap.insert(station2);

        // Verify initial size
        assertEquals(3, minHeap.getSize());

        // Pop the minimum element
        Station poppedStation = minHeap.pop();

        // Verify that the popped station is the minimum element
        assertEquals(station1, poppedStation);
        // Verify that the size is decremented
        assertEquals(2, minHeap.getSize());
        // Verify that the heap is in the expected order
        assertEquals("[Station (ID:2, Code:DEF, UIC:456, Name:LongName2, Slug:slug2, Country:Country2, Type:Type2, Latitude:34.567, Longitude:89.012)\n" +
                "Departure: \n" +
                "[]\n" +
                ", Station (ID:3, Code:GHI, UIC:789, Name:LongName3, Slug:slug3, Country:Country3, Type:Type3, Latitude:56.789, Longitude:90.123)\n" +
                "Departure: \n" +
                "[]\n" +
                "]", minHeap.toString());
    }

    @Test
    void minHeappopBW() {
        MyMinHeap minHeap = new MyMinHeap(5, Comparator.comparing(Station::getId, Comparator.nullsLast(Integer::compare)));

        // Verify initial size is 0
        assertEquals(0, minHeap.getSize());

        // Try to pop from an empty heap
        try {
            minHeap.pop();
            fail("Expected an IllegalStateException, but no exception was thrown.");
        } catch (IllegalStateException e) {
            // Verify that the heap is still empty
            assertEquals(0, minHeap.getSize());
        }
    }



    @Test
    void minHeapgetSize() {
        MyMinHeap minHeap = new MyMinHeap(5, Comparator.comparing(Station::getId, Comparator.nullsLast(Integer::compare)));

        // Verify initial size is 0
        assertEquals(0, minHeap.getSize());

        // Insert stations
        minHeap.insert(new Station(1, "ABC", 123, "ShortName1", "MediumName1", "LongName1", "slug1", "Country1", "Type1", 12.345, 67.890));
        minHeap.insert(new Station(2, "DEF", 456, "ShortName2", "MediumName2", "LongName2", "slug2", "Country2", "Type2", 34.567, 89.012));

        // Verify size after insertions
        assertEquals(2, minHeap.getSize());

        // Pop a station
        minHeap.pop();

        // Verify size after popping
        assertEquals(1, minHeap.getSize());



    }


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




