package tests.genericTests;

import modal.Objects.Station;
import modal.generic.MyMinHeap;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyMinHeapTest {
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
}
