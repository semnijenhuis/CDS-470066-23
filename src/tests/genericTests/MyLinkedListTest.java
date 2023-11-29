package tests.genericTests;

import modal.Objects.Station;
import modal.generic.MyLinkedList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
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
}
