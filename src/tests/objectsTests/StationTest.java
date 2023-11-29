package tests.objectsTests;

import modal.Objects.Station;
import modal.Objects.Track;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {


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

}
