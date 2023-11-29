package tests.treeTests;

import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Linear;
import modal.tree.AVLTree;
import modal.utils.ReadFile;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

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


}
