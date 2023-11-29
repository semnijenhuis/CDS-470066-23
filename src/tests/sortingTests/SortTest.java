package tests.sortingTests;

import modal.Objects.Station;
import modal.sorting.Sort;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {
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


}
