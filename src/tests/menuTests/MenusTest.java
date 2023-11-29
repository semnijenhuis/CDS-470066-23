package tests.menuTests;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Linear;
import modal.menu.Menus;
import modal.menu.Printer;
import modal.tree.AVLTree;
import modal.utils.ReadFile;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MenusTest {

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

}
