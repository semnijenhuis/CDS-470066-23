package tests.sortingTests;

import modal.Objects.Station;
import modal.sorting.SelectionSort;
import modal.sorting.Sort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {
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
}
