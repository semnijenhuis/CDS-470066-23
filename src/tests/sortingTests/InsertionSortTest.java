package tests.sortingTests;

import modal.Objects.Station;
import modal.sorting.InsertionSort;
import modal.sorting.Sort;
import modal.sorting.SortingComparator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

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
