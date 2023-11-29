package tests.seachingTests;

import modal.Objects.Station;
import modal.Searching.Linear;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LinearTest {

    @Test
    void searchStationIDLinear() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        int existingStationID = 2;
        int nonExistingStationID = 5;

        // Test case for an existing station ID
        Station foundStation = linearSearch.searchStationID(stationList, existingStationID);
        assertEquals(existingStationID, foundStation.getId());

        // Test case for a non-existing station ID
        assertNull(linearSearch.searchStationID(stationList, nonExistingStationID));
    }

    @Test
    void searchStationCodeLinear() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationCode = "ST2";
        String nonExistingStationCode = "ST5";

        // Test case for an existing station code
        Station foundStation = linearSearch.searchStationCode(stationList, existingStationCode);
        assertEquals(existingStationCode, foundStation.getCode());

        // Test case for a non-existing station code
        assertNull(linearSearch.searchStationCode(stationList, nonExistingStationCode));
    }

    @Test
    void searchStationStringLinear() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationName = "Long2";
        String nonExistingStationName = "Long5";

        // Test case for an existing station name
        Station foundStation = linearSearch.searchStationString(stationList, existingStationName);
        assertEquals(existingStationName, foundStation.getName_long());

        // Test case for a non-existing station name
        assertNull(linearSearch.searchStationString(stationList, nonExistingStationName));
    }

    @Test
    void searchStationIDLinearBW() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        int existingStationID = 2;
        int nonExistingStationID = 5;

        // Test case for an existing station ID
        Station foundStation = linearSearch.searchStationID(stationList, existingStationID);
        assertEquals(existingStationID, foundStation.getId());

        // Test case for a non-existing station ID
        assertNull(linearSearch.searchStationID(stationList, nonExistingStationID));
    }

    @Test
    void searchStationCodeLinearBW() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationCode = "ST2";
        String nonExistingStationCode = "ST5";

        // Test case for an existing station code
        Station foundStation = linearSearch.searchStationCode(stationList, existingStationCode);
        assertEquals(existingStationCode, foundStation.getCode());

        // Test case for a non-existing station code
        assertNull(linearSearch.searchStationCode(stationList, nonExistingStationCode));
    }

    @Test
    void searchStationStringLinearBW() {
        Linear linearSearch = new Linear();

        ArrayList<Station> stationList = new ArrayList<>();
        stationList.add(new Station(1, "ST1", 121, "Short1", "Medium1", "Long1", "Slug1", "Country1", "Type1", 1.0, 2.0));
        stationList.add(new Station(2, "ST2", 122, "Short2", "Medium2", "Long2", "Slug2", "Country2", "Type2", 2.0, 3.0));
        stationList.add(new Station(3, "ST3", 123, "Short3", "Medium3", "Long3", "Slug3", "Country3", "Type3", 3.0, 4.0));
        String existingStationName = "Long2";
        String nonExistingStationName = "Long5";

        // Test case for an existing station name
        Station foundStation = linearSearch.searchStationString(stationList, existingStationName);
        assertEquals(existingStationName, foundStation.getName_long());

        // Test case for a non-existing station name
        assertNull(linearSearch.searchStationString(stationList, nonExistingStationName));
    }


}
