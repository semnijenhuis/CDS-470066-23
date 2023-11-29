package tests.seachingTests;

import modal.Objects.Station;
import modal.Searching.Binary;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTest {

    @Test
    void searchStationIDBin() {
        Binary binarySearch = new Binary();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        int existingStationID = 2;
        int nonExistingStationID = 5;

        // Test case for an existing station ID
        Station foundStation = binarySearch.searchStationIDBin(stationList, existingStationID);
        assertEquals(existingStationID, foundStation.getId());

        // Test case for a non-existing station ID
        assertNull(binarySearch.searchStationIDBin(stationList, nonExistingStationID));

    }

    @Test
    void searchStationCodeBin() {

        Binary binarySearch = new Binary();
        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationCode = "ST2";
        String nonExistingStationCode = "ST5";

        // Test case for an existing station code
        Station foundStation = binarySearch.searchStationCodeBin(stationList, existingStationCode);
        assertEquals(existingStationCode, foundStation.getCode());

        // Test case for a non-existing station code
        assertNull(binarySearch.searchStationCodeBin(stationList, nonExistingStationCode));
    }

    @Test
    void searchStationNameBin() {

        Binary binarySearch = new Binary();
        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationName = "Long2";
        String nonExistingStationName = "Long5";

        // Test case for an existing station name
        Station foundStation = binarySearch.searchStationNameBin(stationList, existingStationName);
        assertEquals(existingStationName, foundStation.getName_long());

        // Test case for a non-existing station name
        assertNull(binarySearch.searchStationNameBin(stationList, nonExistingStationName));
    }

    @Test
    void searchStationIDBinBadWeather() {
        Binary badWeatherSearch = new Binary();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        int existingStationID = 2;
        int nonExistingStationID = 5;

        // Test case for an existing station ID
        Station foundStation = badWeatherSearch.searchStationIDBin(stationList, existingStationID);
        assertEquals(existingStationID, foundStation.getId());

        // Test case for a non-existing station ID
        assertNull(badWeatherSearch.searchStationIDBin(stationList, nonExistingStationID));
    }

    @Test
    void searchStationCodeBinBadWeather() {
        Binary badWeatherSearch = new Binary();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationCode = "ST2";
        String nonExistingStationCode = "ST5";

        // Test case for an existing station code
        Station foundStation = badWeatherSearch.searchStationCodeBin(stationList, existingStationCode);
        assertEquals(existingStationCode, foundStation.getCode());

        // Test case for a non-existing station code
        assertNull(badWeatherSearch.searchStationCodeBin(stationList, nonExistingStationCode));
    }

    @Test
    void searchStationNameBinBadWeather() {
        Binary badWeatherSearch = new Binary();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationName = "Long2";
        String nonExistingStationName = "Long5";

        // Test case for an existing station name
        Station foundStation = badWeatherSearch.searchStationNameBin(stationList, existingStationName);
        assertEquals(existingStationName, foundStation.getName_long());

        // Test case for a non-existing station name
        assertNull(badWeatherSearch.searchStationNameBin(stationList, nonExistingStationName));
    }

}
