package tests.sortingTests;

import modal.Objects.Station;
import modal.sorting.MergeSort;
import modal.sorting.Sort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

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

}
