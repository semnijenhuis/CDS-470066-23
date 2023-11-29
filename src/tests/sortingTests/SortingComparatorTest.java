package tests.sortingTests;

import modal.Objects.Station;
import modal.sorting.SortingComparator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SortingComparatorTest {
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
}
